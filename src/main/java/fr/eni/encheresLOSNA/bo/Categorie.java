/**
 * 
 */
package fr.eni.encheresLOSNA.bo;

import java.util.List;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 1 juin 2021 - 09:47:20
 */
public class Categorie {
	private int noCategorie;
	private String libelle;
	
	private List<ArticleVendu> listeArticles;

	/**
	 * Constructeur
	 */
	public Categorie() {
		super();
	}

	// Getters & Setters
	
	/**
	 * @return the noCategorie
	 */
	public int getNoCategorie() {
		return noCategorie;
	}

	/**
	 * @param noCategorie the noCategorie to set
	 */
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * @return the listeAriticles
	 */
	public List<ArticleVendu> getListeAriticles() {
		return listeArticles;
	}

	/**
	 * @param listeAriticles the listeAriticles to set
	 */
	public void setListeAriticles(List<ArticleVendu> listeAriticles) {
		this.listeArticles = listeAriticles;
	}
	
	
}
