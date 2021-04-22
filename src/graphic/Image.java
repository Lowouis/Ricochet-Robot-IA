package graphic;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class Image extends JPanel {
		  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		BufferedImage image;

		public Image(String URL){
		    try {
		      image = ImageIO.read(new File(URL));
		      
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		}
		  
		public void paintComponent(Graphics g,int x,int y) {
				super.paintComponent(g);
				g.drawImage(image,x,y, this);
		}
		
		
}
