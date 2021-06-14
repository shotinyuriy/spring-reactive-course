package edu.reactive.server;

import edu.reactive.server.converter.WeatherDataToCityWeatherResponseConverter;
import edu.reactive.server.repository.WeatherDataRepository;
import edu.reactive.server.rest.WeatherRestController;
import edu.reactive.server.service.WeatherService;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@ComponentScan(basePackageClasses = {WeatherRestController.class, WeatherService.class, WeatherDataToCityWeatherResponseConverter.class})
@EnableR2dbcRepositories(basePackageClasses = WeatherDataRepository.class)
public class ReactiveServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveServerApplication.class, args);
	}

	@Bean
	public static ConnectionFactory connectionFactory() {
		Map<String, String> options = new HashMap<>();
		options.put("lock_timeout", "10s");

		PostgresqlConnectionFactory connectionFactory = new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
				.host("localhost")
				.port(5432)  // optional, defaults to 5432
				.username("postgres")
				.password("example")
				.database("weatherdb")  // optional
				.options(options) // optional
				.build());
		return connectionFactory;
	}

	@Bean
	public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(connectionFactory);
		return initializer;
	}
}
