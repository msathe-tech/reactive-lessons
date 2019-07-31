package learn.reactive.lesson2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class CoffeesRouter {

	@Bean
	public RouterFunction<ServerResponse> router(CoffeesHandler handler) {
		return RouterFunctions
				.route(GET("/coffees/flux").and(accept(MediaType.APPLICATION_JSON)), handler::fluxCoffees)
				.andRoute(GET("/coffees/mono").and(accept(MediaType.APPLICATION_JSON)), handler::monoCoffees);
	}
}
