/**
 * 
 */
package fr.eni.encheresLOSNA.dal.jdbc;

import java.util.List;

import fr.eni.encheresLOSNA.bo.ArticleVendu;
import fr.eni.encheresLOSNA.dal.ArticleVenduDAO;
import fr.eni.encheresLOSNA.dal.DAO;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 1 juin 2021 - 11:07:47
 */
public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insert(ArticleVendu t) {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(ArticleVendu t) {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ArticleVendu selectById(int id) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ArticleVendu> selectAll() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(ArticleVendu t) {
		
	}

	@Override
	public List<ArticleVendu> selectByKW(String kw) {
		return null;
	}

	@Override
	public List<ArticleVendu> selectByCategorie(String categorie) {
		return null;
	}

}
