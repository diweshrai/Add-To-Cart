package Product1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.jdbc.pool.OracleDataSource;


@WebServlet("/Dec")
public class Dec extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rset = null; 
	
	
   
    public Dec() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	

	public Connection getDBCon() {
		Connection con = null;
		
		try {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
		con = ods.getConnection("diwesh2","icsd");
		System.out.println("Connection Established Successfuly");
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return con;
	}
	
	public void init(ServletConfig config) throws ServletException{
		con = getDBCon();
		System.out.println("...............");
			
		try {
			stmt = con.prepareStatement("select * from products where categoryid = ?");
		
			
			System.out.println("inside int");
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str1,str2; 
		int id1; 
		id1 = Integer.parseInt(request.getParameter("id"));
		
		HttpSession session = request.getSession(false);
	str1 = (String)	session.getAttribute("unm");
	str2 = (String)	session.getAttribute("pwd");

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Welcome page</title></head><body>");
		out.println("<p>Welcome : <span>"+str1+"</span></p>");
		out.println("<p>your pass is : <span>"+str2+"</span></p>");
		System.out.println("user name "+str1);
		try {
		out.println("<table border='1'>");
		out.println("<tr>");
		out.println("<th> catid</th>");
		out.println("<th> catname</th>");
		out.println("<th> catimage</th>");
		out.println("<th> catDes</th>");
		out.println("<th> catdesc</th>");
		out.println("<th> QTY</th>");
		out.println("<th> Price</th>");
		out.println("</tr>");
		
		
		stmt.setInt(1, id1);
		System.out.println("done1...................");
		rset=stmt.executeQuery();
		System.out.println("done1");

		while(rset.next()) {
			String catid,proid,proName,proImg,proDesc, proqty,proprice;
			catid=rset.getString("categoryid");
			proid = rset.getString("prodid");
			proName = rset.getString("productname");
		proDesc = rset.getString("proddesc");
			proImg = rset.getString("prodimgurl");
			proqty = rset.getString("qty");
			proprice = rset.getString("price");
		
			
			out.println("<tr>");
			
			System.out.println("done ");

			out.println("<td>"+catid+"</td>");
		out.println("<td>"+proid+"</td>");
			out.println("<td><a href='AddToCart?pid="+catid+"'>"+proName+"</td>");
			out.println("<td>"+proDesc+"</td>");
			out.println("<td><img src='image/"+proImg+"'></td>");
			
			out.println("<td>"+proqty+"</td>");

			out.println("<td>"+proprice+"</td>");
			out.println("<tr>");
			
		}
		out.println("</table>");
	} catch (SQLException e) {
		e.printStackTrace();
	}
		out.println("</body>");
		out.println("</html>");
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


public void destroy() {
	try {
		if(con!=null) {
			con.close();
		}
	if(stmt!=null) {
		stmt.close();
	}
	if(rset!=null) {
		rset.close();
	}
	
	}
	catch(SQLException se) {
		System.out.println(se.getMessage());
	}
}}



