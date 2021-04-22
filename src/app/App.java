package app;
import iniz.*;
import inteligence.Algorithms;
import graphic.*;


import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class App extends JFrame implements ActionListener, MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PionJoueur selectedPJ;
	public Maps m;
	
	public App(Maps m){
		super("Ricochet Robot");
		setSize(new Dimension(1280,960));
		this.m=m;
		
		Container cp = this.getContentPane();
		DisplayGraphics DP = new DisplayGraphics(m);
		JPanel panelDownA = new JPanel();
		JPanel panelDownB = new JPanel();
		JPanel panelDown = new JPanel();
		JPanel panelUpA = new JPanel();
		JPanel panelUpB = new JPanel();
		JPanel panelUp = new JPanel();
		
		cp.setLayout(new BorderLayout());
		
		panelUp.add(panelUpA);
		panelUp.add(panelUpB);
		panelDown.add(panelDownA);
		panelDown.add(panelDownB);
		
		cp.add(DP, BorderLayout.CENTER);
		cp.add(panelUp,BorderLayout.NORTH);
		cp.add(panelDown,BorderLayout.SOUTH);
		panelUp.setLayout(new GridLayout(2,1));
		panelUpA.setLayout(new GridLayout(0,4));
		panelDown.setLayout(new GridLayout(2,1));
		panelDownB.setLayout(new GridLayout(0,2));
		panelDownA.setLayout(new GridLayout(0,4));
		
		JButton updatePO = new JButton("Actualiser");
		JButton solve = new JButton("Résoudre");
		
		JButton blue = new JButton("Bleu");
		JButton red = new JButton("Rouge");
		JButton green = new JButton("Vert");
		JButton yellow = new JButton("Jaune");
		
	
		JButton up = new JButton("↑");
		JButton down = new JButton("↓");
		JButton left = new JButton("←");
		JButton right = new JButton("→");		
		JLabel downText = new JLabel("Vous avez selectionner le pion de couleur ");
		
		
		blue.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				downText.setText("Vous avez selectionner le pion de couleur bleu");
				selectedPJ = m.getModele().getPJ("blue");
			}
		});
		red.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				downText.setText("Vous avez selectionner le pion de couleur rouge");
				selectedPJ = m.getModele().getPJ("red");	
			}
		});
		green.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				downText.setText("Vous avez selectionner le pion de couleur vert");
				selectedPJ = m.getModele().getPJ("green");
			}
		});
		yellow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				downText.setText("Vous avez selectionner le pion de couleur jaune");
				selectedPJ = m.getModele().getPJ("yellow");
								
			}
		});
		
		
		solve.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Algorithms algo = new Algorithms(m.copy());
				algo.testing();
				DP.setPath(algo.getPath());
				DP.updateGraphicsMaps(m);
				
			}
		});
		
		updatePO.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				m.changeMaps();
				DP.updateGraphicsMaps(m);
			}
		});
		

		up.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(selectedPJ != null){
					selectedPJ.setCoord(m.movedir(selectedPJ.getCoord(), 0, m.getModele().getPJPoints()));
				if(m.isOver()){
					m.changeMaps();
				}
				DP.updateGraphicsMaps(m);
				}
			}
		});
		down.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(selectedPJ != null){
					selectedPJ.setCoord(m.movedir(selectedPJ.getCoord(), 2, m.getModele().getPJPoints()));
				}
				if(m.isOver()){
					m.changeMaps();
				}
				DP.updateGraphicsMaps(m);
			
			}
		});
		left.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(selectedPJ != null){
					selectedPJ.setCoord(m.movedir(selectedPJ.getCoord(), 3, m.getModele().getPJPoints()));
				}
				if(m.isOver()){
					m.changeMaps();
				}
				DP.updateGraphicsMaps(m);
				
			}
		});
		right.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(selectedPJ != null){
					selectedPJ.setCoord(m.movedir(selectedPJ.getCoord(), 1, m.getModele().getPJPoints()));
				}
				if(m.isOver()){
					m.changeMaps();
				}
				DP.updateGraphicsMaps(m);
				
			}
		});
		
		panelDownA.add(left);
		panelDownA.add(up);
		panelDownA.add(down);
		panelDownA.add(right);
		panelDownB.add(updatePO);
		panelDownB.add(solve);
		panelUpA.add(yellow);
		panelUpA.add(red);
		panelUpA.add(green);
		panelUpA.add(blue);
		panelUpB.add(downText);

		pack();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public PionJoueur getselectedPJ(){return selectedPJ;}
	
	public void actionPerformed(ActionEvent e){}
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
