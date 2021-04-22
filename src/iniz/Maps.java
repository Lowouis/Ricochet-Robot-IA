package iniz;
import storage.*;

import java.util.*;

import java.awt.Point;


public class Maps{
	
	/*	TOUT LES ATTRIBUTS NECESSAIRES
	 * 
	 */
	
	private PionJoueur currentPlayer;
	private Case[][] table;
	private Maps copy;
	private static Random r = new Random();
	private PionObjectif randPO;
	private Modele modele;
	private String[][] randParcel = {Donnees.ParcelHautGauche1,Donnees.ParcelHautGauche2, Donnees.ParcelHautDroite1, Donnees.ParcelHautDroite2,
			Donnees.ParcelBasGauche1,Donnees.ParcelBasGauche2, Donnees.ParcelBasDroite1,Donnees.ParcelBasDroite2 };

	
	/*	CONSTRUCTEUR
	 * 
	 */
	public Maps(){
		this.table = new Case[16][16];
		for(int i=0;this.table.length > i;i++){
			for(int j=0; this.table[i].length > j;j++){
				this.table[i][j] = new Case(null,null,false,false,false,false);
			}
		}
		randPO = createRandPO();
		translator(this);
		this.modele = new Modele();
	}


	/*  TOUT LES GETTERS NECESSAIRE*/
	
	public Modele getModele(){return this.modele;}
	public void updateModele(){this.modele = new Modele();}
	public PionObjectif getPO(){return randPO;}
	public Case[][] getMaps(){return this.table;}
	public PionObjectif getRandPO(){return randPO;}
	public String getCurrentPlayer(){return this.currentPlayer.getPJcolor();}
	public String getPOColor(){return randPO.getColor();}
	public Point searchPO(){
		for(int i=0; i < getMaps().length ; i++){
			for(int j=0; j < getMaps().length ; j++){
				if(randPO.getColor() == this.table[i][j].getPOColor() && randPO.getForms() == this.table[i][j].getPOForms()){
					return new Point(i,j);
				}
			}
		}
		return null;
	}
	public int searchPOIndice(){
		if(randPO.getColor() == "red"){return 0;}
		if(randPO.getColor() == "blue"){return 1;}
		if(randPO.getColor() == "green"){return 2;}
		if(randPO.getColor() == "yellow"){return 3;}
		return -1;
	}
	
	
	/*  TOUT LES SETTERS NECESSAIRE*/
	
	public void placementMurs(int x, int y, boolean h, boolean d, boolean b, boolean g){
			this.table[x][y].placementMurs(h,d,b,g);
	}
	public void placementObjectif(int x, int y, String couleur, String forme){
		this.table[x][y].setPion(couleur, forme);
	}
	
	
		
		
		
	/*	changeMaps()
	 *	Actualise tout les attributs important de l'objet en question
	 */
	
	public void changeMaps(){
		this.table = new Case[16][16];
		for(int i=0;this.table.length > i;i++){
			for(int j=0; this.table[i].length > j;j++){
				this.table[i][j] = new Case(null,null,false,false,false,false);
			}
		}
		this.randPO = createRandPO();
		translator(this);
		updateModele();
	}
	
	
	/*	isOver()
	 * 	Renvoie true si la position du PionObjectif ciblé et la meme que celui du PionJoueur qui doit l'atteindre
	 */
	
	public boolean isOver(){
		
		if(searchPO().equals(this.modele.getPJ(randPO.getColor()).getCoord())){return true;}
		return false;
	}
	
	/*	createRandPO()
	 *	Renvoie un object PionObjectif avec des attributs aléatoire ici, la couleur et une forme
	 *	en récuperant un indice aléatoire dans une liste contenant les couleurs (red,blue,green,yellow)
	 *	ainsi que les formes (stars, circle, losange, triangle)
	 */
	
	public PionObjectif createRandPO(){
		String randColor = Donnees.colorList[r.nextInt(Donnees.colorList.length)];
		String randForm = Donnees.formList[r.nextInt(Donnees.formList.length)];
		return new PionObjectif(randColor,randForm);
	}

	
	/* FABRICATION D'UNE CARTE DE JEU*/
	
	
	
	/* aleaParcel()
	 * Qui aléatoirement selectionne 4 parcelles parmis toutes nos parcelles ce qui représente 1/4 dans la taille final
	 * de notre grille, une parcelle est donc un sous-ensemble de notre object actuelle
	 */
	
