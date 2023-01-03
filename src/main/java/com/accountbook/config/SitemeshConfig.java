package com.accountbook.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class SitemeshConfig extends ConfigurableSiteMeshFilter {
	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		builder.addDecoratorPath("/community", "/WEB-INF/views/decorator/default-layout.jsp");
		builder.addDecoratorPath("/notice", "/WEB-INF/views/decorator/default-layout.jsp");
		builder.addDecoratorPath("/faq", "/WEB-INF/views/decorator/default-layout.jsp");
		builder.addDecoratorPath("/inquiry", "/WEB-INF/views/decorator/default-layout.jsp");
		super.applyCustomConfiguration(builder);
	}
	

}
