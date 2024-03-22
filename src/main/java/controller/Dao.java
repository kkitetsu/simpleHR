package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpSession;

public class Dao {
	protected String url       = "jdbc:mysql://localhost/hrInfo";
    protected String user      = "root";
    protected String password  = "password";
    protected Connection connection; 
    protected PreparedStatement statement = null;
	protected ResultSet results = null;
    
    public void connect() throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
    	connection = DriverManager.getConnection(url, user, password);
    }
    
    public String getNonBossStatement(HttpSession session) throws Exception {
    	
    	String userId = session.getAttribute("user_id").toString();
    	
    	String sql1 = "SELECT main.position FROM main\n"
    			+ "JOIN employees ON main.employee = employees.id\n"
    			+ "WHERE employee_name=\"" + userId + "\";";
    	
		connect();
		statement = connection.prepareStatement(sql1);
		results = statement.executeQuery();
		
		int positionNum = 0;
		
		while (results.next()) {
			positionNum = results.getInt("position");
		}
		
		// ６からが部長クラス
		if (positionNum <= 5) {
			session.setAttribute("isBoss", false);
			return " AND employee_name=\"" + session.getAttribute("user_id").toString() + "\";";
		}
		session.setAttribute("isBoss", true);
    	return ";";
    }
    
	public HashMap<Integer, ArrayList<String>> select(HttpSession session) throws Exception {
		
		String sql = "SELECT main.id, divisions.division_name, employees.employee_name, positions.position_name\n"
				+ "FROM main\n"
    			+ "JOIN divisions ON main.division = divisions.id\n"
    			+ "JOIN employees ON main.employee = employees.id\n"
    			+ "JOIN positions ON main.position = positions.id\n"
    			+ "WHERE del_Flag=0" + getNonBossStatement(session);
    	
    	LinkedHashMap<Integer, ArrayList<String>> l = new LinkedHashMap<Integer, ArrayList<String>>();
    
    	connect();
    	statement = connection.prepareStatement(sql);
    	results = statement.executeQuery();
    	
    	while (results.next()) {
    		ArrayList<String> row = new ArrayList<String>();
			row.add(results.getString("division_name"));
			row.add(results.getString("employee_name"));
			row.add(results.getString("position_name"));
			
    		int id = results.getInt("id");
    		l.put(id, row);
    	}
    	
    	return l;
    
    }
	
	public int update(String name, String newDivision, String newPosition) throws Exception {
	    String sql = "UPDATE main m JOIN employees ON m.employee = employees.id" 
	    						+ " SET m.division=\"" + newDivision + "\"" 
	    						+ ", m.position=\"" + newPosition + "\" WHERE employee_name=\"" + name + "\";";
	    System.out.println(sql);
		connect();
		PreparedStatement statement = connection.prepareStatement(sql);
		return statement.executeUpdate();
	}
	
	public int delete(String name) throws Exception {
		String sql = "UPDATE main m JOIN employees ON m.employee = employees.id\n" 
							+ " SET del_Flag=1 WHERE employee_name=\"" + name + "\";";
		connect();
		PreparedStatement statement = connection.prepareStatement(sql);
		return statement.executeUpdate();
	}
	
	public int insert(String title, String content, String priority, String userId) throws Exception {
		String sql = "INSERT INTO main (title, contents, user_id, createdAt, priority) VALUES (\"" + 
				   title + "\", \"" + content + "\", " + userId + ", CURRENT_TIMESTAMP, " + priority + ")";
		System.out.println(sql);
		
	    connect();
		PreparedStatement statement = connection.prepareStatement(sql);
		return statement.executeUpdate();
	}
}
