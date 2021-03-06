package boot.dubbo.simple.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import boot.dubbo.api.User;
import boot.dubbo.api.UserService;

import com.alibaba.dubbo.config.annotation.Reference;

@Service
public class UserServiceUsing {
	@Reference
	private UserService userService;
	
	@Value("${dubbo.test}")
	private String dubb_test;
	
	public void test() {
		List<User> users = userService.findAll();
		System.out.println("=========== " + users.size());
		System.out.println("=========== " + dubb_test);
	}
}
