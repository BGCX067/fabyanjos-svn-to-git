<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 	prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 	prefix="f" %>
<%@ taglib uri="MyCustomTag" 						prefix="m" %>

<c:if test="${empty page}">
	<jsp:useBean id="page" type="java.lang.Integer"/>
</c:if>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Refresh" 		content="300">
	
	<script type="text/javascript">
		function count(field, target){
			document.getElementById(target).value = field.value.length;
			if(field.value.length >= 140) {
				field.value = field.value.substring(0, 140);
				document.getElementById(target).value = field.value.length;
				return;
			}
		}
	</script>
	
	<title>Home</title>
</head>
<body>

<div id="tweet">
	<form method="post" action="newStatus" accept-charset="UTF-8">
		<textarea rows="3" cols="40" onkeyup="count(this, 'left');" name="status"></textarea>
		<input value="0" id="left" border="0" size="3" readonly="readonly"/>
		<input type="submit" value="Tweet">
	</form>
</div>

<div id="timeline">
	<c:if test="${not empty status}">
		<c:forEach items="${status}" var="s">
			<p>
				<b>${s.user.name}: </b><m:url value="${s.text}"/>, 
				<font size="2px">
					<i><f:formatDate value="${s.createdAt}" pattern="dd/MM/yy HH:mm" timeZone="GMT-3"/></i> - 
					From ${s.source}
				</font>
			</p>
		</c:forEach>
		<c:if test="${page > 1}">
			<a href="/twitter?page=${page-1}">Anterior</a>
		</c:if>
		<a href="/twitter?page=${page+1}">Pr&oacute;ximo</a>
	</c:if>
</div>

</body>
</html>