package paku.game.game;

import java.util.Hashtable;

import paku.game.engine.GameEngine;
import paku.game.engine.GameEngineImpl;
import paku.game.frame.IHMPaku;
import paku.game.command.*;

/**
 * 
 * @author zimon
 * Launcher, enregistre les commandes, instancie le moteur, l'IHM, bref, le point d'entrée
 */
public class Game {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		GameEngine engine = new GameEngineImpl();
		Hashtable<String,Command> commands = new Hashtable<String,Command>();
		
		commands.put(NewSample.NAME, new NewSample(engine));
		
		IHMPaku ihm = new IHMPaku(commands, engine);
		ihm.setVisible(true);
		
		// TODO Auto-generated method stub

	}

}
