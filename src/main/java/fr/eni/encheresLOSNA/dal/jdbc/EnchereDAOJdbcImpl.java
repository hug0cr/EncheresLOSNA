/**
 * 
 */
package fr.eni.encheresLOSNA.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheresLOSNA.bo.Enchere;
import fr.eni.encheresLOSNA.dal.DALException;
import fr.eni.encheresLOSNA.dal.EnchereDAO;


/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 1 juin 2021 - 11:04:17
 */
public class EnchereDAOJdbcImpl implements EnchereDAO {
	
	private final String INSERT = "INSERT INTO ENCHERES "
			+ "(no_utilisateur, "
			+ "no_article, "
			+ "date_enchere, "
			+ "montant_enchere) "
			+ "VALUES (?, ?, ?, ?);";
	
	private final String SELECT_ALL_BY_NO_UTILISATEUR = "SELECT * "
			+ "no_utilisateur, "
			+ "no_article, "
			+ "date_enchere, "
			+ "montant_enchere "
			+ "FROM ENCHERES WHERE no_utilisateur=?;";
	
	private final String SELECT_ALL_BY_NO_ARTICLE = "SELECT * "
			+ "no_utilisateur, "
			+ "no_article, "
			+ "date_enchere, "
			+ "montant_enchere "
			+ "FROM UTILISATEURS WHERE no_article=?;";
	
	private final String SELECT_MAX_MONTANT_BY_ARTICLE = "SELECT MAX(montant_enchere) FROM ENCHERES WHERE no_article = ?;";
	
	private final String UPDATE = "UPDATE ENCHERES SET "
			+ "no_utilisateur=?, "
			+ "no_article=?, "
			+ "date_enchere=?, "
			+ "montant_enchere=? "
			+ "WHERE no_utilisateur=?;";
	
	private final String UPDATE_MONTANT_ENCHERE = "UPDATE ENCHERES SET date_enchere=?, montant_enchere=? WHERE no_utilisateur=? AND no_article=?";
	
	private final String DELETE = "DELETE FROM ENCHERES WHERE no_utilisateur=? AND no_article=?;";
	
	private final String SELECT_ALL = "SELECT * FROM ENCHERES;";
	
	private final String SELECT_NO_UTILISATEUR_NO_ARTICLE = "SELECT * FROM ENCHERES WHERE no_utilisateur=? AND no_article=?;";
	
	private static Connection con;
	
