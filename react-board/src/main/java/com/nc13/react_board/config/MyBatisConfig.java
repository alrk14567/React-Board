package com.nc13.react_board.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.AntPathMatcher;

import javax.sql.DataSource;

@Configuration
public class MyBatisConfig {

    @Bean
    // sqlSessionFactory 에서 dataSource를 통해 DB연결, 마이바티스컨피그 경로 DB와 xml을 연결하는 설정 각 매퍼 로케이션은 resources를 통해 연결
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));
        Resource[] resources=new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mappers/*.xml");
        factoryBean.setMapperLocations(resources);

        return factoryBean.getObject();
    }

    @Bean
    // sqlSessionTemplate의 역할은 이런 전체 연결은 템플레이트로 저장???
    public SqlSessionTemplate sqlSessionTemplate (SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}

