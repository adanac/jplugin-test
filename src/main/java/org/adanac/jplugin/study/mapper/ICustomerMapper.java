package org.adanac.jplugin.study.mapper;

import java.util.HashMap;
import java.util.List;

import org.adanac.jplugin.study.dbo.Customer;
import org.apache.ibatis.annotations.Param;

import net.jplugin.core.das.api.PageCond;

public interface ICustomerMapper {

	public long insert(Customer cust);

	public List<Customer> queryWithPage(@Param("query") HashMap<String, String> query,
			@Param("page") PageCond pageCond);

	public Customer find(@Param("custId") long custId);

	public int update(@Param("cust") Customer cust);

	public int updateStatus(@Param("custId") Long custId, @Param("status") String status);

	public int remove(@Param("custId") Long custId);

	public List<Customer> queryAllCustomer();

}
