/**
 * 
 */
package fr.eni.encheresLOSNA.bo;

import java.util.Date;
import java.util.Calendar;

import fr.eni.encheresLOSNA.bll.BLLException;
import fr.eni.encheresLOSNA.bll.UtilisateurManager;

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
	private Integer miseAPrix;
	private Integer prixVente;
	private String etatVente;
	private Integer noUtilisateur;
	private Integer noCategorie;
	
	private Utilisateur vendeur;

	
	public static final String ETAT_CREE = "Crée", ETAT_EN_COURS = "En cours", ETAT_ENCHERES_TERMINEES = "Enchères terminées", ETAT_RETRAIT_EFFECTUE = "Retrait effectué";
	
	/**
	 * Constructeur
	 */
	public ArticleVendu() {
		super();
	}
	
	/**
	 * Constructeur
	 * @param noArticle
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixVente
	 * @param noUtilisateur
	 * @param noCategorie
	 */
	public ArticleVendu(Integer noArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, Integer miseAPrix, Integer prixVente, Integer noUtilisateur,
			Integer noCategorie) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
		this.etatVente = verificationEtatVente(dateDebutEncheres, dateFinEncheres);
		this.vendeur = recuperationDuVendeur(noUtilisateur);
	}

	
	/** ArticleVendu Constructor
	 * @param nomArticle le nom de l'article
	 * @param description la description
	 * @param dateDebutEncheres la date de début des enchères
	 * @param dateFinEncheres la date de fin des enchères
	 * @param miseAPrix
	 * @param noUtilisateur
	 * @param noCategorie
	 */
	public ArticleVendu(String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres,
			Integer miseAPrix, Integer noUtilisateur, Integer noCategorie) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = null;
		this.etatVente = ETAT_CREE;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
		this.etatVente = verificationEtatVente(dateDebutEncheres, dateFinEncheres);
		this.vendeur = recuperationDuVendeur(noUtilisateur);
	}

	/**
	 * Methode en charge de
	 * @param noUtilisateur
	 * @return
	 */
	private Utilisateur recuperationDuVendeur(Integer noUtilisateur) {
		Utilisateur vendeur = null;
		try {
			vendeur = UtilisateurManager.getInstance().getUtilisateurById(noUtilisateur);
		} catch (BLLException e) {
			System.err.println("Echec de récupération du vendeur sur l'article.");
			e.printStackTrace();
		}
		return vendeur;
	}

	/**
	 * Methode en charge d'attribuer un état à l'attribut etatVente en fonction des dates de début et de fin d'enchère
	 * @param dateFinEncheres 
	 * @param dateDebutEncheres
	 * @return
	 */
	private String verificationEtatVente(Date dateDebutEncheres, Date dateFinEncheres) {
		String etatVente = null;
		Date maintenant = new Date(Calendar.getInstance().getTimeInMillis()); // Date.getTime()
		
		if (maintenant.before(dateDebutEncheres)) {
			etatVente = ETAT_CREE;
		} else if (maintenant.after(dateDebutEncheres) && maintenant.before(dateFinEncheres)) {
			etatVente = ETAT_EN_COURS;
		} else if (maintenant.after(dateFinEncheres)) {
			etatVente = ETAT_ENCHERES_TERMINEES;
		}
		
		return etatVente;
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
	public Integer getMiseAPrix() {
		return miseAPrix;
	}

	/**
	 * @param miseAPrix the miseAPrix to set
	 */
	public void setMiseAPrix(Integer miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	/**
	 * @return the prixVente
	 */
	public Integer getPrixVente() {
		return prixVente;
	}

	/**
	 * @param prixVente the prixVente to set
	 */
	public void setPrixVente(Integer prixVente) {
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
		
}
