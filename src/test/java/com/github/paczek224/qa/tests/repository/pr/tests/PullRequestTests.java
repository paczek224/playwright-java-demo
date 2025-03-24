package com.github.paczek224.qa.tests.repository.pr.tests;

import com.github.paczek224.qa.infrastructure.BaseTest;
import com.github.paczek224.qa.infrastructure.configuration.ApplicationProperties;
import com.github.paczek224.qa.infrastructure.playwright.PlayWrightManager;
import com.github.paczek224.qa.tests.repository.RepositoryDetailsManager;
import com.github.paczek224.qa.tests.repository.pr.page.PullRequestDetailsPage;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Pattern;

import static com.github.paczek224.qa.infrastructure.configuration.ResourceLocks.THREAD_TWO;


@ResourceLock(THREAD_TWO)
public class PullRequestTests extends BaseTest {

    private RepositoryDetailsManager repositoryDetailsManager;

    @Autowired
    public PullRequestTests(PlayWrightManager playWrightManager, ApplicationProperties applicationProperties) {
        super(playWrightManager, applicationProperties);
    }

    @Override
    protected void initPageManagers(Page page) {
        repositoryDetailsManager = new RepositoryDetailsManager(page, getApplicationProperties());
    }

    @Test
    public void isAbleToCompareSelectedBranchesDuringPullRequestCreation() {

        String baseBranchName = "main";
        String selectedBranchName = "task";

        //given
        repositoryDetailsManager
                .repositoryDetailsPage()
                .goTo()
                .openBranchesView();

        //when
        repositoryDetailsManager
                .pullRequestDetailsPage()
                .goToAll();

        //then
        repositoryDetailsManager
                .assertPullRequestDetailsPage()
                .elementsCountEqualTo(PullRequestDetailsPage::getBranchRows, 3)
                .hasUrl(Pattern.compile(".*/branches/all$"));

        //when
        repositoryDetailsManager
                .pullRequestDetailsPage()
                .openBranchMenu(selectedBranchName)
                .newPullRequest();

        //then
        repositoryDetailsManager
                .assertPullRequestDetailsPage()
                .twoBranchesAreSelectedToPullRequest(baseBranchName, selectedBranchName);
    }

    @AfterEach
    protected void assertAll() {
        repositoryDetailsManager.assertAll();
    }
}
