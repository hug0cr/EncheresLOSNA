/**
 * 
 */
package fr.eni.encheresLOSNA.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.eni.encheresLOSNA.bo.EnchereRemportee;
import fr.eni.encheresLOSNA.dal.DALException;
import fr.eni.encheresLOSNA.dal.EnchereRemporteeDAO;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 9 juin 2021 - 13:46:13
 */
public class EnchereRemporteeDAOJdbcImpl implements EnchereRemporteeDAO {
	
	private final String INSERT = "INSERT INTO ENCHERES_REMPORTEES "
			+ "(no_article, "
			+ "no_utilisateur) "
			+ "VALUES (?, ?);";
	
	private final String SELECT_BY_NO_ARTICLE = "SELECT "
			+ "no_article, "
			+ "no_utilisateur "
			+ "FROM ENCHERES_REMPORTEES WHERE no_article=?;";
	
	private final String SELECT_BY_NO_UTILISATEUR = "SELECT "
			+ "no_article, "
			+ "no_utilisateur "
			+ "FROM ENCHERES_REMPORTEES WHERE no_utilisateur=?;";
	
	private static Connection con;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insert(EnchereRemportee t) throws DALException {
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			stmt = con.prepareStatement(INSERT);
			
			stmt.setInt(1, t.getNoArticle());
			stmt.setInt(2, t.getNoUtilisateur());

			stmt.executeUpdate();
			System.out.println("1 ligne insérée");
				
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DALException("Insert error");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(EnchereRemportee t) throws DALException {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EnchereRemportee selectById(int id) throws DALException {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EnchereRemportee> selectAll() throws DALException {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(EnchereRemportee t) throws DALException {

	}
	
	public EnchereRemportee selectByNoArticle(Integer noArticle) throws DALException {
		EnchereRemportee enchere = null;
		
		try {
			
			con = ConnectionProvider.getConnection();
			PreparedStatement stmt = con.prepareStatement(SELECT_BY_NO_ARTICLE);
			
			stmt.setInt(1, noArticle);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				enchere = new EnchereRemportee(rs.getInt("no_article"), rs.getInt("no_utilisateur"));
			}
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DALException("Select by noArticle error");
		}	
		return enchere;
	}
	
	public EnchereRemportee selectByNoUtilisateur(Integer noUtilisateur) throws DALException {
		EnchereRemportee enchere = null;
		
		try {
			
			con = ConnectionProvider.getConnection();
			PreparedStatement stmt = con.prepareStatement(SELECT_BY_NO_UTILISATEUR);
			
			stmt.setInt(1, noUtilisateur);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				enchere = new EnchereRemportee(rs.getInt("no_article"), rs.getInt("no_utilisateur"));
			}
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DALException("Select by noUtilisateur error");
		}	
		return enchere;
	}
	
	public boolean isEnchereRemportee(Integer noArticle) throws DALException {
		boolean isEnchereRemportee = false;
		
		try {
			
			con = ConnectionProvider.getConnection();
			PreparedStatement stmt = con.prepareStatement(SELECT_BY_NO_ARTICLE);
			
			stmt.setInt(1, noArticle);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				isEnchereRemportee = true;
			}
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DALException("isEnchereRemportee error");
		}	
		return isEnchereRemportee;
	}

}
