package com.scarabERP.ERP;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class MysqlTest {
	
	
	@Autowired
	MysqlDAO dao;
	
	
	@Test
	public void test() {
		dao.now();
	}
}
