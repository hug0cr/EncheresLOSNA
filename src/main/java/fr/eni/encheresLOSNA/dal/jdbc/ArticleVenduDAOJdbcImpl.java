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

import fr.eni.encheresLOSNA.bo.ArticleVendu;
import fr.eni.encheresLOSNA.dal.ArticleVenduDAO;
import fr.eni.encheresLOSNA.dal.DALException;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 1 juin 2021 - 11:07:47
 */
public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {
	
	private final String INSERT = "INSERT INTO ARTICLES_VENDUS "
			+ "(nom_article, "
			+ "description, "
			+ "date_debut_encheres, "
			+ "date_fin_encheres, "
			+ "prix_initial, "
			+ "prix_vente, "
			+ "no_utilisateur, "
			+ "no_categorie) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	
	private final String SELECT_BY_ID = "SELECT "
			+ "no_article, "
			+ "nom_article, "
			+ "description, "
			+ "date_debut_encheres, "
			+ "date_fin_encheres, "
			+ "prix_initial, "
			+ "prix_vente, "
			+ "no_utilisateur, "
			+ "no_categorie "
			+ "FROM ARTICLES_VENDUS WHERE no_article=?;";
	
	private final String UPDATE = "UPDATE ARTICLES_VENDUS SET "
			+ "nom_article=?, "
			+ "nom_article=?, "
			+ "description=?, "
			+ "date_debut_encheres=?, "
			+ "date_fin_encheres=?, "
			+ "prix_initial=?, "
			+ "prix_vente=?, "
			+ "no_utilisateur=?, "
			+ "no_categorie=? "
			+ "WHERE no_article=?;";
	
	private final String DELETE = "DELETE FROM ARTICLES_VENDUS WHERE no_article=?;";
	
	private final String SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS;";
	
	private static Connection con;
	
	/**
	 * {@inheritDoc}
	 * @throws DALException 
	 */
	@Override
	public void insert(ArticleVendu t) throws DALException {
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			stmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, t.getNomArticle());
			stmt.setString(2, t.getDescription());
			stmt.setDate(3, t.getDateDebutEncheres());
			stmt.setDate(4, t.getDateFinEncheres());
			stmt.setFloat(5, t.getMiseAPrix());
			stmt.setFloat(6, t.getPrixVente());
			stmt.setInt(7, t.getNoUtilisateur());
			stmt.setInt(8, t.getNoCategorie());
			
			int rowsAffected = stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				t.setNoArticle(rs.getInt(1));
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
	public void update(ArticleVendu t) throws DALException {
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			stmt = con.prepareStatement(UPDATE);
			
			stmt.setString(1, t.getNomArticle());
			stmt.setString(2, t.getDescription());
			stmt.setDate(3, t.getDateDebutEncheres());
			stmt.setDate(4, t.getDateFinEncheres());
			stmt.setFloat(5, t.getMiseAPrix());
			stmt.setFloat(6, t.getPrixVente());
			stmt.setInt(7, t.getNoUtilisateur());
			stmt.setInt(8, t.getNoCategorie());
			stmt.setInt(9, t.getNoArticle());
			
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
	public ArticleVendu selectById(int id) throws DALException {
		ArticleVendu a = null;
		
		try {
			
			con = ConnectionProvider.getConnection();
			PreparedStatement stmt = con.prepareStatement(SELECT_BY_ID);
			
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				a = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"), 
						rs.getString("description"), rs.getDate("date_debut_encheres"), 
						rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"), 
						rs.getInt("prix_vente"), rs.getString("etat_vente"),
						rs.getInt("no_utilisateur"), rs.getInt("no_categorie"));
			}
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DALException("Select by ID error");
		}	
		return a;
	}

	/**
	 * {@inheritDoc}
	 * @throws DALException 
	 */
	@Override
	public List<ArticleVendu> selectAll() throws DALException {
		List<ArticleVendu> listeArticlesVendus = new ArrayList<>();
		ArticleVendu a = null;
		
		try {
			con = ConnectionProvider.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			
			while (rs.next()) {
				a = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"), 
				rs.getString("description"), rs.getDate("date_debut_encheres"), 
				rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"), 
				rs.getInt("prix_vente"), rs.getString("etat_vente"),
				rs.getInt("no_utilisateur"), rs.getInt("no_categorie"));			
				listeArticlesVendus.add(a);
			}
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DALException("Select all error");
		}			
		return listeArticlesVendus;
	}

	/**
	 * {@inheritDoc}
	 * @throws DALException 
	 */
	@Override
	public void delete(ArticleVendu t) throws DALException {
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
