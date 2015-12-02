import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class MyServer extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
