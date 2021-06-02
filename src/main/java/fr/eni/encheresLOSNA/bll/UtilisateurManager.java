/**
 * 
 */
package fr.eni.encheresLOSNA.bll;

import java.util.List;

import fr.eni.encheresLOSNA.bo.Utilisateur;
import fr.eni.encheresLOSNA.dal.DALException;
import fr.eni.encheresLOSNA.dal.DAOFactory;
import fr.eni.encheresLOSNA.dal.UtilisateurDAO;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 1 juin 2021 - 15:15:53
 */
public class UtilisateurManager {
	
	private static UtilisateurManager instance;
	private static UtilisateurDAO utilisateurDAO;
	
	public static UtilisateurManager getInstance() {
		if (instance == null) instance = new UtilisateurManager();
		return instance;
	}
	
	/**
	 * Constructeur
	 * @throws BLLException
	 */
	private UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	/**
	 * Methode en charge de
	 * @return
	 * @throws BLLException
	 */
	public List<Utilisateur> getUtilisateurs() throws BLLException {
		List<Utilisateur> utilisateurs = null;
		try {
			utilisateurs = utilisateurDAO.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération catalogue", e);
		}
		return utilisateurs;
	}
	
	/**
	 * Methode en charge de
	 * @param newUtilisateur
	 * @throws BLLException
	 */
	public void addUtilisateur(Utilisateur newUtilisateur) throws BLLException {
		if(newUtilisateur.getNoUtilisateur() != null) {
			throw new BLLException("Utilisateur déjà existant.");
		}
		try {
			validerUtilisateur(newUtilisateur);
			utilisateurDAO.insert(newUtilisateur);
		} catch (DALException e) {
			throw new BLLException("Echec addArticle", e);
		}
	}
	
	/**
	 * Methode en charge de
	 * @param utilisateur
	 * @throws BLLException
	 */
	public void updateUtilisateur(Utilisateur utilisateur) throws BLLException {
		try {
			validerUtilisateur(utilisateur);
			utilisateurDAO.update(utilisateur);
			
		} catch (DALException e) {
			throw new BLLException("Echec updateUtilisateur-utilisateur:"+utilisateur, e);
		}
	}
	
	/**
	 * Methode en charge de
	 * @param utilisateur
	 * @throws BLLException
	 */
	public void removeUtilisateur(Utilisateur utilisateur) throws BLLException{
		try {
			utilisateurDAO.delete(utilisateur);
		} catch (DALException e) {
			throw new BLLException("Echec de la suppression de l'utilisateur - ", e);
		}	
	}
	
	public void validerUtilisateur(Utilisateur u) throws BLLException
	{
		boolean valide = true;
		StringBuffer sb = new StringBuffer();
		
		if(u==null){
			throw new BLLException("Utilisateur null");
		}
		
		//Les attributs des utilisateur sont obligatoires
		if(u.getPseudo() == null || u.getPseudo().trim().length()==0) {
			sb.append("Le pseudo de l'utilisateur est obligatoire.\n");
			valide = false;
		}
		if(u.getPrenom() == null || u.getPrenom().trim().length()==0) {
			sb.append("Le prénom  est obligatoire.\n");
			valide = false;
		}
		if(u.getNom() == null || u.getNom().trim().length()==0){
			sb.append("Le nom est obligatoire.\n");
			valide = false;
		}
		if(u.getEmail() == null || u.getEmail().trim().length()==0){
			sb.append("L'email est obligatoire.\n");
			valide = false;
		}
		if(u.getTelephone() == null || u.getTelephone().trim().length()==0){
			sb.append("Le numéro de téléphone est obligatoire.\n");
			valide = false;
		}
		if(u.getRue() == null || u.getRue().trim().length()==0){
			sb.append("La rue est obligatoire.\n");
			valide = false;
		}
		if(u.getCodePostal() == null || u.getCodePostal().trim().length()==0){
			sb.append("Le code postal est obligatoire.\n");
			valide = false;
		}
		if(u.getVille() == null || u.getVille().trim().length()==0){
			sb.append("La ville est obligatoire.\n");
			valide = false;
		}
		if(u.getMotDePasse() == null || u.getMotDePasse().trim().length()==0){
			sb.append("Le mot de passe est obligatoire.\n");
			valide = false;
		}
		if(u.getCredit() == null){
			sb.append("Un montant pour le crédit est obligatoire.\n");
			valide = false;
		}
		if(u.isAdministrateur() == null){
			sb.append("Est un admin ? est obligatoire.\n");
			valide = false;
		}
		
		if(!valide){
			throw new BLLException(sb.toString());
		}

	}
}
