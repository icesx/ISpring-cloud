/**
 * Program  : Index.java<br/>
 * Author   : i<br/>
 * Create   : 09-Jul-2020 08:20:24<br/>
 *
 * Copyright 1997-2013 by 12157724@qq.com ltd.,
 * All rights reserved.
 */
package cn.xportal.sc.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.xportal.sc.consumer.config.DynamicConfig;
import reactor.core.publisher.Mono;

@RestController
public class IndexController {
	@Autowired
	private DynamicConfig config;

	@Value("${cn.xportal.cs.config.base}")
	private String base;

	@GetMapping("/")
	public Mono<String> index() {
		return Mono.just("@ConfigurationProperties="+config.getBase()+",@Value="+base);
	}

}
