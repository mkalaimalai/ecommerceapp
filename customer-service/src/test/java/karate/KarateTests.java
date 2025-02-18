package karate;

import com.intuit.karate.junit5.Karate;
import com.mkalaimalai.customer_service.CustomerServiceApplication;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.junit.jupiter.api.BeforeAll;

@SpringBootTest(
    classes = CustomerServiceApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = "server.port=0"
)
public class KarateTests {

    @LocalServerPort
    private int port;

    @Karate.Test
    Karate testCustomers() {
        System.setProperty("karate.env", "test");
        System.setProperty("server.port", String.valueOf(port));
        return Karate.run("classpath:karate/customers.feature")
            .systemProperty("baseUrl", "http://localhost:" + port);
    }
} 