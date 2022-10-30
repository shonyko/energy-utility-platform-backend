package ro.alexk.energyutilityplatformbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ro.alexk.energyutilityplatformbackend.security.SecurityConfig;

@SpringBootApplication
@EnableConfigurationProperties({SecurityConfig.class})
public class EnergyUtilityPlatformBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnergyUtilityPlatformBackendApplication.class, args);
    }

}
