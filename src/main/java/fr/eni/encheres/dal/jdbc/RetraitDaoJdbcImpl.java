package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.RetraitDao;

public class RetraitDaoJdbcImpl implements RetraitDao {

	private static final String SAVE = "INSERT INTO RETRAIT (no_article,rue,code_postal,ville) VALUES (?,?,?,?)";
	private static final String UPDATE = "UPDATE RETRAIT SET rue=?,code_postal=?,ville=? WHERE no_article = ?";

	private static final String SELECT = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente, r.rue, r.code_postal, r.ville"
			+ " FROM ARTICLE_VENDU a INNER JOIN RETRAIT r ON a.no_article = r.no_article WHERE a.no_article = ?";

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
	public void update(Retrait retrait) {
		try (Connection connection = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = connection.prepareStatement(UPDATE);

			pstmt.setString(1, retrait.getRue());
			pstmt.setString(2, retrait.getCodePostal());
			pstmt.setString(3, retrait.getVille());
			pstmt.setInt(4, retrait.getArticleVendu().getNoArticle());
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
				Retrait retrait = new Retrait();
				ArticleVendu articles = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description"), rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"),
						rs.getInt("prix_vente"));
				retrait.setArticleVendu(articles);
				retrait.setRue(rs.getString("rue"));
				retrait.setCodePostal(rs.getString("code_postal"));
				retrait.setVille(rs.getString("ville"));
				return retrait;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
}
