package fr.eni.encheres.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import fr.eni.encheres.bll.ArticlesManager;
import fr.eni.encheres.bll.CategoriesManager;
import fr.eni.encheres.bll.EncheresManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;

@WebServlet("/modifiermesarticles")
public class ModifierArticlesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Categorie> listCategories = CategoriesManager.getInstance().searchByCategories();
			List<ArticleVendu> listArticles = null;

			String recherche = request.getParameter("q");
			String categorie = request.getParameter("c");

			if (recherche != null && !recherche.isBlank()) {
				listArticles = ArticlesManager.getInstance().searchArticle(recherche);

			} else if (categorie != null && !categorie.isBlank()) {
				listArticles = ArticlesManager.getInstance().searchCategorie(categorie);

			} else {
				listArticles = ArticlesManager.getInstance().searchAllArticle();
			}

			String imageArticle = null;

			// transmettre l'objet vers la jsp
			// request.setAttribute("user", users);
			request.setAttribute("image", imageArticle);
			request.setAttribute("categorie", listCategories);
			request.setAttribute("articles", listArticles);
			// forward
			request.getRequestDispatcher("/WEB-INF/pages/modifier-vente.jsp").forward(request, response);
		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
			request.getRequestDispatcher("404");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
