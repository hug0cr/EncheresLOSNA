/**
 * 
 */
package fr.eni.encheresLOSNA.bo;

import java.sql.Date;
import java.util.List;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 1 juin 2021 - 09:48:47
 */
public class ArticleVendu {
	private Integer noArticle;
	private String nomArticle;
	private String description;
	private Date dateDebutEncheres;
	private Date dateFinEncheres;
	private float miseAPrix;
	private float prixVente;
	private String etatVente;
	
	//private Utilisateur acheteur;
	private Utilisateur vendeur;
	private Retrait lieuRetrait;
	private Categorie categorieArticle;
	private List<Enchere> listeEncheres;
	
	/**
	 * Constructeur
	 */
	public ArticleVendu() {
		super();
	}
	
	// Getters & Setters

	/**
	 * @return the noArticle
	 */
	public Integer getNoArticle() {
		return noArticle;
	}

	/**
	 * @param noArticle the noArticle to set
	 */
	public void setNoArticle(Integer noArticle) {
		this.noArticle = noArticle;
	}

	/**
	 * @return the nomArticle
	 */
	public String getNomArticle() {
		return nomArticle;
	}

	/**
	 * @param nomArticle the nomArticle to set
	 */
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the dateDebutEncheres
	 */
	public Date getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	/**
	 * @param dateDebutEncheres the dateDebutEncheres to set
	 */
	public void setDateDebutEncheres(Date dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	/**
	 * @return the dateFinEncheres
	 */
	public Date getDateFinEncheres() {
		return dateFinEncheres;
	}

	/**
	 * @param dateFinEncheres the dateFinEncheres to set
	 */
	public void setDateFinEncheres(Date dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	/**
	 * @return the miseAPrix
	 */
	public float getMiseAPrix() {
		return miseAPrix;
	}

	/**
	 * @param miseAPrix the miseAPrix to set
	 */
	public void setMiseAPrix(float miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	/**
	 * @return the prixVente
	 */
	public float getPrixVente() {
		return prixVente;
	}

	/**
	 * @param prixVente the prixVente to set
	 */
	public void setPrixVente(float prixVente) {
		this.prixVente = prixVente;
	}

	/**
	 * @return the etatVente
	 */
	public String getEtatVente() {
		return etatVente;
	}

	/**
	 * @param etatVente the etatVente to set
	 */
	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}

	/**
	 * @return the vendeur
	 */
	public Utilisateur getVendeur() {
		return vendeur;
	}

	/**
	 * @param vendeur the vendeur to set
	 */
	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}

	/**
	 * @return the lieuRetrait
	 */
	public Retrait getLieuRetrait() {
		return lieuRetrait;
	}

	/**
	 * @param lieuRetrait the lieuRetrait to set
	 */
	public void setLieuRetrait(Retrait lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	}

	/**
	 * @return the categorieArticle
	 */
	public Categorie getCategorieArticle() {
		return categorieArticle;
	}

	/**
	 * @param categorieArticle the categorieArticle to set
	 */
	public void setCategorieArticle(Categorie categorieArticle) {
		this.categorieArticle = categorieArticle;
	}

	/**
	 * @return the listeEncheres
	 */
	public List<Enchere> getListeEncheres() {
		return listeEncheres;
	}

	/**
	 * @param listeEncheres the listeEncheres to set
	 */
	public void setListeEncheres(List<Enchere> listeEncheres) {
		this.listeEncheres = listeEncheres;
	}
		
}
