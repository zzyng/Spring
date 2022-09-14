package com.db.basic.config;

import java.io.IOException;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
//<mybatis-spring:scan base-package="com.db.basic.repository"/>
@MapperScan(basePackages = {"com.db.basic.repository", "com.db.basic.ajax"})
public class DataBaseConfig {
//	<bean class="com.zaxxer.hikari.HikariConfig" id="hikariConfig">
//	<property name="driverClassName" value="oracle.jdbc.driver.OracleDriver"/>
//	<property name="jdbcUrl" value="jdbc:oracle:thin:@localhost:1521:xe"/>
//	<property name="username" value="oracle"/>
//	<property name="password" value="oracle"/>
//</bean>	
	
//<bean class="com.zaxxer.hikari.HikariDataSource" id="dataSource">
//	<constructor-arg ref="hikariConfig"/>
//</bean>
	@Bean
	public HikariDataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		hikariConfig.setUsername("oracle");
		hikariConfig.setPassword("oracle");
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		return dataSource;
	}
//<bean class="org.mybatis.spring.SqlSessionFactoryBean" id="sessionFactory">
//	<property name="dataSource" ref="dataSource"/>
//	<property name="mapperLocations" value="classpath:/mappers/**/*Mapper.xml" />
//</bean>
	@Bean
	public SqlSessionFactoryBean sessionFactory() throws IOException {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sessionFactory.setMapperLocations(resolver.getResources("/mappers/**/*Mapper.xml"));
		return sessionFactory;
	}

}
