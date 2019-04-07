package ttms._dao;

import java.util.List;

import ttms._domain.SimpleFilm;

public interface IsimpleFilmsdao {
	void delete(Long id);						//根据id删除某个影片信息
	public List<SimpleFilm> getAlllist();				//获取所有的简单影片信息
	public SimpleFilm get(Long id);
}
