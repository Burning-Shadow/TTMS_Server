package ttms._test;


import org.junit.Test;
import ttms._dao.ISeatdao;
import ttms._dao._impl.SeatdaoImpl;

public class TestForBeanUtils {
	@Test
	public void test() {
		/**
		 * private Long id;//编号
	private String username;//用户名
	private String password;//密码
	private String name;//昵称
	private String identity;//身份
		 */
//		User user = new User();
//		Map<k,v> map = new Map<String, Object>;
//		BeanUtils.copyProperties(user, map);
		ISeatdao dao = new SeatdaoImpl();
		dao.UpdateSeat(dao.getAllseats());
		System.out.println();
	}
}
