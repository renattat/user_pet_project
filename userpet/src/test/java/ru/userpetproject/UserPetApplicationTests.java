package ru.userpetproject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.userpetproject.entity.Email;
import ru.userpetproject.entity.Phone;
import ru.userpetproject.entity.User;
import ru.userpetproject.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserPetApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private UserRepository userRepository;

	@AfterEach
	public void afterEach() {
		userRepository.deleteAll();
	}

	@BeforeEach
	public void beforeEach() {
		userRepository.deleteAll();
	}

	@Test
	void postUserTest() {
		// Given
		User user = getStubUser();

		//When
		ResponseEntity<User> response = restTemplate.postForEntity("/user", user, User.class);

		//Then
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
		Assertions.assertNotNull(response.getBody().getId());
		Assertions.assertEquals(response.getBody().getName(), "John");
		Assertions.assertEquals(response.getBody().getEmails().get(0).getAddress(), "john@mail.ru");
		Assertions.assertEquals(response.getBody().getPhones().get(0).getNumber(), "+79876546565");
		Assertions.assertEquals(response.getBody().getPhones().get(0).getType(), "work");
	}

	@Test
	void getUserTest() {
		// Given
		User user = getStubUser();
		Long createdUserId = restTemplate.postForEntity("/user", user, User.class).getBody().getId();

		//When
		ResponseEntity<User> response = restTemplate.getForEntity("/user/{id}", User.class, createdUserId);

		//Then
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
		Assertions.assertNotNull(response.getBody().getId());
		Assertions.assertEquals(response.getBody().getName(), "John");
		Assertions.assertEquals(response.getBody().getEmails().get(0).getAddress(), "john@mail.ru");
		Assertions.assertEquals(response.getBody().getPhones().get(0).getNumber(), "+79876546565");
		Assertions.assertEquals(response.getBody().getPhones().get(0).getType(), "work");
	}

	private User getStubUser(){
		User user = new User();
		user.setName("John");

		Phone phone = new Phone();
		phone.setNumber("+79876546565");
		phone.setType("work");
		user.addPhone(phone);

		Email email = new Email();
		email.setAddress("john@mail.ru");
		user.addEmail(email);
		return user;
	}
}
