package com.italo.waiter.utils;

import com.italo.waiter.utils.security.CryptoUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CryptoUtilsTest {
    @Test
    public void shouldEncryptAndDecryptOneMessage(){
        String encrypt = CryptoUtils.encrypt("my secret message", "mypassword");
        String decrypt = CryptoUtils.decrypt(encrypt, "mypassword");
        Assert.assertEquals("my secret message", decrypt);
    }

    @Test
    public void shouldProduceDifferentEncryptedPayloadsOnEachCall(){
        String encrypt1 = CryptoUtils.encrypt("my secret message", "mypassword");
        String encrypt2 = CryptoUtils.encrypt("my secret message", "mypassword");
        Assert.assertNotEquals(encrypt1, encrypt2);
    }

    @Test
    public void shouldFailOnWrongPassword(){
        String encrypt = CryptoUtils.encrypt("my secret message", "rightpassword");
        try{
            CryptoUtils.decrypt(encrypt, "wrongpassword");
            Assert.fail("Should throw exception");
        }catch (Exception e){
            Assert.assertTrue(e instanceof IllegalStateException);
        }
    }
}
