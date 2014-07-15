package ru.todo100.cube3d.service;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;

import ru.todo100.cube3d.model.Item;
import ru.todo100.cube3d.model.MarkItem;
import ru.todo100.cube3d.model.MarkItemGroup;

@SuppressWarnings(value = { "unchecked" })
public class MarkService extends ServiceAbstract{



	public List<MarkItem> getByStart(String start) {
		List<MarkItem> lst = getSession().createQuery("from MarkItem c where mark_name like '"+start+"%'").list();
		return lst;
	}
	
	public void save(MarkItem item) {
		Session session = getSession();
		session.saveOrUpdate(item);
	}

	@Override
	public Class<? extends Item> getItemClass() {
		// TODO Auto-generated method stub
		return MarkItem.class;
	}
	
	public List<MarkItemGroup> getWithGroup() {
		List<MarkItemGroup> marks = getCriteria().setProjection( Projections.projectionList()
			    .add( Projections.property("id"),"id")
			    .add( Projections.property("name"),"name")
			    .add( Projections.count("id"),"count")
			    .add( Projections.groupProperty("name") )).setResultTransformer(Transformers.aliasToBean(MarkItemGroup.class)).list();
		return marks;
	}
	
}
