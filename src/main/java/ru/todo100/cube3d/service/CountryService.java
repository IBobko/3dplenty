package ru.todo100.cube3d.service;

import ru.todo100.cube3d.model.Item;
import ru.todo100.cube3d.model.CountryItem;

@SuppressWarnings(value = { "unchecked" })
public class CountryService extends ServiceAbstract{

	@Override
	public Class<? extends Item> getItemClass() {
		// TODO Auto-generated method stub
		return CountryItem.class;
	}


}
