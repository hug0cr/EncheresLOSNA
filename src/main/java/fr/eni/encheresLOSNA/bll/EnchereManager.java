/**
 * 
 */
package fr.eni.encheresLOSNA.bll;

import java.util.List;

import fr.eni.encheresLOSNA.bo.Enchere;
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
	
	public List<Enchere> getEncheresByKW(String keyWord){
		return this.enchereDAO.selectByKW(keyWord);
	}
	
	public List<Enchere> getEncheresByCategorie(String categorie){
		return this.enchereDAO.selectByCategorie(categorie);
	}
}
