package paku.game.engine;

import java.util.ArrayList;
/**
 * 
 * @author zimon
 * Notre Žchantillon qui regroupe une liste de partie
 */
public class Sample {
	
	private ArrayList<Run> run;
	private int nbParties;
	
	
	public Sample (int nbParties){
		
		this.nbParties = nbParties;
		run = new ArrayList<Run>();
		for(int i=0; i<nbParties; i++){
			run.add(new Run());
		}
	}
	
	public int getNbPartie(){
		return nbParties;
	}
	
	public ArrayList<Run> getRuns(){
		return run;
	}
}
