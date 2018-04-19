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
		Joy: <input type='checkbox' name='joy' checked> <input type='range' min='1' max='100' value='1' name='joyRange'> <span>1</span><br>
		Disgust: <input type='checkbox' name='disgust' checked> <input type='range' min='1' max='100' value='1' name='disgustRange'> <span>1</span> <br>
		Fear: <input type='checkbox' name='fear' checked> <input type='range' min='1' max='100' value='1' name='fearRange'> <span>1</span> <br>
		Anger: <input type='checkbox' name='anger' checked> <input type='range' min='1' max='100' value='1' name='angerRange'> <span>1</span> <br>
		Sadness: <input type='checkbox' name='sadness' checked> <input type='range' min='1' max='100' value='1' name='sadnessRange'> <span>1</span> <br><br>
		
		Document Sentiment Threshold: <input type='range' min='-100' max='100' value='-100' name='sentimentThreshold'> <span>-100</span> <br>
		Image Confidence Threshold: <input type='range' min='1' max='100' value='1' name='imgThreshold'> <span>1</span> <br><br>
		
		SubReddit: <input type='text' name='sub' value='${param.sub}'><br><br>
		
		Max number of posts:<input type='number' name='max' value='5'><br>
		(The higher the number, the longer it takes)<br>
		
		<input type='submit' value='Analyse'><br><br>
		
	</form>
</div>
<div class='table-div'>
	<c:if test="${param.sub != null && !param.sub.isEmpty()}">
		<table id='myTable' class='main-table'>
			<thead>
				<tr>
					<th>Title<i></i></th>
					<c:if test='${param.joy != null }'><th>Joy<i></i></th></c:if>
					<c:if test='${param.disgust != null}'><th>Disgust<i></i></th></c:if>
					<c:if test='${param.fear != null}'><th>Fear<i></i></th></c:if>
					<c:if test='${param.anger != null}'><th>Anger<i></i></th></c:if>
					<c:if test='${param.sadness != null}'><th>Sadness<i></i></th></c:if>
					<c:if test='${param.sentiment != null}'><th>Overall Sentiment<i></i></th></c:if>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${posts}" var="item">
					<c:set var='currentItemEmotions' value='${item.analysis.emotion.document.emotion}' />
					<c:set var='currentItemSentiment' value='${item.analysis.sentiment.document}' />
					
					<c:set var='joy' value='${currentItemEmotions.joy * 100}' />
					<c:set var='disgust' value='${currentItemEmotions.disgust * 100}' />
					<c:set var='fear' value='${currentItemEmotions.fear * 100}' />
					<c:set var='anger' value='${currentItemEmotions.anger * 100}' />
					<c:set var='sadness' value='${currentItemEmotions.sadness * 100}' />
					<c:set var='sentiment' value='${item.analysis.sentiment.document.score * 100}' />
					<c:if test='${joy >= param.joyRange && 
								  disgust >= param.disgustRange &&
								  fear >= param.fearRange &&
								  anger >= param.angerRange &&
								  sadness >= param.sadnessRange &&
								  sentiment >= param.sentimentThreshold}'>
								  
						<tr class='table-row' data-href='${item.link}'>
							<td>${item.title}</td>
							<c:if test='${param.joy != null}'><td><fmt:formatNumber value='${joy}' maxFractionDigits='2' minFractionDigits='2' /></td></c:if>
							<c:if test='${param.disgust != null}'><td><fmt:formatNumber value='${disgust}' maxFractionDigits='2' minFractionDigits='2' /></td></c:if>
							<c:if test='${param.fear != null}'><td><fmt:formatNumber value='${fear}' maxFractionDigits='2' minFractionDigits='2' /></td></c:if>
							<c:if test='${param.anger != null}'><td><fmt:formatNumber value='${anger}' maxFractionDigits='2' minFractionDigits='2' /></td></c:if>
							<c:if test='${param.sadness != null}'><td><fmt:formatNumber value='${sadness}' maxFractionDigits='2' minFractionDigits='2' /></td></c:if>
							<c:if test='${param.sentiment != null}'><td><fmt:formatNumber value='${sentiment}' maxFractionDigits='2' minFractionDigits='2' /></td></c:if>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
		<br>
	</c:if>
</div>
<div class='table-div'>

	<c:forEach items="${imgPosts}" var="img">
		<h2>${img.title}</h2>
		<a href='${img.link}'>Link</a> <a onclick='hide(this, ${img.id})' href='#'>Hide</a>
		<table id='${img.id}' class='main-table'>
			<thead>
				<tr>
					<th>Class Type<i></i></th>
					<th>Confidence<i></i></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${img.image.classifiers[0].classes}" var="defClass">
					<c:set var='score' value='${defClass.score * 100}' />
					
					<c:if test='${score >= param.imgThreshold}'>
						<tr>
							<td>${defClass.className}</td>
							<td><fmt:formatNumber value='${score}' maxFractionDigits='2' minFractionDigits='2' /></td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</c:forEach>
</div>
</body>
</html>