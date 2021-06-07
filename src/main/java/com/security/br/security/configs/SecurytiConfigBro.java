package com.security.br.security.configs;

import com.security.br.security.services.ServiceBr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurytiConfigBro extends WebSecurityConfigurerAdapter {

    @Autowired
    private ServiceBr serviceBr;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/bro/**").authenticated()
                .antMatchers("/ti/**").hasRole("ADMIN")
                .antMatchers("/read_profile/**").hasAuthority("BRO")
                .and()
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/");
    }

    //Memory
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.builder()
//                .username("USER")
//                //ti
//                .password("{bcrypt}$2y$12$ra/aauL2jG8OuWHpHViufuXgBn0lnOdT1Q4krouzRgIdiX9qFrnYK")
//                .roles("USER")
//                .build();
//        UserDetails bro = User.builder()
//                .username("BRO")
//                //ti
//                .password("{bcrypt}$2y$12$ra/aauL2jG8OuWHpHViufuXgBn0lnOdT1Q4krouzRgIdiX9qFrnYK")
//                .roles("BRO,ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, bro);
//    }

    //JDBC Simple
//
//    @Bean
//    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
//        UserDetails user = User.builder()
//                .username("USER")
//                //ti
//                .password("{bcrypt}$2y$12$ra/aauL2jG8OuWHpHViufuXgBn0lnOdT1Q4krouzRgIdiX9qFrnYK")
//                .roles("USER")
//                .build();
//        UserDetails bro = User.builder()
//                .username("BRO")
//                //ti
//                .password("{bcrypt}$2y$12$ra/aauL2jG8OuWHpHViufuXgBn0lnOdT1Q4krouzRgIdiX9qFrnYK")
//                .roles("BRO","ADMIN")
//                .build();
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//
//        if (jdbcUserDetailsManager.userExists(user.getUsername())){
//            jdbcUserDetailsManager.deleteUser(user.getUsername());
//        }
//        if (jdbcUserDetailsManager.userExists(bro.getUsername())){
//            jdbcUserDetailsManager.deleteUser(bro.getUsername());
//        }
//
//        jdbcUserDetailsManager.createUser(user);
//        jdbcUserDetailsManager.createUser(bro);
//
//
//        return jdbcUserDetailsManager;
//    }

@Bean
public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
}



@Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider  daoAuthenticationProvider =new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(serviceBr);

        return daoAuthenticationProvider;
}



}


