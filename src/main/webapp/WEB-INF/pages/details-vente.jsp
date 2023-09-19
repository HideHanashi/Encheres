<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf" %>
<main class="row">
	<div class="col">
	<div class="row text-center mt-4">
		<h1> Détail vente </h1>
	</div>
	<img src=""/>
		<div class="mb-3">
			<label for="enchere" class="form-label">"objet vente" </label>
			<input type="text" class="form-control" readonly="readonly" value="${ game.enchere }" id="enchere" >
		</div>
		<div class="mb-3">
			<label for="description" class="form-label">Description: </label>
			<input type="text" class="form-control" readonly="readonly" value="${ game.description }" id="description" >
		</div>
		<div class="mb-3">
			<label for="meilleureOffre" class="form-label">Meilleure offre: </label>
			<input type="text" class="form-control" readonly="readonly" value="${ game.meilleureOffre }" id="meilleureOffre" >
		</div>
		<div class="mb-3">
			<label for="miseAPrix" class="form-label">Mise à prix: </label>
			<input type="text" class="form-control" readonly="readonly" value="${ game.miseAPrix }" id="miseAPrix" >
		</div>
		<div class="mb-3">
			<label for="finEnchere" class="form-label">Fin de l'enchère: </label>
			<input type="date" class="form-control" readonly="readonly" value="${ game.finEnchere }" id="finEnchere" >
		</div>
		<div class="mb-3">
			<label for="retrait" class="form-label">Retrait: </label>
			<input type="text" class="form-control" readonly="readonly" value="${ game.retrait }" id="retrait" >
		</div>
		<div class="mb-3">
			<label for="vendeur" class="form-label">Vendeur: </label>
			<input type="text" class="form-control" readonly="readonly" value="${ game.vendeur }" id="vendeur" >
		</div>
		<div class="mb-3">
			<label for="proposition" class="form-label">Ma proposition: </label>
			<input type="number" class="form-control" readonly="readonly" value="${ game.vendeur }" id="vendeur" >
		</div>
		<button class="btn btn-primary" role="button" type="submit" >Enchérir</button>
	</div>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf" %>