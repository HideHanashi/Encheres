package fr.eni.encheres.ihm;

import java.io.IOException;

import fr.eni.encheres.bll.ArticlesManager;
import fr.eni.encheres.bll.RetraitManager;
import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/detailvente")
public class DetailVenteServlet extends HttpServlet {
	Utilisateur util = null;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			HttpSession session = request.getSession();
			Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("user");
			int idUser = utilisateurSession.getNoUtilisateur();
			Utilisateur utilisateur = UtilisateursManager.getInstance().recupUtilisateur(idUser);
			
			int idArticle = Integer.parseInt(request.getParameter("auction"));
			Retrait retrait = RetraitManager.getInstance().recupRetrait(idArticle);
			ArticleVendu article = ArticlesManager.getInstance().recupArticle(idArticle);
			System.out.println(retrait);
			
			request.setAttribute("article", article);
			request.setAttribute("retrait", retrait);
			request.setAttribute("utilisateur", utilisateur);
			request.getRequestDispatcher("/WEB-INF/pages/details-vente.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		Enchere enchere = new Enchere();
//		LocalDate ts = LocalDate.from(Instant.now());
//		enchere.setDateEnchere(ts);
//		enchere.setUtilisateur(util.getNoUtilisateur());
//		enchere.setNoArticle(Integer.parseInt((String) session.getAttribute("idArticle")));
//		enchere.setMontantEnchere(Integer.parseInt(request.getParameter("maProposition")));
//	}
}
