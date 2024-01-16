package com.example.ContentSubscription;

import com.example.ContentSubscription.domain.Creator;
import com.example.ContentSubscription.repository.CreatorRepo;
import com.example.ContentSubscription.service.CreatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // Reset database after each test
public class CreatorTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CreatorRepo creatorRepo;

    @Autowired
    private CreatorService creatorService;

    @Test
    public void testCreateAndRetrieveCreator() {
        Creator creator = new Creator();
        creator.setBio("Sample Bio");
        creator.setPriceUltimate(100L);
        creator.setPriceLite(50L);
        creator.setPricePro(75L);

        creator = entityManager.persistAndFlush(creator);

        assertNotNull(creator.getCreatorId());

        Creator retrievedCreator = creatorService.getCreatorById(creator.getCreatorId());
        assertNotNull(retrievedCreator);
        assertEquals("Sample Bio", retrievedCreator.getBio());
    }


}
