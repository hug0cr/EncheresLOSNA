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

import fr.eni.encheresLOSNA.bo.Utilisateur;
import fr.eni.encheresLOSNA.dal.DALException;
import fr.eni.encheresLOSNA.dal.UtilisateurDAO;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 1 juin 2021 - 11:08:53
 */
public class UtilisateurDOAJdbcImpl implements UtilisateurDAO {

	private final String INSERT = "INSERT INTO UTILISATEURS "
			+ "(pseudo, "
			+ "nom, "
			+ "prenom, "
			+ "email, "
			+ "telephone, "
			+ "rue, "
			+ "code_postal, "
			+ "ville, "
			+ "mot_de_passe, "
			+ "credit, "
			+ "administrateur) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	
	private final String SELECT_BY_ID = "SELECT "
			+ "no_utilisateur, "
			+ "pseudo, "
			+ "nom, "
			+ "prenom, "
			+ "email, "
			+ "telephone, "
			+ "rue, "
			+ "code_postal, "
			+ "ville, "
			+ "mot_de_passe, "
			+ "credit, "
			+ "administrateur "
			+ "FROM UTILISATEURS WHERE no_utilisateur=?;";
	
	private final String SELECT_BY_EMAIL = "SELECT "
			+ "no_utilisateur, "
			+ "pseudo, "
			+ "nom, "
			+ "prenom, "
			+ "email, "
			+ "telephone, "
			+ "rue, "
			+ "code_postal, "
			+ "ville, "
			+ "mot_de_passe, "
			+ "credit, "
			+ "administrateur "
			+ "FROM UTILISATEURS WHERE email=?;";
	
	private final String UPDATE = "UPDATE UTILISATEURS SET "
			+ "pseudo=?, "
			+ "nom=?, "
			+ "prenom=?, "
			+ "email=?, "
			+ "telephone=?, "
			+ "rue=?, "
			+ "code_postal=?, "
			+ "ville=?, "
			+ "mot_de_passe=?, "
			+ "credit=?, "
			+ "administrateur=? "
			+ "WHERE no_utilisateur=?;";
	
	private final String DELETE = "DELETE FROM UTILISATEURS WHERE no_utilisateur=?;";
	
	private final String SELECT_ALL = "SELECT * FROM UTILISATEURS;";
	
	private static Connection con;
	
	/**
	 * {@inheritDoc}
	 * @throws DALException 
	 */
	@Override
	public void insert(Utilisateur t) throws DALException {
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			stmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, t.getPseudo());
			stmt.setString(2, t.getNom());
			stmt.setString(3, t.getPrenom());
			stmt.setString(4, t.getEmail());
			stmt.setString(5, t.getTelephone());
			stmt.setString(6, t.getRue());
			stmt.setString(7, t.getCodePostal());
			stmt.setString(8, t.getVille());
			stmt.setString(9, t.getMotDePasse());
			stmt.setInt(10, t.getCredit());
			stmt.setBoolean(11, t.isAdministrateur());
			
			int rowsAffected = stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				t.setNoUtilisateur(rs.getInt(1));
				System.out.println(rowsAffected + " ligne(s) insérée(s) " + rs.getInt(1));
			}
				
			stmt.close();
			//JdbcTools.closeConnection();
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
	public void update(Utilisateur t) throws DALException {
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			stmt = con.prepareStatement(UPDATE);
			
			stmt.setString(1, t.getPseudo());
			stmt.setString(2, t.getNom());
			stmt.setString(3, t.getPrenom());
			stmt.setString(4, t.getEmail());
			stmt.setString(5, t.getTelephone());
			stmt.setString(6, t.getRue());
			stmt.setString(7, t.getCodePostal());
			stmt.setString(8, t.getVille());
			stmt.setString(9, t.getMotDePasse());
			stmt.setInt(10, t.getCredit());
			stmt.setBoolean(11, t.isAdministrateur());
			stmt.setInt(12, t.getNoUtilisateur());
			
			int rowsAffected = stmt.executeUpdate();
			
			System.out.println(rowsAffected + " ligne modifiée");
			
			stmt.close();
			//JdbcTools.closeConnection();
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
	public Utilisateur selectById(int id) throws DALException {
		Utilisateur u = null;
		
		try {
			
			con = ConnectionProvider.getConnection();
			PreparedStatement stmt = con.prepareStatement(SELECT_BY_ID);
			
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				u = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), 
						rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), 
						rs.getString("telephone"), rs.getString("rue"), 
						rs.getString("code_postal"), rs.getString("ville"), 
						rs.getString("mot_de_passe"), rs.getInt("credit"),
						rs.getBoolean("administrateur"));
			}
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DALException("Select by ID error");
		}	
		return u;
	}

	/**
	 * {@inheritDoc}
	 * @throws DALException 
	 */
	@Override
	public List<Utilisateur> selectAll() throws DALException {
		List<Utilisateur> listeUtilisateurs = new ArrayList<>();
		Utilisateur u = null;
		
		try {
			con = ConnectionProvider.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			
			while (rs.next()) {
				u = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), 
						rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), 
						rs.getString("telephone"), rs.getString("rue"), 
						rs.getString("code_postal"), rs.getString("ville"), 
						rs.getString("mot_de_passe"), rs.getInt("credit"),
						rs.getBoolean("administrateur"));			
				listeUtilisateurs.add(u);
			}
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new DALException("Select all error");
		}			
		return listeUtilisateurs;
	}

	/**
	 * {@inheritDoc}
	 * @throws DALException
	 * TODO Vérifier si on prend prend en param un utilisateur ou juste un numéro d'id
	 */
	@Override
	public void delete(Utilisateur t) throws DALException {
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			stmt = con.prepareStatement(DELETE);
			stmt.setInt(1, t.getNoUtilisateur());
			
			int rowsAffected = stmt.executeUpdate();
			
			System.out.println(rowsAffected + " ligne supprimée");
			
			stmt.close();
			//JdbcTools.closeConnection();
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
	@Override
	public Utilisateur selectByEmail(String email) throws DALException {
		Utilisateur u = null;
		
		try {
			
			con = ConnectionProvider.getConnection();
			PreparedStatement stmt = con.prepareStatement(SELECT_BY_EMAIL);
			
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				u = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), 
						rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), 
						rs.getString("telephone"), rs.getString("rue"), 
						rs.getString("code_postal"), rs.getString("ville"), 
						rs.getString("mot_de_passe"), rs.getInt("credit"),
						rs.getBoolean("administrateur"));
			}
			
			stmt.close();
			//JdbcTools.closeConnection();
			con.close();
		} catch (SQLException e) {
			throw new DALException("Select by email error");
		}	
		return u;
	}

}
