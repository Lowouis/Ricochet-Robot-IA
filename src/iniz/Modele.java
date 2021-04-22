package iniz;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Modele{
	
	private PionJoueur pjred,pjblue,pjgreen,pjyellow;
	private final Random r = new Random();
	
	public Modele(){
		this.pjred= new PionJoueur("red",randCoord());
		this.pjblue = new PionJoueur("blue",randCoord());
		this.pjgreen = new PionJoueur("green",randCoord());
		this.pjyellow= new PionJoueur("yellow",randCoord());
	}
	
	public PionJoueur getPJ(String color){
		switch(color){
		case "red": return this.pjred;
		case "blue": return this.pjblue;
		case "green": return this.pjgreen;
		case "yellow": return this.pjyellow;
		}
		return null;
	}
	
	public ArrayList<Point> getPJPoints(){
		 ArrayList<Point> pjs = new  ArrayList<Point>();
		 pjs.add(pjred.getCoord());
		 pjs.add(pjblue.getCoord());
		 pjs.add(pjgreen.getCoord());
		 pjs.add(pjyellow.getCoord());
		 return pjs;
	}
	
	
	private Point randCoord(){
		boolean stop=true;
		while(stop){
			Point tmp = new Point(r.nextInt(16),r.nextInt(16));
			if(isRestricted(tmp)){
				return tmp;
			}
		}
		return null;
		
	}
	
	
	private boolean isRestricted(Point tmp){
		if(tmp == null){return false;}
		if(tmp.x == 7 && tmp.y == 7){return false;}
		if(tmp.x == 8 && tmp.y == 7){return false;}
		if(tmp.x == 7 && tmp.y == 8){return false;}
		if(tmp.x == 8 && tmp.y == 8){return false;}
		else{return true;}
	}
	
	
	
}
