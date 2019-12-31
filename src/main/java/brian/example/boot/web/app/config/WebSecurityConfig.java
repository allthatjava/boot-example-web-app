package brian.example.boot.web.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    @Qualifier("appUserService")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/" ).permitAll()
                .antMatchers("/h2-console/**").permitAll()  // to enable H2 console under Spring Security
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/").permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/app-login")           //- Spring Handles it when login form submitted
                .defaultSuccessUrl("/login-success")        //- Once logged in, it will redirect to here
                .failureUrl("/login-error")                 //- When loggin failed, it will direct to here
                .and()
                .logout()
//                .logoutUrl("/logout")                     // default is "/logout"
                .logoutSuccessUrl("/");

        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)    //- Using Stateful session
//                .invalidSessionUrl("/invalidSession.html")              //- If tried with invalid session
                .maximumSessions(2)                                     //- Maximum concurrent session allowed
//                .expiredUrl("/sessionExpired.html")                     //- If tried with expired session
                ;
    }

    /**
     * To use UserDetailsService...
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    /**
     * To use the HttpSession...
     * @return
     */
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    /**
     * this method allows static resources to be neglected by spring security
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**","/webjars/**");
    }
}
