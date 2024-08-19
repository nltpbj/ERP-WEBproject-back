package com.scarabERP.ERP.jun.salary;

import java.util.List;

import com.scarabERP.ERP.common.QueryVO;
import com.scarabERP.ERP.jun.member.MemberVO;

public interface SalaryDAO {
	public List<SalaryVO> list(String member_info_key);
	public List<MemberVO> memberList(QueryVO query, MemberVO vo);
	public int memberTotal(QueryVO query, MemberVO vo);
	public void insert(SalaryVO vo);
}
