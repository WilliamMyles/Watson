<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Example Return</title>
</head>
<body>
	<form action='Example1' method='GET'>
		Input Text: <br>
		<textarea rows="7" cols="20" name='text'></textarea><br>
		URL? <input type='checkbox' name='url'><br>
		<input type='submit' value='Submit'>
	</form>
	${output}
</body>
</html>