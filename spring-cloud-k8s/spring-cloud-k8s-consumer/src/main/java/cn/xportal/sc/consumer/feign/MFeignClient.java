package cn.xportal.sc.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cn.xportal.sc.consumer.feign.config.MFeignConfig;

import java.util.List;

@FeignClient(name = "spring-cloud-k8s-provider", path = "sc-k8s-provider", fallback = MFeignClientFallback.class, configuration = MFeignConfig.class)
public interface MFeignClient {
	// 这是被请求微服务的地址，也就是provider的地址
	@GetMapping(value = "/index/{msg}")
	String sayHelloWorld(@PathVariable("msg") String msg);

	@GetMapping(value = "/index/list")
	List<Integer> list();

	@GetMapping(value = "/index/list")
	Integer[] array();
}
