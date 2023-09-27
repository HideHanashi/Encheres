package fr.eni.encheres.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import fr.eni.encheres.bll.ArticlesManager;
import fr.eni.encheres.bll.EncheresManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;

@WebServlet("/articleparticipe")
public class ArticleParticipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<ArticleVendu> listArticles = null;
			List<Enchere> listEncheres = null;
			listArticles = ArticlesManager.getInstance().searchAllArticle();
			listEncheres = EncheresManager.getInstance().searchAllEncheres();
				
			request.setAttribute("articles", listArticles);
			request.setAttribute("encheres", listEncheres);
			request.getRequestDispatcher("/WEB-INF/pages/enchere-participe-inload.jsp").forward(request, response);
		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
			request.getRequestDispatcher("404");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
