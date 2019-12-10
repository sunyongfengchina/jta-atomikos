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
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@SuppressWarnings("ALL")
@Configuration
@MapperScan(basePackages = "com.sun.customer.mapper.NB", sqlSessionFactoryRef = "sqlSessionFactoryBapNB")
public class DataSoureceNB {

    @Bean(value = "dataSourceBapNB", destroyMethod = "close",initMethod = "init")
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.datasourcenb")
    public DataSource dataSourceBapNB() {
        return new AtomikosDataSourceBean();
    }


    @Bean(name = "sqlSessionFactoryBapNB")
//    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactory sqlSessionFactoryBapNB(@Qualifier("dataSourceBapNB") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }
    @Bean(name = "sqlSessionTemplateNB")
    public SqlSessionTemplate sqlSessionTemplateNB(@Qualifier("sqlSessionFactoryBapNB") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }
}
