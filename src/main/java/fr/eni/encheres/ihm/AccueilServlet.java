package fr.eni.encheres.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.bll.ArticlesManager;
import fr.eni.encheres.bll.EncheresManager;
import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

@WebServlet("")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			HttpSession session = request.getSession();
			Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("user");

//			int id = utilisateurSession.getNoUtilisateur();

			// récupérer le param dans url
			// Utilisateur users = UtilisateursManager.getInstance().recupUtilisateur(id);
			List<Categorie> listCategories = ArticlesManager.getInstance().searchByCategories();
			List<Enchere> listEncheres = null;

			if (request.getParameter("q") != null) {
				listEncheres = EncheresManager.getInstance().searchEnchere(request.getParameter("q"));
			} else {
				listEncheres = EncheresManager.getInstance().searchAllEncheres();
			}

			// transmettre l'objet vers la jsp
			// request.setAttribute("user", users);
			request.setAttribute("categorie", listCategories);
			request.setAttribute("encheres", listEncheres);
			// forward
			request.getRequestDispatcher("/WEB-INF/pages/liste-encheres.jsp").forward(request, response);
		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
			request.getRequestDispatcher("404");
		}

	}

}
