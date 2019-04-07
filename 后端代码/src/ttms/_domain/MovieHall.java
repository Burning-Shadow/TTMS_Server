package ttms._domain;

public class MovieHall {
	private Long id;
	private String name;
	private Integer seat;
	private Integer numberRemaining;
	private Integer xMax;
	private Integer yMax;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSeat() {
		return seat;
	}
	public void setSeat(Integer seat) {
		this.seat = seat;
	}
	public Integer getNumberRemaining() {
		return numberRemaining;
	}
	public void setNumberRemaining(Integer numberRemaining) {
		this.numberRemaining = numberRemaining;
	}
	public Integer getxMax() {
		return xMax;
	}
	public void setxMax(Integer xMax) {
		this.xMax = xMax;
	}
	public Integer getyMax() {
		return yMax;
	}
	public void setyMax(Integer yMax) {
		this.yMax = yMax;
	}
	@Override
	public String toString() {
		return "MovieHall [id=" + id + ", name=" + name + ", seat=" + seat + ", numberRemaining=" + numberRemaining
				+ ", xMax=" + xMax + ", yMax=" + yMax + "]";
	}
}
