package cn.xportal.sc.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class K8sProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(K8sProviderApplication.class, args);
	}
}
