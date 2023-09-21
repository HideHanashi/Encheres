<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>
<main class="row">
	<div class="col">
		<div class="row text-center mt-4">
			<h1>Mon profil</h1>
		</div>
		<div class="row mt-5">
			<div class="col-4 offset-4">
				<div class="mb-3">
					<label for="pseudo" class="form-label">Pseudo : </label> <input
						type="text" class="form-control" readonly="readonly"
						value="${ user.pseudo }" id="pseudo" name="pseudo">
				</div>
				<div class="mb-3">
					<label for="nom" class="form-label">Nom : </label> <input
						type="text" class="form-control" readonly="readonly"
						value="${ user.nom }" id="nom" name="nom">
				</div>
				<div class="mb-3">
					<label for="prenom" class="form-label">Prénom : </label> <input
						type="text" class="form-control" readonly="readonly"
						value="${ user.prenom }" id="prenom" name="prenom">
				</div>
				<div class="mb-3">
					<label for="mdp" class="form-label">Mot de Passe : </label> <input
						type="text" class="form-control" readonly="readonly"
						value="${ user.motDePasse }" id="mdp" name="mdp">
				</div>
				<div class="mb-3">
					<label for="email" class="form-label">Email : </label> <input
						type="text" class="form-control" readonly="readonly"
						value="${ user.email }" id="email" name="email">
				</div>
				<div class="mb-3">
					<label for="telephone" class="form-label">Téléphone : </label> <input
						type="text" class="form-control" readonly="readonly"
						value="${ user.telephone }" id="telephone" name="telephone">
				</div>
				<div class="mb-3">
					<label for="codePostal" class="form-label">Code Postal : </label> <input
						type="text" class="form-control" readonly="readonly"
						value="${ user.codePostal }" id="codePostal" name="codePostal">
				</div>
				<div class="mb-3">
					<label for="rue" class="form-label">Rue : </label> <input
						type="text" class="form-control" readonly="readonly"
						value="${ user.rue }" id="rue" name="rue">
				</div>
				<div class="mb-3">
					<label for="ville" class="form-label">Ville : </label> <input
						type="text" class="form-control" readonly="readonly"
						value="${ user.ville }" id="ville" name="ville">
				</div>
				<form method="POST"
					action="${ pageContext.request.contextPath }/supprimerprofil"
					onsubmit="return confirm('Voulez-vous vraiment supprimer votre compte ?')">
					<div class="mb-3 text-center mt-5">
						<a class="btn btn-info"
							href="${ pageContext.request.contextPath }/modifiermonprofil?id=${ user.noUtilisateur }"><i
							class="fa-solid fa-pen"></i></a>
						<button type="submit" name="noUtilisateur" value="${ user.noUtilisateur }"
							class="btn btn-danger">
							<i class="fa-solid fa-trash"></i>
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf"%>
