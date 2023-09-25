package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.bll.ArticlesManager;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.RetraitDao;

public class RetraitDaoJdbcImpl implements RetraitDao {

	private static final String SAVE = "INSERT RETRAIT (no_article,rue,code_postal,ville) VALUES (?,?,?,?)";
	
	private static final String SELECT = "SELECT * FROM RETRAIT WHERE no_article = ?";
	
	@Override
	public void save(Retrait retrait) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SAVE);) {
			// valoriser les params de la requete
			pstmt.setInt(1, retrait.getArticleVendu().getNoArticle());
			pstmt.setString(2, retrait.getRue());
			pstmt.setString(3, retrait.getCodePostal());
			pstmt.setString(4, retrait.getVille());

			// executer la requete
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Retrait findOne(int id) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT);) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new Retrait(ArticlesManager.getInstance().recupArticle(rs.getInt("no_article")), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
}