	/**
	 * {@inheritDoc}
	 * @throws DALException 
	 */
	@Override
	public void insert(Enchere t) throws DALException {
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			stmt = con.prepareStatement(INSERT);
			
			stmt.setInt(1, t.getNoUtilisateur());
			stmt.setInt(2, t.getNoArticle());
			stmt.setTimestamp(3, Timestamp.from(Instant.now()));
			stmt.setInt(4, t.getMontantEnchere());

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
	 * @throws DALException 
	 */
	@Override
	public void update(Enchere t) throws DALException {
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			stmt = con.prepareStatement(UPDATE);
			
			stmt.setInt(1, t.getNoUtilisateur());
			stmt.setInt(2, t.getNoArticle());
			stmt.setTimestamp(3, Timestamp.from(Instant.now()));
			stmt.setInt(4, t.getMontantEnchere());
			stmt.setInt(5, t.getNoUtilisateur());
			
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
	 */
	@Override
	public Enchere selectById(int id) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @throws DALException 
	 */
	@Override
	public List<Enchere> selectAll() throws DALException {
		List<Enchere> listeEncheres = new ArrayList<>();
		Enchere enchere = null;
		
		try {
			con = ConnectionProvider.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			
			while (rs.next()) {
				enchere = new Enchere(rs.getInt("no_utilisateur"), rs.getInt("no_article"), 
						rs.getDate("date_enchere"), rs.getInt("montant_enchere"));			
				listeEncheres.add(enchere);
			}
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DALException("Select all error");
		}			
		return listeEncheres;
	}

	/**
	 * {@inheritDoc}
	 * @throws DALException 
	 */
	@Override
	public void delete(Enchere t) throws DALException {
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			stmt = con.prepareStatement(DELETE);
			stmt.setInt(1, t.getNoUtilisateur());
			stmt.setInt(2, t.getNoArticle());
			
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
	 * Methode en charge de
	 * @param nouveauMontant
	 * @param noUtilisateur
	 * @param noArticle
	 * @throws DALException 
	 */
	public void updateMontantEnchere (Integer nouveauMontant, Integer noUtilisateur, Integer noArticle) throws DALException {
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			stmt = con.prepareStatement(UPDATE_MONTANT_ENCHERE);
			
			stmt.setTimestamp(1, Timestamp.from(Instant.now()));
			stmt.setInt(2, nouveauMontant);
			stmt.setInt(3, noUtilisateur);
			stmt.setInt(4, noArticle);
			
			int rowsAffected = stmt.executeUpdate();
			
			System.out.println(rowsAffected + " ligne modifiée");
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DALException("Update du montant de l'enchère error");
		} catch (Exception e) {
			e.getMessage();
		}	
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Enchere> selectAllByNoUtilisateur(Integer noUtilisateur) throws DALException {
		List<Enchere> listeEncheres = new ArrayList<>();
		Enchere enchere = null;
		
		try {
			con = ConnectionProvider.getConnection();
			PreparedStatement stmt = con.prepareStatement(SELECT_ALL_BY_NO_UTILISATEUR);
			
			stmt.setInt(1, noUtilisateur);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				enchere = new Enchere(rs.getInt("no_utilisateur"), rs.getInt("no_article"), 
						rs.getDate("date_enchere"), rs.getInt("montant_enchere"));			
				listeEncheres.add(enchere);
			}
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DALException("Select all by noUtilisateur error");
		}			
		return listeEncheres;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Enchere> selectAllByNoArticle(Integer noArticle) throws DALException {
		List<Enchere> listeEncheres = new ArrayList<>();
		Enchere enchere = null;
		
		try {
			con = ConnectionProvider.getConnection();
			PreparedStatement stmt = con.prepareStatement(SELECT_ALL_BY_NO_ARTICLE);
			
			stmt.setInt(1, noArticle);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				enchere = new Enchere(rs.getInt("no_utilisateur"), rs.getInt("no_article"), 
						rs.getDate("date_enchere"), rs.getInt("montant_enchere"));			
				listeEncheres.add(enchere);
			}
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DALException("Select all by noUtilisateur error");
		}			
		return listeEncheres;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer selectMaxMontantByNoArticle(Enchere enchere) throws DALException {
		Integer montantMax = null;
		
		try {
			con = ConnectionProvider.getConnection();
			PreparedStatement stmt = con.prepareStatement(SELECT_MAX_MONTANT_BY_ARTICLE);
			
			stmt.setInt(1, enchere.getNoArticle());
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				montantMax = rs.getInt("montant_enchere");
			}
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DALException("Select max montant by noArticle error");
		}			
		return montantMax;
	}

	/**
	 * {@inheritDoc}
	 * Retourne true si l'enchère existe dans la BDD
	 */
	@Override
	public boolean isAlreadyCreated(Enchere enchere) throws DALException {
		Enchere enchereATester = null;
		
		try {
			con = ConnectionProvider.getConnection();
			PreparedStatement stmt = con.prepareStatement(SELECT_NO_UTILISATEUR_NO_ARTICLE);
			
			stmt.setInt(1, enchere.getNoUtilisateur());
			stmt.setInt(2, enchere.getNoArticle());
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				enchere = new Enchere(rs.getInt("no_utilisateur"), rs.getInt("no_article"), 
						rs.getDate("date_enchere"), rs.getInt("montant_enchere"));			
			}
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DALException("Select isAlreadyCreated error");
		}
		System.out.println(enchereATester);
		boolean isAlreadyCreated = true;
		if (enchereATester == null) {
			isAlreadyCreated = false;
		}
		return isAlreadyCreated;
	}
	
	

}
