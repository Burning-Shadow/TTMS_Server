package ttms._servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;
import ttms._dao.DAO;
import ttms._domain.User;
import ttms._util.SendMessage;

@WebServlet("/register")
public class RegisterOrRetrieveThePassword extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private DAO dao = new DAO();
	JSONObject json = new JSONObject();
	private String cmd;
	@Override
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
		cmd = req.getParameter("cmd");
		System.out.println(cmd);
		switch (cmd) {
		case "resgister":
			this.resgister(req,resp);
			break;
		case "retrievethepassword":
			this.retrievethepassword(req,resp);
			break;
		case "sendmessage":
			this.sendmessage(req,resp);
			break;
		case "isOK":
			this.isOK(req,resp);
			break;
		case "checkusername":
			this.checkusername(req,resp);
			break;
		case "sendmessageforretrievethepassword":
			this.sendmessageforretrievethepassword(req,resp);
			break;
		default:
			break;
		}
	}
	private void sendmessageforretrievethepassword(HttpServletRequest req, HttpServletResponse resp) {
		String telephone = req.getParameter("telephonenumber");
		User user = dao.userdao.get(telephone);
		if(user!=null) {
			try {
				String s = SendMessage.send(telephone);
				user.setTicketid(s);
				dao.userdao.update(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			json.put("result", "该手机号未被注册");
			try {
				resp.getWriter().println(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private boolean isOK(HttpServletRequest req, HttpServletResponse resp) {
		String key = req.getParameter("key");
		try {
			if(key.equals(dao.userdao.get(req.getParameter("telephonenumber")).getTicketid())) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	private void retrievethepassword(HttpServletRequest req, HttpServletResponse resp) {
		String number = req.getParameter("telephonenumber");
		User user = dao.userdao.get(number);
		System.out.println(user);
		if(user!=null) {
			if(isOK(req, resp)) {
				user.setPassword(req.getParameter("password"));
				user.setTicketid("");
				dao.userdao.update(user);
				json.put("result", "1");
				try {
					resp.getWriter().println(json);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			json.put("result", "0");
			try {
				resp.getWriter().print(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private void sendmessage(HttpServletRequest req, HttpServletResponse resp) {
		String telephone = req.getParameter("telephonenumber");
		List<User> list = dao.userdao.getAllUser();
		Integer flage = 0;
		for (User user : list) {
			if(telephone.equals(user.getPhonenumber())) {
				flage = 1;
			}
		}
		if(flage.equals(0)) {
			try {
				String s = SendMessage.send(telephone);
				User user = new User();
				user.setPhonenumber(telephone);
				user.setTicketid(s);
				System.out.println(user);
				dao.userdao.save(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			json.put("result", "该手机号已注册");
			try {
				resp.getWriter().println(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private void resgister(HttpServletRequest req, HttpServletResponse resp) {
		List<User> users = dao.userdao.getAllUser();
		for (User use : users) {
			if(use.getPhonenumber().equals(req.getParameter("telephonenumber"))) {
				if(isOK(req, resp)) {
					User user = new User();
					user.setIdentity("employee");
					user.setName(req.getParameter("name"));
					user.setPassword(req.getParameter("password"));
					user.setPhonenumber(req.getParameter("telephonenumber"));
					user.setTicketid(null);
					user.setUsername(req.getParameter("username"));
					user.setId(dao.userdao.get(req.getParameter("telephonenumber")).getId());
					dao.userdao.update(user);
					System.out.println("OK");
					json.put("result", "OK");
					try {
						resp.getWriter().println(json);
					} catch (IOException e) {
						e.printStackTrace();
					};
				}else {
					System.out.println("ERROR");
					json.put("result", "ERROR");
					try {
						resp.getWriter().println(json);
					} catch (IOException e) {
						e.printStackTrace();
					};
				}
				return ;
			}
		}
		json.put("result", "请重新获取验证码");
		try {
			resp.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void checkusername(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("username");
		int flage = 0;
		List<User> list = dao.userdao.getAllUser();
		for (User user : list) {
			if(username.equals(user.getUsername())) {
				flage = 1;
			}
		}
		if(flage == 0) {
			try {
				json.put("result", "该账号可用");
				resp.getWriter().println(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				json.put("result", "该账号已存在");
				resp.getWriter().println(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
