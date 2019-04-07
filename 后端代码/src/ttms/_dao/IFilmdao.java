package ttms._dao;

import java.util.List;

import ttms._domain.Film;
import ttms._domain.MovieHall;
import ttms._domain.Session;


public interface IFilmdao {
	void save(Film film);						//对新影片信息进行存入
	void update(Film film);						//对某个影片信息进行修改
	void delete(Long id);						//根据id删除某个影片信息
	Film get(Long id);							//根据影片id获取影片对象
	List<MovieHall> getmovieHall(Long filmid);	//获取该影片所在的所有放映室的集合
	List<Session> getSession(Long filmid);		//根据本影片id获取所有的场次信息
	List<Film> getAlllist();					//获取所有的影片详细信息
	List<Film> getlist(String message);			//根据关键字进行查找
}
