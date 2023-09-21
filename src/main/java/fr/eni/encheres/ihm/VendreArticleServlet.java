package fr.eni.encheres.ihm;

import java.io.IOException;
import java.time.LocalDate;

import fr.eni.encheres.bll.ArticlesManager;
import fr.eni.encheres.bll.ReatraitManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/nouvelle-vente")
public class VendreArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		request.getRequestDispatcher("/WEB-INF/pages/nouvelle-vente.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String nomArticle = request.getParameter("nomArticle");
			String description = request.getParameter("description");
			LocalDate dateDebutEncheres = LocalDate.parse(request.getParameter("dateDebutEncheres"));
			LocalDate dateFinEncheres = LocalDate.parse(request.getParameter("dateFinEncheres"));
			int miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
			int categorie = Integer.parseInt(request.getParameter("categorie"));
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");
			ArticleVendu articleVendu = new ArticleVendu(
			nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, categorie, rue, codePostal, ville
			);
			Retrait retrait = new Retrait(rue, codePostal, ville);
			
			ArticlesManager.getInstance().addArticle(articleVendu);
			ReatraitManager.getInstance().addRetrait(retrait);
			response.sendRedirect( request.getContextPath() +"/liste-encheres");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
