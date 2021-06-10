/**
 * 
 */
package fr.eni.encheresLOSNA.dal;

import fr.eni.encheresLOSNA.bo.EnchereRemportee;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 9 juin 2021 - 13:44:19
 */
public interface EnchereRemporteeDAO extends DAO<EnchereRemportee> {
	public EnchereRemportee selectByNoArticle(Integer noArticle) throws DALException;
	public EnchereRemportee selectByNoUtilisateur(Integer noUtilisateur) throws DALException;
	public boolean isEnchereRemportee(Integer noArticle) throws DALException;
}
