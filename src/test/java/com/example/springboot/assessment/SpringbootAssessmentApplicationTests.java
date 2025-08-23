package com.example.springboot.assessment;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = SpringbootAssessmentApplication.class)

class SpringbootAssessmentApplicationTests {

    @Test
    void contextLoads() {

        assertTrue(true);
    }

}
