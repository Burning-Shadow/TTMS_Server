package ttms._domain;

public class Personal extends User{
	private Ticket ticket;
	private Double balance;
	private User user;
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Personal [ticket=" + ticket + ", balance=" + balance + ", user=" + user + "]";
	}
}
