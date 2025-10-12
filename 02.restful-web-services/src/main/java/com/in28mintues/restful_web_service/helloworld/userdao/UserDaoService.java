package com.in28mintues.restful_web_service.helloworld.userdao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.in28mintues.restful_web_service.helloworld.user.User;

@Component
public class UserDaoService {

	private static List<User> users=new ArrayList<>();

	private static int userCount =3;
	static {
		users.add(new User(1,"Satish",LocalDate.now().minusYears(30)));
		users.add(new User(2,"Souji",LocalDate.now().minusYears(25)));
		users.add(new User(3,"Bhargavi",LocalDate.now().minusYears(20)));

	}

	public List<User> findAllUsers() {
		return users;

	}

	public User findById(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		return users.stream()
				.filter(predicate)
				.findFirst()  // returns Optional<User>
				.orElse(null);
		//  .orElse(null); // return null if not found

	}
	
	public void deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		 users.removeIf(predicate);
				

	}
	

	public User saveUser(User user ) {
		user.setId(++userCount);
		users.add(user);
		return user;
	}

}
