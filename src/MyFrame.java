import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MyFrame implements ActionListener{
	private static int now = -1;
	private Font fo = new Font ("AR JULIAN",Font.BOLD,28); //for font 
	private Font fo2 = new Font ("Consolas",Font.PLAIN,15);

	public static int personCount = 0;
	public int wait_location[] = {120, 200, 280, 360};

	JFrame mainFrame = new JFrame("Pink Elevator");

	JTextArea floorArea = new JTextArea(5, 20);
	JTextArea first = new JTextArea(5, 20);
	JTextArea second = new JTextArea(5, 20);

	JPanel jp;
	JPanel floorPanel = new JPanel();

	JPanel doorPanel = new JPanel();
	JPanel selectButton = new JPanel();
	JPanel showBest1 = new JPanel();
	JPanel showBest2 = new JPanel();
	JPanel showFloor = new JPanel();
	JPanel speedTable = new JPanel();

	JPanel pink = new JPanel();

	private JButton[] recoBtn = new JButton[2];   
	private JButton[] floorBtn = new JButton[12];
	private JButton[] selectBtn = new JButton[12];
	private JButton[] speedBtn = new JButton[5];
	private int d= 0; //elevator number

	public ArrayList<Person> drawPer = new ArrayList<Person>();
	public Elevator[] elev = new Elevator[4];

	public Person tmpPerson;

	public MyFrame(){
		mainFrame.setBounds(132, 5, 1043 + 10, 660 + 38); //mainFrame's setting
		mainFrame.addWindowListener(new WindowDestroyer());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(null);

		//show elevator
		jp = new MyPanel(drawPer, elev);
		pink.setBounds(600, 330, 400, 230);
		pink.setBackground(new Color(252, 195, 173));

		Container contentPane = mainFrame.getContentPane();
		contentPane.add(jp);
		contentPane.setBackground(new Color(232, 217, 254));
		contentPane.add(pink);

		//floor
		mainFrame.add(new FloorPanel());


		//door 
		doorPanel.setLayout(new GridLayout(12,1));
		ImageIcon doorIcon = new ImageIcon ("door.png"); //for door button
		for(int i=11; i>=10; i--) { // for gradation floor's color. Use 6 for loop. 
			floorBtn[i] = new JButton();
			floorBtn[i].setActionCommand(Integer.toString(i+1) +"");
			floorBtn[i].setIcon(doorIcon);
			floorBtn[i].setBorder(null);
			floorBtn[i].setBackground(new Color(249, 246, 254));
			doorPanel.add(floorBtn[i]);
			floorBtn[i].addActionListener(this);
		}
		for(int i=9; i>=8; i--) {
			floorBtn[i] = new JButton();
			floorBtn[i].setActionCommand(Integer.toString(i+1) +"");
			floorBtn[i].setIcon(doorIcon);
			floorBtn[i].setBorder(null);
			floorBtn[i].setBackground(new Color(240, 231, 254));
			doorPanel.add(floorBtn[i]);
			floorBtn[i].addActionListener(this);
		}
		for(int i=7; i>=6; i--) {
			floorBtn[i] = new JButton();
			floorBtn[i].setActionCommand(Integer.toString(i+1) +"");
			floorBtn[i].setIcon(doorIcon);
			floorBtn[i].setBorder(null);
			floorBtn[i].setBackground(new Color(232, 217, 254));
			doorPanel.add(floorBtn[i]);
			floorBtn[i].addActionListener(this);
		}
		for(int i=5; i>=4; i--) {
			floorBtn[i] = new JButton();
			floorBtn[i].setActionCommand(Integer.toString(i+1) +"");
			floorBtn[i].setIcon(doorIcon);
			floorBtn[i].setBorder(null);
			floorBtn[i].setBackground(new Color(221, 200, 254));
			doorPanel.add(floorBtn[i]);
			floorBtn[i].addActionListener(this);
		}
		for(int i=3; i>=2; i--) {
			floorBtn[i] = new JButton();
			floorBtn[i].setActionCommand(Integer.toString(i+1) +"");
			floorBtn[i].setIcon(doorIcon);
			floorBtn[i].setBorder(null);
			floorBtn[i].setBackground(new Color(199, 163, 254));
			doorPanel.add(floorBtn[i]);
			floorBtn[i].addActionListener(this);
		}
		for(int i=1; i>=0; i--) {
			floorBtn[i] = new JButton();
			floorBtn[i].setActionCommand(Integer.toString(i+1) +"");
			floorBtn[i].setIcon(doorIcon);
			floorBtn[i].setBorder(null);
			floorBtn[i].setBackground(new Color(183, 134, 254));
			doorPanel.add(floorBtn[i]);
			floorBtn[i].addActionListener(this);
		}

		doorPanel.setBounds(510, 0, 56, 660);
		contentPane.add(doorPanel);

		//show elevator

		
		//speed button
		speedBtn[0] = new JButton();
		speedBtn[0].setActionCommand("stop");
		speedBtn[0].setText("бс");
		speedBtn[0].setBackground(new Color(250, 233, 246));
		speedBtn[0].addActionListener(this);
		speedBtn[1] = new JButton();
		speedBtn[1].setActionCommand("x1");
		speedBtn[1].setText("в║");
		speedBtn[1].setBackground(new Color(250, 233, 246));
		speedBtn[1].addActionListener(this);
		speedBtn[2] = new JButton();
		speedBtn[2].setActionCommand("x2");
		speedBtn[2].setText("X 2");
		speedBtn[2].setBackground(new Color(250, 233, 246));
		speedBtn[2].addActionListener(this);
		speedBtn[3] = new JButton();
		speedBtn[3].setActionCommand("x3");
		speedBtn[3].setText("X 3");
		speedBtn[3].setBackground(new Color(250, 233, 246));
		speedBtn[3].addActionListener(this);
		speedBtn[4] = new JButton();
		speedBtn[4].setActionCommand("x5");
		speedBtn[4].setText("X 5");
		speedBtn[4].setBackground(new Color(250, 233, 246));
		speedBtn[4].addActionListener(this);
		speedTable.setLayout(new GridLayout(1,5));
		speedTable.add(speedBtn[0]);
		speedTable.add(speedBtn[1]);
		speedTable.add(speedBtn[2]);
		speedTable.add(speedBtn[3]);
		speedTable.add(speedBtn[4]);

		contentPane.setBackground(new Color(250, 233, 246));
		contentPane.add(speedTable);
		speedTable.setBounds(600, 580, 400, 60);
		// speed button
		
		//select Area
		
		floorArea.setFont(fo); //show present floor
		floorArea.setForeground(Color.pink);

		//the recommend textArea would not be editable.
		floorArea.setEditable(false);
		first.setEditable(false);
		second.setEditable(false);

		showFloor.setBounds(605, 13, 225, 95); //show present floor
		showFloor.setBackground(new Color(250, 233, 246));
		floorArea.setBackground(new Color(245, 238, 246));
		showFloor.add(floorArea);
		contentPane.add(showFloor);

		showBest1.setBackground(new Color(250, 233, 246)); //Recommend elevator. (first, second one. use textArea)
		showBest1.add(first);
		showBest1.add(second);
		
		first.setFont(fo2);
		first.setForeground(new Color(184, 165, 186));
		second.setFont(fo2);
		second.setForeground(new Color(184, 165, 186));

		showBest1.setBounds(605, 110, 160, 200);
		contentPane.add(showBest1);
		
		showBest2.setBackground(new Color(250, 233, 246)); //select button
		showBest2.setLayout(new GridLayout(2,1));

		recoBtn[0] = new JButton(); //first recommend
		recoBtn[0].setBorder(null);
		recoBtn[0].setBackground(new Color(252, 236, 207));
		showBest2.add(recoBtn[0]);
		recoBtn[0].addActionListener(this);

		recoBtn[1] = new JButton(); //second recommend
		recoBtn[1].setBorder(null);
		recoBtn[1].setBackground(new Color(253, 219, 205));
		showBest2.add(recoBtn[1]);
		recoBtn[1].addActionListener(this);

		showBest2.setBounds(778, 115, 50, 196);
		contentPane.add(showBest2);
		//
		
		//select goal floor
		selectButton.setLayout(new GridLayout(6,2));
		ImageIcon buttonIcon[] = new ImageIcon[12];
		for (int i = 0; i < 12; i++) { 
			buttonIcon[i] = new ImageIcon ("b" + Integer.toString(i+1) + ".png");
			selectBtn[i] = new JButton();
			selectBtn[i].setActionCommand("D" + Integer.toString(i+1));
			selectBtn[i].setIcon(buttonIcon[i]);
			selectBtn[i].setBorder(null);
			selectBtn[i].setBackground(new Color(232, 217, 254));
			selectButton.add(selectBtn[i]);
			selectBtn[i].addActionListener(this);
		}
		selectButton.setBounds(853, 13, 150, 298);
		contentPane.add(selectButton);

		selectDisable(false);
		bestDisable(false);
		mainFrame.setVisible(true); //show main frame
	}
	public static void main(String[] args) {
		new MyFrame();
	}
	public void selectDisable(boolean a) {
		for(int i = 0; i < 12; i++) {
			if(i == now-1) {
				selectBtn[i].setEnabled(false);
				now = -1;
			}
			else
				selectBtn[i].setEnabled(a);
		}
	}
	public void floorDisable(boolean a) {
		for(int i = 0; i < 12; i++) {
			floorBtn[i].setEnabled(a);
		}
	}
	public void bestDisable(boolean a) {
		for(int i = 0; i < 2; i++) {
			recoBtn[i].setEnabled(a);
		}
	}
	public void suggestionEle(Person P, int except,int count)	
	{
		int current=P.getY();// person's current location (we assume person's current floor)
		int minY=Integer.MAX_VALUE;
		int minEle=0,tem=0;
		int all=0;

		for(int i=0; i<4; i++)  
		{
			if (i == except)	// 2nd suggestion-elevator !=  1st suggestion-elevator
				continue;
			else if (except != -1 && elev[except].getLeftWeight() == 0  &&  elev[i].getLeftWeight() == 0){
				// When 1st suggestion-elevator is full, 2nd suggestion-elevator have not to full. so pass this elevator.    
				all=0;
				for(int k=0; k<4; k++){
					if(elev[k].getLeftWeight() != 0)
						all=1;
				}
				if(all == 1)  
					continue;
				// else if (all == 0)  -> every-elevator is full-state.
			}

			if(P.getUpDown()==elev[i].getUpDown())	// elevator's direction == Direction that person want to go
			{
				if(elev[i].getUpDown()==1)//UP
				{
					if(current-elev[i].getY() <= 0)// same floor or exist further down the location of the elevator
					{
						tem = ab(current-elev[i].getY());
						System.out.println("tem: " + tem);
						if( minY >= tem){
							if(minY == tem){
								if(elev[minEle].getLeftWeight() > elev[i].getLeftWeight())
									continue;
							}
							minY=tem;
							minEle=i;
						}
					}
					else//exist further up the location of the elevator
					{
						tem= ab(elev[i].maxFloorY(elev[i])-elev[i].getY()) + ab(elev[i].maxFloorY(elev[i])-current);
						if( minY >= tem){
							if(minY == tem){
								if(elev[minEle].getLeftWeight() > elev[i].getLeftWeight())
									continue;
							}
							minY=tem;
							minEle=i;
						}
					}
				}
				else if(elev[i].getUpDown()==-1)				   //DOWN
				{
					if(current-elev[i].getY() >= 0)// same floor or exist further up the location of the elevator
					{
						tem = ab(elev[i].getY()-current);
						if( minY >= tem)
						{
							if(minY == tem){
								if(elev[minEle].getLeftWeight() > elev[i].getLeftWeight())
									continue;
							}
							minY=tem;
							minEle=i;
						}
					}
					else //exist further down the location of the elevator
					{
						tem= ab(elev[i].getY()-elev[i].minFloorY(elev[i])) + ab(current-elev[i].minFloorY(elev[i]));
						if( minY >= tem){
							if(minY == tem){
								if(elev[minEle].getLeftWeight() > elev[i].getLeftWeight())
									continue;
							}
							minY=tem;
							minEle=i;
						}
					}
				}
			}
			else if (elev[i].getUpDown() != 0) 	// elevator's direction != Direction that person want to go (but both moving now)
			{
				if(elev[i].getUpDown()==1)	//elevator->UP request->DOWN
				{
					tem = ab(elev[i].maxFloorY(elev[i])-elev[i].getY()) + ab(elev[i].maxFloorY(elev[i])-current); 
					if( minY >= tem){
						if(minY == tem){
							if(elev[minEle].getLeftWeight() > elev[i].getLeftWeight())
								continue;
						}
						minY=tem;
						minEle=i;
					}
				}
				else //elevator->DOWN request->UP
				{

					tem = ab(elev[i].minFloorY(elev[i])-elev[i].getY()) + ab(elev[i].minFloorY(elev[i])-current);
					if( minY >= tem){
						if(minY == tem){
							if(elev[minEle].getLeftWeight() > elev[i].getLeftWeight())
								continue;
						}
						minY=tem;
						minEle=i;
					}
				}
			}
			else if (elev[i].getUpDown()== 0)// elevator stop.
			{
				tem = ab(elev[i].getY()-current);
				if( minY >= tem)
				{
					if(minY == tem){
						if(elev[minEle].getLeftWeight() > elev[i].getLeftWeight())
							continue;
					}
					minY=tem;
					minEle=i;
				}
			}
		}

		recoBtn[count].setActionCommand(Integer.toString(minEle)+"E"); //set suggestion-elevator
		
		if(count==0)
		{//1st suggestion-elevator
			first.setText("\n     Elevator "+(minEle+1)+ "\n     "+ elev[minEle].getNowFloor()+" floor");
			if(elev[minEle].getLeftWeight()<68)
				first.append("\n     "+"full");
			else
				first.append("\n     "+elev[minEle].getLeftWeight()/68+" persons");
		}
		else if(count==1)
		{//2nd suggestion-elevator
			second.setText("\n     Elevator "+(minEle+1)+ "\n     "+ elev[minEle].getNowFloor()+" floor");
			if(elev[minEle].getLeftWeight()<68)
				second.append("\n     "+"full");
			else
				second.append("\n     "+elev[minEle].getLeftWeight()/68+" persons");
		}
		
		if(count==0)//recursion for finding 2nd suggestion-elevator
			suggestionEle(P,minEle,++count);
	}
	
	public int ab(int x)//Absolute value
	{
		if (x<0)
			return (-1)*x;
		return x;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("1")){
			now = 1;
			floorArea.setText("\n         Floor 1");
			tmpPerson = new Person(personCount,1,1,607);
			drawPer.add(tmpPerson);
			floorDisable(false);
			selectDisable(true);
		}
		else if (e.getActionCommand().equals("2")){
			now = 2;
			floorArea.setText("\n         Floor 2");
			tmpPerson = new Person(personCount,2,2,552) ;
			drawPer.add(tmpPerson);
			floorDisable(false);
			selectDisable(true);
		}
		else if (e.getActionCommand().equals("3")){
			now = 3;
			floorArea.setText("\n         Floor 3");
			tmpPerson = new Person(personCount,3,3,497);
			drawPer.add(tmpPerson);
			floorDisable(false);
			selectDisable(true);
		}
		else if (e.getActionCommand().equals("4")){
			now = 4;
			tmpPerson = new Person(personCount,4,4,442);
			floorArea.setText("\n         Floor 4");
			drawPer.add(tmpPerson);
			floorDisable(false);
			selectDisable(true);
		}
		else if (e.getActionCommand().equals("5")){
			now = 5;
			tmpPerson = new Person(personCount,5,5,387);
			floorArea.setText("\n         Floor 5");
			drawPer.add(tmpPerson);
			floorDisable(false);
			selectDisable(true);
		}
		else if (e.getActionCommand().equals("6")){
			now = 6;
			tmpPerson = new Person(personCount,6,6,332);
			floorArea.setText("\n         Floor 6");
			drawPer.add(tmpPerson);
			floorDisable(false);
			selectDisable(true);
		}
		else if (e.getActionCommand().equals("7")){
			now = 7;
			tmpPerson = new Person(personCount,7,7,277);
			floorArea.setText("\n         Floor 7");
			drawPer.add(tmpPerson);
			floorDisable(false);
			selectDisable(true);
		}
		else if (e.getActionCommand().equals("8")){
			now = 8;
			tmpPerson = new Person(personCount,8,8,222);
			floorArea.setText("\n         Floor 8");
			drawPer.add(tmpPerson);
			floorDisable(false);
			selectDisable(true);
		}
		else if (e.getActionCommand().equals("9")){
			now = 9;
			tmpPerson = new Person(personCount,9,9,167);
			floorArea.setText("\n         Floor 9");
			drawPer.add(tmpPerson);
			floorDisable(false);
			selectDisable(true);
		}
		else if (e.getActionCommand().equals("10")){
			now = 10;
			tmpPerson = new Person(personCount,10,10,112);
			floorArea.setText("\n         Floor 10");
			drawPer.add(tmpPerson);
			floorDisable(false);
			selectDisable(true);
		}
		else if (e.getActionCommand().equals("11")){
			now = 11;
			tmpPerson = new Person(personCount,11,11,57);
			floorArea.setText("\n         Floor 11");
			drawPer.add(tmpPerson);
			floorDisable(false);
			selectDisable(true);
		}
		else if (e.getActionCommand().equals("12")){
			now = 12;
			tmpPerson = new Person(personCount,12,12,2);
			floorArea.setText("\n         Floor 12");
			drawPer.add(tmpPerson);
			floorDisable(false);
			selectDisable(true);
		}
		else if (e.getActionCommand().charAt(0)=='D'){//select destination floor.
			tmpPerson.setDestFloor(Integer.parseInt(e.getActionCommand().substring(1)));
			if(tmpPerson.getDestFloor() > tmpPerson.getNowFloor())
				tmpPerson.setUpDown(1);
			else
				tmpPerson.setUpDown(-1);
			suggestionEle(tmpPerson,-1,0);
			selectDisable(false);
			bestDisable(true);
		}
		else if (e.getActionCommand().charAt(1)=='E'){// If we select n elevator, get 'nE'. Then add person information to n elevator. 

			d=Integer.parseInt(""+e.getActionCommand().charAt(0));      
			if(elev[d].getLeftWeight() >= (68*tmpPerson.getParty()))//Check left weight.
			{
				tmpPerson.setRide(true);// add to ride list 		
				elev[d].setLeftWeight(elev[d].getLeftWeight()-68*tmpPerson.getParty());
			}
			//Set person's information && restart
			tmpPerson.setSelectedEle(d);
			tmpPerson.setDestX(wait_location[d]);
			elev[d].waitingListAt(((607 - tmpPerson.getY()) / 55) + 1).add(tmpPerson);
			personCount++;
			first.setText("");
			second.setText("");
			bestDisable(false);
			floorDisable(true);
			Elevator.findNext(elev[d]);
		}
		else if (e.getActionCommand().equals("stop")) { // increase sleep time 
			T.speed = 3000;
		}
		else if (e.getActionCommand().equals("x1")) {
			T.speed = 30;
		}
		else if (e.getActionCommand().equals("x2")) {
			T.speed = 15;
		}
		else if (e.getActionCommand().equals("x3")) {
			T.speed = 10;
		}
		else if (e.getActionCommand().equals("x5")) {
			T.speed = 6;
		}
		else {
			System.out.println("Error in button interface.");
		}
	}
}