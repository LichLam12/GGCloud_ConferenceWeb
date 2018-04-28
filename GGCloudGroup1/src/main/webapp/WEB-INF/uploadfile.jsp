<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AWS S3 FILE UPLOAD USING JAVA</title>
</head>
<body>
	<form action="uploadFile" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>Upload File</td>
				<td><input type="file" name="file"></td>
				<td><button type="submit">Submit</button></td>
			</tr>
		</table>
	</form>
	
</body>
</html>