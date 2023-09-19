package fr.eni.encheres.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.bll.ArticlesManager;
import fr.eni.encheres.bo.ArticleVendu;

@WebServlet("")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<ArticleVendu> listArticles = null;
		if (request.getParameter("q") != null) {
			listArticles = ArticlesManager.getInstance().searchArticle(request.getParameter("q"));
		} else {
			listArticles = ArticlesManager.getInstance().searchAllArticle();
		}
		request.setAttribute("articles", listArticles);
		request.setAttribute("annee", LocalDate.now().getYear());

		request.getRequestDispatcher("/WEB-INF/pages/liste-encheres.jsp").forward(request, response);

	}

}
