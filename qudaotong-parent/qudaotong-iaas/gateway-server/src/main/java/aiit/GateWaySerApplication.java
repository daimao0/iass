package aiit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 呆毛
 * @date 2021/1/7 15:43
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GateWaySerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GateWaySerApplication.class, args);
    }
}
