/**
 * 
 */
package fr.eni.encheresLOSNA.bll;

import java.util.List;

import fr.eni.encheresLOSNA.bo.Categorie;
import fr.eni.encheresLOSNA.dal.CategorieDAO;
import fr.eni.encheresLOSNA.dal.DALException;
import fr.eni.encheresLOSNA.dal.DAOFactory;

/**
 * @author Adrien
 *
 */
public class CategorieManager {

	private CategorieDAO categorieDAO;
	private static CategorieManager instance = null;

	/**
	 * Methode en charge de retourner la seule instance de la classe.
	 * Singleton
	 * @return
	 */
	public static CategorieManager getInstance() {
		if (instance == null) instance = new CategorieManager();
		return instance;
	}

	private CategorieManager() {
		this.categorieDAO = DAOFactory.getCategorieDAO();
	}

	///////// Méthodes insert, update, selectById, selectAll, delete //////////

	/**
	 * Methode en charge d'ajouter une catégorie à la base de données.
	 * @param newCategorie
	 * @throws BLLException
	 */
	public void addCategorie(Categorie newCategorie) throws BLLException {
		if(newCategorie.getNoCategorie() != null) {
			throw new BLLException("Catégorie déjà existant.");
		}
		try {
			validerCategorie(newCategorie);
			categorieDAO.insert(newCategorie);
		} catch (DALException e) {
			throw new BLLException("Echec addCategorie", e);
		}
	}

	/**
	 * Methode en charge de mettre à jour une catégorie dans la base de données.
	 * @param categorie
	 * @throws BLLException
	 */
	public void updateCategorie(Categorie categorie) throws BLLException {
		try {
			validerCategorie(categorie);
			categorieDAO.update(categorie);

		} catch (DALException e) {
			throw new BLLException("Echec updateCategorie-categorie:" + categorie, e);
		}
	}

	/**
	 * Methode en charge de retourner un objet Categorie selin son id.
	 * @param id de la catégorie
	 * @return
	 * @throws BLLException
	 */
	public Categorie getCategorieById(int id) throws BLLException {
		Categorie c = null;
		try {
			c = categorieDAO.selectById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération catégorie", e);
		}
		return c;
	}

	/**
	 * Methode en charge de retourner la liste de toutes les catégories dans la base de données.
	 * @return
	 * @throws BLLException
	 */
	public List<Categorie> getCategories() throws BLLException {
		List<Categorie> categories = null;
		try {
			categories = categorieDAO.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération des catégories", e);
		}
		return categories;
	}


	/**
	 * Methode en charge de supprimer une catégorie de la base de données
	 * @param categorie
	 * @throws BLLException
	 */
	public void removeCategorie(Categorie categorie) throws BLLException{
		try {
			categorieDAO.delete(categorie);
		} catch (DALException e) {
			throw new BLLException("Echec de la suppression de la catégorie - ", e);
		}	
	}
	
	///////// Fin des méthodes de base //////////

	/**
	 * Methode en charge de valider les informations d'un objet Categorie.
	 * @param c
	 * @throws BLLException
	 */
	public void validerCategorie(Categorie c) throws BLLException
	{
		boolean valide = true;
		StringBuffer sb = new StringBuffer();

		if(c == null){
			throw new BLLException("Catégorie null");
		}
		if(c.getLibelle() == null || c.getLibelle().trim().length()==0) {
			sb.append("Le libelle de la catégorie est obligatoire.\n");
			valide = false;
		}

		if(!valide){
			throw new BLLException(sb.toString());
		}
	}
}
