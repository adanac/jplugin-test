package org.adanac.jplugin.study.service;

import java.util.HashMap;
import java.util.List;

import org.adanac.jplugin.study.dbo.Customer;

import net.jplugin.core.ctx.api.Rule;
import net.jplugin.core.ctx.api.Rule.TxType;
import net.jplugin.core.das.api.PageCond;
import net.jplugin.core.das.api.PageQueryResult;

public interface ICustomerService {
	@Rule
	List<Customer> queryAllCustomer();

	@Rule(methodType = TxType.REQUIRED)
	boolean removeCustomer(Long custId);

	@Rule(methodType = TxType.REQUIRED)
	void changeStatus(Long custId, String status);

	@Rule(methodType = TxType.REQUIRED)
	void updateCustomer(Customer cust);

	@Rule
	Customer getCustomer(long custId);

	@Rule
	PageQueryResult<Customer> queryCustomer(HashMap<String, String> query, PageCond pageCond);

	@Rule(methodType = TxType.REQUIRED)
	long addCustomer(Customer cust);

}
