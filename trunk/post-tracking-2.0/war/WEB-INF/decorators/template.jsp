<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
     <meta charset="utf-8">
		<title><decorator:title default="Tracking Service" /></title>
		
		<!-- Sencha Touch CSS -->
		 <link rel="stylesheet" href="${request.contextPath}/css/sencha-touch.css" type="text/css">

		 <!-- Custom CSS -->
		 <link rel="stylesheet" href="${request.contextPath}/css/track.css" type="text/css">
	
		 <!-- Sencha Touch JS -->
		 <script type="text/javascript" src="${request.contextPath}/js/sencha-touch-debug.js"></script>
	
		 <!-- Application JS -->
		 <script type="text/javascript" src="${request.contextPath}/js/track.js"></script>

		<decorator:head />
  </head>
	<body>
		<decorator:body />
	</body>
</html>