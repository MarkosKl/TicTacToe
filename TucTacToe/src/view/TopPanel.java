package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import control.GameController;

public class TopPanel extends GamePanel{	

	private static final long serialVersionUID = 1L;
	JButton quitBtn;
	JButton startGameBtn;
	JButton doneBtn;
	JButton addPlayerBtn;
	
	public TopPanel (GameController gc) {
		super(gc);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setPreferredSize(new Dimension(MainWindow.WIDTH,MainWindow.TOP_HEIGHT));
		this.setBorder(new LineBorder(Color.GRAY,1,true));
		quitBtn = new JButton("Quit App");	
		quitBtn.setPreferredSize(new Dimension(100, 40));
		quitBtn.addActionListener((e)->{this.gc.quit();});		
		
		startGameBtn = new JButton("Start Game");
		startGameBtn.setPreferredSize(new Dimension(100, 40));
		startGameBtn.setEnabled(false);
		startGameBtn.addActionListener((e)->{this.gc.startGame();});
		
		doneBtn = new JButton("Done");		
		doneBtn.setPreferredSize(new Dimension(100, 40));		
		doneBtn.setEnabled(false);
		doneBtn.addActionListener((e)->{gc.getView().getMainPanel().showCard(MainAreaPanel.HOF);});
		doneBtn.addActionListener((e)->{this.getDoneBtn().setEnabled(false);});
		/* So it won't show Player Name and Stats when Done button is pressed */
		doneBtn.addActionListener((e)->{gc.getView().getLeftPanel().getPlName().setVisible(false);});
		doneBtn.addActionListener((e)->{gc.getView().getRightPanel().getPlName().setVisible(false);});
		doneBtn.addActionListener((e)->{gc.getView().getLeftPanel().getPlStats().setVisible(false);});
		doneBtn.addActionListener((e)->{gc.getView().getRightPanel().getPlStats().setVisible(false);});
		/*To be able to choose player after Done is pressed */
		doneBtn.addActionListener((e)->{gc.getView().getLeftPanel().getSelectPlayerBtn().setEnabled(true);});
		doneBtn.addActionListener((e)->{gc.getView().getRightPanel().getSelectPlayerBtn().setEnabled(true);});
		doneBtn.addActionListener((e)->{this.getAddPlayerBtn().setEnabled(true);});

		/* When pressed AddPlayer is called */
		addPlayerBtn = new JButton("Add Player");
		addPlayerBtn.setPreferredSize(new Dimension(100, 40));		
		addPlayerBtn.setEnabled(true);
		addPlayerBtn.addActionListener((e)->{gc.getModel().getPlayerRoster().addPlayer();});
		
		add(startGameBtn);
		add(doneBtn);
		add(quitBtn);	
		add(addPlayerBtn);
	}
	
	

	public JButton getAddPlayerBtn() {
		return addPlayerBtn;
	}



	public void setAddPlayerBtn(JButton addPlayerBtn) {
		this.addPlayerBtn = addPlayerBtn;
	}



	public JButton getQuitBtn() {
		return quitBtn;
	}

	public JButton getStartBtn() {
		return startGameBtn;
	}
	

	public JButton getDoneBtn() {
		return doneBtn;
	}	
	
}
