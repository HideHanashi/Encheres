package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.bo.ForgetPassword;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ForgetPasswordDao;

public class ForgetPasswordDaoJdbcImpl implements ForgetPasswordDao {
	
	private static final String INSERT_FORGET_PASSWORD = "INSERT FORGET_PASSWORD ( code,no_utilisateur ) VALUES (?,?)";
	private static final String SELECT_FOR_RESET_PASSWORD = "SELECT TOP(1) *"
			+ " FROM  UTILISATEUR u INNER JOIN forget_password fp"
			+ " ON u.no_utilisateur = fp.no_utilisateur"
			+ " WHERE u.email = ? "
			+ " ORDER BY fp.date_created DESC";

	@Override
	public void save(ForgetPassword fp) {
		try(
				Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(INSERT_FORGET_PASSWORD);
					){
				pstmt.setString(1, fp.getCode());
				pstmt.setInt(2, fp.getUser().getNoUtilisateur());
				pstmt.executeUpdate();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	public ForgetPassword resetPassword(String email) {
		try(
				Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT_FOR_RESET_PASSWORD);
					){
				pstmt.setString(1, email);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					return new ForgetPassword(rs.getInt("id"), 
							rs.getString("code"), 
							new Utilisateur(
									rs.getInt(1), // ID DE L'UTILISATEUR
									rs.getString(10) // MOT DE PASSE
									), 
							rs.getDate("date_created").toLocalDate());
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return null;
	}
}
