package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AziendaDAO;
import dao.CorsistiDAO;
import dao.CorsoDAO;
import util.FreeMarker;
import util.SecurityLayer;

/**
 * Servlet implementation class Note
 */
@WebServlet("/Note")
public class Note extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map data= new HashMap<String, Object>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Note() {
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
			int idazie= (int) s.getAttribute("id");
			ResultSet a=
		FreeMarker.process("note.html", data, response, getServletContext());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession s = SecurityLayer.checkSession(request);
		if(s!=null){
		int ida=(int) s.getAttribute("id");
		System.out.println(ida + "id azienda");
		
			try {
				data.put("nomeaz", AziendaDAO.specifica(ida));
			data.put("corsi", CorsoDAO.corsi());
			data.put("corsisti", CorsistiDAO.corsisti(ida));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FreeMarker.process("formazione.html", data, response, getServletContext());
		}else{
			response.sendRedirect("Log");
		}
	}

}
