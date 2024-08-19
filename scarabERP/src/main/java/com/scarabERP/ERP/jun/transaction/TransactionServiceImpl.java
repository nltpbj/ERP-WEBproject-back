package com.scarabERP.ERP.jun.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scarabERP.ERP.jun.account.AccountDAO;
import com.scarabERP.ERP.jun.salary.SalaryDAO;
import com.scarabERP.ERP.jun.salary.SalaryVO;
import com.scarabERP.ERP.starim.purchase.PurchaseDAO;
import com.scarabERP.ERP.starim.purchase.PurchaseVO;
import com.scarabERP.ERP.starim.sales.SalesDAO;
import com.scarabERP.ERP.starim.sales.SalesVO;
import com.mysql.cj.jdbc.exceptions.MySQLTransactionRollbackException;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionDAO tdao;
    
    @Autowired
    AccountDAO adao;
    
    @Autowired
    PurchaseDAO pdao;
    
    @Autowired
    SalesDAO sdao;
    
    @Autowired
    SalaryDAO ldao;

    @Transactional
    @Override
    public void insert(TransactionVO vo) throws MySQLTransactionRollbackException {
        int retries = 10; // 재시도 횟수를 10번으로 설정합니다.
        while (retries > 0) {
            performInsert(vo);
			break; // 성공적으로 완료되면 반복 종료
        }
    }
    
    private void performInsert(TransactionVO vo) {
        String account_number = vo.getAccount_number();
        int account_total = adao.read(account_number).getAccount_total();
        tdao.insert(vo);
        
        if (vo.getSales_id() != null) {
            adao.updateTotal(account_number, account_total + vo.getTransaction_deposit());
            SalesVO sales = new SalesVO();
            sales.setSales_id(vo.getSales_id());
            sales.setSales_type(vo.getSales_type());
            sdao.update(sales);
        } else if (vo.getPurchase_id() != null) {
            adao.updateTotal(account_number, account_total - vo.getTransaction_withdraw());
            PurchaseVO purchase = new PurchaseVO();
            purchase.setPurchase_id(vo.getPurchase_id());
            purchase.setPurchase_type(vo.getPurchase_type());
            pdao.update(purchase);
        } else {
            adao.updateTotal(account_number, account_total - vo.getTransaction_withdraw());
            SalaryVO salary = new SalaryVO();
            salary.setMember_info_key(vo.getMember_info_key());
            salary.setMember_salary_money(vo.getTransaction_withdraw());
            ldao.insert(salary);
        }
    }
}
