package com.github.paczek224.qa.tests.repository.pr.page;

import com.github.paczek224.qa.infrastructure.assertions.BaseAssertions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class PullRequestDetailsPageAssertion extends BaseAssertions<PullRequestDetailsPage> {

    public PullRequestDetailsPageAssertion(PullRequestDetailsPage page) {
        super(page);
    }

    public PullRequestDetailsPageAssertion twoBranchesAreSelectedToPullRequest(String base, String selected) {
        softAssert(() -> PlaywrightAssertions.assertThat(page.getBaseBranch()).hasText(base));
        softAssert(() -> PlaywrightAssertions.assertThat(page.getSelectedBranch()).hasText(selected));
        return this;
    }
    //more business assertions
}
