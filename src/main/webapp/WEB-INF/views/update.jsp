<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>Update Data</h2>
	
	<form action="update" method="post">
	
	    <label for="address">所属課:</label><br>
	    <select id="newDivison" name="newDivison" required>
	        <option value=1>IT部</option>
	        <option value=2>CE部</option>
	        <option value=3>CS部</option>
	        <option value=4>アセット部</option>
	        <option value=5>FX部</option>
	        <option value=6>業務管理部</option>
	        <option value=7>人事総務部</option>
	    </select><br><br>
	
	    <label for="phone">役職:</label><br>
	    <select id="newPosition" name="newPosition" required>
	        <option value=1>アソシエイト</option>
	        <option value=2>リーダー</option>
	        <option value=3>アソシエイトマネージャー</option>
	        <option value=4>マネージャー</option>
	        <option value=5>副部長</option>
	        <option value=6>部長</option>
	        <option value=7>本部長</option>
	    </select><br><br>
	
	    <input type="submit" value="Update">
	</form>
	
</body>
</html>