package boot.dubbo.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("user1")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public interface UserService1 {
	/**
	 * 获取所有用户
	 * 
	 * @return所有用户
	 */
    @GET
    @Path("findAll")
	List<User> findAll();

    @GET
    @Path("create")
	String create(@BeanParam User user);
}
