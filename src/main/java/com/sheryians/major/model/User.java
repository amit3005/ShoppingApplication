package com.sheryians.major.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uid;
	@Column(nullable = false)
	private String firstName;
	private String lastName;
	@Column(nullable = false,unique = true)
	private String emailId;
	private String password;
	@ManyToMany(cascade = CascadeType.MERGE ,fetch = FetchType.EAGER)
	@JoinTable(
			name="user_role",
			joinColumns = {@JoinColumn(name="user_id")},
			inverseJoinColumns = {@JoinColumn(name="role_id")}
			)
	private List<Role> rolesList;
	public int getUid() {
		return uid;
	}
	public void setUid(int id) {
		this.uid = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Role> getRolesList() {
		return rolesList;
	}
	public void setRolesList(List<Role> role) {
		this.rolesList = role;
	}
	public User(User user) {
		this.uid = user.getUid();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.emailId = user.getEmailId();
		this.password = user.getPassword();
		this.rolesList = user.getRolesList();
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [id=" + uid + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", password=" + password + "]";
	}
}
