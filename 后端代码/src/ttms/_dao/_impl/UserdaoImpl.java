package ttms._dao._impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ttms._dao.ITicket;
import ttms._dao.IUserdao;
import ttms._domain.Ticket;
import ttms._domain.User;
import ttms._template.IResultSetHandler;
import ttms._template.JdbcTemplate;

public class UserdaoImpl implements IUserdao{

	@Override
	public User get(Long id) {
		String sql = "select * from personnel where id = ?";
		List<User> list = JdbcTemplate.query(sql, new UserResultSetHandler(), id);
		return list.size()==1?list.get(0):null;
	}

	@Override
	public User get(String username) {
		String sql = "select * from personnel where username = ? or phonenumber=?";
		Object[] params= {username,username};
		List<User> list = JdbcTemplate.query(sql, new UserResultSetHandler(), params);
		return list.size()==1?list.get(0):null;
	}

	@Override
	public List<User> getlist(String identity) {
		String sql = "select * from personnel where identity=?";
		List<User> list = JdbcTemplate.query(sql, new UserResultSetHandler(),identity);
		return list;
	}

	@Override
	public List<User> getAllUser() {
		String sql = "select * from personnel";
		List<User> list = JdbcTemplate.query(sql, new UserResultSetHandler());
		return list;
	}

	@Override
	public void delete(Long id) {
		String sql = "delete from personnel where id=?";
		JdbcTemplate.update(sql, id);
	}

	@Override
	public void delete(String username) {
		String sql = "delete from personnel where username=?";
		JdbcTemplate.update(sql, username);
	}

	@Override
	public void update(User user) {
		String sql = "update personnel set identity=?,name=?,password=?,username=?,phonenumber=?,ticketid=? where id=?";
		Object[] params= {user.getIdentity(),user.getName(),
				user.getPassword(),user.getUsername(),user.getPhonenumber(),user.getTicketid(),user.getId()};
		JdbcTemplate.update(sql, params);
	}

	@Override
	public void save(User user) {
		String sql = "insert into personnel (identity,name,password,username,phonenumber,ticketid) value(?,?,?,?,?,?)";
		Object[] params= {user.getIdentity(),user.getName(),
				user.getPassword(),user.getUsername(),user.getPhonenumber(),user.getTicketid()};
		JdbcTemplate.update(sql, params);
	}
	@Override
	public Ticket getticket(Long ticketid) {
		ITicket ticketdao = new TicketdaoImpl();
		return ticketdao.getticketByid(ticketid);
	}
	class UserResultSetHandler implements IResultSetHandler<List<User>>{
		public List<User> handle(ResultSet rs) throws SQLException {
			List<User> list = new ArrayList<>();
			while(rs.next()) {
				User user = new User();
				list.add(user);
				user.setId(rs.getLong("id"));
				user.setIdentity(rs.getString("identity"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setUsername(rs.getString("username"));
				user.setPhonenumber(rs.getString("phonenumber"));
				user.setTicketid(rs.getString("ticketid"));
			}
			return list;
		}
	}
}
