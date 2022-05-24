package view;

import java.awt.Graphics;

import javax.swing.JPanel;

import control.GameController;

public class HallOfFame extends GamePanel{
	
	private static final long serialVersionUID = 1L;	
	private int xVariable = this.getWidth()/2 + 260;
	private int yVariable = this.getHeight()+60;
	private GameController gc;

	
	public HallOfFame(GameController gc) {
		super(gc);
		this.gc=gc;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int x = this.getWidth()/2 - 50;
		int y = this.getHeight()/25;		
		g.drawString("Hall Of Fame", x, y);
		for(int i=0; i<gc.getModel().getPlayerCatalogue().getNumOfPlayers(); i++) {
			g.drawString(gc.getModel().getPlayerCatalogue().getPlayer(i),xVariable,yVariable+20*i);
		}
	}

}
