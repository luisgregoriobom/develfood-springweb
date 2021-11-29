package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;


@Data
@Component
public class EmailConfigDto {

    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private Integer port;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;

//    @Primary
//    @Bean
//    public FreeMarkerConfigurationFactoryBean factoryBean(){
//        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
//        bean.setTemplateLoaderPath("classpath:/templates");
//        return bean;
//    }

}
