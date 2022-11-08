package br.edu.ifrs.restinga.MeuMercadoTarefa.securety.config;

import br.edu.ifrs.restinga.MeuMercadoTarefa.admin.repository.AdminRepository;
import br.edu.ifrs.restinga.MeuMercadoTarefa.securety.service.AutenticacaoService;
import br.edu.ifrs.restinga.MeuMercadoTarefa.securety.service.AutenticacaoTokenFilter;
import br.edu.ifrs.restinga.MeuMercadoTarefa.securety.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    //config autorizacao
    
    private final TokenService tokenService;
    
    private final AutenticacaoService autenticacaoService;
    
    private final AdminRepository adminRepository;
    
    
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(HttpMethod.GET, "/produto/*/*").permitAll()
            .antMatchers(HttpMethod.GET, "/produto/*").permitAll()
            .antMatchers(HttpMethod.GET, "/produto").permitAll()
            .antMatchers(HttpMethod.GET, "/admin/*/*").permitAll()
            .antMatchers(HttpMethod.GET, "/admin/*").permitAll()
            .antMatchers(HttpMethod.GET, "/admin").permitAll()
            .antMatchers(HttpMethod.POST, "/admin/auth").permitAll()
            .antMatchers(HttpMethod.POST, "/admin/salvar").permitAll()
            .anyRequest().authenticated()
            .and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().addFilterBefore(new AutenticacaoTokenFilter(tokenService, adminRepository), UsernamePasswordAuthenticationFilter.class);
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
        
    }
    
}
