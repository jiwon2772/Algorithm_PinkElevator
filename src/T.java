import java.util.ArrayList;
public class T extends Thread {
	static int speed = 30; // It means basic speed.
	ArrayList<Person> per;
	Elevator[] elev;
	MyPanel mp;
	public T(ArrayList<Person> per, Elevator[] elev, MyPanel mp){
		this.per = per;
		this.elev = elev;
		this.mp = mp;
	}

	//class -> main  thread->run
	public void run(){
		while(true){
			try {
				Thread.sleep(speed); // control speed 

				for (int i = 0; i < per.size(); i++) { // In this loop, all person move a little who is allowed to move. 
					if((per.get(i).getDestX() == per.get(i).getX()) && (per.get(i).isIncluded() == false) && per.get(i).getX() != 390) {
						per.get(i).setArrived(true);
						per.get(i).setIncluded(true);
					}
					if(per.get(i).getDestX() < per.get(i).getX())
						per.get(i).setX(per.get(i).getX() - 1);
					if(per.get(i).getX() ==  -50) // If some person is in final destination, remove person in person array.
						per.remove(i);
				}
				for(int i=0; i<elev.length; i++) {  // In this loop, all movement about elevator are controlled.
					if(elev[i].isStop()) {
						elev[i].setStopCount(elev[i].getStopCount() + 1);
						if(elev[i].getStopCount() == 60) { // For showing situation that elevator is opening, We use stopCount.
							elev[i].setStop(false);
							Elevator.findNext(elev[i]);
							elev[i].setStopCount(0);
						}
						continue;
					}
					if(elev[i].getY() % 55 == 2 ) { // If elevator's now position is the point that means that elevator is crossing this floor, We update elevator's now floor.
						if(elev[i].isWait() == true) {
							if(elev[i].waitingListOfUpdown(elev[i].getNowFloor(), elev[i].getUpDown()) == Elevator.getRide(elev[i], elev[i].getNowFloor(), per)) {
								elev[i].setWait(false);
								Elevator.ride(elev[i],elev[i].getNowFloor(), per);
								Elevator.findNext(elev[i]);
								continue;
							}
						}
						if(elev[i].getUpDown() > 0) { // It means that now this elevator is going up.
							if(elev[i].isUpdate() == false) { // for blocking duplication about adding floor
								elev[i].setNowFloor(12 - (elev[i].getY() - 2) / 55 );
								if(Elevator.iswillRide(elev[i], elev[i].getNowFloor(), per)) // check whether there are people who want to ride elevator or not.
									elev[i].setWait(true);
								elev[i].setUpdate(true);			
								if(elev[i].getDestY() == elev[i].getY()) {
									elev[i].setStop(true);
									Elevator.getoff(elev[i],elev[i].getNowFloor(), per); 
									if(elev[i].waitingListOfUpdown(elev[i].getNowFloor(), elev[i].getUpDown()) == Elevator.getRide(elev[i], elev[i].getNowFloor(), per)) {
										Elevator.ride(elev[i],elev[i].getNowFloor(), per);
									}
									else {
										elev[i].setWait(true);
									}


								}
							}
						}
						else if(elev[i].getUpDown() < 0) { // It means that now this elevator is going down.
							if(elev[i].isUpdate() == false) {
								elev[i].setNowFloor(12 - (elev[i].getY() - 2) / 55 );
								if(Elevator.iswillRide(elev[i], elev[i].getNowFloor(), per)) 
									elev[i].setWait(true);
								elev[i].setUpdate(true);
								if(elev[i].getDestY() == elev[i].getY()) {
									elev[i].setStop(true);
									if(elev[i].waitingListOfUpdown(elev[i].getNowFloor(), elev[i].getUpDown()) == Elevator.getRide(elev[i], elev[i].getNowFloor(), per)) {
										Elevator.ride(elev[i],elev[i].getNowFloor(), per);
									}
									else {
										elev[i].setWait(true);
									}
									Elevator.getoff(elev[i],elev[i].getNowFloor(), per);
								}
							}
						}
					}
					if(elev[i].isRealStop() == true) { // If elevator have not any request, Elevator check whether new request appear or not.
						Elevator.findNext(elev[i]);
					}
					if(elev[i].getDestY() < elev[i].getY()){ // this part only control about elevator's move.
						elev[i].setUpdate(false);
						elev[i].setY(elev[i].getY() - 1);
					}
					else if(elev[i].getDestY() > elev[i].getY()){ // this part only control about elevator's move.
						elev[i].setUpdate(false);
						elev[i].setY(elev[i].getY() + 1);
					}
				}
				mp.repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 

		}
	}
}