package com.example.demo.core.log;

import org.springframework.boot.web.embedded.tomcat.ConfigurableTomcatWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

import ch.qos.logback.access.tomcat.LogbackValve;

/** 
 * アクセスログカスタマイズ 
 */
@Configuration
public class AccessLogCustomizer implements WebServerFactoryCustomizer<ConfigurableTomcatWebServerFactory> {
	
	@Override
	public void customize(ConfigurableTomcatWebServerFactory factory) {
		// tomcat アクセスログ追加
		factory.addEngineValves(new LogbackValve());
	}
}