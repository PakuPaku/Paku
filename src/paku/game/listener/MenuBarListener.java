package paku.game.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import paku.game.frame.*;


public class MenuBarListener implements ActionListener {
	
	private IHMPaku ihm;
	
	public MenuBarListener(IHMPaku ihm){
		this.ihm=ihm;
	}

	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		System.out.println(">>>>"+action);
		
		if(action.equals("Ouvrir...")){
			ihm.openFile();
			
		}
		else if(action.equals("Nouveau")){
			ihm.newFile();
		}
		
		
		// TODO Auto-generated method stub
		
	}

}
