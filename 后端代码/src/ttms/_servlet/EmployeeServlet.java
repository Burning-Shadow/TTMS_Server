package ttms._servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import ttms._dao.DAO;
import ttms._domain.Film;
import ttms._domain.MovieHall;
import ttms._domain.Seat;
import ttms._domain.Seatbean;
import ttms._domain.Session;
import ttms._domain.SimpleFilm;
import ttms._domain.Ticket;
import ttms._domain.User;
@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private DAO dao = new DAO();
	JSONObject json = new JSONObject();

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
		String cmd = req.getParameter("cmd");
		System.out.println(cmd);
		if(cmd.equals("getAllFilm")) {					//获取所有的影片信息
			this.getAllFilm(req, resp);
		} else if(cmd.equals("getAllFilmByid")){		//获取与点击有关的所有的影片信息
			this.getAllFilmById(req, resp);
		}else if(cmd.equals("sellTicket")){				//售票
			this.sellTicket(req, resp);
		}else if(cmd.equals("getAllMovieHall")) {		//获取与点击有关的所有的影厅信息
			this.getAllMovieHall(req, resp);
		}else if(cmd.equals("returnTicket")) {			//退票
			this.returnTicket(req,resp);
		} else if(cmd.equals("getAllsimplefilm")) {		//获取所有的影片名字以及海报路径
			this.getAllsimplefilm(req,resp);
		}else if(cmd.equals("getAllsimplefilmBykey")) {	//搜索功能
			this.getAllsimplefilmBykey(req,resp);
		}else if(cmd.equals("getAllSession")) {			//获取所有的片场信息
			this.getAllSession(req,resp); 
		}else if(cmd.equals("getseats")) {				//根据影片id和影院id还有放映时间确定座位的情况
			this.getseats(req,resp);
		}else if(cmd.equals("getUserSchedule")) {		//获取该人员已经购买的票的id集合
			this.getUserSchedule(req,resp);
		}
	}
	
	private void getUserSchedule(HttpServletRequest req, HttpServletResponse resp) {
		User user = dao.userdao.get(Long.valueOf(req.getParameter("id")));
		String[] s = null;
		if(user.getTicketid()!=null) {
			s = user.getTicketid().split(",");
		}else {
			try {
				resp.getWriter().println("null");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return ;
		}
		List<Ticket> list = new ArrayList<>();
		for (String string : s) {
			Ticket ticket = new Ticket();
			ticket = dao.ticketdao.getticketByid(Long.valueOf(string));
			ticket.setMovieLength(dao.filmdao.get(ticket.getFilmid()).getFilmTime());
			list.add(ticket);
		}
		json.put("session", list);
		try {
			System.out.println(json);
			resp.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void getseats(HttpServletRequest req, HttpServletResponse resp) {
		Seatbean bean = new Seatbean();
		try {
			BeanUtils.copyProperties(bean, req.getParameterMap());
			System.out.println(bean);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		Film film = dao.filmdao.get(bean.getFilmid());
		String starttime = bean.getStarttime();
		String[] time = starttime.split("\\.");
		json.put("session", dao.sessiondao.getByFilmIdAndMovieHallIdAndStartTime(bean.getFilmid(), bean.getMoviehallid(), time[0]));
		json.put("list", dao.seatdao.getseatsByIMovieHallidAndFilmidAndStartTime(bean.getFilmid(), bean.getMoviehallid(), time[0]));
		System.out.println(dao.seatdao.getseatsByIMovieHallidAndFilmidAndStartTime(bean.getFilmid(), bean.getMoviehallid(), time[0]));
		json.put("film", film);
		System.out.println(json);
		try {
			resp.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void getAllFilmById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		json.put("film", dao.filmdao.get(id));
		json.put("session", dao.sessiondao.getByFilmid(id));
		resp.getWriter().println(json);
	}
	public void getAllSession(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Session> sessionlist = dao.sessiondao.getAllSession();
		json.put("sessions", sessionlist);
		resp.getWriter().println(json);
	}
	//信息传输的结构有问题
	public void getAllsimplefilmBykey(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String message = req.getParameter("message");
		List<Film> filmlist = dao.filmdao.getlist(message);
		List<List<Session>> sessionlist = new LinkedList<>();
		for (Film film : filmlist) {
			List<Session> list = dao.filmdao.getSession(film.getId()); 
			sessionlist.add(list);
		}
		json.put("filmlist", filmlist);
		json.put("sessionlist",sessionlist);
		resp.getWriter().print(json);
	}

	public void getAllsimplefilm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<SimpleFilm> list = dao.simplefilmdao.getAlllist();
		json.put("filmlist", list);
		resp.getWriter().print(json);
	}

	public void returnTicket(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException, IOException{
		Long id = Long.valueOf(req.getParameter("id"));
		List<Seat> list = dao.seatdao.getseatsByFilmid(Long.valueOf(dao.ticketdao.getticketByid(id).getFilmid()));
		dao.ticketdao.delete(id);
		dao.seatdao.deleteSeat(list);
	}
	public void getAllMovieHall(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException, IOException {
		Long filmid = Long.valueOf(req.getParameter("id"));
		List<Session> list = dao.sessiondao.getByFilmid(filmid);
		List<MovieHall> moviehalllist = new ArrayList<>();
		 for (Session session : list) {
			moviehalllist.add(dao.moviehalldao.getMovieHallById(session.getMoviehallid()));
		}
		json.put("moviehalllist", moviehalllist);
		resp.getWriter().println(json);
	}
	public void getAllFilm(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException, IOException {
		int sum = 0;
		List<Film> list = dao.filmdao.getAlllist();
		json.put("film", dao.filmdao.getAlllist());
		List<Integer> ticket = new ArrayList<>();
		for (Film film : list) {
			List<Ticket> tickets = dao.ticketdao.getListByFilmId(film.getId());
			for (Ticket ticket2 : tickets) {
				sum++;
			}
			ticket.add(sum);
			sum = 0;
		}
		json.put("tickets", ticket);
		resp.getWriter().println(json);
	}
	public void sellTicket(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException, IOException{
		List<Seat> seat = new ArrayList<>();
		String string = req.getParameter("list");
		seat = JSON.parseObject(string,new TypeReference<List<Seat>>(){});
		System.out.println(string);
		for (Seat seat2 : seat) {
			System.out.println(seat2);
		}
		
		try {
			BeanUtils.copyProperties(seat, req.getParameter("list"));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		System.out.println(seat);
		System.out.println(req.getParameter("userid"));
		Long ids = json.parseObject(req.getParameter("userid"),new TypeReference<Long>(){});
		System.out.println(ids);
		for(int i = 0; i < seat.size(); i++ ) {
			Ticket ticket = new Ticket();
			ticket.setFilmid(seat.get(i).getFilmid());
			ticket.setFilmname(dao.filmdao.get(seat.get(i).getFilmid()).getFilmName());
			ticket.setMoviehallid(seat.get(i).getMovieHallId());
			ticket.setMoviehallname(dao.moviehalldao.getMovieHallById(seat.get(i).getMovieHallId()).getName());
			ticket.setPrice(dao.filmdao.get(seat.get(i).getFilmid()).getFilmPrice());
			ticket.setSession(null);
			ticket.setStarttime(seat.get(i).getStartTime());
			ticket.setX(seat.get(i).getX());
			ticket.setY(seat.get(i).getY());
			ticket.setMovieLength(dao.filmdao.get(seat.get(i).getFilmid()).getFilmTime());
			dao.ticketdao.saveTicket(ticket);
			try {
				ticket = dao.ticketdao.getTicketBysStartTimeAndfilmidAndMovieHallidAndXAndY(ticket.getFilmid(), ticket.getMoviehallid(), seat.get(i).getStartTime(), ticket.getX(), ticket.getY());
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			User user = dao.userdao.get(ids);
			if(user.getTicketid()==null) {
				user.setTicketid(ticket.getId().toString());
			}else {
				user.setTicketid(user.getTicketid()+","+ticket.getId());
			}
			dao.userdao.update(user);
			System.out.println(seat);
			dao.seatdao.saveSeat(seat.get(i));
		}
		resp.getWriter().println("SUCCESS");
	}
}