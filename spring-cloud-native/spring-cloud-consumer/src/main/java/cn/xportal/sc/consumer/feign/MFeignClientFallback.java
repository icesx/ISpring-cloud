package cn.xportal.sc.consumer.feign;

import org.springframework.stereotype.Component;

import cn.xportal.sc.consumer.bean.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Component
public class MFeignClientFallback implements MFeignClient {
	@Override
	public String sayHelloWorld(String msg) {
		return "feigon fallback";
	}

	@Override
	public List<Integer> list() {
		return new ArrayList<>();
	}

	@Override
	public Integer[] array() {
		return new Integer[0];
	}

	@Override
	public UserEntity[] getUsers() {
		return null;
	}
}
