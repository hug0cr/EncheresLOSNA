/**
 * 
 */
package fr.eni.encheresLOSNA.bll;

import fr.eni.encheresLOSNA.bo.EnchereRemportee;
import fr.eni.encheresLOSNA.dal.DALException;
import fr.eni.encheresLOSNA.dal.DAOFactory;
import fr.eni.encheresLOSNA.dal.EnchereRemporteeDAO;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 9 juin 2021 - 14:00:57
 */
public class EnchereRemporteeManager {
	private EnchereRemporteeDAO enchereRemporteeDAO;
	private static EnchereRemporteeManager instance = null;

	/**
	 * Methode en charge de retourner la seule instance de la classe.
	 * Singleton
	 * @return
	 */
	public static EnchereRemporteeManager getInstance() {
		if (instance == null) instance = new EnchereRemporteeManager();
		return instance;
	}

	private EnchereRemporteeManager() {
		this.enchereRemporteeDAO = DAOFactory.getEnchereRemporteeDAO();
	}
	
	///////// Méthodes insert //////////
	
	public void addEnchereRemportee(Integer noArticle, Integer noUtilisateur) throws BLLException {
		if (isEnchereRemportee(noArticle)) {
			throw new BLLException("Enchère déjà remportée.");
		}
		try {
			enchereRemporteeDAO.insert(new EnchereRemportee(noArticle, noUtilisateur));
		} catch (DALException e) {
			throw new BLLException("Echec addEnchereRemportee", e);
		}
	}
	
	///////// Méthode de vérification ///////////
	
	public boolean isEnchereRemportee(Integer noArticle) {
		boolean isEnchereRemportee = false;
		try {
			if(enchereRemporteeDAO.isEnchereRemportee(noArticle)) {
				isEnchereRemportee = true;
			} else {
				isEnchereRemportee = false;
			}
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		return isEnchereRemportee;
	}
}
