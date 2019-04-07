package ttms._domain;

public class Seatbean {
	private Long filmid;
	private Long moviehallid;
	private String starttime;
	private String cmd;
	public Long getFilmid() {
		return filmid;
	}
	public void setFilmid(Long filmid) {
		this.filmid = filmid;
	}
	public Long getMoviehallid() {
		return moviehallid;
	}
	public void setMoviehallid(Long moviehallid) {
		this.moviehallid = moviehallid;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	@Override
	public String toString() {
		return "Seatbean [filmid=" + filmid + ", moviehallid=" + moviehallid + ", starttime=" + starttime + ", cmd="
				+ cmd + "]";
	}
}
