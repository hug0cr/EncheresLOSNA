/**
 * 
 */
package fr.eni.encheresLOSNA.bo;

import java.util.Date;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 1 juin 2021 - 09:45:58
 */
public class Enchere {
	private Integer noUtilisateur;
	private Integer noArticle;
	private Date dateEnchere;
	private Integer montantEnchere;
	
	//private Utilisateur encherisseur;

	/**
	 * Constructeur
	 */
	public Enchere() {
		super();
	}
	
	/**
	 * Constructeur
	 * @param noUtilisateur
	 * @param noArticle
	 * @param dateEnchere
	 * @param montantEnchere
	 */
	public Enchere(Integer noUtilisateur, Integer noArticle, Date dateEnchere, Integer montantEnchere) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.noArticle = noArticle;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}

	/**
	 * Constructeur
	 * @param noUtilisateur
	 * @param noArticle
	 * @param montantEnchere
	 */
	public Enchere(Integer noUtilisateur, Integer noArticle, Integer montantEnchere) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.noArticle = noArticle;
		this.montantEnchere = montantEnchere;
	}

	/**
	 * @return the noUtilisateur
	 */
	public Integer getNoUtilisateur() {
		return noUtilisateur;
	}

	/**
	 * @param noUtilisateur the noUtilisateur to set
	 */
	public void setNoUtilisateur(Integer noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

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
	 * @return the dateEnchere
	 */
	public Date getDateEnchere() {
		return dateEnchere;
	}

	/**
	 * @param dateEnchere the dateEnchere to set
	 */
	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	/**
	 * @return the montantEnchere
	 */
	public Integer getMontantEnchere() {
		return montantEnchere;
	}

	/**
	 * @param montantEnchere the montantEnchere to set
	 */
	public void setMontantEnchere(Integer montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Enchere [noUtilisateur=" + noUtilisateur + ", noArticle=" + noArticle + ", dateEnchere=" + dateEnchere
				+ ", montantEnchere=" + montantEnchere + "]";
	}
	
	//Getters & Setters

	
}
