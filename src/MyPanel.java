import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MyPanel extends JPanel {
	private static int count = 0;
	ArrayList<Person> per;
	Elevator[] elev;
	T t;

	public MyPanel(ArrayList<Person> p, Elevator[] e) {
		this.per = p;
		this.elev = e;
		
		this.setLayout(null);

		//make elevator
		elev[0] = new Elevator(83,0);
		elev[1] = new Elevator(163,1);
		elev[2] = new Elevator(243,2);
		elev[3] = new Elevator(323,3);
		//
		
		t = new T(per, elev, this);
		t.start(); // start thread

		this.setBounds(60, 0, 450, 700);
	}

	public void paintComponent(Graphics g) {
		//gradation floor's color
		g.setColor(new Color(249, 246, 254));
		g.fillRect(0, 0, getWidth(), 110);
		
		g.setColor(new Color(240, 231, 254));
		g.fillRect(0, 110, getWidth(), 110);
		
		g.setColor(new Color(232, 217, 254));//
		g.fillRect(0, 220, getWidth(), 110);
		
		g.setColor(new Color(221, 200, 254));
		g.fillRect(0, 330, getWidth(), 110);
		
		g.setColor(new Color(199, 163, 254));
		g.fillRect(0, 440, getWidth(), 110);
		
		g.setColor(new Color(183, 134, 254));
		g.fillRect(0, 550, getWidth(), 110);
		//

		//draw a line for floor. horizontal line. 
		// floor
		int floor = 0;
		for (int i = 0; i < 12; i++) {
			floor = floor + 55;
			g.setColor(new Color(250, 233, 246));
			g.drawLine(0, floor, 530, floor);
			g.setColor(new Color(235, 225, 248));
			g.drawLine(0, floor + 1, 530, floor + 1);
		}

		
		//draw a line (elevator's string). perpendicular line.
		// Elev 1
		g.setColor(Color.gray);
		g.drawLine(100, 0, 100, 660);
		g.setColor(Color.GRAY);
		g.drawLine(101, 0, 101, 660);
		g.setColor(Color.gray);
		g.drawLine(102, 0, 102, 660);

		// Elev 2
		g.drawLine(180, 0, 180, 660);
		g.setColor(Color.GRAY);
		g.drawLine(181, 0, 181, 660);
		g.setColor(Color.gray);
		g.drawLine(182, 0, 182, 660);

		// Elev 3
		g.drawLine(260, 0, 260, 660);
		g.setColor(Color.GRAY);
		g.drawLine(261, 0, 261, 660);
		g.setColor(Color.gray);
		g.drawLine(262, 0, 262, 660);

		// Elev 4
		g.drawLine(340, 0, 340, 660);
		g.setColor(Color.GRAY);
		g.drawLine(341, 0, 341, 660);
		g.setColor(Color.gray);
		g.drawLine(342, 0, 342, 660);

		for (int i = 0; i < elev.length; i++) { //draw elevator
			if(elev[i].isStop() == true) { //open elevator
				g.drawImage(elev[i].getImg(1), elev[i].getX(), elev[i].getY(), this); // x, y
			}
			else { //close elevator
				g.drawImage(elev[i].getImg(0), elev[i].getX(), elev[i].getY(), this); // x, y
			}
		}
		for (int i = 0; i < per.size(); i++) { //draw person.
			if (count % 6 == 0 || count % 6 == 1)
				g.drawImage(per.get(i).getImg(0), per.get(i).getX(), per.get(i).getY(), this); // x, y
			else
				g.drawImage(per.get(i).getImg(1), per.get(i).getX(), per.get(i).getY(), this);
		}

		count++; //use this, person look like walking.
	}

}
