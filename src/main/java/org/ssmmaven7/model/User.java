package org.ssmmaven7.model;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private Integer roleId;
	private String userName;
	private String userPwd;
	private String phone;
	private String email;
	private String pictureUrl;
	private String address;
	private Long score;
	private Role role;

	/** full constructor */
	/**
	 * 
	 * @param userId        用户编号
	 * @param roleId		角色编号
	 * @param userName		用户名
	 * @param userPwd		密码
	 * @param phone			联系电话
	 * @param email			右邮箱
	 * @param pictureUrl    头像图片地址
	 * @param address		联系地址
	 * @param score			评分
	 * @param role			角色
	 */
	public User(Integer userId, Integer roleId, String userName, String userPwd, String phone, String email,
			String pictureUrl,String address, Long score,Role role) {
		this.userId = userId;
		this.roleId = roleId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.phone = phone;
		this.email = email;
		this.pictureUrl = pictureUrl;
		this.address = address;
		this.score = score;
		this.role = role;
	}

	/** default constructor */
	public User() {
	}
	// Constructors

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}
	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return this.userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPictureUrl() {
		return this.pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress(){
		return address;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}