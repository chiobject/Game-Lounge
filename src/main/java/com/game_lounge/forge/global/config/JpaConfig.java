package com.game_lounge.forge.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaConfig {
    // @CreatedDate, @LastModifiedDate 자동 작동 활성화
}