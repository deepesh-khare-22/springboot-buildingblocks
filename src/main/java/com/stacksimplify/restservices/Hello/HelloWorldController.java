package com.stacksimplify.restservices.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
		//simple method
		//URI /helloWorld
	//@RequestMapping(method = RequestMethod.GET, path = "/helloWorld")	
	@GetMapping("/helloWorld")
	public String helloWorld() {
		return "Hello World1";
	}
	
	@GetMapping("/helloWorld-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Deepesh", "Khare", "Raipur");
	}
}
