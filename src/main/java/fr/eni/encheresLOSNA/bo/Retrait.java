/**
 * 
 */
package fr.eni.encheresLOSNA.bo;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 1 juin 2021 - 09:47:55
 */
public class Retrait {
	private Integer noArticle;
	private String rue;
	private String codePostal;
	private String ville;
		
	private ArticleVendu article;

	/**
	 * Constructeur
	 */
	public Retrait() {
		super();
	}
	
	/**
	 * Constructeur
	 * @param noArticle
	 * @param rue
	 * @param codePostal
	 * @param ville
	 */
	public Retrait(Integer noArticle, String rue, String codePostal, String ville) {
		super();
		this.noArticle = noArticle;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
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
	 * @return the codePostal
	 */
	public String getCodePostal() {
		return codePostal;
	}

	/**
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}

	/**
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}

	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	/**
	 * @return the article
	 */
	public ArticleVendu getArticle() {
		return article;
	}

	/**
	 * @param article the article to set
	 */
	public void setArticle(ArticleVendu article) {
		this.article = article;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Retrait [noArticle=" + noArticle + ", rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville
				+ ", article=" + article + "]";
	}
	
	
}
