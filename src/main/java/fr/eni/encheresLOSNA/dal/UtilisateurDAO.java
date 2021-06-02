/**
 * 
 */
package fr.eni.encheresLOSNA.dal;

import fr.eni.encheresLOSNA.bo.Utilisateur;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 1 juin 2021 - 16:16:11
 */

// TODO rajouter select by mot clé ?
public interface UtilisateurDAO extends DAO<Utilisateur> {
	public Utilisateur selectByEmail(String email) throws DALException;
}
