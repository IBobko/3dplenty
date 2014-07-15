package ru.todo100.cube3d.filter;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.GenericFilterBean;

import ru.todo100.cube3d.model.CategoryItem;
import ru.todo100.cube3d.model.CountryItem;
import ru.todo100.cube3d.model.MarkItemGroup;
import ru.todo100.cube3d.service.CategoryService;
import ru.todo100.cube3d.service.CountryService;
import ru.todo100.cube3d.service.MarkService;

@Transactional
@SuppressWarnings(value={"unchecked"})
public class AddingStandartItemsFilter extends GenericFilterBean {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private MarkService markService;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		request.setAttribute("countries",getCountry());
		request.setAttribute("months",getMonth());
		request.setAttribute("marks", getMarks());		
		request.setAttribute("categories", getCategories());
		filterChain.doFilter(request, response);
	}
	
	private SortedMap<String,String> getCountry() {
		Criteria criteria = countryService.getCriteria();
		List<CountryItem> countriesList =  (List<CountryItem>)criteria.list();
		SortedMap<String,String> countriesMap = new TreeMap<>();
		for (CountryItem item : countriesList ) {
			countriesMap.put(item.getCode(), item.getName());
		}
		return countriesMap;			
	}
	
	/**
	 * @todo По мне непонятной причине, метод getMonth возвращает на один элемент больше, причем пустой
	 * */
	private String[] getMonth() {
		DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
		String[] months = dateFormatSymbols.getMonths();
		String[] newMonths = new String[12];
		for (int i = 0; i < newMonths.length; ++i) {
			newMonths[i] = months[i];
		}
		return newMonths;		
	}
	
	private List<MarkItemGroup> getMarks() {
		List<MarkItemGroup> marks = markService.getWithGroup();
		if (marks.size() > 0) {
			Number min = marks.get(0).getCount();
			Number max = 0;
			for (MarkItemGroup i: marks) {
				if (min.intValue() > i.getCount().intValue() ) {
					min = i.getCount();
				}
				if (max.intValue() < i.getCount().intValue()) {
					max = i.getCount();
				}
			}
			Integer minFontSize = 9;
			Integer maxFontSize = 30;
			Integer dxFontSize =  maxFontSize - minFontSize;
			for (MarkItemGroup i: marks) {
				Float size = dxFontSize * i.getCount().intValue() / max.floatValue();
				i.setFontSize(size.intValue() + minFontSize);
			}
		}
		return marks;
	}
	
	private List<CategoryItem> getCategories() {
		Criteria criteria = categoryService.getCriteria();
		criteria.add(Restrictions.or(Restrictions.isNull("parent"), Restrictions.eq("parent", 0l)));
		List<CategoryItem> categories = criteria.list();
		return categories;		
	}
}
