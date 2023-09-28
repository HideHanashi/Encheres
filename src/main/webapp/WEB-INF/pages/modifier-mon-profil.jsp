<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>
<main class="row">
	<form method="post" action="">
		<div class="modifierprofil">
			<h1 class="row d-flex justify-content-center mb-5 p-2">Modifier mon Profil</h1>
			<div class="p-3 profilmodifier">
				<div class="row mb-3">
					<div class="col">
						<div class="row">
							<div class="col-4"><label>Pseudo:</label></div>
							<div class="col-8">
								<div class="case"><input class="case" type="text" id="pseudo" name="pseudo" value="${user.pseudo}" required></div>
							</div>
						</div>
					</div>
					<div class="col">
						<div class="row">
							<div class="col-4"><label>Nom:</label></div>
							<div class="col-8">
								<div class="case"><input class="case" type="text" id="nom" name="nom" value="${user.nom}" required></div>
							</div>
						</div>
					</div>
				</div>

				<div class="row mb-3">
					<div class="col">
						<div class="row">
							<div class="col-4"><label>Prénom:</label></div>
							<div class="col-8">
								<div class=""><input class="case" type="text" id="prenom" name="prenom" value="${user.prenom}" required></div>
							</div>
						</div>
					</div>
					<div class="col">
						<div class="row">
							<div class="col-4"><label>Email:</label></div>
							<div class="col-8">
								<div class=""><input class="case" type="email" id="email" name="email" value="${user.email}" required></div>
							</div>
						</div>
					</div>
				</div>

				<div class="row mb-3">
					<div class="col">
						<div class="row">
							<div class="col-4"><label>Téléphone:</label></div>
							<div class="col-8">
								<div class=""><input class="case" type="text" id="telephone" name="telephone" value="${user.telephone}" required></div>
							</div>
						</div>
					</div>
					<div class="col">
						<div class="row">
							<div class="col-4"><label>Rue:</label></div>
							<div class="col-8">
								<div class=""><input class="case" type="text" id="rue" name="rue" value="${user.rue}" required></div>
							</div>
						</div>
					</div>
				</div>

				<div class="row mb-3">
					<div class="col">
						<div class="row">
							<div class="col-4"><label>Code postal:</label></div>
							<div class="col-8">
								<div class=""><input class="case" type="text" name="codePostal" id="codePostal" value="${user.codePostal}" required></div>
							</div>
						</div>
					</div>
					<div class="col">
						<div class="row">
							<div class="col-4"><label>Ville:</label></div>
							<div class="col-8">
								<div class=""><input class="case" type="text" id="ville" name="ville"value="${user.ville}" required></div>
							</div>
						</div>
					</div>
				</div>

				<div class="row mb-3">
					<div class="col">
						<div class="row">
							<div class="col-4"><label>Mot de passe actuel:</label></div>
							<div class="col-8">
								<div class=""><input class="case" type="password" id="motDePasse" name="motDePasse"></div>
							</div>
						</div>
					</div>
					<div class="col">
						<div class="row">
							<div class="col-4"><label></label></div>
							<div class="col-8"><label></label></div>
						</div>
					</div>
				</div>
				<div class="row mb-3">
					<div class="col">
						<div class="row">
							<div class="col-4"><label>Nouveau Mot de passe :</label></div>
							<div class="col-8">
								<div class=""><input class="case" type="password" id="newMotDePasse" name="newMotDePasse"></div>
							</div>
						</div>
					</div>
					<div class="col">
						<div class="row">
							<div class="col-4"><label>Confirmation :</label></div>
							<div class="col-8">
								<div class=""><input class="case" type="password" id="confirm" name="confirm"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="row mb-3">
					<div class="col">
						<div class="row">
							<div class="col-2"><label>Crédit:</label></div>
							<div class="col-1">
								<div class=""><label id="credit">${user.credit}</label></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row mb-3">
				<div class="col d-flex justify-content-center"><button type="submit" name="button" class="btn btn-dark" value="valider">Enregistrer</button></div>
				<div class="col">
					<div class="col d-flex justify-content-center"><a class="btn btn-dark"href="${ pageContext.request.contextPath }/supprimermonprofil">Supprimer mon compte</a></div>
				</div>
			</div>
		</div>
	</form>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf"%>
