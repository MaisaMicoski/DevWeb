package com.maisamicoski.projdevweb.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Habilita a configuração de CORS que definimos no WebConfig
                .cors(withDefaults())

                // Desabilita a proteção CSRF, que não é necessária para nossa API stateless
                .csrf(AbstractHttpConfigurer::disable)

                // Define as regras de autorização para as requisições
                .authorizeHttpRequests(authorize -> authorize
                        // A REGRA MAIS IMPORTANTE:
                        // Permite o acesso PÚBLICO a QUALQUER URL que comece com /api/
                        // O padrão "/**" significa que ele pega tudo, incluindo sub-rotas como /turmas/1/alunos
                        .requestMatchers("/api/**").permitAll()

                        // Para qualquer outra requisição que NÃO comece com /api/, exige autenticação.
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}