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
public class EnchereDAOJdbcImpl implements DAO<Enchere>, EnchereDAO {

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

	@Override
	public List<Enchere> selectByKW(String kw) {
		return null;
	}

	@Override
	public List<Enchere> selectByCategorie(String categorie) {
		return null;
	}

}
