<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf" %>
<main class="row">
	<div class="col position-absolute top-50 start-50 translate-middle">
		<div class="row text-center mt-4"><h1> Inscription </h1></div>
		<div class="container overflow-hidden text-center">
			<c:if test="${ ! empty error }">
				<div class="alert alert-danger">${ error }</div>
			</c:if>
			<form method="post" >
				<div class="row gx-5">
					<div class="col">
						<div class="p-3"><label for="pseudo" class="form-label">Pseudo : </label><input type="text" class="form-control" value="${ param.pseudo }" name="pseudo" id="pseudo"></div>
						<div class="p-3"><label for="nom" class="form-label">Nom : </label><input type="text" class="form-control" value="${ param.nom }" name="nom" id="nom"></div>
						<div class="p-3"><label for="prenom" class="form-label">Prénom : </label><input type="text" class="form-control" value="${ param.prenom }" name="prenom" id="prenom"></div>
						<div class="p-3"><label for="mdp" class="form-label">Mot de passe : </label><input type="password" class="form-control" value="${ param.mdp }" name="mdp" id="mdp"></div>
						<div class="p-3"><label for="email" class="form-label">Email : </label><input type="email" class="form-control" value="${ param.email }" name="email" id="email"></div>
					</div>
					<div class="col">
						<div class="p-3"><label for="telephone" class="form-label">Teléphone : </label><input type="number" class="form-control" value="${ param.telephone }" name="telephone" id="telephone"></div>
						<div class="p-3"><label for="codePostal" class="form-label">Code Postal : </label><input type="number" class="form-control" value="${ param.codePostal }" name="codePostal" id="codePostal"></div>
						<div class="p-3"><label for="rue" class="form-label">Rue : </label><input type="text" class="form-control" value="${ param.rue }" name="rue" id="rue"></div>
						<div class="p-3"><label for="ville" class="form-label">Ville : </label><input type="text" class="form-control" value="${ param.ville }" name="ville" id="ville"></div>
						<div class="p-3 mt-4 pt-4"><button class="btn btn-primary" role="button" type="submit" >Inscription</button>
						<a class="btn btn-secondary" role="button" type="reset" href="${ pageContext.request.contextPath }">Annuler</a></div>
					</div>
				</div>
			</form>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf" %>