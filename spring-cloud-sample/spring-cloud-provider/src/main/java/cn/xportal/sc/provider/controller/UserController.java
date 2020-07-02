package cn.xportal.sc.provider.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.xportal.sc.provider.entity.UserEntity;
import cn.xportal.sc.provider.service.IUserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private IUserService userService;
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("myv");
		return mv;
	}
	@RequestMapping("/getUsers")
	public List<UserEntity> getUsers() {
		List<UserEntity> users = userService.getUsers();
		return users;
	}

	@RequestMapping("/getUser/{id}")
	public UserEntity getUser(@PathVariable("id") Long id) {
		return userService.getUser(id);
	}

	@RequestMapping("/add")
	public void save(UserEntity user) {
		userService.save(user);
	}

	@RequestMapping(value = "update")
	public void update(UserEntity user) {
		userService.update(user);
	}

	@RequestMapping(value = "/delete/{id}")
	public void delete(@PathVariable("id") Long id) {
		userService.delete(id);
	}

}