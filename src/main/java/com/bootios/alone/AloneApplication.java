package com.bootios.alone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableFeignClients // feign
@SpringBootApplication
public class AloneApplication {

  public static void main(String[] args) {
    SpringApplication.run(AloneApplication.class, args);
  }
}
