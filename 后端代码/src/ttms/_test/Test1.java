package ttms._test;

import java.util.List;

import org.junit.Test;

import ttms._dao.IFilmdao;
import ttms._dao.ISessiondao;
import ttms._dao._impl.FilmdaoImpl;
import ttms._dao._impl.SessiondaoImpl;
import ttms._domain.Session;

public class Test1 {
	private IFilmdao filmdao = new FilmdaoImpl();
	@Test
	public void test() {
		ISessiondao dao = new SessiondaoImpl();
		Session S = new Session();
		List<Session> session = dao.getByMovieHallid(1L);
		session.get(0).setFilmid(3L);
		S = session.get(0);
		session.set(0, S);
//		dao.save(S);
		S.setMoviehallid(2L);
		dao.update(S);
		dao.deleteBySessionid(1);
		filmdao.get(1L);
	}
}
