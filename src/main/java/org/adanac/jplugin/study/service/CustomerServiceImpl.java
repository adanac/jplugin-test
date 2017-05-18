package org.adanac.jplugin.study.service;

import java.util.HashMap;
import java.util.List;

import org.adanac.jplugin.study.dbo.Customer;
import org.adanac.jplugin.study.mapper.ICustomerMapper;
import org.adanac.jplugin.study.util.DbConstant;

import net.jplugin.common.kits.ObjectRef;
import net.jplugin.core.das.api.PageCond;
import net.jplugin.core.das.api.PageQueryResult;
import net.jplugin.core.das.mybatis.api.MyBatisServiceFactory;
import net.jplugin.core.das.mybatis.impl.IMapperHandler;
import net.jplugin.core.das.mybatis.impl.IMybatisService;

public class CustomerServiceImpl implements ICustomerService {

	private IMybatisService dbService() {
		return MyBatisServiceFactory.getService(DbConstant.DB_NAME);
	}

	public long addCustomer(final Customer cust) {
		final ObjectRef<Long> ref = new ObjectRef<Long>();
		dbService().runWithMapper(ICustomerMapper.class, new IMapperHandler<ICustomerMapper>() {
			public void run(ICustomerMapper mapper) {
				ref.set(mapper.insert(cust));
			}
		});
		return ref.get();
	}

	@Override
	public List<Customer> queryAllCustomer() {
		final ObjectRef<List<Customer>> ref = new ObjectRef<List<Customer>>();
		dbService().runWithMapper(ICustomerMapper.class, new IMapperHandler<ICustomerMapper>() {
			public void run(ICustomerMapper mapper) {
				ref.set(mapper.queryAllCustomer());
			}
		});
		return ref.get();
	}

	@Override
	public boolean removeCustomer(final Long custId) {
		final ObjectRef<Boolean> ref = new ObjectRef<Boolean>();
		dbService().runWithMapper(ICustomerMapper.class, new IMapperHandler<ICustomerMapper>() {
			public void run(ICustomerMapper mapper) {
				ref.set(mapper.remove(custId) > 0 ? true : false);
			}
		});
		return ref.get();
	}

	@Override
	public void changeStatus(final Long custId, final String status) {
		final ObjectRef<Boolean> ref = new ObjectRef<Boolean>();
		dbService().runWithMapper(ICustomerMapper.class, new IMapperHandler<ICustomerMapper>() {
			public void run(ICustomerMapper mapper) {
				ref.set(mapper.updateStatus(custId, status) > 0 ? true : false);
			}
		});
	}

	@Override
	public void updateCustomer(final Customer cust) {
		final ObjectRef<Integer> ref = new ObjectRef<>();
		dbService().runWithMapper(ICustomerMapper.class, new IMapperHandler<ICustomerMapper>() {
			public void run(ICustomerMapper mapper) {
				ref.set(mapper.update(cust));
			}
		});
	}

	@Override
	public Customer getCustomer(final long custId) {
		final ObjectRef<Customer> ref = new ObjectRef<Customer>();
		IMybatisService mybatisService = dbService();
		mybatisService.runWithMapper(ICustomerMapper.class, new IMapperHandler<ICustomerMapper>() {
			public void run(ICustomerMapper mapper) {
				// HashMap<String, String> query = new HashMap<String,
				// String>();
				// query.put("custId", custId + "");
				// PageCond pageCond = new PageCond(10, 1);
				// ref.set(mapper.queryWithPage(query, pageCond).get(0));
				ref.set(mapper.find(custId));
			}
		});
		return ref.get();
	}

	@Override
	public PageQueryResult<Customer> queryCustomer(HashMap<String, String> query, final PageCond pageCond) {
		final ObjectRef<PageQueryResult<Customer>> ref = new ObjectRef<PageQueryResult<Customer>>();
		dbService().runWithMapper(ICustomerMapper.class, new IMapperHandler<ICustomerMapper>() {
			public void run(ICustomerMapper mapper) {
				HashMap<String, String> query = new HashMap<String, String>();
				List<Customer> resData = mapper.queryWithPage(query, pageCond);
				ref.set(new PageQueryResult<>(pageCond, resData));
			}
		});
		return ref.get();
	}

}
