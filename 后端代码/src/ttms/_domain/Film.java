package ttms._domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ttms._dao.IFilmdao;
import ttms._dao._impl.FilmdaoImpl;

public class Film {
	private Long id;			//唯一标识
	private String filmName;	//影片名字
	private Double filmPrice;	//影片价格
	private String trailerPath;	//影片预告片路径
	private String posterPath;	//影片海报路径
	private String director;	//导演
	private String screenwriter;//编剧
	private String performer;	//主演
	private String type;		//类型
	private String country;		//制片国家
	private String language;	//语言
	private String releasedate;	//上映日期
	private String filmTime;	//片长
	private String synopsis;	//影片简介
	private Integer state;		//影片的状态
	private List<List<String>> listsynopsis;
	
	public List<String> getsynopsisContent() throws Exception{
		List<String> S = new  ArrayList<>();
		File file = new File(this.getDirector());//Text文件
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)
        		, "GBK"));//构造一个BufferedReader类来读取文件
        String s = null;
        while((s = br.readLine())!=null){//使用readLine方法，一次读一行
        	if(!s.equals("")) {
        		S.add(s);
        	}
        }
        br.close();
		return S;
	}
	public static List<List<String>> getListsynopsisContent() throws Exception {
		IFilmdao filmdao = new FilmdaoImpl();
		List<List<String>> Str = new ArrayList<>();
		List<Film> film = filmdao.getAlllist();
		for (Film film2 : film) {
			List<String> S = new  ArrayList<>();
				File file = new File(film2.getDirector());//Text文件
		        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)
		        		, "GBK"));//构造一个BufferedReader类来读取文件
		        String s = null;
		        while((s = br.readLine())!=null){//使用readLine方法，一次读一行
		        	if(!s.equals("")) {
		        		S.add(s);
		        	}
		        }
		        Str.add(S);
		        br.close();
		        film2.setListsynopsis(Str);
		}
		return Str;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
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
	public Double getFilmPrice() {
		return filmPrice;
	}
	public void setFilmPrice(Double filmPrice) {
		this.filmPrice = filmPrice;
	}
	public String getTrailerPath() {
		return trailerPath;
	}
	public void setTrailerPath(String trailerPath) {
		this.trailerPath = trailerPath;
	}
	public String getPosterPath() {
		return posterPath;
	}
	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getScreenwriter() {
		return screenwriter;
	}
	public void setScreenwriter(String screenwriter) {
		this.screenwriter = screenwriter;
	}
	public String getPerformer() {
		return performer;
	}
	public void setPerformer(String performer) {
		this.performer = performer;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getReleasedate() {
		return releasedate;
	}
	public void setReleasedate(String releasedate) {
		this.releasedate = releasedate;
	}
	public String getFilmTime() {
		return filmTime;
	}
	public void setFilmTime(String filmTime) {
		this.filmTime = filmTime;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public List<List<String>> getListsynopsis() {
		return listsynopsis;
	}
	public void setListsynopsis(List<List<String>> listsynopsis) {
		this.listsynopsis = listsynopsis;
	}
	@Override
	public String toString() {
		return "Film [id=" + id + ", filmName=" + filmName + ", filmPrice=" + filmPrice + ", trailerPath=" + trailerPath
				+ ", posterPath=" + posterPath + ", director=" + director + ", screenwriter=" + screenwriter
				+ ", performer=" + performer + ", type=" + type + ", country=" + country + ", language=" + language
				+ ", releasedate=" + releasedate + ", filmTime=" + filmTime + ", synopsis=" + synopsis + ", state="
				+ state + ", listsynopsis=" + listsynopsis + "]";
	}
}
