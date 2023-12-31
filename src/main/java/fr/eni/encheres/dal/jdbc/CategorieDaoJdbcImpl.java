package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.CategorieDao;

public class CategorieDaoJdbcImpl implements CategorieDao {

	private static final String FIND_ALL_CATEGORIE = "SELECT * FROM CATEGORIE";
	private static final String FIND_BY_ID = "SELECT * FROM CATEGORIE WHERE no_categorie = ?";

	@Override
	public List<Categorie> findByCategorie() {
		List<Categorie> listesCat = new ArrayList<>();
		try (Connection connection = ConnectionProvider.getConnection();
				Statement stmt = connection.createStatement();) {

			ResultSet rs = stmt.executeQuery(FIND_ALL_CATEGORIE);
			while (rs.next()) {
				Categorie categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
				listesCat.add(categorie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listesCat;
	}

	@Override
	public Categorie findCategorieById(int id) {
		List<Categorie> listesCat = new ArrayList<>();
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(FIND_BY_ID);) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Categorie categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
				listesCat.add(categorie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listesCat.get(0);

	}

}
