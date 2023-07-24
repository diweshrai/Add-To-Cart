package product.DBHandler;
import oracle.jdbc.pool.OracleDataSource;
import product.Model.ProductCls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClsDbHandler {



public Connection getDBCon() {
	Connection con = null;
	try {
		OracleDataSource ods=new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
		con=ods.getConnection("diwesh2","icsd");
		System.out.println("db connnection estalished successfully");
	}catch (SQLException e) {
		e.printStackTrace();
	}
return con;
}




public ProductCls getProdByProdId(String pid) {
	ProductCls obj =null;
	Connection  con = getDBCon();

	try {
		PreparedStatement stmt = con.prepareStatement("select * from products where prodid=?");
		stmt.setString(1, pid);
		ResultSet rset=stmt.executeQuery();
		if(rset.next()) {
			int categoryId= rset.getInt("categoryid");
			String    productName=rset.getString("productname"),
			prodDesc = rset.getString("prodimgurl"),
			prodImgUrl = rset.getString("prodimgurl");
			int qty = rset.getInt("qty");
			double price = rset.getDouble("price");
			obj = new ProductCls(categoryId,Integer.parseInt(pid),productName, prodDesc,prodImgUrl,qty,price);
		}
	}
	catch (SQLException e) {
		e.printStackTrace();
					
		System.out.println("done here");
		
		}
	return obj;
	}

}











