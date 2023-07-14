package laveemcasa.api_lave_em_casa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();



//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("localhost:8080/auth/autenticacao").permitAll() // Permite o acesso à rota de autenticação sem autenticação
//                .anyRequest().authenticated(); // Exige autenticação para qualquer outra rota
    }
}
