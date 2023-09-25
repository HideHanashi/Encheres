 package fr.eni.encheres.ihm;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

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
		util = (Utilisateur) session.getAttribute("utilisateur");
		Integer id = Integer.valueOf(request.getParameter("noArticle"));
		session.setAttribute("idArticle", request.getParameter("nomArticle"));
		List<ArticleVendu> listeArticles = null;
		
		if (request.getParameter("nomArticle") != null) {
			int nomArticle = Integer.parseInt(request.getParameter("nomArticle"));
		}
			if (session.getAttribute("utilisateur") !=null) {
				listeArticles = am.chooseArticle(id, util.getNoUtilisateur());
			}
				for (ArticleVendu article : listeArticles) {
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
                
                    if (article.getRetrait().getRue() != null) {
                    	request.setAttribute("rue", article.getRetrait().getRue());
                    	request.setAttribute("CP", article.getRetrait().getCodePostal());
                    	request.setAttribute("ville", article.getRetrait().getVille());
                    } else {
                    	request.setAttribute("rue", article.getUtilisateur().getRue());
                    	request.setAttribute("CP", article.getUtilisateur().getCodePostal());
                    	request.setAttribute("ville", article.getUtilisateur().getVille());
                    }
                    request.setAttribute("vendeur", article.getUtilisateur().getPseudo());
                    request.setAttribute("Proposition", offre + 1);
				}
				
				 request.getRequestDispatcher("WEB-INF/html/details-vente.jsp").forward(request, response);
			
			}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
