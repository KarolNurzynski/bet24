package pl.coderslab.appconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import pl.coderslab.app.SpringDataUserDetailsService;

/**
 * Spring Security configuration. For test purposes some urls are still not secured.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/*").permitAll()
                .antMatchers("/login", "/home", "/").anonymous()
                .antMatchers("/bet", "/logout").authenticated()
                .antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui", "/swagger-resources", "/swagger-resources/configuration/security", "/swagger-ui.html", "/webjars/**").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/betOffer/**", "/event/**").permitAll()     // for production it will be authenticated with ADMIN_ROLE account
                .anyRequest().permitAll()   //denyAll() - 403 access denied page
            .and()
                .formLogin()
                    .loginPage("/login")        //overrides standard Spring login form defined above
                        .defaultSuccessUrl("/home")
            .and()
                .logout()
                    .deleteCookies()
                    .invalidateHttpSession(true)
                        .logoutSuccessUrl("/home") ;   //overrides standard Spring logout action
//            .and()
//                .exceptionHandling()
//                    .accessDeniedPage("/403.html");

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public SpringDataUserDetailsService customUserDetailsService() {
        return new SpringDataUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
