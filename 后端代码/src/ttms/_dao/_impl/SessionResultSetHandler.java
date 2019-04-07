package ttms._dao._impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ttms._domain.Session;
import ttms._template.IResultSetHandler;

public class SessionResultSetHandler implements IResultSetHandler<List<Session>>{
	public List<Session> handle(ResultSet rs) throws SQLException {
		List<Session> list = new ArrayList<>();
		while(rs.next()) {
			Session session = new Session();
			list.add(session);
			session.setId(rs.getInt("id"));
			session.setFilmid(rs.getLong("filmid"));
			session.setMoviehallid(rs.getLong("moviehallid"));
			session.setStarttime(rs.getString("starttime"));
			session.setTimelength(rs.getInt("timelength"));
		}
		return list;
	}
}