	private ArrayList<String[]> aleaParcel(){
		ArrayList<String[]> aleaParcel = new ArrayList<String[]>();
		for(int i = 0; i < randParcel.length; i+=2){
			int k = r.nextInt(2);
			aleaParcel.add(randParcel[i+k]);
		}
		return aleaParcel;
	}

	/* concatenate()
	 *	Qui recupére 4 listes de String, pour dans l'ordre respectif de l'ArrayList pour additionner les lignes de chaque liste
	 *	afin de former notre table complète non-traduite.
	 */
	
	private List<String> concatenate(){
		ArrayList <String[]> aleaParcel = aleaParcel();
		List<String> completed = new ArrayList<String>();

		for(int i = 0; i < 8 ; i++){
				completed.add(aleaParcel.get(0)[i]+"I"+aleaParcel.get(1)[i]+"I");
			}
		for(int i = 0; i < 8 ; i++){
				completed.add(aleaParcel.get(2)[i]+"I"+ aleaParcel.get(3)[i]+"I");
		}
		return completed;
	}
	
	/* translator()
	 *	Traduit notre table-non traduite en table traduite de taille 16x16
	 *	
	 */

	private Maps translator(Maps maps){
		List<String> completed = concatenate();
		boolean h = false,d = false,b = false,g = false;
		String color = "",form = "";
		int tmp=0;
		for(int x = 0; x < 16; x++){
			tmp=0;
			for(int y = 0; y < completed.get(x).length() ; y++){
				char a = completed.get(x).charAt(y);
				if(a == 'h'){h = true;}
				if(a == 'd'){d = true;}
				if(a == 'b'){b = true;}
				if(a == 'g'){g = true;}
				if(a == 'R'){color = "red";}
				if(a == 'Y'){color = "yellow";}
				if(a == 'B'){color = "blue";}
				if(a == 'G'){color = "green";}
				if(a == 'S'){form = "stars";}
				if(a == 'C'){form = "circle";}
				if(a == 'L'){form = "losange";}
				if(a == 'T'){form = "triangle";}
				if(a == 'N'){form = "nexus";color = "";}
				if(a == 'I'){
					maps.table[x][tmp].placementMurs(h,d,b,g);
					maps.table[x][tmp].setPion(color,form);
					h = false;d = false;b = false;g = false;color = "";form = "";
					tmp+=1;
				}
			} 
		}
		return maps;
	}


	
	
	
	/* DEPLACEMENT D'UN PIONJOUEUR*/
	
	
	/* robotCheck()
	 * Renvoie true si selon le Point recu s'il y n'y a pas un autre PionJoueur au meme endroit
	 * Autrement renvoie false.
	 */
	
	private boolean robotCheck(Point p, ArrayList<Point> pj){
		for(int a = 0; a < Donnees.colorList.length;a++){
			if(p.x == pj.get(a).x && p.y==pj.get(a).y){
				return false;
			}
		}
		return true;
	}
	
	
	/* movedir()
	 * Renvoie une Point qui représente le movement demander, donc ici <dir> qui représente
	 * la direction, comme expliquer ci-dessous.
	 */
	
	public Point movedir(Point p, int dir, ArrayList<Point> pj){
		int dx=0, dy=0;
		
		/*
		 * dir POUR DIRECTION, EST UN INTEGER QUI INDIQUE SI ON VEUT ALLER 
		 * EN HAUT, A DROITE, EN BAS OU A GAUCHE, RESPECTIVEMENT 0,1,2,3.
		 */
		
		if(dir==0){dx = -1; dy = 0;}
		if(dir==1){dx = 0 ; dy = 1;}
		if(dir==2){dx = 1 ; dy = 0;}
		if(dir==3){dx = 0 ; dy = -1;}
		
		boolean stop = false;
		
		int x = p.x;
		int y = p.y;
		while(!stop){
			x+=dx;
			y+=dy;
			if(this.table[x-dx][y-dy].getMurs()[dir] == true || robotCheck(new Point(x,y), pj) == false){
				stop = true;
			}
			
		}
		return new Point(x-dx,y-dy);
	}
	
	
	
	/*	copy()
	 * Renvoie une copie de l'object actuel.
	 */
	
	public Maps copy(){
		return this.copy = this;
	}
	
	/*	updatePO()
	 * Change l'attribut randPO qui est un PionObjectif, pour un nouveau
	 */
	
	public void updatePO(){
		this.randPO = createRandPO();
	}
}


