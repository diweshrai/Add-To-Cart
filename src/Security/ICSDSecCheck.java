package Security;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
public class ICSDSecCheck {

	
	public static HttpSession doSecCheck(HttpServletRequest request) throws ICSDAuthException
	{
		HttpSession session=request.getSession(false);
		if(session==null)
		{
			throw new ICSDAuthException("pls login first,not a valid user");
		}
		return session;
	}
	
}
