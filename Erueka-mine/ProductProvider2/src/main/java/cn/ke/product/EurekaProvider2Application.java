package cn.ke.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@EnableEurekaClient
@SpringBootApplication
public class EurekaProvider2Application {

	public static void main(String[] args) {
		SpringApplication.run(EurekaProvider2Application.class, args);
	}

	/**
	 * 假如这个客户端要提供一个getUser的方法
	 * @return
	 */
	@GetMapping(value = "/getUser")
	public Map<String,Object> getUser(){
		Map<String,Object> data = new HashMap<>();
		data.put("id",9999999);
		data.put("userName","张三");
		data.put("from","provider-one");
		return data;
	}

}

