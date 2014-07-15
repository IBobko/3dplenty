package ru.todo100.cube3d.service;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import ru.todo100.cube3d.model.AccountItem;
import ru.todo100.cube3d.util.InputError;
import ru.todo100.cube3d.util.MailBean;


@SuppressWarnings(value = { "unchecked" })
public class AccountService extends ServiceAbstract{
	public List<AccountItem> getAll() {
		List<AccountItem> lst = getSession().createQuery("from AccountItem c").setMaxResults(10).list();
		return lst;
	}

	@Override
	public Class<AccountItem> getItemClass() {
		return AccountItem.class;
	}
	
	public AccountItem get(Long id) {
		Session session = getSession();
		AccountItem obj = (AccountItem) session.get(this.getItemClass(), id);
		return obj;
	}
	
	public void save(AccountItem account) {
		Session session = getSession();
		session.saveOrUpdate(account);
	}
	
	public AccountItem get(String login) {
		Session session = getSession();
		AccountItem account = (AccountItem)session.createCriteria(AccountItem.class).
		           add( Restrictions.eq("username", login) ).uniqueResult();
		return account;
	}
	
	public AccountItem getByEmail(String email) {
		AccountItem account = (AccountItem)getCriteria().
				add( Restrictions.eq("email", email) ).uniqueResult();
		return account;
	}
	
	public AccountItem getCurrentAccount() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.isAuthenticated()) {
			return get(auth.getName());	
		}
		return null;
	}
	
	public AccountItem saveByRequest(HttpServletRequest request) throws InputError{
		String email = request.getParameter("email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String thirdName = request.getParameter("thirdName");
		String phone = request.getParameter("phone");
		String countryCode = request.getParameter("country");
		String address = request.getParameter("address");
		String birthDay = request.getParameter("birthDay");
		String birthMonth = request.getParameter("birthMonth");
		String birthYear = request.getParameter("birthYear");
 
		InputError ie = new InputError();
		
		if (!request.getParameterMap().containsKey("agree-commission")) {
			//ie.addError("Agreement commision");
        }
		if (!request.getParameterMap().containsKey("agree-buysell")) {
			ie.addError("Agreement buysell");
        }

		if (email.trim().equals("")) {
			ie.addError("E-mail is empty");
		}
		
		if (!MailBean.isValidEmailAddress(email)) {
			ie.addError("E-mail is invalid");
		}
		
		AccountItem exists = this.get(email);
		if (exists != null) {
			ie.addError("Login is busy");
		}
		
		if (firstName.trim().equals("")) {
			ie.addError("First name is empty");
		}
		
		if (lastName.trim().equals("")) {
			ie.addError("Last name is empty");
		}
		
		if (thirdName.trim().equals("")) {
			ie.addError("Third name is empty");
		}
		
		if (countryCode.trim().equals("0")) {
			ie.addError("Country name is empty");
		}
		
		if (address.trim().equals("")) {
			ie.addError("Address name is empty");
		}
		
		if (phone.trim().equals("")) {
			ie.addError("Phone is empty");
		}
		
		Calendar birth = Calendar.getInstance();
		
		birth.set(Integer.parseInt(birthYear),Integer.parseInt(birthMonth), Integer.parseInt(birthDay));

		if (!ie.getErrors().isEmpty()) {
			throw ie;
		}
		
		String password = RandomStringUtils.random(8,true,true);		
		AccountItem account = new AccountItem();
		account.setEmail(email);
		account.setUsername(email);
		account.setPassword(password);
		account.setFirstName(firstName);
		account.setLastName(lastName);
		account.setThirdName(thirdName);
		account.setPhone(phone);		
		account.setCountry(countryCode);
		account.setAddress(address);
		account.setBirthday(birth);;
		account.addRole("ROLE_USER");
		save(account);
		return account;
	}
}
