/**
 * Program  : DynamicConfig.java<br/>
 * Author   : i<br/>
 * Create   : 09-Jul-2020 08:14:29<br/>
 *
 * Copyright 1997-2013 by 12157724@qq.com ltd.,
 * All rights reserved.
 */
package cn.xportal.sc.consumer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "cn.xportal.cs.config")
public class DynamicConfig {

	private String base = "not bind to config server.";

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

}
