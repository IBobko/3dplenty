package ru.todo100.cube3d.service;

import java.io.Serializable;

import ru.todo100.cube3d.model.BlogItem;
import ru.todo100.cube3d.model.Item;

public class BlogService extends ServiceAbstract{

	@Override
	public Class<BlogItem> getItemClass() {
		// TODO Auto-generated method stub
		return BlogItem.class;
	}

}
