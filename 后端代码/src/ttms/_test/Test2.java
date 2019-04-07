package ttms._test;

import java.util.List;

import org.junit.Test;
import ttms._dao.IFilmdao;
import ttms._dao._impl.FilmdaoImpl;
import ttms._domain.Film;

public class Test2 {
	IFilmdao dao = new FilmdaoImpl();
	@Test
	public void test() throws Exception{
//		resources/电影/阿甘正传/阿甘正传.jpg
//		images/阿甘正传.jpg
		List<Film> f = dao.getAlllist();
		for (Film film : f) {
			String s = film.getPosterPath();
			if(null!=s) {
//				s = s.substring(, s.length());
				s="views/"+s;
				film.setPosterPath(s);
			}
			dao.update(film);
		}
//		for (Film film : f) {
//			String s = film.getPosterPath().replaceAll(".jpg", ".txt");
//			film.setDirector(s);
//			dao.update(film);
//		}
//		List<List<String>> list = Film.getListsynopsisContent();
//		int i = 0;
//		int j = 0;
//		for (List<String> string : list) {
//			Film film = f.get(j);
//			i = 0;
//			for (String s : string) {
//				System.out.println(s);
//				if(i ==0)
//					film.setDirector(s);
//				if(i ==1)
//					film.setScreenwriter(s);
//				if(i ==2)
//					film.setPerformer(s);
//				if(i ==3)
//					film.setType(s);
//				if(i ==4)
//					film.setCountry(s);
//				if(i ==5)
//					film.setLanguage(s);
//				if(i ==6)
//					film.setReleasedate(s);
//				if(i ==7)
//					film.setFilmTime(s);
//				if(i ==8)
//					film.setSynopsis(s);
//				i++;
//			}
//			dao.update(film);
//			j++;
//		}
//		int i = 0;
//		Film film = dao.get(7L);
//		List<String> string = film.getsynopsisContent();
//		for (String s : string) {
//			System.out.println(s);
//			if(i ==0)
//				film.setDirector(s);
//			if(i ==1)
//				film.setScreenwriter(s);
//			if(i ==2)
//				film.setCountry(s);
//			if(i ==3)
//				film.setPerformer(s);
//			if(i ==4)
//				film.setType(s);
//			if(i ==5)
//				film.setLanguage(s);
//			if(i ==6)
//				film.setReleasedate(s);
//			if(i ==7)
//				film.setFilmTime(s);
//			if(i ==8)
//				film.setSynopsis(s);
//			i++;
//		}
//		dao.update(film);
		String a = "resources\\电影\\阿甘正传\\阿甘正传.txt";
		System.out.println(a);
	}
}