package com.oa.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	public void contextLoads() {
		CodeGenerator gse = new CodeGenerator();
		//要给那些表生成
		gse.generateByTables(false,"apis");
	}
}
