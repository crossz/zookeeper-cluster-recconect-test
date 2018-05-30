## 模块介绍
 * boot-dubbo-api
 * boot-dubbo-simple-provider
 * boot-dubbo-simple-consumer

 以上三件套是标准dubbo的配置，用spring boot实现的，并沿用dubbo的xml配置方式；

 * boot-dubbo-simple-consumer2-noxml
 
 这里实现了去 xml，完全用 JavaConfig 的方式实现参数化，并注入 dubbo 
 

## 关于dubbox的配置方式

此  boot-dubbo-simple 的 demo 仍保留 dubbo 默认的 xml 方式对 dubbo 进行配置；但是，通过 Configuation 类，来指向 dubbo 的 xml 配置文件；如此整合便于理解与兼容原有的dubbo使用习惯。

另 boot-dubbo-consumer-noxml 则是通过 JavaConfig 的方式，即无 xml，来实现 dubbo 与 spring boot 的整合。
Spring boot的官网建议尽量少用 xml 文件的配置方式：如下：

### Configuration classes
Spring Boot favors Java-based configuration. Although it is possible to call SpringApplication.run() with an XML source, we generally recommend that your primary source is a @Configuration class. Usually the class that defines the main method is also a good candidate as the primary @Configuration.
### Importing XML configuration
If you absolutely must use XML based configuration, we recommend that you still start with a @Configuration class. You can then use an additional @ImportResource annotation to load XML configuration files.

详见：https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-configuration-classes.html

## 具体配置说明

### provider 的配置方式：dubbo的默认xml方式（完全通过dubbo.properties实现参数化）

1. 这里的配置是通过默认的 dubbo.properties 来配置 dubbo 的；
2. 可以引用了 dubbox 的 Service 注解用来声明服务，但此 demo 仍旧使用 spring 的 Service 来注解；

```
<dubbo:annotation package="boot.dubbo" />

```

- provider对应的服务：注解需要在 provider的serviceImple中，public class上加入：

```
@Service(protocol = { "dubbo" })
public class UserServiceImpl implements UserService {

}
```

### consumer 的配置方式：boot的方式声明变量（无dubbo.properties变量配置文件，完全是 boot 的 application.properties 与 dubbo 的 xml 整合）
1. 这里的配置是通过 boot 的 application.properties 来配置 dubbo 的 xml 的；
2. 引用了 dubbox 的 Reference 注解来声明所需服务， com.alibaba.dubbo.config.annotation.Reference;
3. 通过单元测试来调用 dubbo 的服务进行测试的；

- consumer 对应的服务，需要在 class 上加入：

```
import com.alibaba.dubbo.config.annotation.Reference;

@Service
public class UserServiceUsing {
	@Reference
	private UserService userService;
	
}
	
```


---------------------------------------------------------

使用：
 > 1、安装Maven<br>
 > 2、下载dubbox，在${dubbox-home}执行mvn clean install -Dmaven.test.skip=true <br> (执行该命令会从中央仓库下载依赖，若一次安装不成功，多执行几次，一般都是网络问题或依赖问题造成的)，最终肯定会成功安装到本地仓库中<br>
 > 3、使用Eclipse/IDEA导入boot-dubbo基本上的结构你就可以看懂了，下述也有简单的描述<br>
 


--------------------------------------
 
 ## 参考信息：

#### 说明：
 * service:一般我们的service瓶颈在IO或者数据库上，部署无需太多
 * web:一般的压力在web上，如果很多用户并发，session以及转换都在web上，web建议多部署几台
 * 在实际项目的架构中，Service和Infrastrature(DAO)之间应该还需要一层，就是ProxyDao那么这一层应该在Infrastrature这个当中


 


### Dubbox官方地址：<br>
[Dubbox](https://github.com/dangdangdotcom/dubbox)：https://github.com/dangdangdotcom/dubbox<br>


### Spring Boot: <br>
 
> 1、微内核<br>
> 2、配置简单<br>
> 3、模块化<br>
> 4、开箱即用<br>
> 5、完全兼容Spring<br>
> 6、设计理念极其先进，很多思想来自OSGi，但是在现有技术的实现<br>


缺点：<br>

 > 二次改造定制难<br>
 > 缺少成熟的SOA或者RPC框架<br>


----------


Dubbox:<br>
-------

优点：增加 rest 作为protocol，更加微服务化

 > 1、完全兼容Dubbo<br>
 > 2、功能强大<br>
 > 3、性能强悍<br>
 > 4、运行稳定<br>
 > 5、可扩展性较好, Api和Spi分离<br>

缺点：

> 1、官方停止维护已经两年之久了<br>
 > 2、依赖较为陈旧<br>
 > 3、运行时较重，应该微内核和模块化这个框架<br>
 > 4、配置较为复杂，学习成本较高<br>


