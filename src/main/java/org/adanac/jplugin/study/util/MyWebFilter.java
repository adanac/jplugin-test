package org.adanac.jplugin.study.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import net.jplugin.ext.webasic.api.WebFilter;

public class MyWebFilter implements WebFilter {

	public boolean doFilter(HttpServletRequest request, HttpServletResponse response) {
		final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		final HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		final HttpServletRequestWrapper requestWrapper = new MyHttpServletRequestWrapper(httpServletRequest);
		return true;
	}

	public void doAfter(HttpServletRequest req, HttpServletResponse res, Throwable th) {
		System.out.println("doAfter doAfter doAfter");
	}

}