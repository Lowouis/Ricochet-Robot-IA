package graphic;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import iniz.*;
import storage.*;

public class DisplayGraphics extends Canvas implements MouseListener{
	
	
	private final static int DIM = 40;
	private Maps k;
	private static final long serialVersionUID = 1L;
	private int thickness = 40;
	private int height = 3;
	private ArrayList<ArrayList<Point>> path;
	public PionObjectif randPO;
	

	
	
	public DisplayGraphics(Maps k){
		this.k = k;
		this.randPO = k.getRandPO();
		this.setPreferredSize(new Dimension(16*DIM,16*DIM));
	}
	
	public void setPath(ArrayList<ArrayList<Point>> path){
		this.path = path;
	}
	
	public void updateGraphicsMaps(Maps m){
		this.k = m;
		this.randPO=k.getRandPO();
		this.repaint();
	}

    public void paint(Graphics g){
    	setBackground(Color.WHITE);
    	for(int j = 0; j < 16; j++){
    		for(int i = 0; i < 16 ; i++){
    			g.setColor(Color.BLACK);
    			g.drawRect(i*DIM,j*DIM,DIM,DIM);
    			
    			// AFFICHAGE MURS
				if(k.getMaps()[i][j].getMurs()[0]){g.fillRect(j*DIM,i*DIM,thickness,height);}
    			if(k.getMaps()[i][j].getMurs()[1]){g.fillRect(j*DIM+38,i*DIM,height,thickness);}
    			if(k.getMaps()[i][j].getMurs()[2]){g.fillRect(j*DIM,i*DIM+38,thickness,height);}
    			if(k.getMaps()[i][j].getMurs()[3]){g.fillRect(j*DIM,i*DIM,height,thickness);}
    			
    			// AFFICHAGE DU PIONOBJECTIF

    			if(k.getMaps()[i][j].getPOColor() == randPO.getColor() && k.getMaps()[i][j].getPOForms() == randPO.getForms()){
    					Image icon = new Image("graphics/" + randPO.getColor()+randPO.getForms() + ".png");
    					icon.paintComponent(g,7+j*DIM,7+i*DIM);
    				}
    			}
    		}
    	
    			
    	Image Update = new Image("graphics/U.png");
		Update.paintComponent(g,7*DIM,7*DIM);

		// AFFICHAGE PIONJOUEUR
		for(int a = 0; a < Donnees.colorList.length;a++){
			Image icon = new Image("graphics/PJ_"+Donnees.colorList[a]+".png");
			icon.paintComponent(g,k.getModele().getPJ(Donnees.colorList[a]).getCoord().y * DIM,k.getModele().getPJ(Donnees.colorList[a]).getCoord().x *DIM);
		}
		
		//AFFICHAGE DU CHEMIN
		
		
		
		
		
		
		
		
    }
    
   
    
    

	@Override
	public void mouseClicked(MouseEvent e){}
	@Override
	public void mousePressed(MouseEvent e){}
	@Override
	public void mouseReleased(MouseEvent e){}
	@Override
	public void mouseEntered(MouseEvent e){}
	@Override
	public void mouseExited(MouseEvent e){}

    }

