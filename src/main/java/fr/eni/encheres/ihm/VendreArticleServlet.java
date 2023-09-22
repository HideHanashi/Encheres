package fr.eni.encheres.ihm;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.bll.ArticlesManager;
import fr.eni.encheres.bll.CategoriesManager;
import fr.eni.encheres.bll.ReatraitManager;
import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bll.exception.BLLException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
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
			String nomArticle = request.getParameter("nomArticle");
			String description = request.getParameter("description");
			LocalDate dateDebutEncheres = LocalDate.parse(request.getParameter("dateDebutEncheres"));
			LocalDate dateFinEncheres = LocalDate.parse(request.getParameter("dateFinEncheres"));
			int miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
			int categorie_id = Integer.parseInt(request.getParameter("categorie"));
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");

			HttpSession session = request.getSession();
			Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("user");
			int id = utilisateurSession.getNoUtilisateur();
			
			Utilisateur user = UtilisateursManager.getInstance().recupUtilisateur(id);
			Categorie categorie = CategoriesManager.getInstance().getCategorieById(categorie_id);
			
			ArticleVendu articleVendu = new ArticleVendu(
					nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, user, categorie
					);
			
			int articleSession = articleVendu.getNoArticle();
			ArticleVendu articleVendu_id = ArticlesManager.getInstance().recupArticle(articleSession);
			
			Retrait retrait = new Retrait(articleVendu_id, rue, codePostal, ville);
			categorie.addArticle(articleVendu);

			ArticlesManager.getInstance().addArticle(articleVendu);
			ReatraitManager.getInstance().addRetrait(retrait);
			response.sendRedirect(request.getContextPath() + "/liste-encheres");
		} catch (BLLException e) {

			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
		}
	}
}
