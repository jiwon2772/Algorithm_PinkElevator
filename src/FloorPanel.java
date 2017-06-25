import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FloorPanel extends JPanel { // for floor image ex) 1F, 2F, ... , 12F
	Toolkit tk = Toolkit.getDefaultToolkit();
	Image tmpImg[] = new Image[12];

	public FloorPanel() {
		setLayout(null);
		for (int i=0; i <= 11; i++) {
			tmpImg[11 - i] = tk.createImage("f" + i + ".png");
		}
		
		setBounds(0, 0, 60, 665); // fix first position and size.
	}
	
	public void paintComponent(Graphics g){
		g.setColor(new Color(250, 233, 246));
		g.fillRect(0, 0, 60, 665);

		for (int i=0; i<12; i++) {
			g.drawImage(tmpImg[i], 0, (i * 55)+5, this);
		}
	}
}
