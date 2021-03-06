package ru.todo100.cube3d.service;

import java.util.List;

import org.hibernate.Session;

import ru.todo100.cube3d.model.CategoryItem;
import ru.todo100.cube3d.model.WishItem;

@SuppressWarnings(value = { "unchecked" })
public class WishService extends ServiceAbstract{
	public List<WishItem> getAll() {
		List<WishItem> lst = getSession().createQuery("from WishItem c").setMaxResults(10).list();
		return lst;
	}

	@Override
	public Class<WishItem> getItemClass() {
		return WishItem.class;
	}
	
	public CategoryItem get(Long id) {
		Session session = getSession();
		CategoryItem obj = (CategoryItem) session.get(this.getItemClass(), id);
		return obj;
	}
	
	public void save(WishItem item) {
		Session session = getSession();
		session.saveOrUpdate(item);
	}
}
