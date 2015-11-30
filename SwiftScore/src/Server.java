

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Server
 */
public class Server extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Server() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.sendRedirect("Website.html");
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		PrintWriter out = response.getWriter();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void homepage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		if(request.getParameter("LSK") == "1" || request.getParameter("BI") == "2" || request.getParameter("LC") == "3")
		{
			response.sendRedirect("Authentication.html");
		}
		else if (request.getParameter("PA") == "4")
		{
			response.sendRedirect("PlayerAnalysisMode.html");
		}
		else if (request.getParameter("LA") == "5")
		{
			response.sendRedirect("LeagueAnalysisMode.html");
		}
	}

}
