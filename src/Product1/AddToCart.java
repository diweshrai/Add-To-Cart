package Product1;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Security.ICSDAuthException;
import Security.ICSDSecCheck;

import product.DBHandler.ClsDbHandler;
import product.Model.ProductCls;


@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public AddToCart() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
			ICSDSecCheck.doSecCheck(request);
			String pid=request.getParameter("pid");
			
			ClsDbHandler objDH = new ClsDbHandler();
			ProductCls obj=objDH.getProdByProdId(pid);
			
		
			
			HttpSession session=request.getSession(false);
			@SuppressWarnings("unchecked")
			LinkedList<ProductCls> lst=(LinkedList<ProductCls>) session.getAttribute("CART");
			if(lst==null)
			{
				lst=new LinkedList<>();
				session.setAttribute("CART", lst);
			}
			lst.add(obj);
			
			out.println("<html>");
			out.println("<head><title>Add to cart page</title></head><body>");		
			
				
				out.println("<table border='1'>");
				out.println("<tr>");
				out.println("<th>catid</th>");
				out.println("<th>prodidd</th>");
				out.println("<th>prodname</th>");
				out.println("<th>prod desc</th>");
				out.println("<th>prod img</th>");
				out.println("<th>qty</th>");
				out.println("<th>price</th>");
				out.println("</tr>");
			for (ProductCls o : lst)
			{
				
				String prodname=o.getProductName(),
						proddesc=o.getProdDesc(),
						prodimg=o.getProdImgUrl();
						int qty=o.getQty();
				double price=o.getPrice();
				int catid=obj.getCategoryId();
				out.println("<tr>");
				out.println("<td>"+catid+"</td>");
				out.println("<td>"+pid+"</td>");
				out.println("<td>"+prodname+"</td>");
				out.println("<td>"+proddesc+"</td>");
				out.println("<td><img src='image/"+prodimg+"'/></td>");
				out.println("<td>"+qty+"</td>");
				out.println("<td>"+price+"</td>");
				out.println("</tr>");
				
				
				
			}
			
			
			
			out.println("</table>");
			String strUnm=(String) session.getAttribute("unm");
			out.println("<a href='WelcomePage?unm="+strUnm+"'>purchase more...?</a>");
			out.println("</body>");
			out.println("</html>");
			
		} catch (ICSDAuthException e1) {
			out.println("<html>");
			out.println("<head><title>error  page</title></head><body>");		
			out.println("<a href='LoginPage.html'>"+e1.getMessage()+"</a>");
			out.println("</body>");
			out.println("</html>");
		}
		
	}




}
