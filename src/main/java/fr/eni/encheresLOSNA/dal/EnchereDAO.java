/**
 * 
 */
package fr.eni.encheresLOSNA.dal;

import java.util.List;

import fr.eni.encheresLOSNA.bo.Enchere;

/**
 * @author Adrien
 *
 */
public interface EnchereDAO {

	public List<Enchere> selectByKW(String kw);
	public List<Enchere> selectByCategorie(String categorie);
}
