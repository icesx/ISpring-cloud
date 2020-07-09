/**
 * Program  : Index.java<br/>
 * Author   : i<br/>
 * Create   : 09-Jul-2020 08:20:24<br/>
 *
 * Copyright 1997-2013 by 12157724@qq.com ltd.,
 * All rights reserved.
 */
package cn.xportal.sc.configmap.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RefreshScope
@RestController
public class IndexController {
	@Value("${cn.xportal.cs.config.base}")
	private String base;

	@GetMapping("/")
	public Mono<String> index() {
		return Mono.just("from configmap cn.xportal.cs.config.base=" + base);
	}

}
