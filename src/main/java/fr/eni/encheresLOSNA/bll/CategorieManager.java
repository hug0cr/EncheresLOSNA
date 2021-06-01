/**
 * 
 */
package fr.eni.encheresLOSNA.bll;

import fr.eni.encheresLOSNA.bo.Categorie;
import fr.eni.encheresLOSNA.dal.DAO;
import fr.eni.encheresLOSNA.dal.DAOFactory;

/**
 * @author Adrien
 *
 */
public class CategorieManager {

	private DAO<Categorie> categorieDAO;
	private static CategorieManager instance = null;
	
	public static CategorieManager getInstance() {
		if (instance == null) instance = new CategorieManager();
		return instance;
	}
	
	private CategorieManager() {
		this.categorieDAO = DAOFactory.getCategorieDAO();
	}
}
