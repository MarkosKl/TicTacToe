package control;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
	
	/*While quitting, we store our data to a document*/
	public void quit() {		
		System.out.println("bye bye...");
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("tuctactoe.ser"));
			int counter = this.getModel().getPlayerRoster().countArrayElements(this.getModel().getPlayerRoster().getPlayers())+1;
			for(int i = 0; i <= counter; i++)
			outputStream.writeObject(this.getModel().getPlayerRoster().getPlayer(i));
			outputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	
	public void selectPlayer(String p, int pos) {
		this.model.selectPlayer(p, pos);	
		System.out.println("Player " + pos + " set to " + p);
		this.view.getTopPanel().getStartBtn().setEnabled(model.ready());
	}
	
	/*when we start the game we retrieve the data from the saved file */
	public void startGame() {
		this.model.setGameBoard(new char[3][3]);
		this.view.getTopPanel().getStartBtn().setEnabled(false);
		this.view.getMainPanel().showCard(MainAreaPanel.BOARD);
		if(this.view.getLeftPanel().getCurrentPlayer().equals("Hal") && this.view.getRightPanel().getCurrentPlayer() != "Hal") {//vlepei an o PerfectAI einai aristera etsi wste na paiksei prwtos
			this.model.selectPerfectAi();
		}
		if(this.view.getLeftPanel().getCurrentPlayer().equals("MrBean") && this.view.getRightPanel().getCurrentPlayer() != "MrBean") {//vlepei an o BadAI einai aristera etsi wste na paiksei prwtos
			this.model.selectRandomAi();
		}
		this.view.getLeftPanel().getSelectPlayerBtn().setEnabled(model.NoPlay());
		this.view.getRightPanel().getSelectPlayerBtn().setEnabled(model.NoPlay());
		this.view.getTopPanel().getAddPlayerBtn().setEnabled(model.NoPlay());
		this.model.BothAiPlaying();
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
	public void enableDone() {
		String winner = getModel().checkForWinner(getModel().getGameBoard());
		String tie = getModel().checkForTie(getModel().getGameBoard());
		if((getModel().NoPlay() && winner != "") || (getModel().NoPlay() && tie != "")) {
			view.getTopPanel().getDoneBtn().setEnabled(true);
		}
	}
	
	//Shows the winner in a new window
	public void showWinner() {
		String winner = getModel().checkForWinner(getModel().getGameBoard());
		String tie = getModel().checkForTie(getModel().getGameBoard());
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
		
		if(tie == "TIE") {
			JOptionPane pane2 = new JOptionPane();
			JOptionPane.showConfirmDialog(pane2, "Game Over. "+ tie + "","Game over.",
					JOptionPane.OK_CANCEL_OPTION);
		}
	}
}