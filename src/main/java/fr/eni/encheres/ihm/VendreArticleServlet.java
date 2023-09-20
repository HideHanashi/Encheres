package fr.eni.encheres.ihm;

import java.io.IOException;
import java.time.LocalDate;

import fr.eni.encheres.bll.ArticlesManager;
import fr.eni.encheres.bo.ArticleVendu;
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
			int noArticle =   Integer.parseInt(request.getParameter("noArticle"));
			String nomArticle =   request.getParameter("nomArticle");
			String description =   request.getParameter("description");
			LocalDate dateDebutEncheres =  LocalDate.parse(request.getParameter("dateDebutEncheres"));
			LocalDate dateFinEncheres =  LocalDate.parse(request.getParameter("dateFinEncheres"));
			int miseAPrix =   Integer.parseInt(request.getParameter("miseAPrix"));
			int prixVente =   Integer.parseInt(request.getParameter("prixVente"));
			String etatVente =   request.getParameter("etatVente");			
			ArticleVendu articleVendu = new ArticleVendu(noArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, prixVente, etatVente);
			
			ArticlesManager.getInstance().addArticle(articleVendu);
			response.sendRedirect( request.getContextPath() +"/liste-encheres");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
