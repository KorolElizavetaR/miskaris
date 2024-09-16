package korol.miskaris.laba2.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import korol.miskaris.laba2.model.User;
import korol.miskaris.laba2.security.UsersDetails;

@RestController
public class HelloController {
	
	@GetMapping("/hello")
	private String hello()
	{
		UsersDetails userDetails = (UsersDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userDetails.getUser();
		System.out.println(user);
		return "Hello, " + user;
	}

	@GetMapping("/admin")
	private String admin()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UsersDetails userDetails = (UsersDetails)auth.getPrincipal();
		User user = userDetails.getUser();
		System.out.println(user);
		return "Admin page: " + user;
	}
	
//	@GetMapping ("/auth/login")
//	public String loginPage()
//	{
//		return "login";
//	}
}
