package ru.todo100.cube3d.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ru.todo100.cube3d.model.ParameterItem;

@SuppressWarnings(value = { "unchecked" })
public class ParameterService extends ServiceAbstract{
	@Override
	public Class<ParameterItem> getItemClass() {
		return ParameterItem.class;
	}
	
	public String getValue(String name) {
		Criteria criteria = getCriteria().add(Restrictions.eq("name", name));
		ParameterItem item = (ParameterItem)criteria.list().get(0);
		return item.getValue();
	}
}
