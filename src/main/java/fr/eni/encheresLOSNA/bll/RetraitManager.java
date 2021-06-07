/**
 * 
 */
package fr.eni.encheresLOSNA.bll;

import java.util.List;

import fr.eni.encheresLOSNA.bo.Retrait;
import fr.eni.encheresLOSNA.dal.DALException;
import fr.eni.encheresLOSNA.dal.DAOFactory;
import fr.eni.encheresLOSNA.dal.RetraitDAO;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 7 juin 2021 - 10:14:30
 */
public class RetraitManager {
	private static RetraitManager instance;
	private static RetraitDAO retraitDAO;
	
	public static RetraitManager getInstance() {
		if (instance == null) instance = new RetraitManager();
		return instance;
	}
	
	/**
	 * Constructeur
	 * @throws BLLException
	 */
	private RetraitManager() {
		retraitDAO = DAOFactory.getRetraitDAO();
	}
	
	///////// Méthodes insert, update, selectById, selectAll, delete //////////
	
	public void addRetrait(Retrait newRetrait) throws BLLException {
		if(newRetrait.getNoArticle() != null) {
			throw new BLLException("Retrait déjà existant.");
		}
		try {
			//validerRetrait(newRetrait);
			retraitDAO.insert(newRetrait);
		} catch (DALException e) {
			throw new BLLException("Echec addRetrait", e);
		}
	}
	
	public void updateRetrait(Retrait retrait) throws BLLException {
		try {
			//validerRetrait(retrait);
			retraitDAO.update(retrait);
			
		} catch (DALException e) {
			throw new BLLException("Echec updateRetrait-retrait:" + retrait, e);
		}
	}
	
	public Retrait getRetraitById(int id) throws BLLException {
		Retrait r = null;
		try {
			r = retraitDAO.selectById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération retrait", e);
		}
		return r;
	}

	public List<Retrait> getRetraits() throws BLLException {
		List<Retrait> retraits = null;
		try {
			retraits = retraitDAO.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération des retraits", e);
		}
		return retraits;
	}

	public void removeArticleVendu(Retrait retrait) throws BLLException{
		try {
			retraitDAO.delete(retrait);
		} catch (DALException e) {
			throw new BLLException("Echec de la suppression du retrait - ", e);
		}	
	}
	
	///////// Fin des méthodes de base //////////
}
