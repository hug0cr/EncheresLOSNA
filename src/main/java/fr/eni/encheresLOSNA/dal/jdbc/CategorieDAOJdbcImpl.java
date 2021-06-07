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

import fr.eni.encheresLOSNA.bo.Categorie;
import fr.eni.encheresLOSNA.dal.CategorieDAO;
import fr.eni.encheresLOSNA.dal.DALException;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 1 juin 2021 - 11:09:37
 */
public class CategorieDAOJdbcImpl implements CategorieDAO {

	private final String INSERT = "INSERT INTO CATEGORIES "
			+ "(libelle) "
			+ "VALUES (?);";
	
	private final String SELECT_BY_ID = "SELECT "
			+ "no_categorie, "
			+ "libelle "
			+ "FROM CATEGORIES WHERE no_categorie=?;";
	
	private final String UPDATE = "UPDATE CATEGORIES SET "
			+ "libelle=? "
			+ "WHERE no_categorie=?;";
	
	private final String DELETE = "DELETE FROM CATEGORIES WHERE no_categorie=?;";
	
	private final String SELECT_ALL = "SELECT * FROM CATEGORIES;";
	
	private static Connection con;
	
	/**
	 * {@inheritDoc}
	 * @throws DALException 
	 */
	@Override
	public void insert(Categorie t) throws DALException {
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			stmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, t.getLibelle());
			
			int rowsAffected = stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				t.setNoCategorie(rs.getInt(1));
				System.out.println(rowsAffected + " ligne(s) insérée(s) " + rs.getInt(1));
			}
				
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
	public void update(Categorie t) throws DALException {
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			stmt = con.prepareStatement(UPDATE);
			
			stmt.setString(1, t.getLibelle());
			stmt.setInt(2, t.getNoCategorie());
			
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
	public Categorie selectById(int id) throws DALException {
		Categorie c = null;
		
		try {
			
			con = ConnectionProvider.getConnection();
			PreparedStatement stmt = con.prepareStatement(SELECT_BY_ID);
			
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {	
				c = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
			}
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DALException("Select by ID error");
		}	
		return c;
	}

	/**
	 * {@inheritDoc}
	 * @throws DALException 
	 */
	@Override
	public List<Categorie> selectAll() throws DALException {
		List<Categorie> listeCategories = new ArrayList<>();
		Categorie c = null;
		
		try {
			con = ConnectionProvider.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			
			while (rs.next()) {
				c = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));			
				listeCategories.add(c);
			}
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DALException("Select all error");
		}			
		return listeCategories;
	}

	/**
	 * {@inheritDoc}
	 * @throws DALException 
	 */
	@Override
	public void delete(Categorie t) throws DALException {
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			stmt = con.prepareStatement(DELETE);
			stmt.setInt(1, t.getNoCategorie());
			
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
