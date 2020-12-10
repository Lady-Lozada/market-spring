package com.developer.marketspring.web.security;

import com.developer.marketspring.domain.service.UserService;
import com.developer.marketspring.web.security.filter.JWTFilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTFilterRequest jwt;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(WebSecurity web)throws Exception {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui",
                "/swagger-resources/**", "/configuration/security",
                "/swagger-ui.html", "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // Desabilita las peticiones cruzadas
                .authorizeRequests() // Autoriza las peticiones
                .antMatchers("/**/login") //lo que voy a permitir en este caso el servicio login
                .permitAll()
                .anyRequest().authenticated() // Lo que no termine en login, si requiere autenticacion
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // session sin estado

        http.addFilterBefore(jwt, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean // Especifica que este metodo es el gestor de autenticacion
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
