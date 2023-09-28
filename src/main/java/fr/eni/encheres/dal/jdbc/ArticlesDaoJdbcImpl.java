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
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticlesDao;

public class ArticlesDaoJdbcImpl implements ArticlesDao {
	
	private static final String SELECT_ALL = "SELECT a.no_article, a.nom_article, a.date_debut_encheres, a.date_fin_encheres, a.description, a.etat_vente,"
			+ " a.prix_initial, a.prix_vente, a.no_utilisateur, u.pseudo, u.nom, u.prenom, u.telephone, u.ville, u.rue, u.code_postal,"
			+ " u.mot_de_passe, u.email, u.credit, u.administrateur, c.libelle, c.no_categorie"
			+ " FROM ARTICLE_VENDU a INNER JOIN CATEGORIE c ON a.no_categorie = c.no_categorie"
			+ " INNER JOIN UTILISATEUR u ON a.no_utilisateur = u.no_utilisateur";

	private static final String SELECT = "SELECT a.no_article, a.nom_article, a.date_debut_encheres, a.date_fin_encheres, a.description, a.etat_vente,"
			+ " a.prix_initial, a.prix_vente, a.no_utilisateur, u.pseudo, u.nom, u.prenom, u.telephone, u.ville, u.rue, u.code_postal,"
			+ " u.mot_de_passe, u.email, u.credit, u.administrateur, c.libelle, c.no_categorie"
			+ " FROM ARTICLE_VENDU a INNER JOIN CATEGORIE c ON a.no_categorie = c.no_categorie"
			+ " INNER JOIN UTILISATEUR u ON a.no_utilisateur = u.no_utilisateur WHERE no_article = ?";

	private static final String SAVE = "INSERT ARTICLE_VENDU (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,etat_vente,no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String DELETE = "DELETE ARTICLE_VENDU WHERE no_article = ?";
	private static final String UPDATE = "UPDATE ARTICLE_VENDU SET nom_article=?,description=?,date_debut_encheres=?,date_fin_encheres=?,prix_initial=?,prix_vente=?,etat_vente=?,no_categorie=? WHERE no_article = ?";

	private static final String UPDATE_CREDIT = "UPDATE ARTICLE_VENDU SET prix_vente=? WHERE no_article = ?";

	private static final String FIND_BY_NAME = "SELECT a.no_article, a.nom_article, a.date_debut_encheres, a.date_fin_encheres, a.description, a.etat_vente,"
			+ " a.prix_initial, a.prix_vente, a.no_utilisateur, u.pseudo, u.nom, u.prenom, u.telephone, u.ville, u.rue, u.code_postal,"
			+ " u.mot_de_passe, u.email, u.credit, u.administrateur, c.libelle, c.no_categorie"
			+ " FROM ARTICLE_VENDU a INNER JOIN CATEGORIE c ON a.no_categorie = c.no_categorie"
			+ " INNER JOIN UTILISATEUR u ON a.no_utilisateur = u.no_utilisateur" + " WHERE a.nom_article LIKE ?";

	private static final String FIND_BY_ID = "SELECT * FROM ARTICLE_VENDU WHERE id_article LIKE ? ";

	private static final String FIND_ARTICLE_BY_CATEGORIE = "SELECT a.no_article, a.nom_article, a.date_debut_encheres, a.date_fin_encheres, a.description, a.etat_vente,"
			+ " a.prix_initial, a.prix_vente, a.no_utilisateur, u.pseudo, u.nom, u.prenom, u.telephone, u.ville, u.rue, u.code_postal,"
			+ " u.mot_de_passe, u.email, u.credit, u.administrateur, c.libelle, a.no_categorie"
			+ " FROM ARTICLE_VENDU a INNER JOIN CATEGORIE c ON a.no_categorie = c.no_categorie"
			+ " INNER JOIN UTILISATEUR u ON a.no_utilisateur = u.no_utilisateur" + " WHERE c.no_categorie = ?";

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
			pstmt.setString(7, "Créé");
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
				ArticleVendu articles = new ArticleVendu();
				articles.setNoArticle(rs.getInt("no_article"));
				articles.setNomArticle(rs.getString("nom_article"));
				articles.setDescription(rs.getString("description"));
				articles.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				articles.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				articles.setMiseAPrix(rs.getInt("prix_initial"));
				articles.setPrixVente(rs.getInt("prix_vente"));
				articles.setEtatVente(rs.getString("etat_vente"));
				Utilisateur userTemp = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"),
						rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"),
						rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"),
						rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));
				Categorie categorieTemp = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
				articles.setUtilisateur(userTemp);
				articles.setCategorie(categorieTemp);
				return articles;
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
				articles.add(ArticleFromRs(rs));
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
			pstmt.setString(7, "Crée");
			pstmt.setInt(8, articleVendu.getCategorie().getNoCategorie());
			// Where id
			pstmt.setInt(9, articleVendu.getNoArticle());
			// execute
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modifyCredit(ArticleVendu credit) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(UPDATE_CREDIT)) {
			// Update Set
			pstmt.setInt(1, credit.getPrixVente());
			// Where id
			pstmt.setInt(2, credit.getNoArticle());
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
			System.out.println(articles);
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
		Utilisateur userTemp = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"),
				rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
				rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"),
				rs.getBoolean("administrateur"));
		Categorie categorieTemp = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
		articles.setUtilisateur(userTemp);
		articles.setCategorie(categorieTemp);
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
				articles.add(ArticleFromRs(rs));
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
			pstmt.setInt(1, Integer.parseInt(categorie));
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
