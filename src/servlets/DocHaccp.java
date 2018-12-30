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
 * Servlet implementation class DocHaccp
 */
@WebServlet("/DocHaccp")
public class DocHaccp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map data= new HashMap<String, Object>();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DocHaccp() {
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
			data.put("ciao", s.getAttribute("idarea"));
			
		int ida=(int) s.getAttribute("id");
		ResultSet r;
		try {
			Database.connect();
			r = Database.selectRecord("haccpdoc","idazienda="+ ida);
			while(r.next()) {
				cont++;
			}
			//Date datas=null;
			if(cont==0) {
				ResultSet p=Database.selectRecord("documentihaccp");
				while(p.next()) {
					int ids=p.getInt("id");
					agg.put("flag", 0);
					String datap="NULL";
					//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				      //datas = simpleDateFormat.parse(datap);
					//agg.put("data",datap );//simpleDateFormat.format(datas)
					agg.put("iddochaccp", ids);
					agg.put("idazienda", ida);
					Database.insertRecord("haccpdoc", agg);
				}
			
				
			}
			ResultSet a=Database.selectRecord("azienda","id="+ida);
			String nome="";
			while(a.next()) {
				 nome=a.getString("nome");
			}
		Database.close();
		data.put("nome", nome);
			data.put("documentis", DocSicurezzaDAO.documentihaccp(ida));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FreeMarker.process("dochaccp.html", data, response, getServletContext());}
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
			
			DocSicurezzaDAO.aggiornadochaccp(dat,idd);
			} else
			{
				try {
					Database.connect();
					agg.put("data", "0001-01-01");
					agg.put("flag", 0);
					Database.updateRecord("haccpdoc", agg, "haccpdoc.id="+idd);
					Database.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			}
		response.sendRedirect("DocHaccp");
		}
		else {
			response.sendRedirect("Log");
		}
	}

}
