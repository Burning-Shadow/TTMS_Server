package ttms._domain;

public class Ticket {
	private Integer session;	//演出计划id
	private Long filmid;		//电影id
	private Long moviehallid;	//影厅id
	
	private Long id;
	private String filmname;	//电影名
	private String moviehallname;//影厅名字
	private String starttime;		//开始时间
	private String movieLength;	//时长
	private int x;				//座位x
	private int y;				//座位y
	private Double price;		//票价
	
	public Long getId() {
		return id;
	}
	public Integer getSession() {
		return session;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setSession(Integer session) {
		this.session = session;
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
	public String getFilmname() {
		return filmname;
	}
	public void setFilmname(String filmname) {
		this.filmname = filmname;
	}
	public String getMoviehallname() {
		return moviehallname;
	}
	public void setMoviehallname(String moviehallname) {
		this.moviehallname = moviehallname;
	}
	public String getMovieLength() {
		return movieLength;
	}
	public void setMovieLength(String movieLength) {
		this.movieLength = movieLength;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	@Override
	public String toString() {
		return "Ticket [session=" + session + ", filmid=" + filmid + ", moviehallid=" + moviehallid + ", id=" + id
				+ ", filmname=" + filmname + ", moviehallname=" + moviehallname + ", starttime=" + starttime
				+ ", movieLength=" + movieLength + ", x=" + x + ", y=" + y + ", price=" + price + "]";
	}
}
