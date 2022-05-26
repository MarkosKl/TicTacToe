package control;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import model.GameModel;
import model.PlayerRoster;
import view.MainAreaPanel;
import view.MainWindow;

public class GameController extends WindowAdapter {
	MainWindow view;
	GameModel model;
	
	public GameController() {		
		
	}
	
	@Override
	public void windowClosing(WindowEvent event) {
		quit();
	}
	
	
	public void start() {
		this.view= new MainWindow(this);
		this.model = new GameModel(this);
		this.view.addWindowListener(this);
		this.view.setVisible(true);
	}
	
	public void quit() {		
		System.out.println("bye bye...");		
		System.exit(0);
	}
	
	
	public void selectPlayer(String p, int pos) {
		this.model.selectPlayer(p, pos);	
		System.out.println("Player " + pos + " set to " + p);
		this.view.getTopPanel().getStartBtn().setEnabled(model.ready());
	}
	
	public void startGame() {
		this.model.setGameBoard(new String[3][3]);
		this.view.getTopPanel().getStartBtn().setEnabled(false);
		this.view.getMainPanel().showCard(MainAreaPanel.BOARD);
		this.view.getLeftPanel().getSelectPlayerBtn().setEnabled(model.NoPlay());
		this.view.getRightPanel().getSelectPlayerBtn().setEnabled(model.NoPlay());
	}
	
	public GameModel getModel() {
		return model;
	}
	
	public MainWindow getView() {
		return view;
	}
	
	//Enables the Done button after the game ends
	public void EnableDone() {
		String winner = getModel().checkWinner(getModel().getGameBoard());
		if((getModel().NoPlay() && winner != "")) {
			view.getTopPanel().getDoneBtn().setEnabled(true);
		}
	}
	
	//Shows the winner in a new window
	public void showWinner() {
		String winner = getModel().checkWinner(getModel().getGameBoard());
		if(winner == "X wins") {
			JOptionPane pane = new JOptionPane();
			JOptionPane.showConfirmDialog(pane, "Game Over. "+ winner + "","Game over.",
					JOptionPane.OK_CANCEL_OPTION);	
		}
		else if(winner == "O wins") {
			JOptionPane pane1 = new JOptionPane();
			JOptionPane.showConfirmDialog(pane1, "Game Over. "+ winner + "","Game over.",
					JOptionPane.OK_CANCEL_OPTION);
		}
		
		if(winner == "TIE") {
			JOptionPane pane2 = new JOptionPane();
			JOptionPane.showConfirmDialog(pane2, "Game Over. "+ winner + "","Game over.",
					JOptionPane.OK_CANCEL_OPTION);
		}
	}
}
