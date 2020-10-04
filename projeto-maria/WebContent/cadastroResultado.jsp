<%@page import="beans.BeanProjeto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="resources/css/cadastro.css" />
		<script src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>
		<title>Cadastro de Resultado da Maria</title>
	</head>
<body>
	<a href="acessoliberado.jsp"><img alt="Inicio" title="Inicio" src="resources/img/home.png" width="50px" height="50px"></a>
	<a href="index.jsp"><img alt="Sair" title="Sair" src="resources/img/sair.png" width="50px" height="50px"></a>
	
	<center>
		<h1>Cadastro de Resultado de Basquete da Maria</h1>
		<h3 style="color:orange">${msg}</h3>
	</center>
	<form action="salvarResultado" method="post" id="formUser" 
	   onsubmit="return validarCampos()? true : false;" enctype="multipart/form-data" >
		<ul class="form-style-1">
			<li>
				<table >
					<tr>
						<td>ID:</td>
						<td><input type="text" readonly="readonly" id="id" name="id" value="${resultado.id}" /></td>
						
					</tr>
					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login" value="${resultado.login}" maxlength="10" placeholder="Informe o login"/></td>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha" value="${resultado.senha}" maxlength="10" placeholder="Informe a senha"/></td>
					</tr>
					<tr>
						<td>Jogo:</td>
						<td><input type="text" id="jogo" name="jogo" value="${resultado.jogo}" maxlength="50" placeholder ="Informe o jogo"/></td>
						<td>Placar:</td>
						<td><input type="text" id="placar" name="placar" value="${resultado.placar}" maxlength="50" placeholder ="Informe o placar"/></td>
					</tr>
					<tr>
						<td>Minímo Temporada:</td>
						<td><input type="text" id="minimotemporada" name="minimotemporada" value="${resultado.minimotemporada}" maxlength="50" /></td>
						<td>Máximo Temporada:</td>
						<td><input type="text" id="maximotemporada" name="maximotemporada" value="${resultado.maximotemporada}" maxlength="50" /></td>
					</tr>
					<tr>
						<td>Quebra recorde min.:</td>
						<td><input type="text" id="quebraminimo" name="quebraminimo" value="${resultado.quebraminimo}" maxlength="50" /></td>
						<td>Quebra recorde max.:</td>
						<td><input type="text" id="quebramaximo" name="quebramaximo" value="${resultado.quebramaximo}" maxlength="50" /></td>
						
						
					</tr>
	
				
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar" style="width: 173px;"/></td>
						<td></td>
						<td>  <input type="submit" style="width: 173px;" value="Cancelar" onclick="document.getElementById('formUser').action = 'salvarResultado?acao=reset'" /></td>
					</tr>
					
				</table>
			</li>
		</ul>
	</form>
	
	<form method="post" action="servletPesquisa" style="width: 90%">
	<ul class="form-style-1">
			<li>
				<table>
				  <tr>
				   <td>Descrição</td>
				   <td><input type="text"  id="descricaoconsulta" name="descricaoconsulta" placeholder="Informe o jogo"></td>
				   <td><input type="submit" value="Pesquisar"></td>
				  </tr>
				</table>
		   </li>
		</ul>
	</form>	
	
	<div class="container">
		<table class="responsive-table">
			<caption>Lista de resultados</caption>
			<tr>
				<th>Id</th>
				
				
				<th>Jogo</th>
				<th>Placar</th>
				<th>Minímo da Temporada</th>
				<th>Maxímo da Temporada</th>
				<th>Quebra recorde min.</th>
				<th>Quebra recorde max.</th>
				<th>Delete</th>
				<th>Editar</th>
			</tr>
			<c:forEach items="${resultados}" var="resultado">
				<tr>
					<td><c:out value="${resultado.id}" /></td>
					<td><c:out value="${resultado.jogo}" /></td>
					<td><c:out value="${resultado.placar}" /></td>
					<td><c:out value="${resultado.minimotemporada}" /></td>
					<td><c:out value="${resultado.maximotemporada}" /></td>
					<td><c:out value="${resultado.quebraminimo}" /></td>
					<td><c:out value="${resultado.quebramaximo}" /></td>
					
					<td><a href="salvarResultado?acao=delete&resultado=${resultado.id}" onclick="return confirm('Confirmar a exclusão?');" ><img src="resources/img/icon.png" alt="Excluir" title="Excluir" width="32px" height="32px" /></a></td>
					<td><a href="salvarResultado?acao=editar&resultado=${resultado.id}"><img src="resources/img/editar.png" alt="Editar" title="Editar" width="32px" height="32px"/></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript">
		function validarCampos() {
			if(document.getElementById("login").value == '') {
				alert("Informe o Login!");
				return false;
			} else if(document.getElementById("senha").value == '') {
				alert("Informe a Senha!");
				return false;
			} else if(document.getElementById("jogo").value == '') {
				alert("Informe o Jogo!");
				return false;
			
			}
			return true;
		}
		
		
		
		

	
	</script>
</body>
</html>