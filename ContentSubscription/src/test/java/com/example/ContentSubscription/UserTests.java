package com.example.ContentSubscription;

import com.example.ContentSubscription.domain.User;
import com.example.ContentSubscription.repository.UserRepo;
import com.example.ContentSubscription.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // Reset database after each test
public class UserTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Test
    public void testCreateAndRetrieveUser() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        user = entityManager.persistAndFlush(user);

        assertNotNull(user.getUserId());

        User retrievedUser = userService.getUserById(user.getUserId());
        assertNotNull(retrievedUser);
        assertEquals("test@example.com", retrievedUser.getEmail());
    }

    @Test
    public void testLoginUser() {
        User user = new User();
        user.setEmail("login@example.com");
        user.setPassword("password");

        user = entityManager.persistAndFlush(user);

        assertNotNull(user.getUserId());

        User loggedInUser = userService.loginUser("login@example.com", "password");
        assertNotNull(loggedInUser);
        assertEquals("login@example.com", loggedInUser.getEmail());

        User invalidLogin = userService.loginUser("login@example.com", "incorrectPassword");
        assertNull(invalidLogin);
    }

}
