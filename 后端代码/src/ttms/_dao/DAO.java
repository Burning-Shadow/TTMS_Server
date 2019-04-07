package ttms._dao;

import ttms._dao._impl.FilmdaoImpl;
import ttms._dao._impl.MovieHalldaoImpl;
import ttms._dao._impl.SeatdaoImpl;
import ttms._dao._impl.SessiondaoImpl;
import ttms._dao._impl.SimplFilmsdaoImpl;
import ttms._dao._impl.TicketdaoImpl;
import ttms._dao._impl.UserdaoImpl;

public class DAO {
	public IFilmdao filmdao = new FilmdaoImpl();
	public IMovieHalldao moviehalldao = new MovieHalldaoImpl();
	public ISeatdao seatdao = new SeatdaoImpl();
	public ISessiondao sessiondao = new SessiondaoImpl();
	public IsimpleFilmsdao simplefilmdao = new SimplFilmsdaoImpl();
	public ITicket ticketdao = new TicketdaoImpl();
	public IUserdao userdao = new UserdaoImpl();
}
