package Product1;

import java.io.IOException;
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

import oracle.jdbc.pool.OracleDataSource;

@WebServlet("/Product")
public class Product extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rset = null; 
	

	public Product() {
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
			stmt = con.prepareStatement("select * from tbluser where uname = ? and upwd=?");
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strUnm,strPwd;
		strUnm= request.getParameter("txtUnm");
		strPwd= request.getParameter("txtPwd");
		
		try {
			stmt.setString(1, strUnm);
			stmt.setString(2, strPwd);
			rset=stmt.executeQuery();
			if(rset.next()) {
				response.sendRedirect(request.getContextPath()+"/WelcomePage?unm=" +strUnm+"&pwd="+strPwd);
			}else
			{
				response.sendRedirect(request.getContentType()+"/ErrorPage");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
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
