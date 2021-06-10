/**
 * 
 */
package fr.eni.encheresLOSNA.bo;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 9 juin 2021 - 13:43:11
 */
public class EnchereRemportee {
	private Integer noArticle;
	private Integer noUtilisateur;
	/**
	 * Constructeur
	 * @param noArticle
	 * @param noUtilisateur
	 */
	public EnchereRemportee(Integer noArticle, Integer noUtilisateur) {
		super();
		this.noArticle = noArticle;
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
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "EnchereRemportee [noArticle=" + noArticle + ", noUtilisateur=" + noUtilisateur + "]";
	}
	
}
