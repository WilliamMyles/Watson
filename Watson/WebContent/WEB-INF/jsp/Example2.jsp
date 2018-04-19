<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta
	http-equiv="Content-Type"
	content="text/html; charset=ISO-8859-1">
		<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.tablesorter.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/load.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
<title>Example 2</title>
</head>
<body>
<div class='controls'>
	<form method='GET' action='Example2'>
		Document Sentiment: <input type='checkbox' name='sentiment' checked><br><br>
		Emotion Types: <br>
		Joy: <input type='checkbox' name='joy' checked><br>
		Disgust: <input type='checkbox' name='disgust' checked><br>
		Fear: <input type='checkbox' name='fear' checked><br>
		Anger: <input type='checkbox' name='anger' checked><br>
		Sadness: <input type='checkbox' name='sadness' checked><br><br>
		SubReddit: <input type='text' name='sub'><br><br>
		Max number of posts:<input type='number' name='max' value='5'><br>
		(The higher the number, the longer it takes)<br>
		<input type='submit' value='Analyse'><br><br>
	</form>
</div>
<div class='table-div'>
	<table id='myTable' class='main-table'>
	<thead>
		<tr>
			<c:if test='${param.sub != null}'><th>Title<i></i></th></c:if>
			<c:if test='${param.joy != null}'><th>Joy<i></i></th></c:if>
			<c:if test='${param.disgust != null}'><th>Disgust<i></i></th></c:if>
			<c:if test='${param.fear != null}'><th>Fear<i></i></th></c:if>
			<c:if test='${param.anger != null}'><th>Anger<i></i></th></c:if>
			<c:if test='${param.sadness != null}'><th>Sadness<i></i></th></c:if>
			<c:if test='${param.sentiment != null}'><th>Overall Sentiment<i></i></th></c:if>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${posts}" var="item">
			<tr class='table-row' data-href='${item.link}'>
				<td>${item.title}</td>
				<c:if test='${param.joy != null}'><td><c:set var='joy' value='${item.analysis.emotion.document.emotion.joy * 100}' /><fmt:formatNumber value='${joy}' maxFractionDigits='2' minFractionDigits='2' /></td></c:if>
				<c:if test='${param.disgust != null}'><td><c:set var='disgust' value='${item.analysis.emotion.document.emotion.disgust * 100}' /><fmt:formatNumber value='${disgust}' maxFractionDigits='2' minFractionDigits='2' /></td></c:if>
				<c:if test='${param.fear != null}'><td><c:set var='fear' value='${item.analysis.emotion.document.emotion.fear * 100}' /><fmt:formatNumber value='${fear}' maxFractionDigits='2' minFractionDigits='2' /></td></c:if>
				<c:if test='${param.anger != null}'><td><c:set var='anger' value='${item.analysis.emotion.document.emotion.anger * 100}' /><fmt:formatNumber value='${anger}' maxFractionDigits='2' minFractionDigits='2' /></td></c:if>
				<c:if test='${param.sadness != null}'><td><c:set var='sadness' value='${item.analysis.emotion.document.emotion.sadness * 100}' /><fmt:formatNumber value='${sadness}' maxFractionDigits='2' minFractionDigits='2' /></td></c:if>
				<c:if test='${param.sentiment != null}'><td><c:set var='sentiment' value='${item.analysis.sentiment.document.score * 100}' /><fmt:formatNumber value='${sentiment}' maxFractionDigits='2' minFractionDigits='2' /></td></c:if>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
<div id='photoTable' class='main-table'>

	<c:forEach items="${imgPosts}" var="img">
		<c:out value='${img.image.classifiers[0].classes[0].className} + ${img.image.classifiers[0].classes[0].score}' />
	</c:forEach>
</div>
</body>
</html>