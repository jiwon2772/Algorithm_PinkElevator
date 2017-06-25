import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Elevator {
   //GUI부분
   private Toolkit tk; //for image
   private Image img[] = new Image[2]; //for image
   private int x;
   private int y = 608;
   //나머지 부분
   private int id;
   private int destY = 607;
   private int nowFloor = 1; // elevator's starting floor.
   private int leftWeight = 1020; // this elevator' limitation about weight is 1020 kilogram.
   private int upDown; // this variable represents that this elevator's state ( 1 : up or -1 : down 0 : stop)
   private int stopCount = 0;
   private ArrayList<Person> inPersons = new ArrayList<Person>();
   private boolean isStop = true;
   private boolean isRealStop = true;
   private boolean isUpdate = false;
   private boolean isWait = false;
   //각 층에서 이 엘레베이터를 기다리는 사람들의 어레이
   private ArrayList<Person> floor1 = new ArrayList<Person>();
   private ArrayList<Person> floor2 = new ArrayList<Person>();
   private ArrayList<Person> floor3 = new ArrayList<Person>();
   private ArrayList<Person> floor4 = new ArrayList<Person>();
   private ArrayList<Person> floor5 = new ArrayList<Person>();
   private ArrayList<Person> floor6 = new ArrayList<Person>();
   private ArrayList<Person> floor7 = new ArrayList<Person>();
   private ArrayList<Person> floor8 = new ArrayList<Person>();
   private ArrayList<Person> floor9 = new ArrayList<Person>();
   private ArrayList<Person> floor10 = new ArrayList<Person>();
   private ArrayList<Person> floor11 = new ArrayList<Person>();
   private ArrayList<Person> floor12 = new ArrayList<Person>();

   public Elevator(int tx, int id) {
      this.id = id;
      x = tx;
      tk = Toolkit.getDefaultToolkit();
      this.upDown = 0;
      img[0] = tk.createImage("e1.jpg");
      img[1] = tk.createImage("e2.jpg");
   }
   public int maxFloorY(Elevator e)
   {// find maxfloor's y-coordinate
      int max=0,maxDest=0;
      ArrayList<Person> list =  inPersons;   
      
      for(int k = 0; k < list.size(); k++) {// check  persons in elevator((Elevator e) 's destination
         int temp = list.get(k).getDestFloor() - e.getNowFloor();
         if(temp > max) {
            max = temp;
            maxDest = list.get(k).getDestFloor();
         }
      }
      for(int a = 1; a <12; a++) // check  persons that wait for elevator(Elevator e) 's destination
      {
         ArrayList<Person> list2 =  waitingListAt(a);      
         for(int k = 0; k < list2.size(); k++) {
            int temp = list2.get(k).getDestFloor() - e.getNowFloor();
            if(temp > max) {
               max = temp;
               maxDest = list2.get(k).getDestFloor();
            }
         }
      }
      return 607 - ((maxDest-1) *55); 
   }
   public int minFloorY(Elevator e)
   {// find minfloor's y-coordinate
      int min=0,minDest=0;
      ArrayList<Person> list =  inPersons;      
      for(int k = 0; k < list.size(); k++) { //{// check  persons in elevator((Elevator e) 's destination
         int temp = list.get(k).getDestFloor() - e.getNowFloor();
         if(temp > min) {
            min = temp;
            minDest = list.get(k).getDestFloor();
         }
      }
      for(int a = 1; a <12; a++) 
      {//// check  persons that wait for elevator(Elevator e) 's destination
         ArrayList<Person> list2 =  waitingListAt(a);      
         for(int k = 0; k < list2.size(); k++) {
            int temp = list2.get(k).getDestFloor() - e.getNowFloor();
            if(temp > min) {
               min = temp;
               minDest = list2.get(k).getDestFloor();
            }
         }
      }

      return 607 - ((minDest-1) *55); 
   }
   public static void ride(Elevator e,int floor,ArrayList<Person> drawPer) { // This function make person ride this elevator.
      if(floor == 0)
         return;
      int temp = 0;
      ArrayList<Person> waitPer = e.waitingListAt(floor);
      for(int i = 0; i < waitPer.size(); i++) { // counting about all person arrived.
         if(waitPer.get(i).getUpDown() == e.getUpDown() && ( waitPer.get(i).isArrived() == true)) {
            temp++;
         }
      }
      while(temp != 0) {
         for(int i = 0; i < waitPer.size(); i++) {
            if(waitPer.get(i).getUpDown() == e.getUpDown()  && ( waitPer.get(i).isArrived() == true)) {
               for(int j = 0; j < drawPer.size(); j++) {
                  if(drawPer.get(j).getId() == waitPer.get(i).getId()){
                     if(waitPer.get(i).getRide() == true || (waitPer.get(i).getRide() == false && (e.getLeftWeight() >= (68*waitPer.get(i).getParty() ) ) ) ) {
                        e.getInPersons().add(drawPer.get(j));
                        if(waitPer.get(i).getRide() == false) {
                           waitPer.get(i).setRide(true);
                              e.setLeftWeight(e.getLeftWeight() - 68 * waitPer.get(i).getParty());
                        }
                        drawPer.remove(j);     // remove this person in the array that has all person who is waiting this elevator.
                        waitPer.remove(i);
                        temp--;
                        break;
                     }
                     else {
                        temp--;
                        break;
                     }
                  }
               }
            }
         }
      }
   }
   public static boolean iswillRide(Elevator e,int floor,ArrayList<Person> drawPer) { // This function help program check whether there are some person who wait elevator or not.
      int temp = 0;
      if(floor == 0)
         return false;
      ArrayList<Person> waitPer = e.waitingListAt(floor);
      for(int i = 0; i < waitPer.size(); i++) {
         if(waitPer.get(i).getUpDown() == e.getUpDown()) {
            temp++;
         }
      }
      if(temp > 0)
         return true;
      else 
         return false;
   }
   public static boolean isRide(Elevator e,int floor,ArrayList<Person> drawPer) { // This function help program check whether there are some person who wait elevator and is arrived in front of suggested elevator or not.
      int temp = 0;
      if(floor == 0)
         return false;
      ArrayList<Person> waitPer = e.waitingListAt(floor);
      for(int i = 0; i < waitPer.size(); i++) {
         if(waitPer.get(i).getUpDown() == e.getUpDown() && ( waitPer.get(i).isArrived() == true)) {
            temp++;
         }
      }
      if(temp > 0)
         return true;
      else 
         return false;
   }
   public static int getRide(Elevator e,int floor,ArrayList<Person> drawPer) { // return the number of people who is in front of this elevator and is waiting this elevator in now floor.
      int temp = 0;
      if(floor == 0)
         return 0;
      ArrayList<Person> waitPer = e.waitingListAt(floor);
      for(int i = 0; i < waitPer.size(); i++) {
         if(waitPer.get(i).getUpDown() == e.getUpDown() && ( waitPer.get(i).isArrived() == true)) {
            temp++;
         }
      }
      if(temp > 0)
         return temp;
      else 
         return 0;
   }
   public static void getoff(Elevator e,int floor,ArrayList<Person> drawPer) { // This function make person get off in elevator.
      int temp = 0;
      ArrayList<Person> inPer = e.getInPersons();
      for(int i = 0; i < inPer.size(); i++) {
         if(inPer.get(i).getDestFloor() == e.getNowFloor()) {
            temp++;
         }
      }
      while(temp != 0) {
         for(int i = 0; i < inPer.size(); i++) {
            if(inPer.get(i).getDestFloor() == e.getNowFloor()) {
               inPer.get(i).setDestX(-50);
               inPer.get(i).setX(inPer.get(i).getX() - 50);
               inPer.get(i).setY(607 - (55 * (e.getNowFloor() - 1)));
               drawPer.add(inPer.get(i));
               e.setLeftWeight(e.getLeftWeight()+(68*inPer.get(i).getParty()));
               inPer.remove(i);
               temp--;
            }
         }
      }
   }

   public static void findNext(Elevator e) { // Find next destination that this elevator will go to 
      int min = Integer.MAX_VALUE;
      int minDest = 0;
      int minUpdown = 0;
      //////////새로운코드
      if(e.isRealStop() == true) {
         for(int i = 1; i <= 12; i++) {
            ArrayList<Person> waitPer = e.waitingListAt(i);
            for(int j = 0; j < waitPer.size(); j++) {
               e.setUpDown(waitPer.get(j).getUpDown());
               goTo(e, waitPer.get(j).getNowFloor());
            }
         }
      }
      else {
         ArrayList<Person> list = e.getInPersons();
         for(int i = 0; i < list.size(); i++) { // check request that people who is in elevator have.
            int temp = list.get(i).getDestFloor() - e.getNowFloor();
            if(temp < 0)
               temp = temp * -1;
            if(temp < min && temp != 0) {
               min = temp;
               minDest = list.get(i).getDestFloor();
               minUpdown = list.get(i).getUpDown();
            }
         }
         // check request that people who is waiting this elevator have.
         if(e.getUpDown() > 0) {
            for(int i = e.getNowFloor(); i <= 12; i++) {
               ArrayList<Person> waitPer = e.waitingListAt(i);
               for(int j = 0; j < waitPer.size(); j++) {
                  if(waitPer.get(j).getUpDown() == e.getUpDown()) {
                     int temp = waitPer.get(j).getNowFloor() - e.getNowFloor();
                     if(temp < min && temp != 0) {
                        min = temp;
                        minDest = waitPer.get(j).getNowFloor();
                        minUpdown = waitPer.get(j).getUpDown();
                     }
                  }
               }
            }
         }
         else if(e.getUpDown() < 0) {
            for(int i = 1; i <= e.getNowFloor(); i++) {
               ArrayList<Person> waitPer = e.waitingListAt(i);
               for(int j = 0; j < waitPer.size(); j++) {
                  if(waitPer.get(j).getUpDown() == e.getUpDown()) {
                     int temp = e.getNowFloor() - waitPer.get(j).getNowFloor();
                     if(temp < min && temp != 0) {
                        min = temp;
                        minDest = waitPer.get(j).getNowFloor();
                        minUpdown = waitPer.get(j).getUpDown();
                     }
                  }
               }
            }
         }
         // If there is not request 현재 방향으로는 요청이 없을 경우
         if(min == Integer.MAX_VALUE) {
            e.setUpDown(e.getUpDown() * -1);//반대방향으로 가는지 체크해준다.
            int maxTem = Integer.MIN_VALUE;
            int minTem = Integer.MAX_VALUE;
            for(int i = 1; i <= 12; i++) {
               ArrayList<Person> waitPer = e.waitingListAt(i);
               for(int j = 0; j < waitPer.size(); j++) {
                  int temp = waitPer.get(j).getNowFloor();
                  if(e.getUpDown()  > 0) {
                     if(temp < minTem) 
                        minTem = temp;
                  }
                  else if(e.getUpDown() < 0) {
                     if(temp > maxTem)
                        maxTem = temp;
                  }
               }
            }
            if(e.getUpDown()  > 0) {
               if(minTem != Integer.MAX_VALUE)
                  min = minTem;
            }
            else if(e.getUpDown() < 0) { 
               if(maxTem != Integer.MIN_VALUE)
                  min = maxTem;
            }
            if(min == Integer.MAX_VALUE) { // If you pass this if line, It means that your elevator hasn't request and will stop until getting new request.
               e.setUpDown(0);
               e.setRealStop(true);
            }
            else {
               goTo(e, min);  // go to this floor
            }
         }
         else {
            e.setUpDown(minUpdown);
            goTo(e, minDest); // go to this floor
         }
      }
   }
   public static void goTo(Elevator e, int floor) { // This function make this elevator go to the floor. 
      if(e.isWait() == true) // If there is not waiting people, You should stop going to another floor.
         return;
      e.setRealStop(false);
      if(floor == e.getNowFloor()) { // If your destination floor is now floor, enter this part.
         e.setY(e.getY() + 1);
         e.decreaseFloor();
      }
      e.setDestY(607 - ((floor-1) *55));
      e.setWait(false);
   }
   public int waitingListOfUpdown(int floor, int updown) { // counting about person who has same direction that this elevator also has.
      int temp = 0;
      ArrayList<Person> tem = waitingListAt(floor);
      for(int i = 0; i < tem.size(); i++) {
         if(tem.get(i).getUpDown() == updown) {
            temp++;
         }
      }
      return temp;
   }
   public ArrayList<Person> waitingListAt(int floor) { // return ArrayList that has some person who is waiting in floor typed.
      if(floor == 0)
         return floor1;
      switch (floor){
      case 1 : 
         return floor1;
      case 2:
         return floor2;
      case 3 : 
         return floor3;
      case 4:
         return floor4;
      case 5 : 
         return floor5;
      case 6:
         return floor6;
      case 7 : 
         return floor7;
      case 8:
         return floor8;
      case 9 : 
         return floor9;
      case 10:
         return floor10;
      case 11 : 
         return floor11;
      case 12:
         return floor12;

      }
      return null;
   }

   public Toolkit getTk() {
      return tk;
   }

   public void setTk(Toolkit tk) {
      this.tk = tk;
   }

   public Image getImg(int i) {
      return img[i];
   }

   public void setImg(Image[] img) {
      this.img = img;
   }

   public int getX() {
      return x;
   }

   public void setX(int x) {
      this.x = x;
   }

   public int getY() {
      return y;
   }

   public void setY(int y) {
      this.y = y;
   }

   public int getDestY() {
      return destY;
   }

   public void setDestY(int destY) {
      this.destY = destY;
   }

   public int getNowFloor() {
      return nowFloor;
   }

   public void setNowFloor(int nowFloor) {
      this.nowFloor = nowFloor;
   }

   public int getLeftWeight() {
      return leftWeight;
   }

   public void setLeftWeight(int leftWeight) {
      this.leftWeight = leftWeight;
   }

   public int getUpDown() {
      return upDown;
   }

   public void setUpDown(int upDown) {
      this.upDown = upDown;
   }

   public ArrayList<Person> getInPersons() {
      return inPersons;
   }

   public void setInPersons(ArrayList<Person> inPersons) {
      this.inPersons = inPersons;
   }

   public boolean isStop() {
      return isStop;
   }

   public void setStop(boolean isStop) {
      this.isStop = isStop;
   }
   public int getId() {
      return id;
   }
   public void setId(int id) {
      this.id = id;
   }
   public void increaseFloor() {
      this.nowFloor++;
      for(int i = 0; i < this.getInPersons().size(); i++) {
         inPersons.get(i).setNowFloor(inPersons.get(i).getNowFloor() + 1);
      }
   }
   public void decreaseFloor() {
      this.nowFloor--;
      for(int i = 0; i < this.getInPersons().size(); i++) {
         inPersons.get(i).setNowFloor(inPersons.get(i).getNowFloor() - 1);
      }
   }
   public boolean isUpdate() {
      return isUpdate;
   }
   public void setUpdate(boolean isUpdate) {
      this.isUpdate = isUpdate;
   }
   public boolean isWait() {
      return isWait;
   }
   public void setWait(boolean isWait) {
      this.isWait = isWait;
   }
   public boolean isRealStop() {
      return isRealStop;
   }
   public void setRealStop(boolean isRealStop) {
      this.isRealStop = isRealStop;
   }
   public int getStopCount() {
      return stopCount;
   }
   public void setStopCount(int stopCount) {
      this.stopCount = stopCount;
   }
}