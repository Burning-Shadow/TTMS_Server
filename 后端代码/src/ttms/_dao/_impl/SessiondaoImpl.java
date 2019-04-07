package ttms._dao._impl;

import java.util.List;

import ttms._dao.ISessiondao;
import ttms._domain.Session;
import ttms._template.JdbcTemplate;

public class SessiondaoImpl implements ISessiondao{

	@Override
	public List<Session> getByFilmid(Long filmid) {
		String sql = "select * from session where filmid=?";
		List<Session> list = JdbcTemplate.query(sql, new SessionResultSetHandler(),filmid);
		return list;
	}
	@Override
	public List<Session> getByMovieHallid(Long MovieHallid) {
		String sql = "select * from session where movieHallid=?";
		List<Session> list = JdbcTemplate.query(sql, new SessionResultSetHandler(),MovieHallid);
		return list;
	}
	@Override
	public List<Session> getAllSession() {
		String sql = "select * from session";
		List<Session> list = JdbcTemplate.query(sql, new SessionResultSetHandler());
		return list;
	}
	@Override
	public Session getByid(Long id) {
		String sql = "select * from session where id = ?";
		List<Session> list = JdbcTemplate.query(sql, new SessionResultSetHandler(),id);
		return list.size()==1?list.get(0):null;
	}
	@Override
	public void save(Session session) {
		String sql = "insert into session (filmid,moviehallid,starttime,timelength) value(?,?,?,?)";
		Object[] params= {session.getFilmid(),session.getMoviehallid(),session.getStarttime(),session.getTimelength()};
		JdbcTemplate.update(sql, params);
	}
	@Override
	public void update(Session session) {
		String sql = "update session set filmid=?,moviehallid=?,starttime=?,timelength=? where id=?";
		Object[] params= {session.getFilmid(),session.getMoviehallid(),session.getStarttime(),session.getTimelength(),session.getId()};
		JdbcTemplate.update(sql, params);
	}
	@Override
	public void deleteBySessionid(int id) {
		String sql = "delete from session where id=?";
		JdbcTemplate.update(sql, id);
	}

	@Override
	public void deleteByFilmid(Long filmid) {
		String sql = "delete from session where filmid=?";
		JdbcTemplate.update(sql, filmid);
	}
	@Override
	public void deleteByMovieHallid(Long MovieHallid) {
		String sql = "delete from session where moviehallid=?";
		JdbcTemplate.update(sql, MovieHallid);
	}
	@Override
	public Session getByFilmIdAndMovieHallIdAndStartTime(Long filmid, Long MovieHallId,String startTime) {
		String sql = "select * from session where filmid=? and movieHallid=? and starttime=?";
		Object[] params= {filmid,MovieHallId,startTime};
		List<Session> session = JdbcTemplate.query(sql, new SessionResultSetHandler(),params);
		if(!session.isEmpty()) {
			return session.get(0);
		}
		else {
			return null;
		}
	}
}
