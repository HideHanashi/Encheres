 package fr.eni.encheres.ihm;

import java.io.IOException;
import java.util.List;

import fr.eni.encheres.bll.ArticlesManager;
import fr.eni.encheres.bll.EncheresManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/detailvente")
public class DetailVenteServlet extends HttpServlet {
	private ArticlesManager am = new ArticlesManager();
    private EncheresManager em = new EncheresManager();
	Utilisateur util = null;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.setAttribute("noArticle", request.getParameter("nameArticle"));
		util = (Utilisateur) session.getAttribute("utilisateur");
		
		if (session.getAttribute("utilisateur") != null) {
			List<ArticleVendu> ListeArticles = am.ChoisirArticlesEncherir(id, util.getNoUtilisateur());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
