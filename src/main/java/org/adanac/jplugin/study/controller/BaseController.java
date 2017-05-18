package org.adanac.jplugin.study.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.adanac.jplugin.study.Plugin;
import org.adanac.jplugin.study.dbo.Customer;
import org.adanac.jplugin.study.service.ICustomerService;
import org.adanac.jplugin.study.util.JsonUtil;
import org.adanac.jplugin.study.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import net.jplugin.core.das.api.PageCond;
import net.jplugin.core.log.api.LogFactory;
import net.jplugin.core.log.api.Logger;
import net.jplugin.core.rclient.proxyfac.ClientProxyFactory;
import net.jplugin.ext.webasic.api.AbstractExController;

public class BaseController extends AbstractExController {

	private static final String PARA_NAME = "name";
	private static final String PARA_VALUE = "value";
	private static final String PARA_START = "iDisplayStart";
	private static final String PARA_LENGTH = "iDisplayLength";
	private static final String PARA_TOTAL = "iTotalRecords";
	private static final String PARA_DISPLAY_TOTAL = "iTotalDisplayRecords";
	private static final String PARA_AODATA = "aaData";

	// 当前页数
	private static final String CURR_PAGE = "currentPage";
	// 当前长度
	private static final String CURR_LENGTH = "currentLength";

	protected final Logger log = LogFactory.getLogger(getClass());

	protected void writeJson(long total, List<?> resultList) {
		JSONObject json = new JSONObject();
		JSONObject inner = new JSONObject();
		inner.put(PARA_TOTAL, total);
		inner.put(PARA_DISPLAY_TOTAL, total);
		inner.put(PARA_AODATA, resultList == null ? new ArrayList<>() : resultList);
		json.put("data", inner);
		json.put("result", true);
		if (log.isDebugEnabled()) {
			log.debug("json result -> " + json.toJSONString());
		}
		renderJson(json);
	}

	protected void renderJson(Map<String, ?> map) {
		renderJson(JsonUtil.object2Json(map));
	}

	protected PageCond createAjaxPagination() {
		Integer begin = getIntQueryParam(PARA_START);
		Integer length = getIntQueryParam(PARA_LENGTH);
		if (begin == null) {
			begin = 0;
		}
		if (length == null) {
			length = 10;
		}
		Integer pageIndex = (begin / length + 1);
		PageCond pc = new PageCond();
		pc.setPageIndex(pageIndex);
		pc.setPageSize(length);
		pc.setShdCount(true);
		return pc;
	}

	protected Integer getIntQueryParam(String key) {
		if (StringUtils.isEmpty(key)) {
			return null;
		}
		JSONArray params = initialValue();
		if (params != null) {
			for (int i = 0, len = params.size(); i < len; i++) {
				JSONObject json = params.getJSONObject(i);
				if (key.equals(json.getString(PARA_NAME))) {
					return json.getInteger(PARA_VALUE);
				}
			}
		}
		return null;
	}

	protected JSONArray initialValue() {
		String aoData = getParam("aoData");
		JSONArray json = null;
		if (!StringUtils.isEmpty(aoData)) {
			json = JSONObject.parseArray(aoData);
		}
		if (log.isDebugEnabled()) {
			if (json != null) {
				log.debug("aoData : " + json.toJSONString());
			}
		}
		return json;
	}

	protected void renderView(String view) {
		HttpServletRequest request = getReq();
		HttpServletResponse response = getRes();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("异常", e);
			throw new RuntimeException(e.getMessage(), e);
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		setSessAttr("ctx", request.getContextPath());
		try {
			request.getRequestDispatcher(Plugin.TEMPLATE_PREFIX + view + Plugin.TEMPLATE_SUFFIX).forward(request,
					response);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	protected void writeMapForJson(long total, List<Map<String, Object>> mapList) {
		if (mapList == null) {
			mapList = new ArrayList<>();
		}
		JSONObject json = new JSONObject();
		json.put("data", mapList);
		json.put("total", total);
		renderJson(json);
	}

	protected PageCond createJspPagination() {
		String para_page = getParam(CURR_PAGE);
		String para_length = getParam(CURR_LENGTH);
		if (para_page == null) {
			para_page = "1";
		}
		if (para_length == null) {
			para_length = "5";
		}
		Integer page = Integer.parseInt(para_page);
		Integer length = Integer.parseInt(para_length);
		if (page <= 1) {
			page = 1;
		}
		if (length < 1) {
			length = 5;
		}
		PageCond pc = new PageCond();
		pc.setPageIndex(page);
		pc.setPageSize(length);
		pc.setShdCount(true);
		return pc;
	}

	protected List<Map<String, Object>> getCustomerInfo() {
		ICustomerService clientProxy = ClientProxyFactory.instance.getClientProxy(ICustomerService.class);
		List<Customer> custList = clientProxy.queryAllCustomer();
		int size = custList == null ? 0 : custList.size();
		List<Map<String, Object>> list = new ArrayList<>(size);
		if (custList != null) {
			for (Customer c : custList) {
				Map<String, Object> map = new HashMap<>();
				map.put("id", c.getCustId() + "_" + c.getCustName());
				map.put("name", c.getCustName());
				map.put("text", c.getCustName());
				list.add(map);
			}
		}
		return list;
	}
}
