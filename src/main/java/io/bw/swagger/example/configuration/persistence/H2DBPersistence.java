package io.bw.swagger.example.configuration.persistence;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Byungwook Lee on 2018-01-23
 * quddnr153@gmail.com
 * https://github.com/quddnr153
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"io.bw.swagger.example.api.**.mapper"})
public class H2DBPersistence {
    @Bean(name = "h2DataSource", destroyMethod = "")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(BasicDataSource.class).build();
    }

    @Bean(name = "batchSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("h2DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        DatabasePopulatorUtils.execute(new ResourceDatabasePopulator(true, false, null, new ClassPathResource("schema-h2.sql")), dataSource);
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/io/bw/swagger/example/api/**/mapper/*Mapper.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "datasourceTransaction")
    public DataSourceTransactionManager datasourceTransaction(@Qualifier("h2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
