package ttms._dao;

import java.util.List;

import ttms._domain.Ticket;

public interface ITicket {
	public Ticket getticketByid(Long id);
	public void delete(Long id);						
	public List<Ticket> getAlllistByUserId(Long id);
	public List<Ticket> getListByFilmId(Long id);
	public void saveTicket(Ticket ticekt);
	public void saveTickets(List<Ticket> tickets);
	public void updateTicket(Ticket ticket);
	public List<Ticket> getAllTickets();
	public void updateTickets(List<Ticket> tickets);
	public Ticket getTicketBysStartTimeAndfilmidAndMovieHallidAndXAndY(Long filmid,Long moviehallid,String StartTime,int x,int y);
}
