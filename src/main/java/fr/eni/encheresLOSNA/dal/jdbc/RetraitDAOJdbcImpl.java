/**
 * 
 */
package fr.eni.encheresLOSNA.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheresLOSNA.bo.Retrait;
import fr.eni.encheresLOSNA.dal.DALException;
import fr.eni.encheresLOSNA.dal.RetraitDAO;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 1 juin 2021 - 11:06:33
 */
public class RetraitDAOJdbcImpl implements RetraitDAO {
	
	private final String INSERT = "INSERT INTO RETRAITS "
			+ "(no_article, "
			+ "rue, "
			+ "code_postal, "
			+ "ville) "
			+ "VALUES (?, ?, ?, ?);";
	
	private final String SELECT_BY_NO_ARTICLE = "SELECT "
			+ "no_article, "
			+ "rue, "
			+ "code_postal, "
			+ "ville "
			+ "FROM RETRAITS WHERE no_article=?;";
	
	private final String UPDATE = "UPDATE RETRAITS SET "
			+ "no_article=?, "
			+ "rue=?, "
			+ "code_postal=?, "
			+ "ville=? "
			+ "WHERE no_article=?";
	
	private final String DELETE = "DELETE FROM RETRAITS WHERE no_article=?;";
	
	private final String SELECT_ALL = "SELECT * FROM RETRAITS;";
	
	private static Connection con;

	/**
	 * {@inheritDoc}
	 * @throws DALException 
	 */
	@Override
	public void insert(Retrait t) throws DALException {
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			stmt = con.prepareStatement(INSERT);
			
			stmt.setInt(1, t.getNoArticle());
			stmt.setString(2, t.getRue());
			stmt.setString(3, t.getCodePostal());
			stmt.setString(4, t.getVille());
				
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
	 * @throws DALException 
	 */
	@Override
	public void update(Retrait t) throws DALException {
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			stmt = con.prepareStatement(UPDATE);
			
			stmt.setInt(1, t.getNoArticle());
			stmt.setString(2, t.getRue());
			stmt.setString(3, t.getCodePostal());
			stmt.setString(4, t.getVille());
			stmt.setInt(5, t.getNoArticle());
			
			int rowsAffected = stmt.executeUpdate();
			
			System.out.println(rowsAffected + " ligne modifiée");
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DALException("Update error");
		} catch (Exception e) {
			e.getMessage();
		}	
	}

	/**
	 * {@inheritDoc}
	 * @throws DALException 
	 */
	@Override
	public Retrait selectById(int id) throws DALException {
		Retrait r = null;
		
		try {
			con = ConnectionProvider.getConnection();
			PreparedStatement stmt = con.prepareStatement(SELECT_BY_NO_ARTICLE);
			
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {				
				r = new Retrait(rs.getInt("no_article"), rs.getString("rue"), 
						rs.getString("code_postal"), rs.getString("ville"));
			}
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DALException("Select by ID error");
		}	
		return r;
	}

	/**
	 * {@inheritDoc}
	 * @throws DALException 
	 */
	@Override
	public List<Retrait> selectAll() throws DALException {
		List<Retrait> listeRetraits = new ArrayList<>();
		Retrait r = null;
		
		try {
			con = ConnectionProvider.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			
			while (rs.next()) {
				r = new Retrait(rs.getInt("no_article"), rs.getString("rue"), 
						rs.getString("code_postal"), rs.getString("ville"));			
				listeRetraits.add(r);
			}
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DALException("Select all error");
		}			
		return listeRetraits;
	}

	/**
	 * {@inheritDoc}
	 * @throws DALException 
	 */
	@Override
	public void delete(Retrait t) throws DALException {
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			stmt = con.prepareStatement(DELETE);
			stmt.setInt(1, t.getNoArticle());
			
			int rowsAffected = stmt.executeUpdate();
			
			System.out.println(rowsAffected + " ligne supprimée");
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DALException("Delete error");
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
