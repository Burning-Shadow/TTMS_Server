package ttms._servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import com.alibaba.fastjson.JSONObject;
import ttms._dao.DAO;
import ttms._domain.Ticket;
import ttms._domain.User;
@WebServlet("/personal")
public class PersonalServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private DAO dao = new DAO();
	JSONObject json = new JSONObject();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		switch (cmd) {
		case "gettickets":									//根据用户id获取用户自己已经购买的票
			this.gettickets(req,resp);
			break;
		case "getuser":
			this.getuser(req,resp);							//根据用户id获取用户个人信息
			break;
		case "updateuser":
			this.updateuser(req,resp);						//编辑用户的个人信息
			break;
		case "returnticket":
			this.returnticket(req,resp);					//根据票的id退票
			break;
		case "returntickets":
			this.returntickets(req,resp);					//根据id退多张票
			break;
		case "getfilmbyticket":
			this.getfilmbyticket(req,resp);					//根据票获取电影信息
			break;
		case "getsimpleuser":
			this.getsimpleuser(req,resp);					//获取用户信息
			break;
		default:
			break;
		}
	}
	public void getsimpleuser(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		json.put("username", session.getAttribute("username"));
		json.put("userid", session.getAttribute("id"));
		try {
			resp.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void getfilmbyticket(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Long id = dao.ticketdao.getticketByid(Long.valueOf(req.getParameter("id"))).getFilmid();
		json.put("film", dao.filmdao.get(id));
		resp.getWriter().println(json);
	}
	//只是删了表中的数据还没有退钱
	public void returntickets(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Ticket> tickets = new ArrayList<>();
		Map<String, String[]> map = req.getParameterMap();
		map.put("id", null);
		try {
			BeanUtils.copyProperties(tickets, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		for (Ticket ticket : tickets) {
			dao.ticketdao.delete(ticket.getId());
			dao.seatdao.deleteSeat(ticket.getId());
		}
	}
	public void returnticket(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/employee").forward(req, resp);
	}
	public void updateuser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String[]> map = req.getParameterMap();
		map.put("id", null);
		User user = new User();
		try {
			BeanUtils.copyProperties(user, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		dao.userdao.update(user);
		req.getRequestDispatcher("/manager").forward(req, resp);
	}
	public void getuser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		json.put("user", dao.userdao.get(id));
		resp.getWriter().println(json);
	}
	public void gettickets(HttpServletRequest req, HttpServletResponse resp) {
		Long id = Long.valueOf(req.getParameter("id"));
		String ids = dao.userdao.get(id).getTicketid();
		List<Ticket> tickets = new ArrayList<>();
		String[] idstr = ids.split(",");
		for (String string : idstr) {
			tickets.add(dao.ticketdao.getticketByid(Long.valueOf(string)));
		}
		json.put("tickets", tickets);
		try {
			resp.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
