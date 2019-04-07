package ttms._dao._impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ttms._dao.ITicket;
import ttms._domain.Ticket;
import ttms._template.IResultSetHandler;
import ttms._template.JdbcTemplate;

public class TicketdaoImpl implements ITicket{

	@Override
	public Ticket getticketByid(Long id) {
		String sql = "select * from ticket where id=?";
		return JdbcTemplate.query(sql, new TicketResultSetHandler(), id).get(0);
	}

	@Override
	public void delete(Long id) {
		String sql = "delete from ticket where id = ?";
		JdbcTemplate.update(sql, id);
	}

	@Override
	public List<Ticket> getAlllistByUserId(Long id) {
		String sql = "select * from ticket where userid = ?";
		return JdbcTemplate.query(sql, new TicketResultSetHandler(), id);
	}
	@Override
	public void saveTicket(Ticket ticket) {
		String sql = "insert into ticket (session,filmid,moviehallid,filmname,moviehallname,starttime,movieLength,x,y,price) value(?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {ticket.getSession(),ticket.getFilmid(),ticket.getMoviehallid(),ticket.getFilmname(),ticket.getMoviehallname(),
				ticket.getStarttime(),ticket.getMovieLength(),ticket.getX(),ticket.getY(),ticket.getPrice()};
		JdbcTemplate.update(sql, params);
	}

	@Override
	public void saveTickets(List<Ticket> tickets) {
		
	}
	@Override
	public void updateTicket(Ticket ticket) {
		String sql = "update ticket set session=?,filmid=?,moviehallid=?,filmname=?,moviehallname=?,starttime=?,movieLength=?,x=?,y=?,price=? where id=?";
		Object[] params= {ticket.getSession(),ticket.getFilmid(),ticket.getMoviehallid(),ticket.getFilmname(),ticket.getMoviehallname(),
				ticket.getStarttime(),ticket.getMovieLength(),ticket.getX(),ticket.getY(),ticket.getPrice(),ticket.getId()};
		JdbcTemplate.update(sql, params);
	}
	@Override
	public void updateTickets(List<Ticket> tickets) {
		for (Ticket ticket : tickets) {
			this.updateTicket(ticket);
		}
	}
	@Override
	public List<Ticket> getAllTickets() {
		String sql = "select * from ticket";
		List<Ticket> list = JdbcTemplate.query(sql, new TicketResultSetHandler());
		return list;
	}
	@Override
	public Ticket getTicketBysStartTimeAndfilmidAndMovieHallidAndXAndY(Long filmid, Long moviehallid, String StartTime,
			int x, int y) {
		String sql = "select * from ticket where filmid=? and moviehallid=? and starttime=? and x=? and y=?";
		Object[] params = {filmid,moviehallid,StartTime,x,y};
		return JdbcTemplate.query(sql, new TicketResultSetHandler(), params).get(0);
	}
	@Override
	public List<Ticket> getListByFilmId(Long id) {
		String sql = "select * from ticket where filmid = ?";
		return JdbcTemplate.query(sql, new TicketResultSetHandler(), id);
	}
	class TicketResultSetHandler implements IResultSetHandler<List<Ticket>>{
		public List<Ticket> handle(ResultSet rs) throws SQLException {
			List<Ticket> list = new ArrayList<>();
			while(rs.next()) {
				Ticket ticket = new Ticket();
				list.add(ticket);
				ticket.setId(rs.getLong("id"));
				ticket.setSession(rs.getInt("session"));
				ticket.setFilmid(rs.getLong("filmid"));
				ticket.setMoviehallid(rs.getLong("moviehallid"));
				ticket.setFilmname(rs.getString("filmname"));
				ticket.setMoviehallname(rs.getString("moviehallname"));
				ticket.setStarttime(rs.getString("starttime"));
				ticket.setX(rs.getInt("x"));
				ticket.setY(rs.getInt("y"));
				ticket.setPrice(rs.getDouble("price"));
				ticket.setMovieLength(rs.getString("movieLength"));
			}
			return list;
		}
	}
}
