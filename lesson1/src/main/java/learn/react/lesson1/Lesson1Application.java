package learn.react.lesson1;

import java.awt.PageAttributes;
import java.io.Flushable;
import java.time.Duration;

import lombok.experimental.PackagePrivate;
import reactor.core.publisher.Flux;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Lesson1Application {

	public static void main(String[] args) {
		SpringApplication.run(Lesson1Application.class, args);
	}

	@Bean
	CommandLineRunner superBasic() {
		return args -> {
			Flux<String> superBasic = Flux.just("Dark Roast", "Medium Roast", "Light Roast")
//					.log()
					;

			superBasic.subscribe(System.out::println);
		};
	}

	@Bean
	CommandLineRunner moreBasic() {
		return args -> {
			Flux<String> moreBasic = Flux.just("Dark", "Medium", "Light")
					.concatWith(Flux.error(new RuntimeException("Error in flux element")))
//					.log()
					;

			moreBasic.subscribe(System.out::println,
					(e) -> System.out.println(e));
		};
	}

	@Bean
	CommandLineRunner basic() {
		return args -> {
			Flux<String> basic = Flux.just("Chai", "Masala Chai", "Adrak Chai")
					.map(s -> s.toLowerCase())
					.log()
					;

			basic.subscribe(System.out::println,
					(e) -> System.out.println(e),
					() -> System.out.println("basic onComplete"));
		};
	}

	@GetMapping("/reactiveCoffees")
	public Flux<String> reactiveCoffees() {
		Flux<String> coffees = Flux.just("Dark Roast", "Medium Roast", "Light Roast")
				.log()
				;
		return coffees;
	}

	@GetMapping("/reactiveDelayedCoffees")
	public Flux<String> reactiveDelayedCoffees() {
		Flux<String> coffees = Flux.just("Dark Roast", "Medium Roast", "Light Roast")
				.delayElements(Duration.ofSeconds(2))
				.log()
				;

		return coffees;
	}

	@GetMapping(value = "/reactiveDelayedStreamedCoffees", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<String> reactiveDelayedStreamedCoffees() {
		Flux<String> coffees = Flux.just("Dark Roast", "Medium Roast", "Light Roast")
				.delayElements(Duration.ofSeconds(2))
				.log()
				;

		return coffees;
	}

}
