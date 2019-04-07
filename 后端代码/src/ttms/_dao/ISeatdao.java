package ttms._dao;
import java.util.List;
import ttms._domain.Seat;

public interface ISeatdao {
	public Seat getseat(Long id);
	public List<Seat> getAllseats();
	public List<Seat> getseatsByFilmid(Long filmid);
	public List<Seat> getseatsByIMovieHallid(Long IMovieHallid);
	public List<Seat> getseatsByIMovieHallidAndFilmid(Long filmid,Long IMovieHallid);
	public List<Seat> getseatsByIMovieHallidAndFilmidAndStartTime(Long filmid,Long IMovieHallid,String startTime);
	public void UpdateSeat(List<Seat> list);
	public void saveSeats(List<Seat> list);
	public void saveSeat(Seat seat);
	public void deleteSeat(List<Seat> list);
	public void deleteSeat(Long ticketid);
}
