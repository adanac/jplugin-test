package org.adanac.jplugin.study.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.adanac.jplugin.study.dbo.Customer;
import org.adanac.jplugin.study.dbo.RespJson;
import org.adanac.jplugin.study.service.ICustomerService;

import com.alibaba.fastjson.JSON;

import net.jplugin.core.ctx.api.RuleServiceFactory;
import net.jplugin.core.das.api.PageCond;
import net.jplugin.core.das.api.PageQueryResult;
import net.jplugin.core.log.api.ILogService;
import net.jplugin.core.log.api.Logger;
import net.jplugin.core.service.api.ServiceFactory;
import net.jplugin.ext.webasic.api.AbstractExController;

/**
 * 本例演示一个通过Controller到JSP的用法，具体jsp页面未实现
 * 
 * @author Administrator
 *
 */
public class CustomerController extends AbstractExController {
	Logger logger = ServiceFactory.getService(ILogService.class)
			.getLogger("org.adanac.jplugin.study.controller.CustomerController");

	@SuppressWarnings("deprecation")
	public void getCustomer() {
		RespJson respJson = new RespJson();
		HttpServletRequest request = getReq();
		String custId = (String) request.getAttribute("custId");
		// 或者 String custId = (String) request.getParameter("custId");
		Customer cust = RuleServiceFactory.getRuleService(ICustomerService.class).getCustomer(Long.parseLong(custId));
		respJson.setDatabody(cust);
		respJson.setResultCode("1");
		renderJson(JSON.toJSONString(respJson));
		renderJsp("/page/cust-detail.html");
	}

	public void getCustomer2() {
		HttpServletRequest request = getReq();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String custId = (String) request.getAttribute("custId");
		// 或者 String custId = (String) request.getParameter("custId");
		Customer cust = RuleServiceFactory.getRuleService(ICustomerService.class).getCustomer(Long.parseLong(custId));
		setAttr("cust", cust);
		renderJsp("/page/cust-detail.jsp");
	}

	@SuppressWarnings("deprecation")
	public void queryCustomer() throws ParseException {
		RespJson respJson = new RespJson();
		logger.info("查询客户信息开始.........");
		String params = getParam("query");
		String params2 = getParam("pageCond");
		// String pageSize = getParam("pageSize");
		// String pageIndex = getParam("pageIndex");
		// String params = getRequestPayload(getReq());
		System.out.println("====param====" + params + "=====" + params2);

		HashMap<String, String> qparam = new HashMap<String, String>();
		// qparam.put("custId", query);
		// qparam.put("custName", query);
		PageCond pageCond = new PageCond(10, 1);
		try {
			ICustomerService icust = RuleServiceFactory.getRuleService(ICustomerService.class);
			PageQueryResult<Customer> queryResult = icust.queryCustomer(qparam, pageCond);
			if (queryResult != null && queryResult.getList() != null) {
				List<Customer> list = queryResult.getList();
				respJson.setDatabody(list);
				respJson.setResultCode("1");
				renderJson(JSON.toJSONString(respJson));
			} else {
				respJson.setResultCode("0");
				respJson.setMsg("no data");
				renderJson(JSON.toJSONString(respJson));
			}
		} catch (final Exception e1) {
			logger.error("查询客户信息出错：" + e1.toString());
			respJson.setResultCode("0");
			respJson.setMsg("Query Error");
			renderJson(JSON.toJSONString(respJson));
			e1.printStackTrace();
		}
	}

	public static String getRequestPayload(HttpServletRequest req) {
		StringBuilder sb = new StringBuilder();
		// try (BufferedReader reader = req.getReader();) {
		// char[] buff = new char[1024];
		// int len;
		// while ((len = reader.read(buff)) != -1) {
		// sb.append(buff, 0, len);
		// }
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		try {
			ServletInputStream inputStream = req.getInputStream();
			byte[] b = new byte[1024];
			int len;
			while ((len = inputStream.read(b)) != -1) {
				sb.append(getChar(b), 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	private static char[] getChar(byte[] b) {

		Charset cs = Charset.forName("UTF-8");
		ByteBuffer bb = ByteBuffer.allocate(b.length);
		bb.put(b);
		bb.flip();
		CharBuffer cb = cs.decode(bb);
		return cb.array();
	}

}
