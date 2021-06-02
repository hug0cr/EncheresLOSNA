/**
 * 
 */
package fr.eni.encheresLOSNA.dal.jdbc;

import java.util.List;

import fr.eni.encheresLOSNA.bo.Enchere;
import fr.eni.encheresLOSNA.dal.DAO;
import fr.eni.encheresLOSNA.dal.EnchereDAO;


/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 1 juin 2021 - 11:04:17
 */
public class EnchereDAOJdbcImpl implements EnchereDAO {
	//TODO continuer ici
	private static final String SELECT_BY_CATEGORIE = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES WHERE marque = ?";
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insert(Enchere t) {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Enchere t) {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enchere selectById(int id) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Enchere> selectAll() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Enchere t) {
		
	}

}
