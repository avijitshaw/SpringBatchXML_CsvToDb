<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upload File</title>
</head>
<body>
	<h1>File Upload</h1>
	<form id="fileuploadForm" action="fileupload" method="POST"
		enctype="multipart/form-data">
		<label for="file">File</label> 
		<input id="file" type="file" name="file" />
		<p>
			<button type="submit">Upload</button>
		</p>
	</form>

</body>
</html>