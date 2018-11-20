package br.com.teste.cadastro.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Classe de configuração do DataSource.
 */
@Configuration
@EnableTransactionManagement
@ConfigurationProperties("prd")
public class DataSourcePrdConfig extends DataSourceConfig {
}
