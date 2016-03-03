package org.anttribe.vigor.infra.security;

import org.junit.Before;
import org.junit.Test;

public class DefaultPasswordServiceTest
{
    
    private DefaultPasswordService passwordService;
    
    @Test
    public void testEncryptPassword()
    {
        String encrypyPassword = passwordService.encryptPassword("123456");
        System.out.println(encrypyPassword);
    }
    
    @Test
    public void testPasswordsMatch()
    {
        String encrypted = "7c81506156b36f846bb616b7dd1734b6abd5a11cc3c03c6f";
        String plaintext = "123456";
        boolean r = passwordService.passwordsMatch(plaintext, encrypted);
        System.out.println(r);
    }
    
    @Before
    public void createPasswordService()
    {
        passwordService = new DefaultPasswordService();
        try
        {
            passwordService.setAlgorithmName("SHA1");
            passwordService.setIterations(256);
            passwordService.setGeneratePublicSalt(true);
            passwordService.setPublicSaltLength(4);
        }
        catch (AlgorithmNotSupportException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
