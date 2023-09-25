<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>
<main class="row">
	<div class="col">
		<div class="row mt-5">
			<div class="col-4 offset-4">
				<div class="mb-3"><label for="pseudo" class="form-label">Pseudo: </label><input type="text" class="form-control" readonly="readonly" value="${ otheruser.pseudo }" id="pseudo" name="pseudo"></div>
				<div class="mb-3"><label for="nom" class="form-label">Nom: </label><input type="text" class="form-control" readonly="readonly" value="${ otheruser.nom }" id="nom" name="nom"></div>
				<div class="mb-3"><label for="prenom" class="form-label">Prénom: </label><input type="text" class="form-control" readonly="readonly" value="${ otheruser.prenom }" id="prenom" name="prenom"></div>
				<div class="mb-3"><label for="email" class="form-label">Email: </label><input type="text" class="form-control" readonly="readonly"value="${ otheruser.email }" id="email" name="email"></div>
				<div class="mb-3"><label for="telephone" class="form-label">Téléphone: </label><input type="text" class="form-control" readonly="readonly" value="${ otheruser.telephone }" id="telephone" name="telephone"></div>
				<div class="mb-3"><label for="codePostal" class="form-label">Code Postal: </label><input type="text" class="form-control" readonly="readonly" value="${ otheruser.codePostal }" id="codePostal" name="codePostal"></div>
				<div class="mb-3"><label for="rue" class="form-label">Rue: </label><input type="text" class="form-control" readonly="readonly" value="${ otheruser.rue }" id="rue" name="rue"></div>
				<div class="mb-3"><label for="ville" class="form-label">Ville: </label><input type="text" class="form-control" readonly="readonly" value="${ otheruser.ville }" id="ville" name="ville"></div>
				<div class="row mt-5">
					<div class="mb-3"><a class="btn btn-secondary" href="${pageContext.request.contextPath}">Retour</a></div>
				</div>
			</div>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf"%>
