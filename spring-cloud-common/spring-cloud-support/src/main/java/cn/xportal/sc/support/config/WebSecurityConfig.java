package cn.xportal.sc.support.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

	private static final String actuator = "/actuator/**";

	@Override
	public void configure(WebSecurity web) throws Exception {
		logger.info("ignoring {}", actuator);
		web.ignoring().antMatchers(actuator);
	}
	 @Override
     protected void configure(HttpSecurity http) throws Exception {
         http.csrf().disable();
         super.configure(http);
     }

}