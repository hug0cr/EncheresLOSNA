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
	
	private final String SELECT_BY_NO_CATEGORIE = "SELECT "
			+ "no_article, "
			+ "nom_article, "
			+ "description, "
			+ "date_debut_encheres, "
			+ "date_fin_encheres, "
			+ "prix_initial, "
			+ "prix_vente, "
			+ "no_utilisateur, "
			+ "no_categorie "
			+ "FROM ARTICLES_VENDUS WHERE no_categorie=?;";
	
	private static final String SELECT_BY_KW = "SELECT "
			+ "no_article, "
			+ "nom_article, "
			+ "description, "
			+ "date_debut_encheres, "
			+ "date_fin_encheres, "
			+ "prix_initial, "
			+ "prix_vente, "
			+ "no_utilisateur, "
			+ "no_categorie "
			+ "FROM ARTICLES_VENDUS WHERE nom_article LIKE '%'+?+'%' OR description LIKE '%'+?+'%';";
	
	private static final String SELECT_ENCHERES_EN_COURS = "SELECT "
			+ "no_article, "
			+ "nom_article, "
			+ "description, "
			+ "date_debut_encheres, "
			+ "date_fin_encheres, "
			+ "prix_initial, "
			+ "prix_vente, "
			+ "no_utilisateur, "
			+ "no_categorie "
			+ "FROM ARTICLES_VENDUS WHERE date_debut_encheres < GETDATE() AND date_fin_encheres > GETDATE();";
	
	private static final String SELECT_ENCHERES_EN_COURS_D_UN_UTILISATEUR = "SELECT "
			+ "no_article, "
			+ "nom_article, "
			+ "description, "
			+ "date_debut_encheres, "
			+ "date_fin_encheres, "
			+ "prix_initial, "
			+ "prix_vente, "
			+ "no_utilisateur, "
			+ "no_categorie "
			+ "FROM ARTICLES_VENDUS WHERE date_debut_encheres < GETDATE() AND date_fin_encheres > GETDATE() AND no_utilisateur=?;";
	
	private static final String SELECT_ENCHERES_NON_COMMENCEE_D_UN_UTILISATEUR = "SELECT "
			+ "no_article, "
			+ "nom_article, "
			+ "description, "
			+ "date_debut_encheres, "
			+ "date_fin_encheres, "
			+ "prix_initial, "
			+ "prix_vente, "
			+ "no_utilisateur, "
			+ "no_categorie "
			+ "FROM ARTICLES_VENDUS WHERE date_debut_encheres > GETDATE() AND no_utilisateur=?;";
	
	private static final String SELECT_TOP_50 = "SELECT TOP (50) * FROM ARTICLES_VENDUS;";
	
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
	
	private final String UPDATE_PRIX_VENTE = "UPDATE ARTICLES_VENDUS SET prix_vente=? WHERE no_article=?;";
	
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
			stmt.setDate(3, new java.sql.Date(t.getDateDebutEncheres().getTime()));
			stmt.setDate(4, new java.sql.Date(t.getDateFinEncheres().getTime()));
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
			stmt.setDate(3, new java.sql.Date(t.getDateDebutEncheres().getTime()));
			stmt.setDate(4, new java.sql.Date(t.getDateFinEncheres().getTime()));
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
						rs.getString("description"), new java.util.Date(rs.getDate("date_debut_encheres").getTime()), 
						new java.util.Date(rs.getDate("date_fin_encheres").getTime()), rs.getInt("prix_initial"), 
						rs.getInt("prix_vente"), rs.getInt("no_utilisateur"), rs.getInt("no_categorie"));
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
						rs.getString("description"), new java.util.Date(rs.getDate("date_debut_encheres").getTime()), 
						new java.util.Date(rs.getDate("date_fin_encheres").getTime()), rs.getInt("prix_initial"), 
						rs.getInt("prix_vente"), rs.getInt("no_utilisateur"), rs.getInt("no_categorie"));			
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

	/**
	 * {@inheritDoc}
	 * @throws DALException 
	 */
	// TODO vérifier si la requête s'exécute correctement avec le %?% pour rechercher les descriptions
	@Override
	public List<ArticleVendu> selectByKW(String keyWord) throws DALException {
		List<ArticleVendu> listeArticlesVendus = new ArrayList<>();
		ArticleVendu a = null;
		
		try {
			con = ConnectionProvider.getConnection();
			PreparedStatement stmt = con.prepareStatement(SELECT_BY_KW);
			
			stmt.setString(1, keyWord);
			stmt.setString(2, keyWord);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				a = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"), 
						rs.getString("description"), new java.util.Date(rs.getDate("date_debut_encheres").getTime()), 
						new java.util.Date(rs.getDate("date_fin_encheres").getTime()), rs.getInt("prix_initial"), 
						rs.getInt("prix_vente"), rs.getInt("no_utilisateur"), rs.getInt("no_categorie"));			
				listeArticlesVendus.add(a);
			}
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DALException("Select by keyword error");
		}			
		return listeArticlesVendus;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ArticleVendu> selectEncheresEnCours() throws DALException {
		List<ArticleVendu> listeArticlesVendus = new ArrayList<>();
		ArticleVendu a = null;
		
		try {
			con = ConnectionProvider.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ENCHERES_EN_COURS);
			
			while (rs.next()) {
				a = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"), 
						rs.getString("description"), new java.util.Date(rs.getDate("date_debut_encheres").getTime()), 
						new java.util.Date(rs.getDate("date_fin_encheres").getTime()), rs.getInt("prix_initial"), 
						rs.getInt("prix_vente"), rs.getInt("no_utilisateur"), rs.getInt("no_categorie"));			
				listeArticlesVendus.add(a);
			}
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DALException("Select encheres en cours error");
		}			
		return listeArticlesVendus;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ArticleVendu> selectEncheresEnCoursDUnUtilisateur(Integer noUtilisateur) throws DALException {
		List<ArticleVendu> listeArticlesVendus = new ArrayList<>();
		ArticleVendu a = null;
		
		try {
			con = ConnectionProvider.getConnection();
			PreparedStatement stmt = con.prepareStatement(SELECT_ENCHERES_EN_COURS_D_UN_UTILISATEUR);
			
			stmt.setInt(1, noUtilisateur);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				a = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"), 
						rs.getString("description"), new java.util.Date(rs.getDate("date_debut_encheres").getTime()), 
						new java.util.Date(rs.getDate("date_fin_encheres").getTime()), rs.getInt("prix_initial"), 
						rs.getInt("prix_vente"), rs.getInt("no_utilisateur"), rs.getInt("no_categorie"));			
				listeArticlesVendus.add(a);
			}
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DALException("Select encheres en cours d'un utilisateur error");
		}			
		return listeArticlesVendus;
	}
	
	public List<ArticleVendu> selectEncheresNonCommenceeDUnUtilisateur(Integer noUtilisateur) throws DALException {
		List<ArticleVendu> listeArticlesVendus = new ArrayList<>();
		ArticleVendu a = null;
		
		try {
			con = ConnectionProvider.getConnection();
			PreparedStatement stmt = con.prepareStatement(SELECT_ENCHERES_NON_COMMENCEE_D_UN_UTILISATEUR);
			
			stmt.setInt(1, noUtilisateur);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				a = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"), 
						rs.getString("description"), new java.util.Date(rs.getDate("date_debut_encheres").getTime()), 
						new java.util.Date(rs.getDate("date_fin_encheres").getTime()), rs.getInt("prix_initial"), 
						rs.getInt("prix_vente"), rs.getInt("no_utilisateur"), rs.getInt("no_categorie"));			
				listeArticlesVendus.add(a);
			}
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DALException("Select encheres non commencées d'un utilisateur erreur");
		}			
		return listeArticlesVendus;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ArticleVendu> selectTop50() throws DALException {
		List<ArticleVendu> listeArticlesVendus = new ArrayList<>();
		ArticleVendu a = null;
		
		try {
			con = ConnectionProvider.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_TOP_50);
			
			while (rs.next()) {
				a = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"), 
						rs.getString("description"), new java.util.Date(rs.getDate("date_debut_encheres").getTime()), 
						new java.util.Date(rs.getDate("date_fin_encheres").getTime()), rs.getInt("prix_initial"), 
						rs.getInt("prix_vente"), rs.getInt("no_utilisateur"), rs.getInt("no_categorie"));			
				listeArticlesVendus.add(a);
			}
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DALException("Select top 50 Articles vendus error ");
		}			
		return listeArticlesVendus;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ArticleVendu> selectByCategorie(Integer noCategorie) throws DALException {
		List<ArticleVendu> listeArticlesVendus = new ArrayList<>();
		ArticleVendu a = null;
		
		try {
			con = ConnectionProvider.getConnection();
			PreparedStatement stmt = con.prepareStatement(SELECT_BY_NO_CATEGORIE);
			
			stmt.setInt(1, noCategorie);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				a = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"), 
						rs.getString("description"), new java.util.Date(rs.getDate("date_debut_encheres").getTime()), 
						new java.util.Date(rs.getDate("date_fin_encheres").getTime()), rs.getInt("prix_initial"), 
						rs.getInt("prix_vente"), rs.getInt("no_utilisateur"), rs.getInt("no_categorie"));			
				listeArticlesVendus.add(a);
			}
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DALException("Select by categorie error");
		}			
		return listeArticlesVendus;
	}
	
	/**
	 * {@inheritDoc}
	 * Update prix de vente d'un article
	 */
	public void updatePrixVente(Integer noArticle, Integer prixVente) throws DALException {
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			stmt = con.prepareStatement(UPDATE_PRIX_VENTE);
			
			stmt.setInt(1, prixVente);
			stmt.setInt(2, noArticle);
			
			int rowsAffected = stmt.executeUpdate();
			
			System.out.println(rowsAffected + " ligne modifiée");
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DALException("Update prix de vente de l'article error");
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
