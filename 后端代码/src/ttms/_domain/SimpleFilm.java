package ttms._domain;

public class SimpleFilm {
	private Long id;			//唯一标识
	private String filmName;	//影片名字
	private String posterPath;	//影片海报路径
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFilmName() {
		return filmName;
	}
	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}
	public String getPosterPath() {
		return posterPath;
	}
	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}
	@Override
	public String toString() {
		return "SimpleFilm [id=" + id + ", filmName=" + filmName + ", posterPath=" + posterPath + "]";
	}
}
