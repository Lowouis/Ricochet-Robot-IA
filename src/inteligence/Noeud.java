package inteligence;

import java.awt.Point;
import java.util.ArrayList;

import iniz.Maps;
import storage.Donnees;

public class Noeud{


	private ArrayList<Point> PJ;
	private Point target;
	private Noeud parent=null;
	private int count;
	private int cost=0;
	private int averagecost=0;
	
	
	


	public Noeud(ArrayList<Point> PJ, int target){
		this.PJ=PJ;
		this.target=PJ.get(target);
		this.count=0;
		this.averagecost=0;
		
	}
	
	public Noeud(Noeud parent,ArrayList<Point> PJ,int count){
		this.PJ = PJ;
		this.target=parent.getTarget();
		this.parent=parent;
		this.count=count;
		this.averagecost=0;
		
	}
	
	
	public void setParent(Noeud Parent){
		this.parent=Parent;
	}
	public Noeud getParent(){return this.parent;}
	
	public Point getTarget(){return this.target;}

	public ArrayList<Point> getPJ(){return this.PJ;}
	public void setPJ(ArrayList<Point> PJ){this.PJ=PJ;}

	
	public int getCount(){return this.count;}
	public void setCount(int count){this.count=count;}
	public void setCost(int cost){this.cost=cost;}
	
	public int getCost(){
		return this.cost;
	}
	
	public int getAverageCost(){
		return this.averagecost;
	}
	
	public void setAverageCost(int cost){
		this.averagecost=cost;
	}
	
	
	public int whoMoves(){
		for(int a = 0; a < getPJ().size(); a++){
			if(getParent().getPJ().get(a).x != this.getPJ().get(a).x || getParent().getPJ().get(a).y != this.getPJ().get(a).y){
				return a;
			}
		}
		return -1;
		
	}
	

	
	
	public Point getPJByName(int i){
		if(i==0){return PJ.get(i);}
		if(i==1){return PJ.get(i);}
		if(i==2){return PJ.get(i);}
		if(i==3){return PJ.get(i);}
		return null;
	}
	
	public void setPJByName(int i,Point p){
		this.PJ.remove(i);
		this.PJ.add(i,p);
		//System.out.println(this.PJ.get(i));
	}


	
	public void showPoints(){
		for(int i = 0; i < this.PJ.size();i++){
				System.out.println(this.PJ.get(i));
			}
		
	}
	
}