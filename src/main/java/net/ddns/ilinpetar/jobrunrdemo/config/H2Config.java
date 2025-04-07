package net.ddns.ilinpetar.jobrunrdemo.config;


import java.sql.SQLException;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class H2Config {

    @Value("${spring.datasource.url}")
    private String jdbcDatasourceUrl;

    @Bean
    @DependsOn("h2Server")
    public JdbcDataSource dataSource() {
        final JdbcDataSource jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setUrl(jdbcDatasourceUrl);
        return jdbcDataSource;
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-ifNotExists", "-tcpAllowOthers", "-tcpPort", "9092");
    }
}
