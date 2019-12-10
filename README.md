# jta-atomikos
springboot +mybatis  + jta+atomikos+ 多数据源事务

解决问题： 用于处理同一个操作需要链接多个数据源 并支持事务的问题

主要就是添加对应数据源的配置，不同数据源需要放在不同的文件夹下
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

