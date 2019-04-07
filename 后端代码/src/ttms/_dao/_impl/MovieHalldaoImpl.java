package ttms._dao._impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ttms._dao.IFilmdao;
import ttms._dao.IMovieHalldao;
import ttms._domain.Film;
import ttms._domain.MovieHall;
import ttms._domain.Session;
import ttms._template.IResultSetHandler;
import ttms._template.JdbcTemplate;

public class MovieHalldaoImpl implements IMovieHalldao{
	@Override
	public List<MovieHall> getAlllist() {
		String sql = "select * from moviehall";
		List<MovieHall> list = JdbcTemplate.query(sql, new MovieHallResultSetHandler());
		return list;
	}

	@Override
	public MovieHall getMovieHallById(Long id) {
		String sql = "select * from moviehall where id = ?";
		List<MovieHall> list = JdbcTemplate.query(sql, new MovieHallResultSetHandler(), id);
		return list.size()==1?list.get(0):null;
	}

	@Override
	public List<Film> getlistByMovieHallModel(Long moviehallid) {
		String sql = "select * from session where moviehallid = ?";
		List<Session> list = JdbcTemplate.query(sql, new SessionResultSetHandler(), moviehallid);
		IFilmdao dao = new FilmdaoImpl();
		List<Film>l = new ArrayList<>();
		for (Session session : list) {
			l.add(dao.get(session.getFilmid()));
		}
		return l;
	}
	
	@Override
	public List<Session> getAllSession(Long id) {
		String sql = "select * from session where moviehallid = ?";
		List<Session> list = JdbcTemplate.query(sql, new SessionResultSetHandler(), id);
		return list;
	}
	@Override
	public void deleteMoviehall(Long id) {
		String sql = "delete from moviehall where id=?";
		JdbcTemplate.update(sql, id);
	}
	@Override
	public void savemoviehall(MovieHall moviehall) {
		String sql = "insert into moviehall(name,seat,numberRemaining,xMax,yMax) values(?,?,?,?,?)";
		Object[] params = {moviehall.getName(),moviehall.getSeat(),moviehall.getNumberRemaining(),moviehall.getxMax(),moviehall.getyMax()};
		JdbcTemplate.update(sql, params);
	}
	@Override
	public void updatemoviehall(MovieHall moviehall) {
		String sql = "update moviehall set name=?,seat=?,numberRemaining=?,xMax=?,yMax=? where id=?";
		Object[] params = {moviehall.getName(),moviehall.getSeat(),moviehall.getNumberRemaining(),moviehall.getxMax(),moviehall.getyMax(),moviehall.getId()};
		JdbcTemplate.update(sql, params);
		
	}
	class MovieHallResultSetHandler implements IResultSetHandler<List<MovieHall>>{
		public List<MovieHall> handle(ResultSet rs) throws SQLException {
			List<MovieHall> list = new ArrayList<>();
			while(rs.next()) {
				MovieHall movie = new MovieHall();
				list.add(movie);
				movie.setId(rs.getLong("id"));
				movie.setName(rs.getString("name"));
				movie.setNumberRemaining(rs.getInt("numberRemaining"));
				movie.setSeat(rs.getInt("seat"));
				movie.setxMax(rs.getInt("xMax"));
				movie.setyMax(rs.getInt("yMax"));
			}
			return list;
		}
	}
}
