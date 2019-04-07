package ttms._dao;

import java.util.List;

import ttms._domain.Ticket;
import ttms._domain.User;

public interface IUserdao {
	public User get(Long id);					//根据id获取User对象
	public User get(String usernameOrphonenumber);//根据登录的用户名获取user对象
	public List<User> getlist(String identity);	//获取某种身份的所有user信息
	public List<User> getAllUser();				//获取所有用户的信息
	public void delete(Long id);				//根据id删除user信息
	public void delete(String username);		//根据username删除user信息
	public void update(User user);				//更新user内容
	public void save(User user);				//添加user信息
	public Ticket getticket(Long ticketid);		//根据id获取票
}
