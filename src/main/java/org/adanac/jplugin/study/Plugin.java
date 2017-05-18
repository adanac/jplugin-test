package org.adanac.jplugin.study;

import org.adanac.jplugin.study.controller.CustomerController;
import org.adanac.jplugin.study.dbo.Customer;
import org.adanac.jplugin.study.mapper.ICustomerMapper;
import org.adanac.jplugin.study.service.CustomerServiceImpl;
import org.adanac.jplugin.study.service.ICustomerService;
import org.adanac.jplugin.study.util.DbConstant;
import org.adanac.jplugin.study.util.MyWebFilter;

import net.jplugin.core.ctx.ExtensionCtxHelper;
import net.jplugin.core.das.ExtensionDasHelper;
import net.jplugin.core.das.mybatis.api.ExtensionMybatisDasHelper;
import net.jplugin.core.das.mybatis.api.MysqlPageInterceptor;
import net.jplugin.core.kernel.api.AbstractPlugin;
import net.jplugin.ext.webasic.ExtensionWebHelper;

public class Plugin extends AbstractPlugin {

	private static final String ENCODE_UTF8 = "UTF-8";
	public static String contentType = "text/html; charset=" + ENCODE_UTF8;
	public static final String TEMPLATE_PREFIX = "/WEB-INF/pages/";
	public static final String TEMPLATE_SUFFIX = ".jsp";

	public Plugin() {
		// 过滤器
		ExtensionWebHelper.addWebFilterExtension(this, MyWebFilter.class);
		// 发布一个到JSP的控制器，JSP页面未实现，仅作服务端代码写法示例
		ExtensionWebHelper.addWebExControllerExtension(this, "/cust", CustomerController.class);
		// 添加mapper映射
		ExtensionMybatisDasHelper.addMappingExtension(this, Customer.class);

		// 注册数据源
		ExtensionDasHelper.addDataSourceExtension(this, DbConstant.DB_NAME, "database");

		// 添加web扩展，通过ajax加载数据
		// ExtensionWebHelper.addWebExControllerExtension(this, "/cust_manager",
		// AjaxController.class);

		// 添加web扩展，通过jsp同步请求加载数据
		// ExtensionWebHelper.addWebExControllerExtension(this, "/cust_jsp",
		// JSPController.class);

		// 添加service扩展
		ExtensionCtxHelper.addRuleExtension(this, ICustomerService.class.getName(), ICustomerService.class,
				CustomerServiceImpl.class);

		// 注册映射接口
		ExtensionMybatisDasHelper.addMappingExtension(this, ICustomerMapper.class);

		// 添加统一分页拦截器
		ExtensionMybatisDasHelper.addInctprorExtension(this, MysqlPageInterceptor.class);

	}

	@Override
	public void init() {

	}

	@Override
	public int getPrivority() {
		return 0;
	}

}