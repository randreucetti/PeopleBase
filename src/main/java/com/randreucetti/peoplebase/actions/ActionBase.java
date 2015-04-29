package com.randreucetti.peoplebase.actions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;


public abstract class ActionBase extends ActionSupport{
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	protected static PrintWriter getWriter() throws IOException {
		final HttpServletResponse response = getResponse();
		response.setContentType("text/html");
		return response.getWriter();
	}
}
