/**
 * 
 */
package fr.eni.encheresLOSNA.dal;

import java.util.List;

import fr.eni.encheresLOSNA.bo.Enchere;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 2 juin 2021 - 12:02:07
 */
public interface EnchereDAO extends DAO<Enchere> {
	public void updateMontantEnchere (Integer nouveauMontant, Integer noUtilisateur, Integer noArticle) throws DALException;
	public List<Enchere> selectAllByNoUtilisateur(Integer noUtilisateur) throws DALException;
	public List<Enchere> selectAllByNoArticle(Integer noArticle) throws DALException;
}
