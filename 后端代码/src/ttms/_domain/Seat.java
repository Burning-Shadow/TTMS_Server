package ttms._domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Seat {
	private Long id;
	private int x;				//x值
	private int y;				//y值
	private int state;			//状态
	private Long MovieHallId;	//影厅id
	private Long filmid;		//电影id
	private String startTime;		//开始时间
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Long getFilmid() {
		return filmid;
	}
	public void setFilmid(Long filmid) {
		this.filmid = filmid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Long getMovieHallId() {
		return MovieHallId;
	}
	public void setMovieHallId(Long movieHallId) {
		MovieHallId = movieHallId;
	}
	@Override
	public String toString() {
		return "Seat [id=" + id + ", x=" + x + ", y=" + y + ", state=" + state + ", MovieHallId=" + MovieHallId
				+ ", filmid=" + filmid + ", startTime=" + startTime + "]";
	}
}
