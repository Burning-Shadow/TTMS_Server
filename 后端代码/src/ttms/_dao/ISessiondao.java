package ttms._dao;

import java.util.List;

import ttms._domain.Session;

public interface ISessiondao {
	public List<Session> getByFilmid(Long filmid);
	public List<Session> getByMovieHallid(Long MovieHallid);
	public Session getByFilmIdAndMovieHallIdAndStartTime(Long filmid,Long MovieHallId,String startTime);
	public List<Session> getAllSession();
	public void save(Session session);
	public void update(Session session);
	public void deleteBySessionid(int id);
	public void deleteByFilmid(Long filmid);
	public void deleteByMovieHallid(Long MovieHallid);
	public Session getByid(Long id);
}
