/**
 * 
 */
package fr.eni.encheresLOSNA.bo;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 1 juin 2021 - 09:47:20
 */
public class Categorie {
	private Integer noCategorie;
	private String libelle;
	
	//private List<ArticleVendu> listeArticles;

	/**
	 * Constructeur
	 */
	public Categorie() {
		super();
	}
	
	/**
	 * Constructeur
	 * @param libelle
	 */
	public Categorie(String libelle) {
		super();
		this.libelle = libelle;
	}

	/**
	 * Constructeur
	 * @param noCategorie
	 * @param libelle
	 */
	public Categorie(Integer noCategorie, String libelle) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}

	// Getters & Setters

	/**
	 * @return the noCategorie
	 */
	public Integer getNoCategorie() {
		return noCategorie;
	}

	/**
	 * @param noCategorie the noCategorie to set
	 */
	public void setNoCategorie(Integer noCategorie) {
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

//	/**
//	 * @return the listeAriticles
//	 */
//	public List<ArticleVendu> getListeAriticles() {
//		return listeArticles;
//	}
//
//	/**
//	 * @param listeAriticles the listeAriticles to set
//	 */
//	public void setListeAriticles(List<ArticleVendu> listeAriticles) {
//		this.listeArticles = listeAriticles;
//	}
	
	
}
