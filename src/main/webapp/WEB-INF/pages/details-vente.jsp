<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf" %>
<main class="row">
	<div class="col position-absolute top-50 start-50 translate-middle">
		<div class="row text-center mt-4">
			<h1> Détail vente </h1>
		</div>
		<form method="post">
			<div class="row gx-5">
				<div class="col">
					<div class="p-3">
						<label for="enchere" class="form-label">Objet en vente :</label>
						<input type="text" class="form-control" readonly="readonly" value="${ article.nomArticle }">
					</div>
					<div class="p-3">
						<label for="description" class="form-label">Description :</label>
						<input type="text" class="form-control" readonly="readonly" value="${ article.description }">
					</div>
					<c:if test="${ article.enchere.montantEnchere != null }">
						<div class="p-3">
							<label for="meilleureOffre" class="form-label">Meilleure offre :</label>
							<input type="text" class="form-control" readonly="readonly" value="${ article.enchere.montantEnchere }" id="prixactuel" name="prixactuel">
						</div>
					</c:if>
					<c:if test="${ article.prixVente == null }">
						<div class="p-3">
							<label for="meilleureOffre" class="form-label">Meilleure offre :</label>
							<input type="text" class="form-control" readonly="readonly" value="${ article.miseAPrix }" id="prixactuel" name="prixactuel">
						</div>
					</c:if>
					<div class="p-3">
						<label for="miseAPrix" class="form-label">Mise à prix :</label>
						<input type="text" class="form-control" readonly="readonly" value="${ article.miseAPrix }">
					</div>
					<div class="p-3">
						<label for="finEnchere" class="form-label">Fin de l'enchère :</label>
						<input type="date" class="form-control" readonly="readonly" value="${ article.dateFinEncheres }">
					</div>
					<div class="p-3">
						<label for="vendeur" class="form-label">Vendeur :</label>
						<input type="text" class="form-control" readonly="readonly" value="${ article.utilisateur.pseudo }">
					</div>
				</div>
				<div class="col">
					<div class="card p-3" style="width: 30rem;">
						<div class="card-body">
							<h5 class="card-title">Lieu de Retrait</h5>
							<div class="p-3">
								<label for="rue" class="form-label">Rue :</label>
								<input type="text" class="form-control" readonly="readonly" value="${ retrait.rue }">
							</div>
							<div class="p-3">
								<label for="codePostal" class="form-label">Code Postal :</label>
								<input type="text" class="form-control" readonly="readonly" value="${ retrait.codePostal }">
							</div>
							<div class="p-3">
								<label for="ville" class="form-label">Ville :</label>
								<input type="text" class="form-control" readonly="readonly" value="${ retrait.ville }">
							</div>
						</div>
					</div>
					<c:if test="${ user != null }">
						<div class="p-3">
								<label for="proposition" class="form-label">Ma proposition :</label>
								<!-- <p class="fst-italic">Vous ne pouvez enchérir qu'une seule fois à la fois.</p> -->
								<input type="number" class="form-control" id="encherir" name="encherir">
								<button class="btn btn-primary" role="button" href="${ pageContext.request.contextPath }/detailvente" 
								type="submit" >Enchérir</button>
						</div>
					</c:if>
					<c:if test="${ user == null }">
						<div class="p-3">
							<p><label for="proposition" class="form-label">Vous devez être connecté pour enchérir</label></p>
							<a class="btn btn-primary" role="button" href="${ pageContext.request.contextPath }/connexion" >Connexion</a>
							<div class="mb-5 mt-2"><a href="${ pageContext.request.contextPath }/inscription">Vous n'avez pas encore de compte ?</a></div>
						</div>
					</c:if>
				</div>
			</div>
		</form>
	</div>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf" %>