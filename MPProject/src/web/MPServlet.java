package web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MP;

/**
 * Servlet implementation class MPServlet
 */
public class MPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MPServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String page = request.getParameter("pagename");
		HttpSession session = request.getSession();
		ArrayList mps = null;
		String pagename = null;
		
		if(page.equalsIgnoreCase("state"))
		{
			String statename = request.getParameter("search");
			System.out.println(statename);
			 mps = MP.searchByState(statename);
			pagename = "searchbystate.jsp";
		}
		else if(page.equalsIgnoreCase("party"))
		{
			String partyname = request.getParameter("search");
			System.out.println(partyname);
			 mps = MP.searchByParty(partyname);	
			 pagename = "searchbyparty.jsp";
		}
		else if(page.equalsIgnoreCase("name"))
		{
			String name = request.getParameter("search");
			System.out.println(name);
			 mps = MP.searchByName(name);	
			 pagename = "searchbyname.jsp";
		}
		else if(page.equalsIgnoreCase("constituency"))
		{
			String constname = request.getParameter("search");
			System.out.println(constname);
			 mps = MP.searchByConsti(constname);
			 pagename = "searchbyconstituency.jsp";
		}
		else if(page.equalsIgnoreCase("age"))
		{
			String minStr = request.getParameter("min");
			String maxStr = request.getParameter("max");
			int min = Integer.parseInt(minStr);
			int max = Integer.parseInt(maxStr);
			System.out.println(minStr);
			System.out.println(maxStr);
			 mps = MP.searchByDate(min, max);	
			 pagename = "searchbyage.jsp";
		}
		
		request.setAttribute("mps",mps);	
		RequestDispatcher dispatcher = request.getRequestDispatcher(pagename);
	     if (dispatcher != null){
	    dispatcher.forward(request, response);
	   }
	}

}
