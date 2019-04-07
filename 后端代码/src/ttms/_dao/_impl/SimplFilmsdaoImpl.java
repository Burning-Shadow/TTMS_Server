package ttms._dao._impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ttms._dao.IsimpleFilmsdao;
import ttms._domain.SimpleFilm;
import ttms._template.IResultSetHandler;
import ttms._template.JdbcTemplate;

public class SimplFilmsdaoImpl implements IsimpleFilmsdao{

	@Override
	public void delete(Long id) {
		String sql = "delete from film where id=?";
		JdbcTemplate.update(sql, id);
	}

	@Override
	public List<SimpleFilm> getAlllist() {
		String sql = "select id,filmName,posterpath from film";
		List<SimpleFilm> list = JdbcTemplate.query(sql, new SimpleFilmResultSetHandler());
		return list;
	}
	
	@Override
	public SimpleFilm get(Long id) {
		String sql = "select * from film where id=?";
		List<SimpleFilm> list = JdbcTemplate.query(sql, new SimpleFilmResultSetHandler(), id);
		return list==null?null:list.get(0);
	}
	
	class SimpleFilmResultSetHandler implements IResultSetHandler<List<SimpleFilm>>{
		public List<SimpleFilm> handle(ResultSet rs) throws SQLException {
			List<SimpleFilm> list = new ArrayList<>();
			while(rs.next()) {
				SimpleFilm film = new SimpleFilm();
				list.add(film);
				film.setId(rs.getLong("id"));
				film.setFilmName(rs.getString("filmname"));
				film.setPosterPath(rs.getString("posterPath"));
			}
			return list;
		}
		
	}
}
