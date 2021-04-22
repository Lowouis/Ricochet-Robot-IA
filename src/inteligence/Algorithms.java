package inteligence;
import iniz.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import storage.*;

public class Algorithms{
	
	private Maps copy;
	private int[][] computedMap;
	
	
	ArrayList<Noeud> openList = new ArrayList<Noeud>();
	ArrayList<Noeud> closedList = new ArrayList<Noeud>();
	ArrayList<Noeud> path = new ArrayList<Noeud>();
	
	ArrayList<ArrayList<Point>> pathPoint = new ArrayList<ArrayList<Point>>();
	
	
	private String[][] verticalMaps = new String[16][16];
	private String[][] directionMaps = new String[16][16];
	
	public Algorithms(Maps copy){
		this.copy=copy;
		this.computedMap = getEmptyMap();
	}
	
	/**
		REMPLIS DE 0 LA COMPUTEDMAP, GRILLE DE TAILLE 16X16
	 */
	
	
	private ArrayList<Point> getBeginPJ(){
		ArrayList<Point> PJs = new ArrayList<Point>();
		for(int i = 0; i < Donnees.colorList.length;i++){
			PJs.add(copy.getModele().getPJ(Donnees.colorList[i]).getCoord());
		}
		return PJs;
	}
	
	private int[][] getEmptyMap(){
		int[][] Map = new int[16][16];
		for(int x=0;x<16;x++){
			for(int y=0;y<16;y++){
				Map[x][y]=0;
			}
		}
		return Map;
	}
	
	
	public int[][] getComputedMap(){
		return computedMap;
	}
	

	/**
	 PARCOURS MULTIDIRECTIONNEL QUI EXPLORE EN BAS, HAUT, DROITE, ET A GAUCHE
	 POUR INSERER DES 1 SUR LES LIGNES DE DEPLACEMENTS EN PARTANT DU POINT QU'ON LUI
	 ENVOIE
	 */
	private void depthOne(Point p){
		for(int i=0;i < Donnees.direction.length;i++){
			if(this.copy.getMaps()[p.x][p.y].getMurs()[i] == false){
				fill(p.x,p.y,Donnees.direction[i],1);		
			}
		}
	}
	
	/**
	 EXPORE LES AUTRES PROFONDEURS A PARTIR DE CHAQUE 1 COMME POINT DE DEPART ET VEILLANT A PARTIR DANS LE BON SENS 
	 GRACE A UNE GRILLE CONTENANT LES DIRECTIONS QUE L'ONT STOCKS AU MOMENT OU L'ON PLACE CHAQUE POINT
	 */
	private void depthOthers(int d){
		for(int i = 0; i < computedMap.length;i++){
			for(int j = 0; j < computedMap[i].length;j++){
				if(computedMap[i][j] == d-1 && verticalMaps[i][j] == "horizontal"){
					fill(i,j,"down",d);
					fill(i,j,"up",d);
				}
				if(computedMap[i][j] == d-1 && verticalMaps[i][j] == "vertical"){
					fill(i,j,"right",d);
					fill(i,j,"left",d);
				}

			}
		}
	}

	/**
	 APPELE LES METHODES DE REMPLISSAGE DES 4 DIRECTIONS EN FONCTION 
	 */
	
	private void fill(int x,int y, String direction, int depth){
		if(direction == "up"){
			goUp(x,y,depth);
		}
		if(direction == "down"){
			goDown(x,y,depth);
		}
		if(direction == "right"){
			goRight(x,y,depth);
		}
		if(direction == "left"){
			goLeft(x,y,depth);
		}
		this.computedMap[copy.searchPO().x][copy.searchPO().y] = 0;
	}
	
	/**
	 COMMENTAIRE
	 */
	private void delineate(){
		depthOne(copy.searchPO());
		for(int d=2;d < 6;d++){
			 depthOthers(d);
		}
		
	}
	

	
	private void printComputedMap(){
		for(int i = 0;i < this.computedMap.length;i++){
			System.out.println();
			for(int j = 0; j < this.computedMap.length;j++){
				System.out.print(this.computedMap[i][j] + " ");
			}
		}
	}
	
	
	/**
	 COMMENTAIRE
	 */
	
