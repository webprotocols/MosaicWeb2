package com.hybrid;


import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.descriptor.JspConfigDescriptor;
import javax.servlet.descriptor.JspPropertyGroupDescriptor;
import javax.servlet.descriptor.TaglibDescriptor;

import org.apache.catalina.Context;
import org.apache.tomcat.util.descriptor.web.ErrorPage;
import org.apache.tomcat.util.descriptor.web.JspConfigDescriptorImpl;
import org.apache.tomcat.util.descriptor.web.JspPropertyGroup;
import org.apache.tomcat.util.descriptor.web.JspPropertyGroupDescriptorImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import com.hybrid.filter.SiteMeshFilter;

@SpringBootApplication
@ServletComponentScan
public class MosaicWebApplication implements EmbeddedServletContainerCustomizer {

	public static void main(String[] args) {
		SpringApplication.run(MosaicWebApplication.class, args);
	}
	
    @Bean
    public FilterRegistrationBean siteMeshFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new SiteMeshFilter());
   
        return filter;
    }

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory = (TomcatEmbeddedServletContainerFactory) container; 
		
		tomcatEmbeddedServletContainerFactory.addContextCustomizers(new TomcatContextCustomizer() {
			
			@Override
			public void customize(Context context) {
				
				/*
				 * Webcome Page
				 */
				context.addWelcomeFile("index.jsp");
				context.addWelcomeFile("index.html");
				
				/*
				 * Session 설정
				 */
				context.setSessionTimeout(30);
				
				/*
				 * Error Page 설정
				 */
				ErrorPage throwableErrorPage = new ErrorPage();
	            throwableErrorPage.setExceptionType("java.lang.Throwable");
	            throwableErrorPage.setLocation("/WEB-INF/error/error.jsp");

	            ErrorPage errorPage403 = new ErrorPage();
	            errorPage403.setErrorCode(403);
	            errorPage403.setLocation("/WEB-INF/error/403.jsp");

	            ErrorPage errorPage404 = new ErrorPage();
	            errorPage404.setErrorCode(404);
	            errorPage404.setLocation("/WEB-INF/error/404.jsp");

	            ErrorPage errorPage500 = new ErrorPage();
	            errorPage500.setErrorCode(500);
	            errorPage500.setLocation("/WEB-INF/error/500.jsp");

	            context.addErrorPage(errorPage500);
	            context.addErrorPage(errorPage404);
	            context.addErrorPage(errorPage403);
	            context.addErrorPage(throwableErrorPage);

				/*
				 * JSP Property Group & Tablib 설정
				 */
				Collection<JspPropertyGroupDescriptor> jspPropertyGroups = new ArrayList<>();
				Collection<TaglibDescriptor> taglibs = new ArrayList<>();

				JspPropertyGroup group = new JspPropertyGroup();
				group.addUrlPattern("*.jsp");
				group.addIncludePrelude("/WEB-INF/common/options.jspf");
				JspPropertyGroupDescriptor descriptor = new JspPropertyGroupDescriptorImpl(group);
				
				jspPropertyGroups.add(descriptor);

				JspConfigDescriptor jspConfigDescriptor = new JspConfigDescriptorImpl(jspPropertyGroups, taglibs);
				
				context.setJspConfigDescriptor(jspConfigDescriptor);
			}
		});
		
	}
}
/*    
    public class DevNexusApplication implements EmbeddedServletContainerCustomizer {
    	  ...
    	  @Override
    	  public void customize(ConfigurableEmbeddedServletContainer container) {
    	    if(container instanceof TomcatEmbeddedServletContainerFactory) {
    	      final TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory
    	          = (TomcatEmbeddedServletContainerFactory) container;

    	      tomcatEmbeddedServletContainerFactory.addContextCustomizers(
    	        new TomcatContextCustomizer() {
    	          @Override
    	          public void customize(Context context) {
    	            context.addWelcomeFile("index.jsp");

    	            final ErrorPage throwableErrorPage = new ErrorPage();
    	            throwableErrorPage.setExceptionType("java.lang.Throwable");
    	            throwableErrorPage.setLocation("/WEB-INF/jsp/error/error.jsp");

    	            final ErrorPage errorPage403 = new ErrorPage();
    	            errorPage403.setErrorCode(403);
    	            errorPage403.setLocation("/WEB-INF/jsp/error/403.jsp");

    	            final ErrorPage errorPage404 = new ErrorPage();
    	            errorPage404.setErrorCode(404);
    	            errorPage404.setLocation("/WEB-INF/jsp/error/404.jsp");

    	            final ErrorPage errorPage500 = new ErrorPage();
    	            errorPage500.setErrorCode(500);
    	            errorPage500.setLocation("/WEB-INF/jsp/error/500.jsp");

    	            context.addErrorPage(errorPage500);
    	            context.addErrorPage(errorPage404);
    	            context.addErrorPage(errorPage403);
    	            context.addErrorPage(throwableErrorPage);

    	            final Collection<JspPropertyGroupDescriptor> jspPropertyGroups = new ArrayList<>();
    	            final Collection<TaglibDescriptor> taglibs = new ArrayList<>();

    	            final JspPropertyGroup group = new JspPropertyGroup();
    	            group.addUrlPattern("*.jsp");
    	            group.setPageEncoding("UTF-8");

    	            final JspPropertyGroupDescriptor descriptor = new JspPropertyGroupDescriptorImpl(group);

    	            jspPropertyGroups.add(descriptor);

    	            final JspConfigDescriptor jspConfigDescriptor = new JspConfigDescriptorImpl(jspPropertyGroups, taglibs);
    	            context.setJspConfigDescriptor(jspConfigDescriptor);
    	          }
    	        }
    	      );
    	    }
    	  }
	
}
*/