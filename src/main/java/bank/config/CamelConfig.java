package bank.config;

import bank.domain.Balance;
import bank.infrastructure.BalanceTypeConverter;
import org.apache.camel.CamelContext;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("bank")
public class CamelConfig {

    private static final String CAMEL_URL_MAPPING = "/bank/account/*";
    private static final String CAMEL_SERVLET_NAME = "CamelServlet";

    @Bean
    public ServletRegistrationBean camelServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(), CAMEL_URL_MAPPING);
        registration.setName(CAMEL_SERVLET_NAME);
        return registration;
    }
}
