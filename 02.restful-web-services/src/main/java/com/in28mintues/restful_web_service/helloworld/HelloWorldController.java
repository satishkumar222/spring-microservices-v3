package com.in28mintues.restful_web_service.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	private MessageSource messageSource;
	
	public HelloWorldController(MessageSource messageSource ) {
		this.messageSource=messageSource;
	}
	
	
	@GetMapping(path = "/hello-world")
	public String hello() {
		return "Hello World";
	}
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	@GetMapping(path = "/hello-world/pathvariable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean("Hello World " + name);
	}
	
	@GetMapping(path = "/hello-world-internalization")
	public String helloWorldInternizalation() {
		Locale Locale=LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default Message", Locale);
		 
	}

}
