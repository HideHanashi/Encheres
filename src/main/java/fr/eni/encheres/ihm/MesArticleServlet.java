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

@WebServlet("/voirarticles")
public class MesArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			List<Categorie> listCategories = CategoriesManager.getInstance().searchByCategories();
			List<ArticleVendu> listArticles = null;

			if (request.getParameter("q") != null) {
				listArticles = ArticlesManager.getInstance().searchArticle(request.getParameter("q"));
			} else if (request.getParameter("q") == null) {
				listArticles = ArticlesManager.getInstance().searchAllArticle();
			} else if (request.getParameter("c") != null) {
				listArticles = ArticlesManager.getInstance().searchCategorie(request.getParameter("c"));
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
			request.getRequestDispatcher("/WEB-INF/pages/ma-vente.jsp").forward(request, response);

		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
			request.getRequestDispatcher("404");
		}

	}

}
