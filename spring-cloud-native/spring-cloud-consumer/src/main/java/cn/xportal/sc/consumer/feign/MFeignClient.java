package cn.xportal.sc.consumer.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cn.xportal.sc.consumer.bean.UserEntity;
import cn.xportal.sc.consumer.feign.config.MFeignConfig;

@FeignClient(name = "provider", path = "sc-provider", fallback = MFeignClientFallback.class, configuration = MFeignConfig.class)
public interface MFeignClient {
	// 这是被请求微服务的地址，也就是provider的地址
	@GetMapping(value = "/index/{msg}")
	String sayHelloWorld(@PathVariable("msg") String msg);

	@GetMapping(value = "/index/list")
	List<Integer> list();

	@GetMapping(value = "/index/array")
	Integer[] array();

	@GetMapping(value = "/user/getUsers")
	UserEntity[] getUsers();
}
