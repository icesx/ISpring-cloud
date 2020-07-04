package cn.xportal.sc.provider.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("index")
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	private final String hostName = System.getenv("HOSTNAME");

	@GetMapping("{msg}")
	public Mono<String> sayHelloWorld(@PathVariable("msg") String msg) {
		logger.info("come on :{}", msg);
		return Mono.just("provider receive : " + msg);
	}

	@GetMapping("list")
	public Flux<Integer> list() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(3);
		list.add(5);
		Flux<Integer> userFlux = Flux.fromIterable(list);
		return userFlux;
	}

	@RequestMapping("/host")
	public String getName() {
		return this.hostName + ", " + LocalDate.now().toString();
	}

}
