
package ru.todo100.cube3d.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.todo100.cube3d.service.Model3dService;

@Controller
@RequestMapping(value = "/")
public class Index {
	@Autowired
	private Model3dService model3dService;
	
	private final int CountOfModels = 6;
	
	
	@RequestMapping(value = "")
	public String index(Model model) {
		System.out.println("ssss");
		model.addAttribute("popularityModels", model3dService.getPopularityModels(CountOfModels));
		model.addAttribute("popularityFreeModels", model3dService.getPopularityFreeModels(CountOfModels));
		return "index/index";
	}
}

