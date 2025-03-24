package com.github.paczek224.qa.tests.repository.details.tests;

import com.github.paczek224.qa.infrastructure.BaseTest;
import com.github.paczek224.qa.infrastructure.configuration.ApplicationProperties;
import com.github.paczek224.qa.infrastructure.playwright.PlayWrightManager;
import com.github.paczek224.qa.tests.repository.RepositoryDetailsManager;
import com.github.paczek224.qa.tests.repository.details.enums.CloneOption;
import com.github.paczek224.qa.tests.repository.details.page.RepositoryDetailsPage;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Pattern;

import static com.github.paczek224.qa.infrastructure.configuration.ResourceLocks.THREAD_TWO;

@ResourceLock(THREAD_TWO)
public class RepositoryDetailsTests extends BaseTest {

    private RepositoryDetailsManager repositoryDetailsManager;

    @Autowired
    public RepositoryDetailsTests(PlayWrightManager playWrightManager, ApplicationProperties applicationProperties) {
        super(playWrightManager, applicationProperties);
    }

    @Override
    protected void initPageManagers(Page page) {
        repositoryDetailsManager = new RepositoryDetailsManager(page, getApplicationProperties());
    }

    @Test
    public void isAbleToSwitchBranches() {

        String branchName = "develop";

        //given
        repositoryDetailsManager
                .repositoryDetailsPage()
                .goTo();

        //when
        repositoryDetailsManager
                .repositoryDetailsPage()
                .openBranchPicker()
                .setBranch(branchName);

        //then
        repositoryDetailsManager
                .assertDetailsPage()
                .elementHasText(RepositoryDetailsPage::getBranchPicker, branchName)
                .hasUrl(Pattern.compile(".*/develop$"));
    }

    @Test
    public void userCanCloneRepositoryByHTTPSBySSHByGithubCli() {

        //given
        repositoryDetailsManager
                .repositoryDetailsPage()
                .goTo();

        //when
        repositoryDetailsManager
                .repositoryDetailsPage()
                .openCodeButton()
                .openTab(CloneOption.HTTPS);

        //then
        String httpsUrl = String.format("https://github.com/%s.git", getApplicationProperties().repoName);
        repositoryDetailsManager
                .assertDetailsPage()
                .elementHasValue(RepositoryDetailsPage::getCloneInput, httpsUrl);

        //when
        repositoryDetailsManager
                .repositoryDetailsPage()
                .openTab(CloneOption.SSH);

        //then
        String sshUrl = String.format("git@github.com:%s.git", getApplicationProperties().repoName);
        repositoryDetailsManager
                .assertDetailsPage()
                .elementHasValue(RepositoryDetailsPage::getCloneInput, sshUrl);

        //when
        repositoryDetailsManager
                .repositoryDetailsPage()
                .openTab(CloneOption.GITHUB_CLI);

        //then
        String cliValue = String.format("gh repo clone %s", getApplicationProperties().repoName);
        repositoryDetailsManager
                .assertDetailsPage()
                .elementHasValue(RepositoryDetailsPage::getCloneInput, cliValue);
    }

    @AfterEach
    protected void assertAll() {
        repositoryDetailsManager.assertAll();
    }
}
