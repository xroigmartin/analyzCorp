package xroigmartin.analyzcorp_backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {AnalyzcorpBackendApplication.class})
@ImportAutoConfiguration(exclude = {FlywayAutoConfiguration.class})
class AnalyzcorpBackendApplicationTests {

	@Test
	void contextLoads() {
	}

}
