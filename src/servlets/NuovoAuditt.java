package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AziendaDAO;
import util.Database;
import util.FreeMarker;
import util.SecurityLayer;
import model.Azienda;

/**
 * Servlet implementation class Audit
 */
@WebServlet("/NuovoAuditt")
public class NuovoAuditt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map data= new HashMap<String, Object>();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NuovoAuditt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession s = SecurityLayer.checkSession(request);
		if(s!=null){
		data.clear();
		
		data.put("lista2", AziendaDAO.lista3((int) s.getAttribute("idarea")));
		
		FreeMarker.process("auditt.html", data, response, getServletContext());
		}else{
			response.sendRedirect("Log");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession s = SecurityLayer.checkSession(request);
		if(s!=null){
String premuto=request.getParameter("tasto");
		
		
		int area=(int) s.getAttribute("idarea");

		

		doGet(request, response);
	}else{
		response.sendRedirect("Log");
	}
	}
}