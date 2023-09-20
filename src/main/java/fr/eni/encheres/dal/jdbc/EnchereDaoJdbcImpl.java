package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.EnchereDao;

public class EnchereDaoJdbcImpl implements EnchereDao {

	// "SELECT * FROM ARTICLE_VENDU a INNER JOIN ENCHERES e ON a.no_article =
	// e.no_article"
	// "SELECT * FROM ARTICLE_VENDU a INNER JOIN UTILISATEUR u ON a.no_utilisateur =
	// u.no_utilisateur
	// "
	// "SELECT * FROM ARTICLE_VENDU a INNER JOIN CATEGORIE c ON a.no_categorie =
	// c.no_categorie"
	// "SELECT * FROM RETRAIT r INNER JOIN ARTICLE_VENDU a ON a.no_article =
	// r.no_article"
	private static final String JOINTURE = "SELECT * FROM ARTICLE_VENDU a INNER JOIN ENCHERES e ON a.no_article = e.no_article"
			+ "SELECT * FROM ARTICLE_VENDU a INNER JOIN UTILISATEUR u ON a.no_utilisateur = u.no_utilisateur"
			+ "SELECT * FROM ARTICLE_VENDU a INNER JOIN CATEGORIE c ON a.no_categorie = c.no_categorie"
			+ "SELECT * FROM RETRAIT r INNER JOIN ARTICLE_VENDU a ON a.no_article = r.no_article";

	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur,no_article,date_encheres,montant_encheres)"
			+ " VALUES (?,?,?,?)" + JOINTURE;

	private static final String UPDATE_ENCHERE = "UPDATE ENCHERES SET (no_utilisateur,no_article,date_encheres,montant_encheres)"
			+ " VALUES (?,?,?,?) WHERE no_article = ?" + JOINTURE;

	private static final String DELETE_ENCHERE = "DELETE ENCHERES WHERE no_article = ?";

	private static final String SELECT_ALL_ENCHERES = "SELECT * FROM ENCHERES";

	@Override
	public void save(Enchere enchere) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(INSERT_ENCHERE);) {
			pstmt.setString(1, enchere.getArticleVendu().getNomArticle());
			pstmt.setString(2, enchere.getArticleVendu().getDescription());
			pstmt.setString(4, enchere.getArticleVendu().getCategorie().getLibelle());
			pstmt.setInt(4, enchere.getArticleVendu().getMiseAPrix());
			pstmt.setDate(3, Date.valueOf(enchere.getArticleVendu().getDateDebutEncheres()));
			pstmt.setDate(3, Date.valueOf(enchere.getArticleVendu().getDateFinEncheres()));
			pstmt.setString(4, enchere.getArticleVendu().getRetrait().getRue());
			pstmt.setString(4, enchere.getArticleVendu().getRetrait().getCodePostal());
			pstmt.setString(4, enchere.getArticleVendu().getRetrait().getVille());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modify(Enchere enchere) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(UPDATE_ENCHERE);) {
			pstmt.setString(1, enchere.getArticleVendu().getNomArticle());
			pstmt.setString(2, enchere.getArticleVendu().getDescription());
			pstmt.setString(4, enchere.getArticleVendu().getCategorie().getLibelle());
			pstmt.setInt(4, enchere.getArticleVendu().getMiseAPrix());
			pstmt.setDate(3, Date.valueOf(enchere.getArticleVendu().getDateDebutEncheres()));
			pstmt.setDate(3, Date.valueOf(enchere.getArticleVendu().getDateFinEncheres()));
			pstmt.setString(4, enchere.getArticleVendu().getRetrait().getRue());
			pstmt.setString(4, enchere.getArticleVendu().getRetrait().getCodePostal());
			pstmt.setString(4, enchere.getArticleVendu().getRetrait().getVille());

			pstmt.executeUpdate();
			pstmt.setInt(4, enchere.getArticleVendu().getNoArticle());
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
			Enchere enchere = new Enchere();
			ResultSet rs = stmt.executeQuery(SELECT_ALL_ENCHERES);
			while (rs.next()) {
				enchere.getUtilisateur();
				enchere.getArticleVendu().getNoArticle();
				enchere.getDateEnchere();
				enchere.getMontant_enchere();
				listEncheres.add(enchere);
			}
			return listEncheres;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
