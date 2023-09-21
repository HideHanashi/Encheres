<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>
<main class="row">
	<div class="container shadow p-4">
    <h1 class="row d-flex justify-content-center mb-5 p-2">Mon Profil</h1>
        <div class="p-3">
            <form action="PageMonProfil" method="post">
            <div class="row mb-3">
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label>Pseudo: </label>
                        </div>
                        <div class="col-8">
                            <div class="input-group input-group-sm mb-3">
                                <input class="w-100" type="text" name="pseudo" id="pseudo" value="${user.pseudo}" required>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label>Nom: </label>
                        </div>
                        <div class="col-8">
                            <div class="input-group input-group-sm mb-3">
                                <input class="w-100" type="text" name="nom" id="nom" value="${user.nom}" required pattern="[A-Za-z]{1,30}">
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label>Prénom: </label>
                        </div>
                        <div class="col-8">
                            <div class="input-group input-group-sm mb-3">
                                <input class="w-100" type="text" name="prenom" id="prenom" value="${user.prenom}" required pattern="[A-Za-z]{1,30}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label>Email: </label>
                        </div>
                        <div class="col-8">
                            <div>

                            </div>
                            <div class="input-group input-group-md mb-3">
                                <input class="w-100" type="email" name="email" id="email" value="${user.email}" required>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label>Téléphone: </label>
                        </div>
                        <div class="col-8">
                            <div class="input-group input-group-sm mb-3">
                                <input class="w-100" type="text" name="telephone" id="telephone" value="${user.telephone}" pattern="^[0-9-.// |]{1,15}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label>Rue: </label>
                        </div>
                        <div class="col-8">
                            <div class="input-group input-group-sm mb-3">
                                <input class="w-100" type="text" name="rue" id="rue" value="${user.rue}" required>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label>Code postal: </label>
                        </div>
                        <div class="col-8">
                            <div class="input-group input-group-sm mb-3">
                                <input class="w-100" type="text" name="CP" id="codePostal" value="${user.codePostal}" required pattern="[0-9]{5}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label>Ville: </label>
                        </div>
                        <div class="col-8">
                            <div class="input-group input-group-sm mb-3">
                                <input class="w-100" type="text" name="ville" id="ville" value="${user.ville}" required pattern="^[A-Za-z'- ]{1,30}">
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label>Mot de passe actuel: </label>
                        </div>
                        <div class="col-8">
                            <div class="input-group input-group-sm mb-3">
                                <input class="w-100" type="password" name="mdpa" id="motPasseActuel" value="${user.motDePasse}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label></label>
                        </div>
                        <div class="col-8">
                            <label></label>
                        </div>
                    </div>
                </div>
            </div>

                <div class="row mb-3">
                    <div class="col">
                        <div class="row">
                            <div class="col-4">
                                <label>Nouveau mot de passe: </label>
                            </div>
                            <div class="col-8">
                                <div class="input-group input-group-sm mb-3">
                                    <input class="w-100" type="password" name="nmdp" id="nouveauMotPasse">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col">
                        <div class="row">
                            <div class="col-4">
                                <label>Confirmation: </label>
                            </div>
                            <div class="col-8">
                                <div class="input-group input-group-sm mb-3">
                                    <input class="w-100" type="password" name="cmdp" id="confirmationMotPasse">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row mb-3">
                    <div class="col">
                        <div class="row">
                            <div class="col-2">
                                <label>Crédit: </label>
                            </div>
                            <div class="col-1">
                                <div class="input-group input-group-sm mb-3">
                                    <label id="credit" name="credit">${user.credit}</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    <div class="row mb-3">
        <div class="col d-flex justify-content-center">
                <button type="submit" name="button" class="btn btn-dark" value="valider">Enregistrer</button>
        </div>
        <div class="col">
            <div class="col d-flex justify-content-center">
                <button type="submit" name="button" class="btn btn-dark" value="supprimer">Supprimer mon compte</button>
            </div>
        </div>
        </form>
    </div>
</div></main>
<%@ include file="/WEB-INF/fragments/footer.jspf"%>
