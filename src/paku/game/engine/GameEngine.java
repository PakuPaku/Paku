package paku.game.engine;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * 
 * @author zimon
 * L'interface du gros bonhomme de l'appli, interface car possibilit� de plusieurs impl�mentation du moteur.
 */
public interface GameEngine {
	public void initGame(Hashtable<String,String> args);
	public void play(Sample sample);
	public void generateDatas();

}
