package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.EnchereDao;

public class EnchereDaoJdbcImpl implements EnchereDao {

//	private static final String SELECT_ALL = "SELECT * FROM ENCHERES";

	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES (noUtilisateur,noArticle,dateEnchere,montantEnchere)"
			+ " VALUES (?,?,?,?)";

	private static final String UPDATE_ENCHERE = "UPDATE ENCHERES SET (noUtilisateur,noArticle,dateEnchere,montantEnchere)"
			+ " VALUES (?,?,?,?) WHERE no_article = ?";

	private static final String DELETE_ENCHERE = "DELETE ENCHERES WHERE no_article = ?";

	private static final String SELECT_ALL_ENCHERES = null;

	@Override
	public void save(Enchere encherir) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(INSERT_ENCHERE);) {
			pstmt.setInt(1, encherir.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(2, encherir.getArticleVendu().getNoArticle());
			pstmt.setDate(3, Date.valueOf(encherir.getDateEnchere()));
			pstmt.setInt(4, encherir.getArticleVendu().getMiseAPrix());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modify(Enchere encherir) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(UPDATE_ENCHERE);) {
			pstmt.setInt(1, encherir.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(2, encherir.getArticleVendu().getNoArticle());
			pstmt.setDate(3, Date.valueOf(encherir.getDateEnchere()));
			pstmt.setInt(4, encherir.getArticleVendu().getMiseAPrix());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove(int id) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(DELETE_ENCHERE);) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Enchere> findAll() {

		try (Connection connection = ConnectionProvider.getConnection();
				Statement stmt = connection.createStatement();) {
			List<Enchere> listEncheres = new ArrayList<>();
			ResultSet rs = stmt.executeQuery(SELECT_ALL_ENCHERES);
			while (rs.next()) {
//				listEncheres.add(

//						new Enchere(rs.getInt("noArticle"), rs.getString("nomArticle"), rs.getString("description"),
//								rs.getDate("dateDebutEncheres").toLocalDate(),
//								rs.getDate("dateFinEncheres").toLocalDate(), rs.getInt("miseAPrix"),
//								rs.getInt("prixVente"), rs.getString("etatVente"))
//
//				);
			}
			return listEncheres;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
