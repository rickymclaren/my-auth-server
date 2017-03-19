package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@SpringBootApplication
@EnableAuthorizationServer
public class MyAuthServerApplication { //extends AuthorizationServerConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(MyAuthServerApplication.class, args);
	}

	@Autowired
	DataSource dataSource;

	@Bean
	TokenStore tokenStore(DataSource dataSource) {
		return new JdbcTokenStore(dataSource);
	}

}
