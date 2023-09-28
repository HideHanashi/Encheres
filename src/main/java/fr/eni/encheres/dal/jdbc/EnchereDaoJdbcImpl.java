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
	
	private static final String SELECT_PRIX_ARTICLE = "SELECT TOP(1) u.no_utilisateur, av.no_article, e.date_encheres,"
			+ " e.montant_encheres, u.pseudo"
			+ " FROM ENCHERES e"
			+ " INNER JOIN ARTICLE_VENDU av ON e.no_article = av.no_article"
			+ " INNER JOIN UTILISATEUR u ON u.no_utilisateur = e.no_utilisateur"
			+ " WHERE av.no_article = ?"
			+ " ORDER BY e.montant_encheres DESC";
	
	private static final String SELECT_ALL_PARTICIPE_WIN = "select e.*"
			+ " from ENCHERES AS e INNER JOIN (select no_article ,max(montant_encheres) as max_montant from ENCHERES"
			+ "	GROUP BY no_article) AS MO ON MO.no_article = e.no_article AND e.montant_encheres = MO.max_montant"
			+ " where e.no_utilisateur = ?";
	
	private static final String SELECT_ALL_PARTICIPE = "SELECT e.no_utilisateur, a.no_article, a.nom_article, a.date_debut_encheres,"
			+ " a.date_fin_encheres, a.description, a.etat_vente, a.prix_initial, a.prix_vente, e.montant_encheres, e.date_encheres"
			+ " FROM ENCHERES e INNER JOIN ARTICLE_VENDU a ON e.no_article = a.no_article WHERE e.no_utilisateur = ?";

	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur,no_article,date_encheres,montant_encheres)"
			+ " VALUES (?,?,?,?)";

	private static final String UPDATE_ENCHERE = "UPDATE ENCHERES SET date_encheres=?,montant_encheres=? WHERE no_article = ? AND no_utilisateur = ?";

	private static final String DELETE_ENCHERE = "DELETE ENCHERES WHERE no_article = ?";

	private static final String SEARCH_ENCHERE = "SELECT * FROM ENCHERES WHERE no_article = ?";

	private static final String SELECT_ALL_ENCHERES = "SELECT * FROM ENCHERES";
	private static final String FIND_BY_NAME = "SELECT * FROM ARTICLE_VENDU a INNER JOIN ENCHERES e ON a.no_article = e.no_article WHERE nom_article LIKE ?";

	@Override
	public Enchere selectPrixArticle(int id) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT_PRIX_ARTICLE);) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new Enchere(UtilisateursManager.getInstance().recupUtilisateur(rs.getInt("no_utilisateur")),
						ArticlesManager.getInstance().recupArticle(rs.getInt("no_article")),
						rs.getDate("date_encheres").toLocalDate(), rs.getInt("montant_encheres"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	@Override
	public List<Enchere> findAllParticipe(int id) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_PARTICIPE);) {
			pstmt.setInt(1, id);
			List<Enchere> encheres = new ArrayList<Enchere>();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				encheres.add(EnchereFromRs(rs));
			}
			return encheres;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	@Override
	public List<Enchere> findAllParticipeWin(int id) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_PARTICIPE_WIN);) {
			pstmt.setInt(1, id);
			List<Enchere> encheres = new ArrayList<Enchere>();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				encheres.add(EnchereFromRs(rs));
			}
			return encheres;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	@Override
	public void save(Enchere enchere) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(INSERT_ENCHERE);) {
			pstmt.setInt(1, enchere.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(2, enchere.getArticleVendu().getNoArticle());
			pstmt.setDate(3, Date.valueOf(enchere.getDateEnchere()));
			pstmt.setInt(4, enchere.getMontantEnchere());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modify(Enchere enchere) {
		try (
				Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(UPDATE_ENCHERE);
			){
			pstmt.setDate(1, Date.valueOf(enchere.getDateEnchere()));
			pstmt.setInt(2, enchere.getMontantEnchere());
			pstmt.setInt(3, enchere.getArticleVendu().getNoArticle());
			pstmt.setInt(4, enchere.getUtilisateur().getNoUtilisateur());
			
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
	public Enchere findOne(int id) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SEARCH_ENCHERE);) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new Enchere(UtilisateursManager.getInstance().recupUtilisateur(rs.getInt("no_utilisateur")),
						ArticlesManager.getInstance().recupArticle(rs.getInt("no_article")),
						rs.getDate("date_encheres").toLocalDate(), rs.getInt("montant_encheres"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<Enchere> findAll() {

		List<Enchere> listEncheres = new ArrayList<>();
		try (
				Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ENCHERES)
			){
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				listEncheres.add(EnchereFromRs(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listEncheres;
	}
	
	@Override
	public boolean verifyIdAll(int idUser, int idArticle) {
		boolean verify = false;
		try (
				Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ENCHERES)
			){
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int idUserSQL = rs.getInt("no_utilisateur");
				int idArticleSQL = rs.getInt("no_article");
				
				if (idUser == idUserSQL && idArticle == idArticleSQL) {
					return verify = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return verify;
	}

	private Enchere EnchereFromRs(ResultSet rs) throws SQLException {
		Enchere enchere = new Enchere();
		Utilisateur user = UtilisateursManager.getInstance().recupUtilisateur(rs.getInt("no_utilisateur"));
		ArticleVendu article = ArticlesManager.getInstance().recupArticle(rs.getInt("no_article"));
		enchere.setUtilisateur(user);
		enchere.setArticleVendu(article);
		enchere.setDateEnchere(rs.getDate("date_encheres").toLocalDate());
		enchere.setMontantEnchere(rs.getInt("montant_encheres"));
		return enchere;
	}

	@Override
	public List<Enchere> findByName(String query) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(FIND_BY_NAME)) {
			pstmt.setString(1, "%" + query + "%");
			List<Enchere> listEncheres = new ArrayList<>();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				listEncheres.add(EnchereFromRs(rs));
			}
			return listEncheres;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
