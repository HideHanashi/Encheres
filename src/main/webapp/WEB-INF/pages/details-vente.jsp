<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf" %>
<main class="row">
	<div class="col">
		<div class="row text-center mt-4"><h1> Détail vente </h1></div>
		<div class="row gx-5">
			<div class="col">
				<div class="p-3"><label for="enchere" class="form-label">Objet en vente :</label><input type="text" class="form-control" readonly="readonly" value="${ enchere.articleVendu.nomArticle }" id="enchere" ></div>
				<div class="p-3"><label for="description" class="form-label">Description :</label><input type="text" class="form-control" readonly="readonly" value="${ enchere.articleVendu.description }" id="description" ></div>
				<div class="p-3"><label for="meilleureOffre" class="form-label">Meilleure offre :</label><input type="text" class="form-control" readonly="readonly" value="${ enchere.montantEnchere }" id="meilleureOffre" ></div>
				<div class="p-3"><label for="miseAPrix" class="form-label">Mise à prix :</label><input type="text" class="form-control" readonly="readonly" value="${ enchere.articleVendu.miseAPrix }" id="miseAPrix" ></div>
				<div class="p-3"><label for="finEnchere" class="form-label">Fin de l'enchère :</label><input type="date" class="form-control" readonly="readonly" value="${ enchere.articleVendu.dateFinEncheres }" id="finEnchere" ></div>
				<div class="p-3"><label for="vendeur" class="form-label">Vendeur :</label><input type="text" class="form-control" readonly="readonly" value="${ enchere.utilisateur.pseudo }" id="vendeur" ></div>
			</div>
			<div class="col">
				<div class="card p-3" style="width: 30rem;">
					<div class="card-body">
						<h5 class="card-title">Lieu de Retrait</h5>
						<div class="p-3"><label for="rue" class="form-label">Rue :</label><input type="text" class="form-control" readonly="readonly" value="${ enchere.articleVendu.retrait.rue }" name="rue" id="rue"></div>
						<div class="p-3"><label for="codePostal" class="form-label">Code Postal :</label><input type="text" class="form-control" readonly="readonly" value="${ enchere.articleVendu.retrait.codePostal }" name="codePostal" id="codePostal"></div>
						<div class="p-3"><label for="ville" class="form-label">Ville :</label><input type="text" class="form-control" readonly="readonly" value="${ enchere.articleVendu.retrait.ville }" name="ville" id="ville"></div>
					</div>
				</div>
				<div class="p-3">
					<label for="proposition" class="form-label">Ma proposition :</label>
					<input type="number" class="form-control" readonly="readonly" value="${ user.pseudo }" id="vendeur" >
					<button class="btn btn-primary" role="button" type="submit" >Enchérir</button>
				</div>
			</div>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf" %>