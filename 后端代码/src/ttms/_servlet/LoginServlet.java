package ttms._servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ttms._dao._impl.UserdaoImpl;
import ttms._domain.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("P3P", "CP=CAO PSA OUR");
        if (req.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(req.getMethod())) {
            resp.addHeader("Access-Control-Allow-Methods", "POST,GET,TRACE,OPTIONS");
            resp.addHeader("Access-Control-Allow-Headers", "Content-Type,Origin,Accept");
            resp.addHeader("Access-Control-Max-Age", "120");
        }
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		String adminName = req.getParameter("username");
		String psw = req.getParameter("password");
		req.setAttribute("username", adminName);
		System.out.println(adminName+psw);
		if(adminName!=null && (psw == ""|| psw==null)) {
			User user = new UserdaoImpl().get(adminName);
			if(user!=null) {
				resp.getWriter().print(JSON.toJSONString("该账号可用"));
			} else {
				resp.getWriter().print(JSON.toJSONString("该账号不存在"));
			}
		} else{
			User user = new UserdaoImpl().get(adminName);
			if(user!=null && user.getPassword().equals(psw)) {
//				List<SimpleFilm> list = new SimplFilmsdaoImpl().getAlllist();
				JSONObject json = new JSONObject();
//				json.put("list", list);
				json.put("id", user.getId());
				json.put("name", user.getName());
				json.put("identity", user.getIdentity());
				System.out.println(json);
				resp.getWriter().println(json);
			} else {
				resp.getWriter().print(JSON.toJSONString("登录失败"));
			}
		}
	}
}