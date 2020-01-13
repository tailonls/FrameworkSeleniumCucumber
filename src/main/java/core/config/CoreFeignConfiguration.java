package br.com.unicred.caixa.servicos.restAPI.configuration;

import feign.codec.Encoder;
import feign.form.FormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CoreFeignConfiguration {

  @Autowired
  private ObjectFactory<HttpMessageConverters> messageConverters;

  @Bean
  @Primary
  //@Scope("prototype")
  Encoder feignFormEncoder() {
    return new FormEncoder(new SpringEncoder(this.messageConverters));
  }
}