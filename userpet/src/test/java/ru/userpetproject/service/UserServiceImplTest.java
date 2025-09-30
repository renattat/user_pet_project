package ru.userpetproject.service;

import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.userpetproject.entity.User;
import ru.userpetproject.repository.UserRepository;
import ru.userpetproject.utils.UserUtil;

// перенести в userServiceImplTest                                          +
// развернуть testcontainer postgres и подключаться в к нему в тестах       +
// развернуть testcontainer kafka и подключаться в к нему в теста           +
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserServiceImplTest {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17.5-alpine3.22");

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @AfterEach
    public void afterEach() {
        userRepository.deleteAll();
    }

    @BeforeEach
    public void beforeEach() {
        userRepository.deleteAll();
    }

    @Test
    void create() {
        // Given
        User user = UserUtil.getStubUser("John", "+79876546565", "work", "john@mail.ru");

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
    void get() {
        // Given
        User user = UserUtil.getStubUser("John", "+79876546565", "work", "john@mail.ru");
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
}