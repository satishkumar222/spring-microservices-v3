package com.in28mintues.restful_web_service.helloworld.userdao;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28mintues.restful_web_service.helloworld.user.User;

import jakarta.validation.Valid;

@RestController
public class UserResource {
     @Autowired
	 private UserDaoService service;
     
     //get All users
     @GetMapping("/users")
     public List<User> retriveAllUsers(){
		return service.findAllUsers() ;
    	 
     }
     //get specific user detail
     @GetMapping("/users/{id}")
     public User retriveById(@PathVariable int id){
    	 
		User user = service.findById(id);
		
		if (user==null)
		throw new UserNotFoundException("id:" +id);
		return user;
    	 
     }
     
     //post one user
     @PostMapping("/users")
     public ResponseEntity<User> CreateUser(@Valid @RequestBody User user) {
    	 User saveUser = service.saveUser(user);
    	URI location=ServletUriComponentsBuilder.fromCurrentRequest()
    			.path("/{id}")
    			.buildAndExpand(saveUser.getId())
    			.toUri();
		return  ResponseEntity.created(location).build();
    	 
     }
     
     
     //get specific user detail
     @DeleteMapping("/users/{id}")
     public void deleteById(@PathVariable int id){    	 
		service.deleteById(id);
     }
}
