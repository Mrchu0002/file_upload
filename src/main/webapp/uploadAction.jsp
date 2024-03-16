<%@ page import="file.FileDAO" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 파일 업로드</title>
</head>
<body>
	<%
		String directory = "C:/JSP/upload";
		int maxSize = 1024 * 1024 * 100; //100MB 까지만 사용가능;
		String encoding = "UTF-8";
		
		MultipartRequest multipartRequest 
		= new MultipartRequest(request, directory, maxSize, encoding, 
		 	new DefaultFileRenamePolicy()); 
		
		
		Enumeration fileNames = multipartRequest.getFileNames();
		while(fileNames.hasMoreElements()){
			String parameter = (String) fileNames.nextElement();
			String fileName = multipartRequest.getOriginalFileName(parameter);
		String fileRealName = multipartRequest.getFilesystemName(parameter);
			
			
		if(fileName == null) continue; // 파일을 선택하지 않은 항목에 대해서는 pass
			// 특정 파일만 불러 올 수 있고 그 외에 파일은 불러와도 바로 삭제됨.
			if(!fileName.endsWith(".gif") && !fileName.endsWith(".png") &&
				!fileName.endsWith(".jpg") && !fileName.endsWith(".txt")){
				File file = new File(directory + fileRealName);
				file.delete();
				out.write("업로드할 수 없는 확장자입니다.");
			}else{
				new FileDAO().upload(fileName, fileRealName);
				out.write("파일명: " + fileName + "<br>");
				out.write("실제파일명: " + fileRealName + "<br>");
			}			
		}
	%>
</body>
</html>
