import java.awt.Image;
import java.awt.Toolkit;

public class Person {
	//GUI ����
	private Toolkit tk;
	private Image img[] = new Image[2];
	private int x = 400; // person is appeared in this x-point.
	private int y;
	private int destX = 390; //destination
	//�� ��
	private int Id; 
	private int weight;
	private int destFloor; // this means destination floor.
	private int nowFloor; // this means know floor.
	private int party = 0; // this means the number of parties(companies) behind him.
	private int upDown; // this variable represents that this person's state ( 1 : up or -1 : down )
	private int selectedEle; // this variable point the elevator selected by this person.
	private boolean isIncluded = false; 
	private boolean isArrived = false; // If this variable is true, This person is in front of elevator suggested.
	private boolean isUpdate = false;
	private boolean ride = false;

	public Person(int Id, int destF, int nowF, int ty) {
		//GUI ����
		y = ty;
		tk = Toolkit.getDefaultToolkit();

		int count = (int)(Math.random()*1000)%11 + 1; 		//random number for party's number (1-3)
		if(count%2 == 0) { // check whether selected count is odd number or not.
			count++;
		}

		img[0] = tk.createImage("p" + count + ".png");
		img[1] = tk.createImage("p" + (count + 1)+ ".png");
		
		// the other variables.
		this.party=this.getCountPerson(count); // It means the number of party.
		this.Id = Id;
		this.weight = 68; // we define that person's average weight is 68 kilogram.
		this.destFloor = destF;
		this.nowFloor = nowF;
	}
	public int getCountPerson(int tem)
	{//for getting party's number
		if(tem==9)
			return 3;
		else if(tem==11)
			return 2;

		return 1;
	}
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getDestFloor() {
		return destFloor;
	}

	public void setDestFloor(int destFloor) {
		this.destFloor = destFloor;
	}

	public int getNowFloor() {
		return nowFloor;
	}

	public void setNowFloor(int nowFloor) {
		this.nowFloor = nowFloor;
	}

	public int getParty() {
		return party;
	}
	public int getUpDown() {
		return upDown;
	}

	public void setUpDown(int upDown) {
		this.upDown = upDown;
	}

	public int getSelectedEle() {
		return selectedEle;
	}

	public void setSelectedEle(int selectedEle) {
		this.selectedEle = selectedEle;
	}

	public boolean isIncluded() {
		return isIncluded;
	}

	public void setIncluded(boolean isIncluded) {
		this.isIncluded = isIncluded;
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

	public int getDestX() {
		return destX;
	}

	public void setDestX(int destX) {
		this.destX = destX;
	}

	public boolean isArrived() {
		return isArrived;
	}

	public void setArrived(boolean isArrived) {
		this.isArrived = isArrived;
	}

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
	public boolean getRide() {
		return ride;
	}
	public void setRide(boolean ride) {
		this.ride = ride;
	}

}