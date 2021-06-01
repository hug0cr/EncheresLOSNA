/**
 * 
 */
package fr.eni.encheresLOSNA.bo;

import java.sql.Date;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 1 juin 2021 - 09:45:58
 */
public class Enchere {
	private Date dateEnchere;
	private float montantEnchere;
	
	private Utilisateur encherisseur;

	/**
	 * Constructeur
	 */
	public Enchere() {
		super();
	}
	
	//Getters & Setters

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
	public float getMontantEnchere() {
		return montantEnchere;
	}

	/**
	 * @param montantEnchere the montantEnchere to set
	 */
	public void setMontantEnchere(float montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	/**
	 * @return the encherisseur
	 */
	public Utilisateur getEncherisseur() {
		return encherisseur;
	}

	/**
	 * @param encherisseur the encherisseur to set
	 */
	public void setEncherisseur(Utilisateur encherisseur) {
		this.encherisseur = encherisseur;
	}
	
	
}
