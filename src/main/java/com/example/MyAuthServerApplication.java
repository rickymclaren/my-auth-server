package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@SpringBootApplication
@EnableAuthorizationServer
public class MyAuthServerApplication { //extends AuthorizationServerConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(MyAuthServerApplication.class, args);
	}

//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//	    super.configure(security);
//        security.checkTokenAccess("permitAll()");
//    }
}
