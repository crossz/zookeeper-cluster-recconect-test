package boot.dubbo.simple.provider.service;

import boot.dubbo.api.User;
import boot.dubbo.api.UserService1;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.ArrayList;
import java.util.List;

@Service(protocol = { "dubbo" })
public class UserService1Impl implements UserService1 {

	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setId(1);
		user.setName("Percy Mutils");
		users.add(user);
		return users;
	}

	@Override
	public String create(User user) {
		return "userId:123df";
	}

}
