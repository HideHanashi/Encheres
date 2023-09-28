<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf" %>
<main class="row">
	<div class="mdp-reset">	
		<div class="row mt-5">
			<div class="col-4 offset-4">
				<h1 class="mb-3"> Mot de passe oublié </h1>
				<c:if test="${ !empty error }"><div class="alert alert-danger">${ error }</div></c:if>
				<form method="post">
					<div class="mb-3"><label for="email" class="form-label">Adresse mail: </label> <input type="email" class="form-control" name="email" id="email"placeholder="ex. your@email.com"></div>
					<div class="mb-3"><label for="code" class="form-label">Code: </label> <input type="text" class="form-control" name="code" id="code"></div>
					<div class="mb-3"><label for="password" class="form-label">Nouveau mot de passe: </label> <input type="password" class="form-control" name="password" id="password"></div>
					<button class="btn btn-primary" role="button" type="submit">Valider</button>
				</form>
			</div>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf"%>