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
	
	/**
	 * Methode en charge de retourner la seule instance de la classe.
	 * Singleton
	 * @return
	 */
	public static RetraitManager getInstance() {
		if (instance == null) instance = new RetraitManager();
		return instance;
	}
	
	/**
	 * Constructeur privé
	 * @throws BLLException
	 */
	private RetraitManager() {
		retraitDAO = DAOFactory.getRetraitDAO();
	}
	
	///////// Méthodes insert, update, selectById, selectAll, delete //////////
	
	/**
	 * Methode en charge d'ajouter un retrait dans la base de données.
	 * @param newRetrait le retrait à ajouter
	 * @throws BLLException
	 */
	public void addRetrait(Retrait newRetrait) throws BLLException {
		if(newRetrait.getNoArticle() != null) {
			throw new BLLException("Retrait déjà existant.");
		}
		try {
			validerRetrait(newRetrait);
			retraitDAO.insert(newRetrait);
		} catch (DALException e) {
			throw new BLLException("Echec addRetrait", e);
		}
	}
	
	/**
	 * Methode en charge de mettre à jour un retrait dans la base de données.
	 * @param retrait de type Retrait à modifier.
	 * @throws BLLException
	 */
	public void updateRetrait(Retrait retrait) throws BLLException {
		try {
			validerRetrait(retrait);
			retraitDAO.update(retrait);
			
		} catch (DALException e) {
			throw new BLLException("Echec updateRetrait-retrait:" + retrait, e);
		}
	}
	
	/**
	 * Methode en charge de retourner un objet Retrait delon un id
	 * @param id du retrait.
	 * @return
	 * @throws BLLException
	 */
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

	/**
	 * Methode en charge de retourner une liste de tout les retraits de la base de données.
	 * @return
	 * @throws BLLException
	 */
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

	/**
	 * Methode en charge de supprimer un retrait de la base de données.
	 * @param retrait
	 * @throws BLLException
	 */
	public void removeRetrait(Retrait retrait) throws BLLException{
		try {
			retraitDAO.delete(retrait);
		} catch (DALException e) {
			throw new BLLException("Echec de la suppression du retrait - ", e);
		}	
	}
	
	///////// Fin des méthodes de base //////////
	
	/**
	 * Methode en charge de vérifier les informations d'un objet Retrait.
	 * @param r
	 * @throws BLLException
	 */
	public void validerRetrait(Retrait r) throws BLLException
	{
		boolean valide = true;
		StringBuffer sb = new StringBuffer();
		
		if(r==null){
			throw new BLLException("Retrait null");
		}
		
		//Les attributs des retraits sont obligatoires
		if (r.getNoArticle() == null) {
			sb.append("Un numéro d'article est obligatoire \n");
			valide = false;
		}
		if(r.getRue() == null || r.getRue().trim().length()==0) {
			sb.append("Une rue est obligatoire.\n");
			valide = false;
		}
		if(!RegexVerification.isOnlyFiveDigits(r.getCodePostal())){
			sb.append("Le code postal doit contenir 5 chiffres.\n");
			valide = false;
		}
		if (r.getVille() == null || r.getVille().trim().length()==0) {
			sb.append("Une ville est obligatoire.\n");
			valide = false;
		}
		if(!valide){
			throw new BLLException(sb.toString());
		}

	}
}
