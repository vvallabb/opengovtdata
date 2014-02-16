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
 * Servlet implementation class DetailServlet
 */
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailServlet() {
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
		String mp = request.getParameter("mpsname");
		HttpSession session = request.getSession();
		ArrayList mps = null;
		
		System.out.println(mp);
			 mps = MP.searchByName(mp);
			
			 String imageUrl = MP.getImageByName(mp);
		
		request.setAttribute("mps",mps);
		session.setAttribute("mpname",mp);
		session.setAttribute("imageUrl",imageUrl);
		RequestDispatcher dispatcher = request.getRequestDispatcher("MP_details.jsp");
	     if (dispatcher != null){
	    dispatcher.forward(request, response);
	   }
	}

}
