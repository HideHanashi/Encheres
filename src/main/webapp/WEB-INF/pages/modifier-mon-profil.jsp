<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>
<main class="row">
	<div>
		<h1 class="row d-flex justify-content-center mb-5 p-2">Mon Profil</h1>
		<div class="p-3">
			<form action="PageMonProfil" method="post">
				<div class="row mb-3">
					<div class="col">
						<div class="row">
							<div class="col-4"><label>Pseudo:</label></div>
							<div class="col-8">
								<div class="input-group"><input class="case" type="text" id="pseudo" value="${user.pseudo}" required></div>
							</div>
						</div>
					</div>
					<div class="col">
						<div class="row">
							<div class="col-4"><label>Nom:</label></div>
							<div class="col-8">
								<div class="input-group"><input class="case" type="text" id="nom" value="${user.nom}" required></div>
							</div>
						</div>
					</div>
				</div>

				<div class="row mb-3">
					<div class="col">
						<div class="row">
							<div class="col-4"><label>Prénom:</label></div>
							<div class="col-8">
								<div class="input-group"><input class="case" type="text" id="prenom" value="${user.prenom}" required></div>
							</div>
						</div>
					</div>
					<div class="col">
						<div class="row">
							<div class="col-4"><label>Email:</label></div>
							<div class="col-8">
								<div class="input-group"><input class="case" type="email" id="email" value="${user.email}" required></div>
							</div>
						</div>
					</div>
				</div>

				<div class="row mb-3">
					<div class="col">
						<div class="row">
							<div class="col-4"><label>Téléphone:</label></div>
							<div class="col-8">
								<div class="input-group"><input class="case" type="text" id="telephone" value="${user.telephone}" required></div>
							</div>
						</div>
					</div>
					<div class="col">
						<div class="row">
							<div class="col-4"><label>Rue:</label></div>
							<div class="col-8">
								<div class="input-group"><input class="case" type="text" id="rue" value="${user.rue}" required></div>
							</div>
						</div>
					</div>
				</div>

				<div class="row mb-3">
					<div class="col">
						<div class="row">
							<div class="col-4"><label>Code postal:</label></div>
							<div class="col-8">
								<div class="input-group"><input class="case" type="text" name="CP" id="codePostal" value="${user.codePostal}" required></div>
							</div>
						</div>
					</div>
					<div class="col">
						<div class="row">
							<div class="col-4"><label>Ville:</label></div>
							<div class="col-8">
								<div class="input-group"><input class="case" type="text" id="ville" value="${user.ville}" required></div>
							</div>
						</div>
					</div>
				</div>

				<div class="row mb-3">
					<div class="col">
						<div class="row">
							<div class="col-4"><label>Mot de passe actuel:</label></div>
							<div class="col-8">
								<div class="input-group"><input class="case" type="password" id="motPasseActuel" value="${user.motDePasse}"></div>
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
							<div class="col-4"><label>Nouveau mot de passe:</label></div>
							<div class="col-8">
								<div class="input-group"><input class="case" type="password" id="nouveauMotPasse"></div>
							</div>
						</div>
					</div>
					<div class="col">
						<div class="row">
							<div class="col-4"><label>Confirmation:</label></div>
							<div class="col-8">
								<div class="input-group"><input class="case" type="password" id="confirmation"></div>
							</div>
						</div>
					</div>
				</div>

				<div class="row mb-3">
					<div class="col">
						<div class="row">
							<div class="col-2"><label>Crédit:</label></div>
							<div class="col-1">
								<div class="input-group"><label id="credit" >${user.credit}</label></div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="row mb-3">
			<div class="col d-flex justify-content-center"><button type="submit" name="button" class="btn btn-dark" value="valider">Enregistrer</button></div>
			<div class="col">
				<div class="col d-flex justify-content-center"><a class="btn btn-dark" href="${ pageContext.request.contextPath }/supprimermonprofil">Supprimer mon compte</a></div>
			</div>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf"%>
