package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DocSicurezzaDAO;
import util.Database;
import util.FreeMarker;
import util.SecurityLayer;

/**
 * Servlet implementation class DocPrivacy
 */
@WebServlet("/DocPrivacy")
public class DocPrivacy extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map data= new HashMap<String, Object>();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DocPrivacy() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession s = SecurityLayer.checkSession(request);
		int cont=0;
		Map<String,Object> agg=new HashMap<String,Object>();
		if(s!=null){
			
		int ida=(int) s.getAttribute("id");
		ResultSet r;
		try {
			Database.connect();
			r = Database.selectRecord("privacydoc","idazienda="+ ida);
			while(r.next()) {
				cont++;
			}
			//Date datas=null;
			if(cont==0) {
				ResultSet p=Database.selectRecord("documentiprivacy");
				while(p.next()) {
					int ids=p.getInt("id");
					agg.put("flag", 0);
					String datap="NULL";
					//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				      //datas = simpleDateFormat.parse(datap);
					//agg.put("data",datap );//simpleDateFormat.format(datas)
					agg.put("iddocpriv", ids);
					agg.put("idazienda", ida);
					Database.insertRecord("privacydoc", agg);
				}
			
				
			}
			ResultSet a=Database.selectRecord("azienda","id="+ida);
			String nome="";
			while(a.next()) {
				 nome=a.getString("nome");
			}
		Database.close();
		data.put("nome", nome);
			data.put("documentis", DocSicurezzaDAO.documentiprivacy(ida));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FreeMarker.process("docprivacy.html", data, response, getServletContext());}
		else {
			response.sendRedirect("Log");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession s = SecurityLayer.checkSession(request);
		
		Map<String,Object> agg=new HashMap<String,Object>();
		if(s!=null){
			int idd=Integer.parseInt(request.getParameter("tasto"));
			int scelta=Integer.parseInt(request.getParameter("scelta"));
			if(scelta==0) {
			String dat=request.getParameter("data");
			
			DocSicurezzaDAO.aggiornadocpriv(dat,idd);
			} else
			{
				try {
					Database.connect();
					agg.put("data", "0001-01-01");
					agg.put("flag", 0);
					Database.updateRecord("privacydoc", agg, "privacydoc.id="+idd);
					Database.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			}
		response.sendRedirect("DocPrivacy");
		}
		else {
			response.sendRedirect("Log");
		}
	}

}
