package learn.reactive.lesson2;

import com.sun.security.ntlm.Server;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
public class CoffeesHandler {

	public Mono<ServerResponse> fluxCoffees(ServerRequest request) {
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(
						Flux
								.just("Dark Roast", "Medium Roast", "Double Shot")
								.log()
						, String.class
				);
	}

	public Mono<ServerResponse> monoCoffees(ServerRequest request) {
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(
						Mono
							.just("Expresso")
							.log()
						, String.class
				);
	}
}

