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

@WebServlet("/WelcomePage")
public class WelcomePage extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rset = null; 
	
	
	 
    public WelcomePage() {
        super();
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
		try {
			stmt = con.prepareStatement("select * from category");
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str1,str2; 
		str1= request.getParameter("unm");
		str2= request.getParameter("pwd");
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
		out.println("<th> Check Full Description</th>");
		out.println("</tr>");
		
		rset=stmt.executeQuery();
	HttpSession session = request.getSession();
	session.setAttribute("unm", str1);
	session.setAttribute("pwd", str2);
		while(rset.next()) {
			String catid,catName,catImg,catDesc,catNext;
			catid=rset.getString("categoryid");
			catName = rset.getString("categoryname");
			catImg = rset.getString("categoryimgurl");
			catDesc = rset.getString("categorydesc");
			catNext="Full Description";
			
			out.println("<tr>");
			//out.println("<td><a href=pro?catid="+catid+">"+catid+"</td>");
			out.println("<td>"+catid+"</td>");
			out.println("<td>"+catName+"</td>");
			//out.println("<td>"+catImg+"</td>");
			out.println("<td><img src='image/"+catImg+"'></td>");
			out.println("<td>"+catDesc+"</td>");
			out.println("<td><a href=Dec?id="+catid+">"+catNext+"</td>");
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
	}

	
}
