package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bll.ArticlesManager;
import fr.eni.encheres.bll.CategoriesManager;
import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticlesDao;

public class ArticlesDaoJdbcImpl implements ArticlesDao {

	private static final String SELECT_ALL = "SELECT * FROM ARTICLE_VENDU";
	private static final String SELECT = "SELECT * FROM ARTICLE_VENDU WHERE no_article = ?";
	private static final String SAVE = "INSERT ARTICLE_VENDU (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,etat_vente,no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String DELETE = "DELETE ARTICLE_VENDU WHERE no_article = ?";
	private static final String UPDATE = "UPDATE ARTICLE_VENDU SET nom_article=?,description=?,date_debut_encheres=?,date_fin_encheres=?,prix_initial=?,prix_vente=?,etat_vente=? WHERE no_article = ?";
	private static final String FIND_BY_NAME = "SELECT * FROM ARTICLE_VENDU WHERE nom_article LIKE ? ";
	private static final String FIND_BY_ID = "SELECT * FROM ARTICLE_VENDU WHERE id_article LIKE ? ";
	private static final String FIND_ARTICLE_BY_CATEGORIE = "SELECT * FROM ARTICLE_VENDU a INNER JOIN ENCHERES e ON a.no_article = e.no_article WHERE no_categorie LIKE ?";

	@Override
	public void save(ArticleVendu articleVendu) {// passage par référence
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SAVE, PreparedStatement.RETURN_GENERATED_KEYS);) {
			// valoriser les params de la requete
			pstmt.setString(1, articleVendu.getNomArticle());
			pstmt.setString(2, articleVendu.getDescription());
			pstmt.setDate(3, Date.valueOf(articleVendu.getDateDebutEncheres()));
			pstmt.setDate(4, Date.valueOf(articleVendu.getDateFinEncheres()));
			pstmt.setInt(5, articleVendu.getMiseAPrix());
			pstmt.setInt(6, articleVendu.getPrixVente());
			pstmt.setString(7, articleVendu.getEtatVente());
			pstmt.setInt(8, articleVendu.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(9, articleVendu.getCategorie().getNoCategorie());

			// executer la requete
			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				articleVendu.setNoArticle(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArticleVendu findOne(int id) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT);) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description"), rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"),
						rs.getInt("prix_vente"), rs.getString("etat_vente"),
						UtilisateursManager.getInstance().recupUtilisateur(rs.getInt("no_utilisateur")),
						CategoriesManager.getInstance().getCategorieById(rs.getInt("no_categorie")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<ArticleVendu> findAll() {
		try (Connection connection = ConnectionProvider.getConnection();
				Statement stmt = connection.createStatement();) {
			List<ArticleVendu> articles = new ArrayList<ArticleVendu>();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while (rs.next()) {
				articles.add(

						new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
								rs.getString("description"), rs.getDate("date_debut_encheres").toLocalDate(),
								rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"),
								rs.getInt("prix_vente"), rs.getString("etat_vente"))

				);
			}
			return articles;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public void modify(ArticleVendu articleVendu) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(UPDATE)) {

			// Update Set
			pstmt.setString(1, articleVendu.getNomArticle());
			pstmt.setString(2, articleVendu.getDescription());
			pstmt.setDate(3, Date.valueOf(articleVendu.getDateDebutEncheres()));
			pstmt.setDate(4, Date.valueOf(articleVendu.getDateFinEncheres()));
			pstmt.setInt(5, articleVendu.getMiseAPrix());
			pstmt.setInt(6, articleVendu.getPrixVente());
			pstmt.setString(7, articleVendu.getEtatVente());
			// Where id
			pstmt.setInt(8, articleVendu.getNoArticle());
			// execute
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove(int id) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(DELETE);) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ArticleVendu> findByName(String query) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(FIND_BY_NAME)) {
			pstmt.setString(1, "%" + query + "%");
			List<ArticleVendu> articles = new ArrayList<ArticleVendu>();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				articles.add(ArticleFromRs(rs));
			}
			return articles;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	private ArticleVendu ArticleFromRs(ResultSet rs) throws SQLException {
		ArticleVendu articles = new ArticleVendu();
		articles.setNoArticle(rs.getInt("no_article"));
		articles.setNomArticle(rs.getString("nom_article"));
		articles.setDescription(rs.getString("description"));
		articles.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
		articles.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
		articles.setMiseAPrix(rs.getInt("prix_initial"));
		articles.setPrixVente(rs.getInt("prix_vente"));
		articles.setEtatVente(rs.getString("etat_vente"));
		Utilisateur user = UtilisateursManager.getInstance().recupUtilisateur(rs.getInt("no_utilisateur"));
		Categorie categorie = CategoriesManager.getInstance().getCategorieById(rs.getInt("no_categorie"));
		articles.setUtilisateur(user);
		articles.setCategorie(categorie);
		return articles;
	}

	@Override
	public List<ArticleVendu> findByID(Integer idArticle) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(FIND_BY_ID)) {
			pstmt.setInt(1, idArticle);
			List<ArticleVendu> articles = new ArrayList<ArticleVendu>();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				articles.add(

						new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
								rs.getString("description"), rs.getDate("date_debut_encheres").toLocalDate(),
								rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"),
								rs.getInt("prix_vente"), rs.getString("etat_vente")));
			}
			return articles;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ArticleVendu> selectAll() {
		try (Connection connection = ConnectionProvider.getConnection();
				Statement stmt = connection.createStatement();) {
			List<ArticleVendu> articles = new ArrayList<ArticleVendu>();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while (rs.next()) {
				articles.add(

						new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
								rs.getString("description"), rs.getDate("date_debut_encheres").toLocalDate(),
								rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"),
								rs.getInt("prix_vente"), rs.getString("etat_vente"))

				);
			}
			return articles;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ArticleVendu> findArticleByCategorie(String categorie) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(FIND_ARTICLE_BY_CATEGORIE)) {
			pstmt.setString(1, "%" + categorie + "%");
			List<ArticleVendu> listArticle = new ArrayList<>();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				listArticle.add(ArticleFromRs(rs));
			}
			return listArticle;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
