package com.sun.customer.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.sql.DataSource;
import java.util.Properties;

//@SuppressWarnings("ALL")
@Configuration
@MapperScan(basePackages = "com.sun.customer.mapper.HS", sqlSessionFactoryRef = "sqlSessionFactoryBapHS")
public class DataSoureceHS {

    @Bean(value = "dataSourceBapHS", destroyMethod = "close",initMethod = "init")
    @ConfigurationProperties("spring.jta.atomikos.datasource.datasourcebaphs")
    public DataSource dataSourceBapHS() {
        return new AtomikosDataSourceBean();
    }

    @Bean(name = "sqlSessionFactoryBapHS")
//    @ConfigurationProperties("mybatis")
    public SqlSessionFactory sqlSessionFactoryBapHS(@Qualifier("dataSourceBapHS") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "sqlSessionTemplatePrimary")
    public SqlSessionTemplate sqlSessionTemplatePrimary(@Qualifier("sqlSessionFactoryBapHS") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }
}
