package ttms._domain;

public class Session {
	private Integer id;
	private Long filmid;
	private Long moviehallid;
	private String starttime;
	private Integer timelength;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public Integer getTimelength() {
		return timelength;
	}
	public void setTimelength(Integer timelength) {
		this.timelength = timelength;
	}
	@Override
	public String toString() {
		return "Session [id=" + id + ", filmid=" + filmid + ", moviehallid=" + moviehallid + ", starttime=" + starttime
				+ ", timelength=" + timelength + "]";
	}
}
