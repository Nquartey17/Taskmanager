package com.leetjourney.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication /* What this contains:
1. @Configuration - tells spring this has configuration
2. @EnableAutoConfiguration - Looks at classpath and auto configs to Beans
3. @ComponentScan - Scans packages and subpackages for components
*/
public class TaskmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagerApplication.class, args);
	}

}
