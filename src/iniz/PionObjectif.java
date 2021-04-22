package iniz;

public class PionObjectif{

	public String couleur;
	public String forme;
	
	public PionObjectif(String couleur, String forme){
		this.couleur = couleur;
		this.forme  = forme;
	} 
	
	//GETTERS
	
	public String getPion(){return this.couleur+this.forme;}
	public String getColor(){return this.couleur;}
	public String getForms(){return this.forme;}
	
	//SETTERS
	
	public void setPion(String couleur, String forme){
		this.couleur = couleur;
		this.forme = forme;
	}
	
}
