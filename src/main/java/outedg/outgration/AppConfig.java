package outedg.outgration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class AppConfig {
    @Value("${driver.class.name}")
    private String driverClassName;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String guest_user;
    @Value("${spring.datasource.password}")
    private String guest_password;
    @Value("${spring.datasource.schema}")
    private String schema;

    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(guest_user);
        dataSource.setPassword(guest_password);
        dataSource.setSchema(schema);

        return dataSource;
    }
}
