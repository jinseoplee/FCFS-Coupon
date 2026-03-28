package io.github.jinseoplee;

import io.github.jinseoplee.config.TestcontainersConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@Import(TestcontainersConfig.class)
@SpringBootTest
class BackendApplicationTests {

    @Test
    void contextLoads() {
    }

}
