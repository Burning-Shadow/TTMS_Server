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

public class FilmdaoImpl implements IFilmdao{
	@Override
	public void save(Film film) {
		String sql = "insert into film (filmname,filmprice,trailerpath,posterpath,director,screenwriter,performer,type,country,language,releasedate,filmtime,synopsis) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params= {film.getFilmName(),film.getFilmPrice(),film.getTrailerPath(),film.getPosterPath(),film.getDirector(),film.getScreenwriter(),film.getPerformer(),film.getType(),film.getCountry(),film.getLanguage(),film.getReleasedate(),film.getFilmTime(),film.getSynopsis()};
		JdbcTemplate.update(sql, params);
	}

	@Override
	public void update(Film film) {
		String sql = "update film set filmname=?,filmprice=?,trailerpath=?,posterpath=?,director=?,screenwriter=?,performer=?,type=?,country=?,language=?,releasedate=?,filmtime=?,synopsis=? where id=?";
		Object[] params= {film.getFilmName(),film.getFilmPrice(),film.getTrailerPath(),film.getPosterPath(),film.getDirector(),film.getScreenwriter(),film.getPerformer(),film.getType(),film.getCountry(),film.getLanguage(),film.getReleasedate(),film.getFilmTime(),film.getSynopsis(),film.getId()};
		JdbcTemplate.update(sql, params);
	}

	@Override
	public void delete(Long id) {
		String sql = "delete from film where id=?";
		JdbcTemplate.update(sql, id);
	}

	@Override
	public Film get(Long id) {
		String sql = "select * from film where id = ?";
		List<Film> list = JdbcTemplate.query(sql, new FilmResultSetHandler(), id);
		return list.size()==1?list.get(0):null;
	}
	
	@Override
	public List<MovieHall> getmovieHall(Long filmid) {
		String sql = "select * from session where filmid=?";
		List<Session> list = JdbcTemplate.query(sql, new SessionResultSetHandler() ,filmid);
		IMovieHalldao dao = new MovieHalldaoImpl();
		List<MovieHall> l = new ArrayList<>();
		for (Session session : list) {
			l.add(dao.getMovieHallById(session.getMoviehallid()));
		}
		return l;
	}

	@Override
	public List<Film> getAlllist() {
		String sql = "select * from film";
		List<Film> list = JdbcTemplate.query(sql, new FilmResultSetHandler());
		return list;
	}
	
	@Override
	public List<Session> getSession(Long filmid) {
		String sql = "select * from session where filmid=?";
		List<Session> list = JdbcTemplate.query(sql, new SessionResultSetHandler() ,filmid);
		return list;
	}
	@Override
	public List<Film> getlist(String message) {
		message = '%'+message+'%';
		String sql = "select * from film where concat(id,filmName,filmPrice,trailerpath,posterpath,director,screenwriter,type,country,performer,language,releasedate) like ?";
		List<Film> list = JdbcTemplate.query(sql, new FilmResultSetHandler(),message);
		return list;
	}
	class FilmResultSetHandler implements IResultSetHandler<List<Film>>{
		public List<Film> handle(ResultSet rs) throws SQLException {
			List<Film> list = new ArrayList<>();
			while(rs.next()) {
				Film film = new Film();
				list.add(film);
				film.setId(rs.getLong("id"));
				film.setFilmName(rs.getString("filmname"));
				film.setFilmPrice(rs.getDouble("filmprice"));
				film.setTrailerPath(rs.getString("trailerpath"));
				film.setPosterPath(rs.getString("posterPath"));
				film.setDirector(rs.getString("director"));
				film.setScreenwriter(rs.getString("screenwriter"));
				film.setPerformer(rs.getString("performer"));
				film.setType(rs.getString("type"));
				film.setCountry(rs.getString("country"));
				film.setLanguage(rs.getString("language"));
				film.setReleasedate(rs.getString("releasedate"));
				film.setFilmTime(rs.getString("filmtime"));
				film.setSynopsis(rs.getString("synopsis"));
			}
			return list;
		}
	}
}
