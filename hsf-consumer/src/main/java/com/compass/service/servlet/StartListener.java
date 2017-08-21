package com.compass.service.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.compass.service.IHelloWordService;
import com.compass.service.IPersonService;

public class StartListener implements ServletContextListener {

	@Override
	public void contextInitialized( ServletContextEvent sce ) {
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext( sce.getServletContext() );
		final IHelloWordService helloWordService = 
				(IHelloWordService) ctx.getBean("helloWordService");
		final IPersonService personService = 
				(IPersonService) ctx.getBean("personService");
		Thread thread = new Thread( new Runnable() {
			@Override
			public void run() {
				while ( true ) {
					try {
						Thread.sleep( 500l );
						System.out.println(helloWordService.sayHello("张三"));
						System.out.println(personService.getName("啊呀呀"));
					} catch ( Throwable e ) {
						e.printStackTrace();
					}
				}
			}
		} );
		thread.start();
	}

	@Override
	public void contextDestroyed( ServletContextEvent sce ) {
	}

}
