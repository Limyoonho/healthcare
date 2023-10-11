package project.healthcare.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.healthcare.repository.PillRepository;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PillApiTest {
    @Autowired
    private PillApi pillApi;
    @Autowired
    private PillRepository pillRepository;

    @Test
    void pillDataAppend() throws IOException {
        pillApi.Pill();
    }
}