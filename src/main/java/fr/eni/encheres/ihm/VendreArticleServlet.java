package fr.eni.encheres.ihm;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.bll.ArticlesManager;
import fr.eni.encheres.bll.CategoriesManager;
import fr.eni.encheres.bll.EncheresManager;
import fr.eni.encheres.bll.ReatraitManager;
import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bll.exception.BLLException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/nouvelle-vente")
public class VendreArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			HttpSession session = request.getSession();
			Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("user");
			int id = utilisateurSession.getNoUtilisateur();
			// récupérer le param dans url
			Utilisateur users = UtilisateursManager.getInstance().recupUtilisateur(id);

			List<Categorie> listCategories = CategoriesManager.getInstance().searchByCategories();

			// transmettre l'objet vers la jsp
			request.setAttribute("user", users);
			request.setAttribute("categorie", listCategories);
			// forward
			request.getRequestDispatcher("/WEB-INF/pages/nouvelle-vente.jsp").forward(request, response);
		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
			request.getRequestDispatcher("404");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// INTÉGRATION DES VARIABLES DE L'UTILISATEUR DANS LES VARIABLES BACK
			String nomArticle = request.getParameter("nomArticle");
			String description = request.getParameter("description");
			LocalDate dateDebutEncheres = LocalDate.parse(request.getParameter("dateDebutEncheres"));
			LocalDate dateFinEncheres = LocalDate.parse(request.getParameter("dateFinEncheres"));
			int miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
			int categorie_id = Integer.parseInt(request.getParameter("categorie"));
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");

			// RÉCUPÉRATION DE LA SESSION UTILISATEUR
			HttpSession session = request.getSession();
			Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("user");
			int id = utilisateurSession.getNoUtilisateur();

			// INTÉGRATION DES IDs DANS LES VARIABLES
			Utilisateur user = UtilisateursManager.getInstance().recupUtilisateur(id);
			Categorie categorie = CategoriesManager.getInstance().getCategorieById(categorie_id);

			// CRÉATION D'UN ARTICLE VIA LES VARIABLES DE L'UTILISATEUR
			ArticleVendu articleVendu = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres,
					miseAPrix, user, categorie);

			// AJOUT DE L'ARTICLE DANS LA CATÉGORIE SELECTIONNÉ PAR L'UTILISATEUR
			categorie.addArticle(articleVendu);

			// CRÉATION DE L'ARTICLE DANS LA BDD
			ArticlesManager.getInstance().addArticle(articleVendu);

			// CRÉATION DU LIEU DE RETRAIT POUR L'ARTICLE CRÉÉ
			Retrait retrait = new Retrait(articleVendu, rue, codePostal, ville);

			// CRÉATION DU LIEU DE RETRAIT DANS LA BDD
			ReatraitManager.getInstance().addRetrait(retrait);

			// RENVOIE L'UTILISATEUR SUR LA PAGE D'ACCUEIL
			response.sendRedirect(request.getContextPath() + "");
		} catch (BLLException e) {

			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
		}
	}
}
