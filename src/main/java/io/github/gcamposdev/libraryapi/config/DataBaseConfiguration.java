package io.github.gcamposdev.libraryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


//Classe responsável por fazer a conexão com o banco de dados
/*A conexão normalmente já é feita pelo arquivo application, mas fica de exemplo
e aprendizado para quando houver algum problema com a conexão.
 */
@Configuration
public class DataBaseConfiguration {

    //Resgatando os valores das propriedades do arquivo application.yaml
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driverClassName}")
    String driver;

    /*
    -> Outra forma de se conectar com o banco, uma forma mais simples e não muito usada
    @Bean
    public DataSource dataSoure(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driver);
        return ds;
    }
    */

    // -> Alternativa de conexão com o banco utilizando pool de conexões, feita de forma "manual".
    // Com este componente ativo, o Spring ignora a configuração de conexão definida no application.yaml.
    @Bean
    public DataSource hikariDataSource(){
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(1);
        config.setPoolName("library-db-pool");
        config.setMaxLifetime(600000);
        config.setConnectionTimeout(100000);
        config.setConnectionTestQuery("select 1");

        return new HikariDataSource(config);
    }
}
