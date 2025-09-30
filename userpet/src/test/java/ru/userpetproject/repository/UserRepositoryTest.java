package ru.userpetproject.repository;

import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.userpetproject.entity.User;
import ru.userpetproject.utils.DatasourceProxyBeanPostProcessor;
import ru.userpetproject.utils.QueryCountService;
import ru.userpetproject.utils.UserUtil;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(DatasourceProxyBeanPostProcessor.class)
class UserRepositoryTest {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17.5-alpine3.22");

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


    @BeforeEach
    void setUp() {
        userRepository.deleteAll();

    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void N1Problem() {
        //given
        User user1 = UserUtil.getStubUser("Ivan", "+79269285421", "work", "ivan@mail.ru");
        User user2 = UserUtil.getStubUser("Slava", "+79277418596", "work", "slava@mail.ru");
        User user3 = UserUtil.getStubUser("Oleg", "+79277412222", "work", "oleg@mail.ru");
        userRepository.saveAll(List.of(user1, user2, user3));

        //when
        QueryCountService.clear();
        userRepository.findAll();

        // then
        Assertions.assertEquals(1L, QueryCountService.get().getSelect());
    }


}