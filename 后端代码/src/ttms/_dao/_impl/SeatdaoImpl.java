package ttms._dao._impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ttms._dao.ISeatdao;
import ttms._domain.Seat;
import ttms._template.IResultSetHandler;
import ttms._template.JdbcTemplate;

public class SeatdaoImpl implements ISeatdao{
	public void updateSeat(Seat seat) {
		String sql = "update seat set x=?,y=?,state=?,MovieHallId=?,filmid=?,startTime=? where id=?";
		Object[] params = {seat.getX(),seat.getY(),seat.getState(),seat.getMovieHallId(),seat.getFilmid(),seat.getStartTime(),seat.getId()};
		JdbcTemplate.update(sql, params);
	}
	@Override
	public void saveSeats(List<Seat> list) {
		for (Seat seat : list) {
			this.saveSeat(seat);
		}
	}
	@Override
	public void saveSeat(Seat seat) {
		String sql="insert into seat (x,y,state,MovieHallId,filmid,startTime) values(?,?,?,?,?,?)";
		Object[] params = {seat.getX(),seat.getY(),seat.getState(),seat.getMovieHallId(),seat.getFilmid(),seat.getStartTime()};
		JdbcTemplate.update(sql, params);
	}
	@Override
	public List<Seat> getAllseats() {
		String sql = "select * from seat";
		List<Seat> list = JdbcTemplate.query(sql, new SeatResultSetHandler());
		return list;
	}

	@Override
	public List<Seat> getseatsByFilmid(Long filmid) {
		String sql = "select * from seat where filmid = ?";
		List<Seat> list = JdbcTemplate.query(sql, new SeatResultSetHandler(),filmid);
		return list;
	}

	@Override
	public List<Seat> getseatsByIMovieHallid(Long IMovieHallid) {
		String sql = "select * from seat where MovieHallid = ?";
		List<Seat> list = JdbcTemplate.query(sql, new SeatResultSetHandler(),IMovieHallid);
		return list;
	}

	@Override
	public List<Seat> getseatsByIMovieHallidAndFilmid(Long filmid, Long IMovieHallid) {
		String sql = "select * from seat where MovieHallid = ? and filmid = ?";
		Object[] params= {IMovieHallid,filmid};
		List<Seat> list = JdbcTemplate.query(sql, new SeatResultSetHandler(),params);
		return list;
	}

	@Override
	public void UpdateSeat(List<Seat> list) {
		for (Seat seat : list) {
			updateSeat(seat);
		}
	}
	
	@Override
	public Seat getseat(Long id) {
		String sql = "select * from seat where id=?";
		List<Seat> seat = JdbcTemplate.query(sql, new SeatResultSetHandler(), id);
		return seat==null?null:seat.get(0);
	}
	
	@Override
	public void deleteSeat(List<Seat> list) {
		for (Seat seat : list) {
			String sql = "delete from seat where id=?";
			JdbcTemplate.update(sql, seat.getId());
		}
	}
	@Override
	public List<Seat> getseatsByIMovieHallidAndFilmidAndStartTime(Long filmid, Long IMovieHallid, String startTime) {
		String sql = "select * from seat where MovieHallid = ? and filmid = ? and startTime=?";
		Object[] params= {IMovieHallid,filmid,startTime};
		List<Seat> list = JdbcTemplate.query(sql, new SeatResultSetHandler(),params);
		return list;
	}
	@Override
	public void deleteSeat(Long ticketid) {
		String sql = "delete from seat where ticketid=?";
		JdbcTemplate.update(sql, ticketid);
	}
	class SeatResultSetHandler implements IResultSetHandler<List<Seat>>{
		public List<Seat> handle(ResultSet rs) throws SQLException {
			List<Seat> list = new ArrayList<>();
			while(rs.next()) {
				Seat seat = new Seat();
				list.add(seat);
				seat.setId(rs.getLong("id"));
				seat.setX(rs.getInt("x"));
				seat.setY(rs.getInt("y"));
				seat.setState(rs.getInt("state"));
				seat.setMovieHallId(rs.getLong("moviehallid"));
				seat.setFilmid(rs.getLong("filmid"));
				seat.setStartTime(rs.getString("startTime"));
			}
			return list;
		}
	}
}
