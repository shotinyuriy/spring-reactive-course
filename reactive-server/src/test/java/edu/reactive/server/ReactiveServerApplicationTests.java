package edu.reactive.server;

import edu.reactive.server.repository.WeatherDataRepository;
import edu.reactive.server.service.WeatherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
class ReactiveServerApplicationTests {

	@MockBean
	private WeatherDataRepository weatherDataRepository;

	@Autowired
	private WeatherService weatherService;

	@Test
	void cities_2Cities() {

		Mockito.when(weatherDataRepository
				.findUniqueCities())
				.thenReturn(Flux.just("City1", "City2"));

		StepVerifier.create(weatherService.cities())
				.assertNext(cityResponse -> {
					Assertions.assertEquals("City1", cityResponse.getName());
				})
				.assertNext(cityResponse -> {
					Assertions.assertEquals("City2", cityResponse.getName());
				})
				.expectComplete()
				.verify();
	}

}
