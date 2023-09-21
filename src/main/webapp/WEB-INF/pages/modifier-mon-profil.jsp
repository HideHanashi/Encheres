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
				<form action="" method="POST">
					<div class="mb-3">
						<label for="pseudo" class="form-label">Pseudo : </label> <input
							type="text" class="form-control" value="${ user.pseudo }"
							id="pseudo" name="pseudo">
					</div>
					<div class="mb-3">
						<label for="nom" class="form-label">Nom : </label> <input
							type="text" class="form-control" value="${ user.nom }" id="nom"
							name="nom">
					</div>
					<div class="mb-3">
						<label for="prenom" class="form-label">Prénom : </label> <input
							type="text" class="form-control" value="${ user.prenom }"
							id="prenom" name="prenom">
					</div>
					<div class="mb-3">
						<label for="mdp" class="form-label">Mot de Passe : </label> <input
							type="text" class="form-control" value="${ user.motDePasse }"
							id="mdp" name="mdp">
					</div>
					<div class="mb-3">
						<label for="email" class="form-label">Email : </label> <input
							type="text" class="form-control" value="${ user.email }"
							id="email" name="email">
					</div>
					<div class="mb-3">
						<label for="telephone" class="form-label">Téléphone : </label> <input
							type="text" class="form-control" value="${ user.telephone }"
							id="telephone" name="telephone">
					</div>
					<div class="mb-3">
						<label for="codePostal" class="form-label">Code Postal : </label>
						<input type="text" class="form-control"
							value="${ user.codePostal }" id="codePostal" name="codePostal">
					</div>
					<div class="mb-3">
						<label for="rue" class="form-label">Rue : </label> <input
							type="text" class="form-control" value="${ user.rue }" id="rue"
							name="rue">
					</div>
					<div class="mb-3">
						<label for="ville" class="form-label">Ville : </label> <input
							type="text" class="form-control" value="${ user.ville }"
							id="ville" name="ville">
					</div>
					<div class="mb-3 text-center mt-5">
						<button class="btn btn-primary" type="submit">
							<i class="fa-regular fa-floppy-disk"></i>
						</button>
						<button class="btn btn-basic" type="reset">
							<i class="fa-solid fa-rotate-right fa-rotate-180"></i>
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf"%>
