package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bll.ArticlesManager;
import fr.eni.encheres.bll.UtilisateursManager;
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
			+ " VALUES (?,?,?,?)";

	private static final String UPDATE_ENCHERE = "UPDATE ENCHERES SET (no_utilisateur,no_article,date_encheres,montant_encheres)"
			+ " VALUES (?,?,?,?) WHERE no_article = ?";

	private static final String DELETE_ENCHERE = "DELETE ENCHERES WHERE no_article = ?";

	private static final String SELECT_ALL_ENCHERES = "SELECT * FROM ENCHERES";

	@Override
	public void save(Enchere enchere) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(INSERT_ENCHERE);) {
			pstmt.setInt(1, enchere.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(2, enchere.getArticleVendu().getNoArticle());
			pstmt.setDate(3, Date.valueOf(enchere.getDateEnchere()));
			pstmt.setInt(4, enchere.getMontant_enchere());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modify(Enchere enchere) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(UPDATE_ENCHERE);) {
			pstmt.setInt(1, enchere.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(2, enchere.getArticleVendu().getNoArticle());
			pstmt.setDate(3, Date.valueOf(enchere.getDateEnchere()));
			pstmt.setInt(4, enchere.getMontant_enchere());

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

		List<Enchere> listEncheres = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ENCHERES)) {
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				listEncheres.add(EnchereFromRs(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listEncheres;
	}

	private Enchere EnchereFromRs(ResultSet rs) throws SQLException {
		Enchere enchere = new Enchere();
		Utilisateur user = UtilisateursManager.getInstance().recupUtilisateur(rs.getInt("no_utilisateur"));
		ArticleVendu article = ArticlesManager.getInstance().recupArticle(rs.getInt("no_article"));
		enchere.setUtilisateur(user);
		enchere.setArticleVendu(article);
		enchere.setDateEnchere(rs.getDate("date_encheres").toLocalDate());
		enchere.setMontant_enchere(rs.getInt("montant_encheres"));
		return enchere;
	}

	@Override
	public List<Enchere> findByName(String query) {
		// TODO Auto-generated method stub
		return null;
	}
}
