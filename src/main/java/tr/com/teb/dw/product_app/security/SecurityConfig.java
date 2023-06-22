package tr.com.teb.dw.product_app.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import tr.com.teb.dw.product_app.utility.Util;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

    @Value("${service.security.secure-key-password}")
    private CharSequence secureKeyPassword;

    @Value("${service.security.secure-key-username}")
    private CharSequence secureKeyUsername;

    @Bean
    public PasswordEncoder createPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    /*
        authentication (kimlik doğrulama) ile ilgili
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
    {
        try
        {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            // https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/in-memory.html
            // https://www.baeldung.com/spring-security-5-default-password-encoder
            auth.inMemoryAuthentication()
                    .passwordEncoder(passwordEncoder)
                    .withUser((String) secureKeyUsername)
                    .password(passwordEncoder.encode(secureKeyPassword))
                    .roles("USER");
        }
        catch (Exception e)
        {
            Util.showGeneralExceptionInfo(e);
        }
    }

    /*
        authorization (yetkilendirme) ile ilgili
    */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception
    {
        super.configure(httpSecurity);

        httpSecurity.csrf().disable();
    }
}
