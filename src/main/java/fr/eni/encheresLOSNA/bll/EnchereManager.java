/**
 * 
 */
package fr.eni.encheresLOSNA.bll;

import java.util.List;

import fr.eni.encheresLOSNA.bo.ArticleVendu;
import fr.eni.encheresLOSNA.bo.Enchere;
import fr.eni.encheresLOSNA.dal.DALException;
import fr.eni.encheresLOSNA.dal.DAOFactory;
import fr.eni.encheresLOSNA.dal.EnchereDAO;

/**
 * @author Adrien
 *
 */
public class EnchereManager {
	
	private EnchereDAO enchereDAO;

	private static EnchereManager instance = null;
	
	public static EnchereManager getInstance() {
		if (instance == null) instance = new EnchereManager();
		return instance;
	}
	
	private EnchereManager() {
		this.enchereDAO = DAOFactory.getEnchereDAO();
	}
	
	///////// Méthodes insert, update, selectById, selectAll, delete //////////
	

	/**
	 * Methode en charge de
	 * @param newEnchere
	 * @throws BLLException
	 */
	public void addEnchere(Enchere newEnchere, ArticleVendu article) throws BLLException {
		Integer montantActuelMax = null;
		
		try {
			montantActuelMax = enchereDAO.selectMaxMontantByNoArticle(newEnchere);
		} catch (DALException e1) {
			System.err.println("AddEnchere - Erreur sur la récupération du montant max");
		}
		
		if (montantActuelMax == null || montantActuelMax < newEnchere.getMontantEnchere() && article.getMiseAPrix() < newEnchere.getMontantEnchere()) {
			try {
				if (enchereDAO.isAlreadyCreated(newEnchere)) {
					System.out.println("Enchère déjà crée, mise à jour de l'ancienne");
					updateMontantEnchere(newEnchere.getMontantEnchere(), newEnchere.getNoUtilisateur(), newEnchere.getNoArticle());
				} else {
					System.out.println("Nouvelle enchère, ajout à la BDD");
					validerEnchere(newEnchere);
					enchereDAO.insert(newEnchere);
				}
			} catch (DALException e) {
				System.err.println("Echec de la vérification isAlreadyCreated");
			}
		}
	}
	
	/**
	 * Methode en charge de
	 * @param enchere
	 * @throws BLLException
	 */
	public void updateEnchere(Enchere enchere) throws BLLException {
		try {
			validerEnchere(enchere);
			enchereDAO.update(enchere);
			
		} catch (DALException e) {
			throw new BLLException("Echec updateEnchere : " + enchere, e);
		}
	}
	
	/**
	 * Methode en charge de
	 * @return
	 * @throws BLLException
	 */
	public List<Enchere> getEncheres() throws BLLException {
		List<Enchere> encheres = null;
		try {
			encheres = enchereDAO.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération enchère - ", e);
		}
		return encheres;
	}
	
	/**
	 * Methode en charge de
	 * @param enchere
	 * @throws BLLException
	 */
	public void removeEnchere(Enchere enchere) throws BLLException{
		try {
			enchereDAO.delete(enchere);
		} catch (DALException e) {
			throw new BLLException("Echec de la suppression de l'enchère - ", e);
		}	
	}
	
	
	///////// Fin des méthodes de base //////////
	
	/**
	 * Methode en charge de
	 * @return
	 * @throws BLLException
	 */
	public void updateMontantEnchere(Integer nouveauMontant, Integer noUtilisateur, Integer noArticle) throws BLLException {
		try {
			enchereDAO.updateMontantEnchere(nouveauMontant, noUtilisateur, noArticle);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur d'update sur le montant de l'enchère", e);
		}
	}
	
	/**
	 * Methode en charge de
	 * @param noUtilisateur
	 * @return
	 * @throws BLLException
	 */
	public List<Enchere> getAllByNoUtilisateur(Integer noUtilisateur) throws BLLException {
		List<Enchere> encheres = null;
		try {
			encheres = enchereDAO.selectAllByNoUtilisateur(noUtilisateur);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération des enchères par noUtilisateur", e);
		}
		return encheres;
	}
	
	public List<Enchere> getAllByNoArticle(Integer noArticle) throws BLLException {
		List<Enchere> encheres = null;
		try {
			encheres = enchereDAO.selectAllByNoUtilisateur(noArticle);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération des enchères par noArticle", e);
		}
		return encheres;
	}
	
	public Integer getMaxMontantByNoArticle(Enchere enchere) throws BLLException {
		Integer maxMontant = null;
		try {
			maxMontant = enchereDAO.selectMaxMontantByNoArticle(enchere);
		} catch (DALException e) {
			throw new BLLException("Erreur récupération montant max d'un article", e);
		}
		return maxMontant;
	}
	
	
	/**
	 * Methode en charge de
	 * @param enchere
	 * @throws BLLException 
	 */
	private void validerEnchere(Enchere enchere) throws BLLException {
		boolean valide = true;
		StringBuffer sb = new StringBuffer();
		
		if(enchere==null){
			throw new BLLException("Enchère null");
		}
		
		//Les attributs des utilisateur sont obligatoires
		if(enchere.getNoUtilisateur() == null) {
			sb.append("Le no utilisateur est obligatoire.\n");
			valide = false;
		}
		if(enchere.getNoArticle() == null) {
			sb.append("Un no article est obligatoire.\n");
			valide = false;
		}
		if(enchere.getMontantEnchere() == null){
			sb.append("Un montant pour l'enchère est obligatoire.\n");
			valide = false;
		}
		
		if(!valide){
			throw new BLLException(sb.toString());
		}
	}

}
