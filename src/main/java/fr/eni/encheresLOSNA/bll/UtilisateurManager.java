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
	
	/**
	 * Methode en charge de retourner la seule instance de la classe.
	 * Singleton
	 * @return
	 */
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
	
	///////// Méthodes insert, update, selectById, selectAll, delete //////////
	
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
	 * @param id
	 * @return
	 * @throws BLLException
	 */
	public Utilisateur getUtilisateurById(int id) throws BLLException {
		Utilisateur u = null;
		try {
			u = utilisateurDAO.selectById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération utilisateur", e);
		}
		return u;
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
			throw new BLLException("Erreur récupération utilisateur", e);
		}
		return utilisateurs;
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
	
	///////// Fin des méthodes de base //////////
	
	/**
	 * Methode en charge de
	 * @param email
	 * @return
	 * @throws BLLException
	 */
	public Utilisateur getUtilisateurByEmail(String email) throws BLLException {
		Utilisateur u = null;
		try {
			u = utilisateurDAO.selectByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération utilisateur", e);
		}
		return u;
	}
	
	
	public void validerUtilisateur(Utilisateur u) throws BLLException
	{
		boolean valide = true;
		StringBuffer sb = new StringBuffer();
		
		if(u==null){
			throw new BLLException("Utilisateur null");
		}
		
		//Les attributs des utilisateur sont obligatoires
		if (!RegexVerification.isOnlyLettersAndNumbers(u.getPseudo())) {
			sb.append(RegexVerification.ONLY_LETTERS_AND_NUMBERS_DESCRIPTION + "\n");
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
		if (!RegexVerification.isValidEmail(u.getEmail())) {
			sb.append("L'email doit être valide.\n");
			valide = false;
		}
		if (!RegexVerification.isOnlyTenDigits(u.getTelephone())) {
			sb.append("Le numéro de téléphone doit contenir 10 chiffres.\n");
			valide = false;
		}
		if(u.getRue() == null || u.getRue().trim().length()==0){
			sb.append("La rue est obligatoire.\n");
			valide = false;
		}
		if (!RegexVerification.isOnlyFiveDigits(u.getCodePostal())) {
			sb.append("Le code postal doit contenir 5 chiffres.\n");
			valide = false;
		}
		if(u.getVille() == null || u.getVille().trim().length()==0){
			sb.append("La ville est obligatoire.\n");
			valide = false;
		}
		if (!RegexVerification.isValidPassword(u.getMotDePasse())) {
			sb.append(RegexVerification.VALID_PASSWORD_DESCRIPTION + "\n");
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
