package ru.todo100.cube3d.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="cube3d_account")
public class AccountItem extends Item{
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "account_username")
	private String username;
	
	@Column(name = "account_password")
	private String password;
	
	@Column(name = "account_email")
	private String email;
	
	@Column(name = "account_firstname")
	private String firstName;
	
	@Column(name = "account_lastname")
	private String lastName;
	
	@Column(name = "account_thirdname")
	private String thirdName;
	
	@Column(name = "country_code")
	private String country;
	
	@Column(name = "account_address")
	private String address;
	
	@Column(name = "account_phone")
	private String phone;
	
	@Column(name = "account_birthday")
	private Calendar birthday;
	
	@Fetch(value=FetchMode.SUBSELECT)
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="account_username",referencedColumnName="account_username")
	List<AuthorityItem> authoritys;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<AuthorityItem> getAuthoritys() {
		return authoritys;
	}

	public void setAuthoritys(List<AuthorityItem> authoritys) {
		this.authoritys = authoritys;
	}
	
	public void addRole(String role) {
		if (authoritys == null) {
			authoritys = new ArrayList<AuthorityItem>();
		}
		AuthorityItem item = new AuthorityItem();
		item.setRole(role);
		item.setAccount(this);
		authoritys.add(item);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCountry() {
		return country;
	}

	public Calendar getBirthday() {
		return birthday;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}

	public String getThirdName() {
		return thirdName;
	}

	public void setThirdName(String thirdName) {
		this.thirdName = thirdName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
