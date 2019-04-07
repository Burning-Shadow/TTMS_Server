package ttms._test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ttms._dao.IFilmdao;
import ttms._dao.ISessiondao;
import ttms._dao._impl.FilmdaoImpl;
import ttms._dao._impl.SessiondaoImpl;
import ttms._domain.Film;

public class Test {
//	IUserdao dao = new UserdaoImpl();
//	IMovieHalldao moviehalldao = new MovieHalldaoImpl();
	
//	IsimpleFilmsdao simpledao = new SimplFilmsdaoImpl();
//	@org.junit.Test
//	public void test() {
////		User user = dao.get(3L);
////		user.setIdentity("imployee");
////		dao.update(user);
////		List<MovieHall> list = moviehalldao.getAlllist();
////		for (MovieHall movieHall : list) {
////			System.out.println(movieHall);
////		}
////		System.out.println(filmdao.getAlllist());
////		System.out.println(dao.getAllUser());
//		System.out.println(simpledao.getAlllist());
//	}
	public static void main(String[] args) throws Exception{
//		IFilmdao filmdao = new FilmdaoImpl();
//		List<List<String>> Str = new ArrayList<>();
//		Film film = filmdao.get(1L);
//		List<String> S = new  ArrayList<>();
//		File file = new File(film.getSynopsis());//Text文件
//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)
//        		, "GBK"));//构造一个BufferedReader类来读取文件
//        String s = null;
//        while((s = br.readLine())!=null){//使用readLine方法，一次读一行
//        	if(!s.equals("")) {
//        		S.add(s);
//        		System.out.println(s);
//        	}
//        }
//        Str.add(S);
//        br.close();
		ISessiondao dao = new SessiondaoImpl();
		System.out.println(dao.getByFilmIdAndMovieHallIdAndStartTime(2L, 3L, "2018-0-05 00:00:00"));
//		
//		
//		
//		byte[] data = null;
//		FileInputStream input = null;
//		String S = filmdao.get(1L).getPosterPath();
//		System.out.println(S);
//		File file = new File(S);
//		BufferedImage bi = ImageIO.read(file);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		ImageIO.write(bi, "jpg", baos);
//		byte[] text = baos.toByteArray();
//		System.out.println(text.length);
//		
//		File file1 = new File("C:\\Users\\hanjiangbo\\Desktop\\text.jpg");
//		
//		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(text);
//		BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
//		System.out.println(bufferedImage);
//		ImageIO.write(bufferedImage, "jpg", file1);
//		
//		
//		
//		FileOutputStream output1 = new FileOutputStream(file1);
//		byte[] b = new byte[1024];
//	//	output1.write(text);
//		output1.close();
//		
//		
//		
//		
//		
//		
//		ByteArrayOutputStream output = new ByteArrayOutputStream();
//		byte[] buf = new byte[1024];
//		int numBytesRead = 0;
//		while((numBytesRead = input.read(buf))!=-1) {
//			output.write(buf,0,numBytesRead);
//		}
//		data = output.toByteArray();
////		for (byte b : data) {
////			System.out.println();
////		}
//		output.close();
//		input.close();
////		if(!file.exists()) {
////			file.mkdirs();
////		}
////		FileOutputStream fos = new FileOutputStream(new File(S));
////		ServletInputStream sis = req
	}
}