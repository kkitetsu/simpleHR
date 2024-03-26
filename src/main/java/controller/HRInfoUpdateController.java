package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HRInfoUpdateController
 */
@WebServlet("/update")
public class HRInfoUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HRInfoUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dao dao = new Dao();
		try {
			HttpSession session = request.getSession();
			
			dao.update(session.getAttribute("name").toString(), 
					   request.getParameter("newDivison"),
					   request.getParameter("newPosition"));
			
			String contextPath = request.getContextPath();
	        String mainControllerURL = contextPath + "/welcome";
	        response.sendRedirect(mainControllerURL);
	        return;
	        
		} catch (Exception e) {
			String view = "/WEB-INF/views/login.jsp";
	        request.getRequestDispatcher(view).forward(request, response);
	        return;
		}
		
	}

}
