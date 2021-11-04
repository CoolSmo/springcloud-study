package cn.ke.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaConsumerApplication {

	@Autowired
	RestTemplate restTemplate;
	@Autowired
	DiscoveryClient discoveryClient;
	public static void main(String[] args) {
		SpringApplication.run(EurekaConsumerApplication.class, args);
	}

	/**
	 * 实例化RestTemplate
	 */
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@GetMapping(value = "/getUserInfo")
	public Map<String,Object> getUserInfo1(){
		ServiceInstance serviceInstance = discoveryClient.getInstances("product-provider1").get(0);
		System.out.println(serviceInstance.getHost());
		System.out.println(serviceInstance.getPort());
		return restTemplate.getForObject("http://product-provider1/getUser",Map.class);

	}

}
