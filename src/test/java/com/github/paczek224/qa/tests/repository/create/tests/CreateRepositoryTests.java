package com.github.paczek224.qa.tests.repository.create.tests;

import com.github.paczek224.qa.infrastructure.BaseTest;
import com.github.paczek224.qa.infrastructure.configuration.ApplicationProperties;
import com.github.paczek224.qa.infrastructure.playwright.PlayWrightManager;
import com.github.paczek224.qa.tests.repository.RepositoryDetailsManager;
import com.github.paczek224.qa.tests.repository.create.page.CreateRepositoryPage;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.paczek224.qa.infrastructure.configuration.ResourceLocks.THREAD_ONE;

@ResourceLock(THREAD_ONE)
public class CreateRepositoryTests extends BaseTest {

    private RepositoryDetailsManager repositoryDetailsManager;

    @Autowired
    public CreateRepositoryTests(PlayWrightManager playWrightManager, ApplicationProperties applicationProperties) {
        super(playWrightManager, applicationProperties);
    }

    @Override
    protected void initPageManagers(Page page) {
        repositoryDetailsManager = new RepositoryDetailsManager(page, getApplicationProperties());
    }

    @Test
    public void isAbleToDisplayCreateRepositoryForm() {

        //given
        repositoryDetailsManager
                .repositoryDetailsPage()
                .goTo();

        //when
        repositoryDetailsManager
                .repositoryDetailsPage()
                .clickCreateNewButton()
                .clickCreateRepository();

        //then
        repositoryDetailsManager
                .assertCreateRepositoryPage()
                .elementHasValue(CreateRepositoryPage::getRepositoryNameInput, "")
                .elementHasValue(CreateRepositoryPage::getDescriptionInput, "")
                .elementIsVisible(CreateRepositoryPage::getCreateRepositorySubmitButton);
    }

    @Test
    public void cannotCreateRepositoryWithoutName() {

        //given
        repositoryDetailsManager
                .repositoryDetailsPage()
                .goTo()
                .clickCreateNewButton()
                .clickCreateRepository();

        //when
        repositoryDetailsManager
                .createRepositoryPage()
                .submit();

        //then
        repositoryDetailsManager
                .assertCreateRepositoryPage()
                .elementHasText(CreateRepositoryPage::getRepositoryNameInputMessage, "New repository name must not be blank");
    }

    @AfterEach
    protected void assertAll() {
        repositoryDetailsManager.assertAll();
    }
}
