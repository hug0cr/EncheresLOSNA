/**
 * 
 */
package fr.eni.encheresLOSNA.bll;

import java.util.List;

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
			throw new BLLException("Erreur récupération utilisateur", e);
		}
		return encheres;
	}
	
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

}
