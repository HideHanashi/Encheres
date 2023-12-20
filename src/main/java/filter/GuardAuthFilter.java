package filter;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// Définition d'un filtre Web avec certaines configurations
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, urlPatterns = { "/deconnexion", "/modifiermonprofil",
		"/supprimermonprofil", "/monprofil", "/nouvelle-vente", "/voirarticles", "/modifiermesarticles", "/articleparticipe" })
public class GuardAuthFilter extends HttpFilter implements Filter {

	// Numéro de version de la classe (non utilisé dans ce code)
	private static final long serialVersionUID = 1L;

	// Méthode de filtrage des requêtes
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// Conversion des objets de requête et de session pour les utiliser spécifiquement pour les requêtes HTTP
		HttpServletRequest rq = (HttpServletRequest) request;
		HttpSession session = rq.getSession();

		// Vérifie si l'utilisateur est connecté en vérifiant la présence d'un attribut "user" dans la session
		if (session.getAttribute("user") == null) {
			// Si l'utilisateur n'est pas connecté, redirige vers la page de connexion
			HttpServletResponse rs = (HttpServletResponse) response;
			rs.sendRedirect(rq.getContextPath() + "/connexion");
			return; // Arrête le traitement du filtre
		}

		// Si l'utilisateur est connecté, permet à la requête de continuer son traitement normal
		chain.doFilter(request, response);
	}
}
