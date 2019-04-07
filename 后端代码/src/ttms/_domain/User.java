package ttms._domain;

public class User{
	private Long id;//编号
	private String username;//用户名
	private String password;//密码
	private String name;//昵称
	private String identity;//身份
	private String telephonenumber;//手机号
	private String ticketid;//自己有的电影票的id
	public String getTicketid() {
		return ticketid;
	}
	public void setTicketid(String ticketid) {
		this.ticketid = ticketid;
	}
	public String getPhonenumber() {
		return telephonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.telephonenumber = phonenumber;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", identity="
				+ identity + ", telephonenumber=" + telephonenumber + ", ticketid=" + ticketid + "]";
	}
	
}
