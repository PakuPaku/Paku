package paku.game.engine;

import java.util.ArrayList;

import paku.game.meshes.Meshes;
/**
 * 
 * @author zimon
 * Class Map, la map comme l'a pense Flavien, une matrice d'entier
 * on lui associe une liste de Meshes, pour ceux qui connaissent les archis jeux video il s'agit des objets statiques
 * de la map
 */
public class Map {
	private int [][] map;
	private int nbMeshes;
	private ArrayList<Meshes> meshes;
	
	public Map(int x, int y){
		map = new int [x][y];
	}
	
	public void initMeshes(int nbMeshes){
		
	}
	public Map getInstance() {
		return this;
	}

	public int getNbMeshes() {
		return nbMeshes;
	}

	public void setNbMeshes(int nbMeshes) {
		this.nbMeshes = nbMeshes;
	}

	public ArrayList<Meshes> getMeshes() {
		return meshes;
	}

	public void setMeshes(ArrayList<Meshes> meshes) {
		this.meshes = meshes;
	}

}
