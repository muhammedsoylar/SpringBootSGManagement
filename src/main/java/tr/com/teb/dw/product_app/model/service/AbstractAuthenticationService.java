package tr.com.teb.dw.product_app.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class AbstractAuthenticationService implements EntityService
{
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void test()
    {
        passwordEncoder.encode("");
    }
}
