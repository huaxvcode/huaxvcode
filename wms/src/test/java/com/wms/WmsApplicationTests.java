package com.wms;

import com.wms.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WmsApplicationTests {

	@Autowired
	private IUserService iUserService;

	@Test
	void contextLoads() {
		System.out.println(iUserService.list());
	}

}
