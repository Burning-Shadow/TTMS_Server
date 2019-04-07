package ttms._dao;

import java.util.List;

import ttms._domain.Film;
import ttms._domain.MovieHall;
import ttms._domain.Session;


public interface IMovieHalldao {
	List<MovieHall> getAlllist();//得到所有放映室的集合
	MovieHall getMovieHallById(Long id);//根据id得到放映室对象
	List<Film> getlistByMovieHallModel(Long moviehallid);//获取某个放映室的所有影片信息
	List<Session> getAllSession(Long id);//获取某个电影有关的所有场次信息
	void deleteMoviehall(Long id);//根据id删除某个MovieHall
	void savemoviehall(MovieHall moviehall);//添加MovieHall
	void updatemoviehall(MovieHall moviehall);//更新MovieHall
}
