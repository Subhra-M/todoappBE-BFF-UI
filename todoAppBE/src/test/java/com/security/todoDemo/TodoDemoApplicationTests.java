package com.security.todoDemo;

import com.security.todoDemo.controllers.RestApiController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TodoDemoApplicationTests {

	@Autowired
	private RestApiController restApiController;

	@Test
	void contextLoads() throws Exception{
		Assertions.assertThat(restApiController).isNotNull();
	}

}
