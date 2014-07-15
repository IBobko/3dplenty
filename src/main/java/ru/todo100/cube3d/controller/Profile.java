package ru.todo100.cube3d.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.todo100.cube3d.model.AccountBuyItem;
import ru.todo100.cube3d.model.AccountItem;
import ru.todo100.cube3d.model.Model3dItem;
import ru.todo100.cube3d.service.AccountBuyService;
import ru.todo100.cube3d.service.AccountService;
import ru.todo100.cube3d.service.Model3dService;
import ru.todo100.cube3d.util.InputError;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(value = "/profile")
public class Profile {
	@Autowired
	AccountService accountService;
	
	@Autowired
	Model3dService model3dService;
	
	@Autowired
	AccountBuyService buyService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String login = auth.getName();
	    
	    AccountItem account = accountService.get(login);
	    
	    List<AccountBuyItem> purchases = buyService.getByAccount(account);
	    List<Model3dItem> goods = model3dService.getByAccount(account);
	    
	    model.addAttribute("purchases", purchases);
	    model.addAttribute("goods", goods);
	    model.addAttribute("account", account);
		return "profile/index";
	}
	
	@RequestMapping(value = "/change")
	public String change(HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) throws IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AccountItem account = accountService.get(auth.getName());
		model.addAttribute("account",account);		
		if (request.getMethod().equals("POST")) {
			String password = request.getParameter("password");
			String repeatPassword = request.getParameter("repeat_password");
			InputError ie = new InputError();
			if (password.trim().equals("")) {
				ie.addError("Password is empty");
			}
			
			if (!password.equals(repeatPassword)) {
				ie.addError("Repeat password is not matched");
			}
			
			if (ie.getErrors().size() != 0) {
				model.addAttribute("ie",ie);
			} else {
				account.setPassword(repeatPassword);
				accountService.save(account);
				model.addAttribute("success",true);
			}
		}
		return "profile/change";
	}	
}
