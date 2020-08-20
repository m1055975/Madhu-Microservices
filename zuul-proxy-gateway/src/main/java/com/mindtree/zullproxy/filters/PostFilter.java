package com.mindtree.zullproxy.filters;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

public class PostFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return true;
	}
	@Override
	public Object run() throws ZuulException {
		System.out.println("Post Filter is Working");
		return null;
	}
	@Override
	public String filterType() {
		return "post";
	}
	@Override
	public int filterOrder() {
		return 1;
	}

}