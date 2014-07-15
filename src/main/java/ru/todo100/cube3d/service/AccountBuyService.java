package ru.todo100.cube3d.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ru.todo100.cube3d.model.AccountBuyItem;
import ru.todo100.cube3d.model.AccountItem;

@SuppressWarnings(value = { "unchecked" })
public class AccountBuyService extends ServiceAbstract{
	@Override
	public Class<AccountBuyItem> getItemClass() {
		return AccountBuyItem.class;
	}
	
	public List<AccountBuyItem> getByAccount(AccountItem account) {
		Session session = getSession();
		return session.createCriteria(AccountBuyItem.class).add( Restrictions.eq("account", account) ).list();
	}

	public void save(AccountBuyItem item) {
		Session session = getSession();
		session.saveOrUpdate(item);
	}
}
