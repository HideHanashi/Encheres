<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf" %>
<main class="row">
	<div class="col position-absolute top-50 start-50 translate-middle">	
		<div class="row mt-5">
			<div class="col-4 offset-4">
				<h1 class="mb-3"> Connexion </h1>
				<c:if test="${ !empty success }">
					<div class="alert alert-success">${ success }</div>
				</c:if>
				<c:if test="${ !empty error }">
					<div class="alert alert-danger">${ error }</div>
				</c:if>
				<c:if test="${ param.err == 1 }">
					<div class="alert alert-danger">Pour mettre un article en enchère vous devez vous connecter.</div>
				</c:if>
				<form method="post">
					<div class="mb-3">
						<label for="email" class="form-label">Adresse mail : </label> <input
							type="email" class="form-control" name="email" id="email"
							placeholder="ex. your@email.com">
					</div>
					<div class="mb-3">
						<label for="password" class="form-label">Mot de passe : </label> <input
							type="password" class="form-control" name="password"
							id="password">
					</div>
					<div class="mt-4 mdpforget">
						<button class="btn btn-primary" role="button" type="submit">Connexion</button>
						<a class="btn btn-secondary" href="${ pageContext.request.contextPath }/inscription">Créer un compte</a>
					</div>
					<div class="mb-5 mt-2 mdpforget">
						<a href="${ pageContext.request.contextPath }/oubliepassword">Mot de passe oublié ?</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf"%>