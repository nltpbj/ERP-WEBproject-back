package com.scarabERP.ERP;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.scarabERP.ERP.jay.BBS.BBSDAO;
import com.scarabERP.ERP.jay.BBS.BBSVO;

@SpringBootTest
class ScarabErpApplicationTests {
	@Autowired
	BBSDAO dao;
	
	@Test
	void contextLoads() {
		
		BBSVO vo=new BBSVO();
		vo.setBbs_id(8);
		vo.setBbs_title("이유닛 테스트");
		vo.setBbs_content("업데이트 된 내용");
		
		
	}

}
