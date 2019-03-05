package ir.vira.travelagency.configuration;

import ir.vira.travelagency.model.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppSecurity extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AppSecurity.class);

    private AccountService accountService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    private AuthenticationHandler authenticationHandler;
//
//    public AppSecurity(AccountService accountService, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationHandler authenticationHandler) {
//        this.accountService = accountService;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//        this.authenticationHandler = authenticationHandler;
//    }


    public AppSecurity(AccountService accountService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.accountService = accountService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/javax.faces.resource/**", "/", "/index", "/login", "/jsf/login.xhtml", "/logout", "/error", "/resetPassword/*").permitAll()
//                .anyRequest().authenticated()
//                .antMatchers("**/*.xhtml").denyAll()
//, "/jsf/login.xhtml"
                .and()

                .formLogin()
                .loginPage("/login")
//                .loginProcessingUrl("/loginAction")
//                .successHandler(authenticationHandler)
//                .failureHandler(authenticationHandler)
//                .defaultSuccessUrl("/home")
                .permitAll()

                .and()

//                .exceptionHandling()
//                .accessDeniedPage("/403")

//                .and()

                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()

                .and()

                .csrf().disable();

//        http.authorizeRequests().antMatchers("/home/**/*").hasRole("USER");
//        http.authorizeRequests().antMatchers("/admin/**/*").hasRole("ADMINISTRATOR");
    }


}
