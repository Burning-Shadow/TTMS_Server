package ttms._servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import ttms._dao.DAO;
import ttms._domain.Film;
import ttms._domain.MovieHall;
import ttms._domain.Session;
import ttms._domain.SimpleFilm;
import ttms._domain.User;

@WebServlet("/manager")
public class ManagerServlet extends EmployeeServlet{
	private static final long serialVersionUID = 1L;
	private DAO dao = new DAO();
	JSONObject json = new JSONObject();
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		if(cmd.equals("deleteUser")) {					//删除指定用户
			this.delete(req, resp);
		} else if(cmd.equals("saveUser")){				//新增用户
			this.save(req, resp);
		}else if(cmd.equals("updateUser")) {			//更新用户信息
			this.update(req, resp);
		}else if(cmd.equals("getAllUser")) {			//获取所有用户信息
			this.getAllUser(req,resp);	
		}else if(cmd.equals("getUser")) {				//获取某个用户的详细信息
			this.get(req,resp);
		}else if(cmd.equals("getAllFilm")) {			//获取所有的film信息
			this.getAllFilm(req,resp);	
		}else if(cmd.equals("deleteFilm")) {			//删除某个film
			this.deleteFilm(req,resp);
		}else if(cmd.equals("updateFilm")) {			//对某个影片进行编辑操作
			this.updateFilm(req,resp);
		}else if(cmd.equals("saveFilm")) {				//添加影片信息
			this.saveFilm(req,resp);
		}else if(cmd.equals("getFilm")) {				//获取单个影片详细信息
			this.getFilm(req,resp);
		}else if(cmd.equals("getsession")) {			//根据filmid获取session
			this.getsession(req,resp);
		}else if(cmd.equals("deletesession")) {			//根据id删除session
			this.deletesession(req,resp);
		}else if(cmd.equals("updatesession")) {			//修改session
			this.updatesession(req,resp);
		}else if(cmd.equals("savesession")) {			//增加session
			this.savesession(req,resp);
		}else if(cmd.equals("getallsession")) {			//获取所有session
			this.getallsession(req,resp);
		}else if(cmd.equals("sellTicket")){				//售票
			this.sellTicket(req, resp);
		}else if(cmd.equals("getAllMovieHall")) {		//获取所有的影厅信息
			this.getAllMovieHall1(req, resp);
		}else if(cmd.equals("returnTicket")) {			//退票
			this.returnTicket(req,resp);
		} else if(cmd.equals("getAllsimplefilm")) {		//获取所有的影片名字以及海报路径
			this.getAllsimplefilm(req,resp);
		}else if(cmd.equals("getAllsimplefilmBykey")) {	//搜索功能
			this.getAllsimplefilmBykey(req,resp);
		}else if(cmd.equals("getAllSession")) {			//获取所有的片场信息
			this.getAllSession(req,resp);
		}else if(cmd.equals("jspGetAllFilms")) {		//jsp页面获取所有的电影信息
			this.jspGetAllFilms(req,resp);
		}else if(cmd.equals("edituser")) {				//用户的编辑处理
			this.edituser(req,resp);
		}else if(cmd.equals("editFilm")) {				//电影信息的编辑处理
			this.editFilm(req, resp);
		}else if(cmd.equals("editSession")) {			//剧目管理的编辑的信息处理
			this.editSession(req,resp);	
		}else if(cmd.equals("session")) {				//jsp获取剧目信息
			this.session(req, resp);
		}else if(cmd.equals("editmoviehall")) {			//影厅信息的编辑处理
			this.editmoviehall(req,resp);
		}else if(cmd.equals("deletemoviehall")) {		//删除影厅的处理
			this.deletemoviehall(req,resp);
		}else if(cmd.equals("savemoviehall")) {			//保存影厅的处理
			this.savemoviehall(req,resp);
		}else if(cmd.equals("updatemoviehall")) {		//更新影厅的处理
			this.updatemoviehall(req,resp);
		}
	}
	private void updatemoviehall(HttpServletRequest req, HttpServletResponse resp) {
		MovieHall moviehall = new MovieHall();
		try {
			BeanUtils.copyProperties(moviehall, req.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		dao.moviehalldao.updatemoviehall(moviehall);
		try {
			req.getRequestDispatcher("/manager?cmd=getAllMovieHall").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
	private void savemoviehall(HttpServletRequest req, HttpServletResponse resp) {
		MovieHall moviehall = new MovieHall();
		try {
			BeanUtils.copyProperties(moviehall, req.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		dao.moviehalldao.savemoviehall(moviehall);
		try {
			req.getRequestDispatcher("/manager?cmd=getAllMovieHall").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
	private void deletemoviehall(HttpServletRequest req, HttpServletResponse resp) {
		dao.moviehalldao.deleteMoviehall((Long.valueOf(req.getParameter("id"))));
		try {
			req.getRequestDispatcher("/manager?cmd=getAllMovieHall").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void editmoviehall(HttpServletRequest req, HttpServletResponse resp) {
		MovieHall moviehall = dao.moviehalldao.getMovieHallById(Long.valueOf(req.getParameter("id")));
		req.setAttribute("moviehall", moviehall);
		try {
			req.getRequestDispatcher("/views/moviehalledit.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
	private void getAllMovieHall1(HttpServletRequest req, HttpServletResponse resp) {
		List<MovieHall> list = dao.moviehalldao.getAlllist();
		req.setAttribute("list", list);
		try {
			req.getRequestDispatcher("/views/moviehalllist.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void session(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("moviehall", dao.moviehalldao.getAlllist());
		req.setAttribute("film", dao.filmdao.getAlllist());
		try {
			try {
				req.getRequestDispatcher("/views/session.jsp").forward(req, resp);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	private void editSession(HttpServletRequest req, HttpServletResponse resp) {
		Session session = new Session();
		session.setFilmid(Long.valueOf(req.getParameter("filmid")));
		session.setMoviehallid(Long.valueOf(req.getParameter("moviehallid")));
		session.setStarttime(req.getParameter("startdate")+" "+req.getParameter("starttime"));
		session.setTimelength(Integer.valueOf(req.getParameter("timelength")));
		session.setId(Integer.valueOf(req.getParameter("id")));
		System.out.println(session);
		dao.sessiondao.update(session);
		System.out.println(session);
		try {
			this.getallsession(req, resp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void updateFilm(HttpServletRequest req, HttpServletResponse resp) {
		Film film = new Film();
		try {
			BeanUtils.copyProperties(film, req.getParameterMap());
			dao.filmdao.update(film);
			req.getRequestDispatcher("/manager?cmd=jspGetAllFilms").forward(req, resp);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void edituser(HttpServletRequest req, HttpServletResponse resp) {
		User user = dao.userdao.get(Long.valueOf(req.getParameter("id")));
		req.setAttribute("user", user);
		System.out.println(user);
		try {
			req.getRequestDispatcher("/views/useredit.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void jspGetAllFilms(HttpServletRequest req, HttpServletResponse resp) {
		List<Film> list = dao.filmdao.getAlllist();
		req.setAttribute("list", list);
		try {
			try {
				req.getRequestDispatcher("/views/list.jsp").forward(req, resp);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	private void getallsession(ServletRequest req, ServletResponse resp) throws IOException {
		List<Session> list = dao.sessiondao.getAllSession();
		req.setAttribute("list", list);
		try {
			req.getRequestDispatcher("/views/sessionlist.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	private void savesession(ServletRequest req, ServletResponse resp) throws IOException {
		Session session = new Session();
		session.setFilmid(Long.valueOf(req.getParameter("filmid")));
		session.setMoviehallid(Long.valueOf(req.getParameter("moviehallid")));
		session.setStarttime(req.getParameter("startdate")+" "+req.getParameter("starttime"));
		session.setTimelength(Integer.valueOf(req.getParameter("timelength")));
		System.out.println(session);
		dao.sessiondao.save(session);
		this.getallsession(req, resp);
	}
	private void updatesession(ServletRequest req, ServletResponse resp) throws IOException {
		req.setAttribute("moviehall", dao.moviehalldao.getAlllist());
		req.setAttribute("film", dao.filmdao.getAlllist());
		req.setAttribute("session", dao.sessiondao.getByid(Long.valueOf(req.getParameter("id"))));
		System.out.println(req.getParameter("id"));
		try {
			req.getRequestDispatcher("/views/sessionedit.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	private void deletesession(ServletRequest req, ServletResponse resp) throws IOException {
		String idstr = req.getParameter("id");
		int id = Integer.valueOf(idstr);
		dao.sessiondao.deleteBySessionid(id);
		this.getallsession(req, resp);
	}
	private void getsession(ServletRequest req, ServletResponse resp) throws IOException {
		Long id = Long.valueOf(req.getParameter("filmid"));
		json.put("session", dao.sessiondao.getByFilmid(id));
		resp.getWriter().print(json);
	}
	private void getFilm(ServletRequest req, ServletResponse resp) throws IOException {
		JSONObject json = new JSONObject();
		json.put("film", dao.filmdao.get(Long.valueOf(req.getParameter("id"))));
		resp.getWriter().print(json);
	}
	private void saveFilm(ServletRequest req, ServletResponse resp){
		Film film = new Film();
		Map<String, String[]> map = req.getParameterMap();
		try {
			BeanUtils.copyProperties(film, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		dao.filmdao.save(film);
		try {
			req.getRequestDispatcher("/manager?cmd=jspGetAllFilms").forward(req, resp);
		}catch (Exception e) {
		}
	}
	private void editFilm(ServletRequest req, ServletResponse resp){
		Film film = new Film();
		film = dao.filmdao.get(Long.valueOf(req.getParameter("id")));
		req.setAttribute("film", film);
		System.out.println(film);
		try {
			req.getRequestDispatcher("/views/filmedit.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void deleteFilm(ServletRequest req, ServletResponse resp) {
		Long id = Long.valueOf(req.getParameter("id"));
		dao.filmdao.delete(id);
		dao.seatdao.deleteSeat(dao.seatdao.getseatsByFilmid(id));
		dao.sessiondao.deleteByFilmid(id);
		try {
			req.getRequestDispatcher("/manager?cmd=jspGetAllFilms").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void getAllFilm(ServletRequest req, ServletResponse resp){
		JSONObject json = new JSONObject();
		List<SimpleFilm> list = dao.simplefilmdao.getAlllist();
		json.put("filmlist", list);
		try {
			resp.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void get(ServletRequest req, ServletResponse resp) throws IOException {
		resp.getWriter().print(JSON.toJSON(dao.userdao.get(Long.valueOf(req.getParameter("id")))));
	}

	public void update(ServletRequest req, ServletResponse resp){
		User user = new User();
		try {
			BeanUtils.copyProperties(user, req.getParameterMap());
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		}
		user.setId(Long.valueOf(req.getParameter("id")));
		System.out.println(user);
		dao.userdao.update(user);
		try {
			req.getRequestDispatcher("/manager?cmd=getAllUser").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getAllUser(ServletRequest req, ServletResponse resp) throws IOException {
		req.setAttribute("userlist", dao.userdao.getAllUser());
		try {
			req.getRequestDispatcher("/views/userlist.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
	public void save(ServletRequest req, ServletResponse resp){
		User user = new User();
		try {
			BeanUtils.copyProperties(user, req.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		dao.userdao.save(user);
		try {
			req.getRequestDispatcher("/manager?cmd=getAllUser").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void delete(ServletRequest req, ServletResponse resp) {
		dao.userdao.delete(Long.valueOf(req.getParameter("id")));
		try {
			req.getRequestDispatcher("/manager?cmd=getAllUser").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
