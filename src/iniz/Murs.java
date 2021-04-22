package iniz;
public class Murs{

	
	public boolean h;
	public boolean d;
	public boolean b;
	public boolean g;
	
	public Murs(boolean h, boolean d, boolean b, boolean g){
		this.h = h;
		this.d = d;
		this.b = b;
		this.g = g;
	}
	
	public void setMurs(boolean h, boolean d, boolean b, boolean g){
		this.h = h;
		this.d = d;
		this.b = b;
		this.g = g;
	}
	
	public boolean[] getMurs(){
		boolean[] list =new boolean[4];
		list[0] = this.h;
		list[1] = this.d;
		list[2] = this.b;
		list[3] = this.g;
		return list;
	}
	
}
