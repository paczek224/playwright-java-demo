package com.github.paczek224.qa.tests.repository.search.tests;

import com.github.paczek224.qa.infrastructure.BaseTest;
import com.github.paczek224.qa.infrastructure.configuration.ApplicationProperties;
import com.github.paczek224.qa.infrastructure.playwright.PlayWrightManager;
import com.github.paczek224.qa.tests.repository.RepositoryDetailsManager;
import com.github.paczek224.qa.tests.repository.details.page.RepositoryDetailsPage;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.paczek224.qa.infrastructure.configuration.ResourceLocks.THREAD_ONE;

@ResourceLock(THREAD_ONE)
public class RepositorySearchTests extends BaseTest {

    private RepositoryDetailsManager repositoryDetailsManager;

    @Autowired
    public RepositorySearchTests(PlayWrightManager playWrightManager, ApplicationProperties applicationProperties) {
        super(playWrightManager, applicationProperties);
    }

    @Override
    protected void initPageManagers(Page page) {
        repositoryDetailsManager = new RepositoryDetailsManager(page, getApplicationProperties());
    }

    @Test
    public void isAbleToFindRepositoryFileUsingSearch() {

        String repositoryName = String.format("repo:%s", getApplicationProperties().repoName);

        //given
        repositoryDetailsManager
                .repositoryDetailsPage()
                .goTo();

        //when
        String file = "README.md";
        repositoryDetailsManager
                .repositoryDetailsPage()
                .searchFor(repositoryName + " " + file)
                .openSearchResult(file);

        //then
        repositoryDetailsManager
                .assertDetailsPage()
                .elementHasText(RepositoryDetailsPage::getBreadCrumbId, file);
    }

    @AfterEach
    protected void assertAll() {
        repositoryDetailsManager.assertAll();
    }
}
