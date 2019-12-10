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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@SuppressWarnings("ALL")
@Configuration
@MapperScan(basePackages = "com.sun.customer.mapper.local", sqlSessionFactoryRef = "sqlSessionFactoryBapLocal")
public class DataSoureceLocal {

    @Bean(value= "dataSourceBapLocal", destroyMethod = "close",initMethod = "init")
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.datasourcebaplocal")
    public DataSource dataSourceBapLocal() {
        return new AtomikosDataSourceBean();
    }


    @Bean(name = "sqlSessionFactoryBapLocal")
//    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactory sqlSessionFactoryBapLocal(@Qualifier("dataSourceBapLocal") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/local/*Mapper.xml"));
        return bean.getObject();
    }
    @Bean(name = "sqlSessionTemplateLocal")
    public SqlSessionTemplate sqlSessionTemplateLocal(@Qualifier("sqlSessionFactoryBapLocal") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }
}
