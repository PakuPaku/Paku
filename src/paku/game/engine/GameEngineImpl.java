package paku.game.engine;

import java.util.ArrayList;
import java.util.Hashtable;

import paku.game.player.Actor;
import paku.game.player.SimplePaku;

/**
 * 
 * @author zimon
 * Implémentation du moteur, c'est lui l'oracle et le médiateur de tout ce bordel, il connait tout sur tout à n'importe quel moment
 * et la majorité des communications se font par son intermédiaire. Voyez ca comme le Systeme  
 */
public class GameEngineImpl implements GameEngine {
	
	private Map map;
	private ArrayList<Actor> players;
	private int nbPlayers;
	private Sample sample;
	
	
	public void initGame(Hashtable<String,String> params) {
		
		System.out.println(params.get("Dimension"));
		System.out.println(params.get("nbPaku"));
		System.out.println(params.get("nbPartie"));
		
		//init map
		int dimensionGrille = Integer.parseInt(params.get("Dimension"));
		map = new Map(dimensionGrille,dimensionGrille);
				
		//init players
		players = new ArrayList<Actor>();
		nbPlayers = Integer.parseInt(params.get("nbPaku"));
		
		for(int i=0;i<nbPlayers;i++){
			players.add(new SimplePaku());
		}
		
		//init sample
		int nbParties = Integer.parseInt(params.get("nbPartie"));
		sample = new Sample(nbParties);
		
		//play(sample);
		
		
	}


	public void play(Sample sample) {
		
		//Jouer toutes les parties de l'echantillon
		for(int i=0; i<sample.getNbPartie();i++){
			
			//exemple...
			double seed = Math.random();
			sample.getRuns().get(i).start(seed);
			generateDatas();
		}
		
		
	}

	public void generateDatas() {
		// TODO Auto-generated method stub
		
	}

	

}
