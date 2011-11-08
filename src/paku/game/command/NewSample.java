package paku.game.command;

import java.util.ArrayList;
import java.util.Hashtable;

import paku.game.engine.GameEngine;
import paku.game.frame.NewFileFrame;

/**
 * 
 * @author zimon
 *Class NewSample, correspond à la commande "Nouvelle Partie, se charge de récupérer les parametres
 *de la nouvelle partie et fournit tout ca au moteur
 */
public class NewSample implements Command{
	
	public static final String NAME = "NewSample";
	private NewFileFrame ihm;
	private GameEngine engine;
	private Hashtable<String,String> args;
	
	public NewSample(GameEngine engine){
		this.engine = engine;
		
		
	}
	public void execute(){
		//Hey ho, file moi les parametres!
		args = ihm.getParams();
		
		//Cool merci, tiens l'engine, amuse toi!
		engine.initGame(args);
		
		
	}
	
	public void setIHM(NewFileFrame ihm){
		this.ihm = ihm;
	}

}
