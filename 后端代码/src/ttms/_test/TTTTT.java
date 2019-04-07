package ttms._test;

import java.text.ParseException;
import java.util.List;

import org.junit.Test;
import ttms._dao.DAO;
import ttms._domain.Film;
import ttms._domain.Seat;
import ttms._domain.Ticket;
import ttms._domain.User;

public class TTTTT {
	@Test
	public void test() {
		DAO dao = new DAO();
		System.out.println(dao.seatdao.getseatsByIMovieHallidAndFilmidAndStartTime(6L, 2L, "2018-06-01 14:30:00"));
	}
}
