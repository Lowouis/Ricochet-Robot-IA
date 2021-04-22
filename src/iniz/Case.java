package iniz;
import java.awt.Point;

public class Case{
	

	public PionObjectif po;
	public Murs m;
	
	public Case(String namePOC, String namePOF,boolean h, boolean d, boolean b, boolean g){
		this.po = new PionObjectif(namePOC, namePOF);
		this.m =  new Murs(h,d,b,g);
	}
	
	// GETTERS
	
	public String getPO(){
		return this.po.getPion();
	}
	public String getPOColor(){return this.po.getColor();}
	public String getPOForms(){return this.po.getForms();}
	
	
	public boolean[] getMurs(){
		boolean[] list = new boolean[4];
		list = this.m.getMurs();
		return list;
	}
	
	//SETTERS
	
	public void placementMurs(boolean h, boolean d, boolean b, boolean g){
		this.m.setMurs(h,d,b,g);
	}
	
	public void setPion(String couleur, String forme){
		this.po.setPion(couleur, forme);
	}
	
	
	
	

	
	
	
	
	
}
