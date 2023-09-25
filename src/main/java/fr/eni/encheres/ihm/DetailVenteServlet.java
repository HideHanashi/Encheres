package fr.eni.encheres.ihm;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import fr.eni.encheres.bll.ArticlesManager;
import fr.eni.encheres.bll.EncheresManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
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
		
		HttpSession session = request.getSession();
		util = (Utilisateur) session.getAttribute("utilisateur");
		Integer id = Integer.valueOf(request.getParameter("noArticle"));

			ArticleVendu article = ArticlesManager.getInstance().recupArticle(id);
					request.setAttribute("nomArticle", article.getNomArticle());
					request.setAttribute("credit", article.getUtilisateur().getCredit());
					request.setAttribute("description", article.getDescription());
					request.setAttribute("categorie", article.getCategorie().getLibelle());
                    int offre = ((ArticleVendu) article.getEnchere()).getPrixVente();
                    request.setAttribute("offre", ((ArticleVendu) article.getEnchere()).getPrixVente());
                    request.setAttribute("gagnant", ((ArticleVendu) article.getEnchere()).getGagnant());
                    request.setAttribute("prixInitial", article.getMiseAPrix());
                    String dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.FRANCE).format(article.getDateFinEncheres());
                    request.setAttribute("dateFinEnchere", dateFormatter);
                    request.setAttribute("rue", article.getRetrait().getRue());
                    request.setAttribute("CP", article.getRetrait().getCodePostal());
                    request.setAttribute("ville", article.getRetrait().getVille());
                    request.setAttribute("vendeur", article.getUtilisateur().getPseudo());
                    request.setAttribute("Proposition", offre);
                    request.getRequestDispatcher("WEB-INF/html/details-vente.jsp").forward(request, response);
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
