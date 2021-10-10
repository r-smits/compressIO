package com.ramon.projects.springtut;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringtutApplicationTests {

	SpringtutApplication springtutApplication;

	@Autowired
	SpringtutApplicationTests(SpringtutApplication springtutApplication) {
		this.springtutApplication = springtutApplication;
	}

	@Test
	void contextLoads() {
	}

}