	private void goUp(int x,int y, int count){
		boolean stop = false;
		while(!stop){
			if(this.computedMap[x][y] == 0){
				this.computedMap[x][y] = count;
				this.verticalMaps[x][y] = "vertical";
				this.directionMaps[x][y] = "haut";
			}
			if(this.copy.getMaps()[x][y].getMurs()[0] == true){
				break;
			}
			x-=1;
		}
	}
	private void goDown(int x,int y, int count){
		boolean stop = false;
		while(!stop){
			if(this.computedMap[x][y] == 0){
				this.computedMap[x][y] = count;
				this.verticalMaps[x][y] = "vertical";	
				this.directionMaps[x][y] = "bas";
			}
			if(this.copy.getMaps()[x][y].getMurs()[2] == true){
				break;
			}
			x+=1;
		}	
	}
	private void goRight(int x,int y, int count){
		boolean stop = false;
		while(!stop){
			if(this.computedMap[x][y] == 0){
				this.computedMap[x][y] = count;
				this.verticalMaps[x][y] = "horizontal";
				this.directionMaps[x][y] = "droite";
			}
			if(this.copy.getMaps()[x][y].getMurs()[1] == true){
				break;
			}
			y+=1;
		}
	}
	private void goLeft(int x,int y, int count){
		boolean stop = false;
		while(!stop){
			if(this.computedMap[x][y] == 0){
				this.computedMap[x][y] = count;
				this.verticalMaps[x][y] = "horizontal";
				this.directionMaps[x][y] = "gauche";
			}
			if(copy.getMaps()[x][y].getMurs()[3] == true){
				break;
			}
			y-=1;
		}	
	}
	
	/**
	 COMMENTAIRE
	 */
	
	private int heuristic(Point a,Point b){
		return (int)Math.sqrt(Math.pow(((double)b.x-(double) a.x),2)+ Math.pow(((double)b.y-(double)a.y),2));
	}
	
	public int whoIsTarget(){
		return copy.searchPOIndice();
	}
	
	

	public void makeChildrens(Noeud parent){
		ArrayList<Noeud> childrens = new ArrayList<Noeud>();
		ArrayList<Point> PJ = new ArrayList<Point>();
		for(int a = 0; a < parent.getPJ().size();a++){
			PJ.add(new Point(parent.getPJ().get(a).x, parent.getPJ().get(a).y));
		}
		

		for(int i = 0; i < PJ.size();i++){
			ArrayList<Point> direction = getMovesFromPJ(PJ.get(i), parent);
			for(int j = 0; j < direction.size();j++){
				
				ArrayList<Point> tmpPJ = new ArrayList<Point>();
				for(int a = 0; a < PJ.size();a++){
					if(i==a){
						tmpPJ.add(new Point(direction.get(j)));
					}
					else{
						tmpPJ.add(new Point(parent.getPJ().get(a).x, parent.getPJ().get(a).y));	
					}
					
				}
				int count = parent.getCount()+1;
				Noeud next = new Noeud(parent, tmpPJ, count);
				childrens.add(next);
				
			}
		}
		addToopenList(childrens);
	}


	
	public void addToopenList(ArrayList<Noeud> children){
		for(int i = 0; i < children.size();i++){
			this.openList.add(children.get(i));
		}
	}
	
	
	public ArrayList<Point> getMovesFromPJ(Point p, Noeud parent){
		ArrayList<Point> allowedMoves = new ArrayList<Point>();
		for(int i = 0; i < 4;i++){
			Point k = copy.movedir(p,i,parent.getPJ());
			if(!k.equals(p)){
				allowedMoves.add(k);
			}
		}
		return allowedMoves;
	}
	
	public ArrayList<Point> replaceDuplicate(ArrayList<Point> ar, Point p){
		ArrayList<Point> replace = new ArrayList<Point>();
		for(int i = 0; i < ar.size();i++){
			if(p == null){replace.add(null);}
			else if(p.equals(ar.get(i))){
				replace.add(null);
			}
			else{
				replace.add(ar.get(i));
			}
		}
		return replace;
	}
	
	public void addDistanceToNoeud(){
		int sum = 0;
		int x = 0;
		int y = 0;
		for(int i = 0; i < openList.size();i++){
			
				sum=0;
			
				if(this.openList.get(i).getCost() == 0){
					x = this.openList.get(i).getTarget().x;
					y = this.openList.get(i).getTarget().y;
					sum = (this.openList.get(i).getCount() + this.computedMap[x][y]);	
					this.openList.get(i).setCost(sum);					
				}
				
				if(this.openList.get(i).getAverageCost() == 0){
					for(int a = 0; a < openList.get(i).getPJ().size();a++){
						if(a != copy.searchPOIndice()){
							x = this.openList.get(i).getPJ().get(a).x;
							y = this.openList.get(i).getPJ().get(a).y;
							sum += (this.openList.get(i).getCount() + this.computedMap[x][y]);		
						}
						
					}
					this.openList.get(i).setAverageCost(sum/3);
					
				}

			}
		
	}
	
