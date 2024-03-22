<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome Page</title>
    <style>
    .center-table {
        margin: 0 auto;
        width: 50%; 
        border: 1px solid black;
        text-align: center;
    }
    </style>
</head>
<body>
    <h1>Welcome</h1>
    <p>Welcome, <%= session.getAttribute("user_id") %>!</p>
    
    <table class="center-table">
	    <thead>
	        <tr>
	            <th>所属課</th>
	            <th>従業員</th>
	            <th>役職</th>
	        </tr>
	    </thead>
    	<tbody>
	    <% // ----------------------------------------------------------------- %>
	    <% HashMap<Integer, ArrayList<String>> map = (HashMap<Integer, ArrayList<String>>)request.getAttribute("rows"); %>
	    <% for (Map.Entry<Integer, ArrayList<String>> entry : map.entrySet()) { %>
	    
	    	<% ArrayList<String> value = entry.getValue(); %>
	        <tr>
	          <td><%= value.get(0) %></td>
	          <td><%= value.get(1) %></td>
	          <td><%= value.get(2) %></td>
	          
	          <% if ((boolean) session.getAttribute("isBoss")) { %>
	          	<form action="welcome" method="post">
		          <td>
		          	<input type="text"   value=""         name="comment" style="border: 1px solid white; ">
		    		<input type="submit" value="Update"   name="action">
		          </td>
		          <td>
		          	<input type="hidden" value="<%=value.get(1) %>" name="key">
		    		<input type="submit" value="Delete"   name="action">
		          </td>
		        </form>
		      <% } %>
	        </tr>
	    <% } %>
	    <% // ----------------------------------------------------------------- %>
	    </tbody>
    </table>

    <form method="POST" action="logout">
      <input type="submit" value="Logout">
    </form>
</body>
</html>