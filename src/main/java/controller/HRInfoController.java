package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HRInfoController
 */
@WebServlet("/welcome")
public class HRInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HRInfoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws 
										ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			String view = "/WEB-INF/views/login.jsp";
	        request.getRequestDispatcher(view).forward(request, response);
	        return;
		}
		Dao dao = new Dao();
		request.setCharacterEncoding("utf-8"); 
		
		try {
			HashMap<Integer, ArrayList<String>> selectedData = dao.select(session);
			request.setAttribute("rows", selectedData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String view = "/WEB-INF/views/welcome.jsp";
        request.getRequestDispatcher(view).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			String view = "/WEB-INF/views/login.jsp";
	        request.getRequestDispatcher(view).forward(request, response);
	        return;
		}
		Dao dao = new Dao();
		request.setCharacterEncoding("utf-8"); 
		String action = request.getParameter("action");
		
		try {
			if (action.equals("Delete")) {
				dao.delete(request.getParameter("key"));
			} else if (action.equals("Update")){
				session.setAttribute("name", request.getParameter("key"));
				System.out.println(request.getAttribute("name"));
				String view = "/WEB-INF/views/update.jsp";
		        request.getRequestDispatcher(view).forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