	public boolean isParentsHelp(Noeud n){
		int startcost=0;
		int endcost=0;
	
		Noeud x = n;
		
		while(x.getParent() != null){
			int starcost = computedMap[x.getTarget().x][x.getTarget().y];
			if(x.whoMoves() != copy.searchPOIndice()){
				if(x.getParent().getParent() != null){
					endcost = computedMap[x.getParent().getParent().getTarget().x][x.getParent().getParent().getTarget().y];
						if(startcost > endcost){
							return true;
					}
				}
				
			}
				
				x=x.getParent();
			}
	
		
		return false;
	}
		
			
		
	
	

	
	public boolean isInClosedList(Noeud n){
		for(int a = 0; a < closedList.size(); a++){
			if(n.equals(closedList.get(a))){
				return true;
			}
		}
		return false;
	}
	
	public Noeud shortest(){
		int tmp=100;
		int indice=-1;
		for(int i = 0; i < this.openList.size();i++){

			int cost = this.openList.get(i).getCost();
			
			
			
			if(!isInClosedList(this.openList.get(i)) && cost < tmp && this.openList.get(i).whoMoves() == whoIsTarget()){
				tmp = cost;
				indice = i;
			}
			
			
		}
		
		return this.openList.get(indice);
		
	}
	

	public void printListN(){
		ArrayList<Noeud> n = this.closedList;
		for(int i = 0; i < n.size(); i++){
			System.out.println("-------" + "PROFONDEUR : " + n.get(i).getCount() +"-------");
			for(int j = 0; j < n.get(i).getPJ().size(); j++){
				System.out.println(n.get(i).getPJ().get(j));
			}
		}
		System.out.println("----------------------");
		
		for(int k=0; k < n.size();k++){
			System.out.print(n.get(k).getCost() + ", ");
		}
		
	}
	
	public boolean checkOver(Noeud end){
		
		if(end.getPJByName(copy.searchPOIndice()).equals(copy.searchPO())){
			return true;
			
		}

		this.closedList.add(end);
		this.openList.remove(end);
		
		return false;
	}
	
	
	public void addPath(Noeud n){
		path = new ArrayList<Noeud>();
		while(n.getParent() != null){
			path.add(n);
			n=n.getParent();
		}
	}
	
	public ArrayList<ArrayList<Point>> getPath(){
		return this.pathPoint;
	}

	public void testing(){
		delineate();
		execute();
	}

	public void execute(){
		
		Noeud selected  = new Noeud(getBeginPJ(), whoIsTarget());
		makeChildrens(selected);
		addDistanceToNoeud();
		selected = shortest();

		int a = 0;
		while(!checkOver(selected)){
			System.out.println("PROFONDEUR : " + selected.getCount());
			System.out.println("COUT PRIMAIRE : " + selected.getCost());
			System.out.println("COUT MOYEN : " + selected.getAverageCost());
			System.out.println("SHORTEST : " + selected.getPJ());
			System.out.println("TAILLE OPEN  : " + openList.size());
			System.out.println("__________________________________");
			makeChildrens(selected);
			addDistanceToNoeud();
			selected = shortest();
			//a++;
			//if (a== 20){break;}
		}
		
		addPath(selected);
		Collections.reverse(this.path);
		for(int i = 0; i < path.size();i++){
			System.out.println(path.get(i).getPJ());
		}
	}
}



/*
public static ArrayList<String> trad(ArrayList<Case> array){
	Collections.reverse(array);
	ArrayList<String> res = new ArrayList<String>();
	for(int i = 0; i < array.size()-1; i++){
		Point a = copy.searchPO();
		Point b = copy.getPJPoint(copy.getPOColor());

		if(b.x > a.x && a.y == b.y){
			res.add("bas");
		}
		if(b.x < a.x && a.y == b.y){
			res.add("haut");
		}
		if(b.y > a.y && a.x == b.x){
			res.add("droite");
		}
		if(b.y < b.y && a.x == b.x){
			res.add("gauche");
		}
	}
	int finalSize = res.size();
	for(int i = 1; i < finalSize; i++){
		System.out.println(res.get(i-1) + ", " + res.get(i));
		if(res.get(i).equals("droite") && res.get(i-1).equals("gauche")){
			res.remove(i-1);
			i--;
			finalSize--;
		}
		if(res.get(i).equals("gauche") && res.get(i-1).equals("droite")){
			res.remove(i-1);
			i--;
			finalSize--;
		}
		if(res.get(i).equals("bas") && res.get(i-1).equals("haut")) {
			res.remove(i-1);
			i--;
			finalSize--;
		}
		if(res.get(i).equals("haut") && res.get(i-1).equals("bas")) {
			res.remove(i-1);
			i--;
			finalSize--;
		}
	}
	return res;
}
*/
