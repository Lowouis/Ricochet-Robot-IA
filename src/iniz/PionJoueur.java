package iniz;
import java.awt.Point;

public class PionJoueur{
	
	public String color;
	private Point coord;
	public String getPJcolor;
	
	public PionJoueur(String color, Point coord){
		this.color = color;
		this.coord = coord;
	}
	
	//GETTERS
	
	public String getPJcolor(){
		return this.color;
	}
	public Point getCoord(){
		return this.coord;
	}
	
	//SETTERS
	public void setCoord(Point p){
		this.coord = p;
	}
	
	public void setName(String name){this.color = color;}

	

}
