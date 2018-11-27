package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import model.Note;
import util.Database;
import util.FreeMarker;
import util.SecurityLayer;

/**
 * Servlet implementation class ModificaNota
 */
@WebServlet("/ModificaNota")
public class ModificaNota extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map data= new HashMap<String, Object>(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaNota() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession s = SecurityLayer.checkSession(request);
		int id=0;
		String testo="";
		List<Note> listan=new ArrayList<Note>();
		if(s!=null){
			data.put("ciao", s.getAttribute("idarea"));
				
				int idnota=Integer.parseInt(request.getParameter("idnota"));
			try {
				Database.connect();
				ResultSet nota=Database.selectRecord("note","note.id="+idnota);
				while(nota.next()) {
					id=nota.getInt("id");
					testo=nota.getString("testo");
					Note n=new Note(id,testo);
					listan.add(n);
				}
				Database.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			data.put("nota", listan);
				FreeMarker.process("modifica_nota.html", data, response, getServletContext());
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
		String scelta=request.getParameter("decisione");
		int idnota=Integer.parseInt(request.getParameter("idnota"));
		String testo=request.getParameter("testo");
		Map<String,Object> notan=new HashMap<String,Object>();
		notan.put("testo", testo);
		if(s!=null){
			if(scelta.equals("salva")) {
				try {
					Database.connect();
					Database.updateRecord("note", notan, "note.id="+idnota);
					Database.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
				response.sendRedirect("AzioneNote");
		}else{
			response.sendRedirect("Log");
		}
	}

}
