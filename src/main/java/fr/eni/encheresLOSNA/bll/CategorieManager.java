/**
 * 
 */
package fr.eni.encheresLOSNA.bll;

import fr.eni.encheresLOSNA.dal.CategorieDAO;
import fr.eni.encheresLOSNA.dal.DAOFactory;

/**
 * @author Adrien
 *
 */
public class CategorieManager {

	
	private CategorieDAO categorieDAO;
	private static CategorieManager instance = null;
	
	public static CategorieManager getInstance() {
		if (instance == null) instance = new CategorieManager();
		return instance;
	}
	
	private CategorieManager() {
		this.categorieDAO = DAOFactory.getCategorieDAO();
	}
}
