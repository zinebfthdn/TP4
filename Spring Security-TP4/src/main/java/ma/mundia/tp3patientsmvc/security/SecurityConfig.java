package ma.mundia.tp3patientsmvc.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //Elle est utilisée pour définir des beans et configurer des composants Spring
@EnableWebSecurity //active la configuration de Spring Security dans votre application.
@EnableGlobalMethodSecurity(prePostEnabled = true) //active la sécurité au niveau des méthodes dans votre application.
public class SecurityConfig {
    @Autowired //d'injecter automatiquement des dépendances dans une classe.
    private PasswordEncoder passwordEncoder;

    /*
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
                return null;
            }
        }
    }
    */

//Spring utilise un système de Bean. Un Bean est un objet qui est instancié,
// assemblé et géré par Spring IoC Container.
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        //est une classe de Spring Security qui permet de gérer des utilisateurs
        // directement en mémoire, sans nécessiter de base de données
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(passwordEncoder.encode("password123")).roles("USER").build(),
                User.withUsername("user2").password(passwordEncoder.encode("password123")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder.encode("12345")).roles("USER","ADMIN").build()

        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin().loginPage("/login").permitAll();
        httpSecurity.rememberMe();
        httpSecurity.authorizeHttpRequests().requestMatchers("/webjars/**", "/h2-console/**").permitAll();
        //httpSecurity.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER");
        //httpSecurity.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN"); //toutes les rqt qui ont admin/ala chose a le role d'admin
        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
        httpSecurity.exceptionHandling().accessDeniedPage("/notAuthorized");
        return httpSecurity.build();
    }
}

