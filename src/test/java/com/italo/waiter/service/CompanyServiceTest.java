package com.italo.waiter.service;

import com.italo.waiter.model.Company;
import com.italo.waiter.model.SystemUser;
import com.italo.waiter.repository.CompanyRepository;
import com.italo.waiter.repository.SystemUserRepository;
import com.italo.waiter.service.builder.CompanyServiceTestBuilder;
import com.italo.waiter.service.builder.SystemUserTestBuilder;
import com.italo.waiter.utils.enums.ErrorMessage;
import com.italo.waiter.utils.exceptions.ConflictException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private SystemUserRepository systemUserRepository;

    @InjectMocks
    private CompanyService companyService;

    @Test
    public void shouldCreateSystemUserToCompany(){
        Company company = CompanyServiceTestBuilder.generateCompany();
        SystemUser systemUser = SystemUserTestBuilder.generateSystemUser();
        company.getSystemUsers().add(systemUser);
        Mockito.when(companyRepository.save(any())).thenReturn(company);
        Mockito.when(systemUserRepository.save(any())).thenReturn(systemUser);
        Company savedCompany = companyService.saveCompany(company);
        Assert.assertEquals(savedCompany.getSystemUsers().get(0), systemUser);
    }

    @Test
    public void shouldThrowExceptionOnCompanyWithoutSystemUser(){
        Company company = CompanyServiceTestBuilder.generateCompany();
        Mockito.when(companyRepository.save(any())).thenReturn(company);
        try{
            companyService.saveCompany(company);
            Assert.fail();
        }catch (Exception e){
            Assert.assertEquals(e.getMessage(), ErrorMessage.COMPANY_WITH_NO_USERS.getFormattedMessage());
        }
    }

    @Test
    public void shouldThrowExceptionIfSystemUserExistsOnNewCompany(){
        SystemUser existingUser = SystemUserTestBuilder.generateSystemUser();
        Company company = CompanyServiceTestBuilder.generateCompany();
        company.getSystemUsers().add(existingUser);
        Mockito.when(systemUserRepository.findByUsername(any())).thenReturn(Optional.of(existingUser));
        try{
            companyService.saveCompany(company);
            Assert.fail();
        }catch (Exception e){
            Assert.assertEquals(e.getMessage(), ErrorMessage.COMPANY_SYSTEM_USER_EXISTS.getFormattedMessage());
        }
    }
}
