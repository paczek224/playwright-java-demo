package com.github.paczek224.qa.tests.repository;

import com.github.paczek224.qa.infrastructure.configuration.ApplicationProperties;
import com.github.paczek224.qa.tests.repository.create.page.CreateRepositoryPage;
import com.github.paczek224.qa.tests.repository.create.page.CreateRepositoryPageAssertion;
import com.github.paczek224.qa.tests.repository.details.page.RepositoryDetailsPage;
import com.github.paczek224.qa.tests.repository.details.page.RepositoryDetailsPageAssertion;
import com.github.paczek224.qa.tests.repository.pr.page.PullRequestDetailsPage;
import com.github.paczek224.qa.tests.repository.pr.page.PullRequestDetailsPageAssertion;
import com.microsoft.playwright.Page;

public class RepositoryDetailsManager {

    private final RepositoryDetailsPage repositoryDetailsPage;
    private final RepositoryDetailsPageAssertion repositoryDetailsPageAssertion;
    private final CreateRepositoryPage createRepositoryDetailsPage;
    private final CreateRepositoryPageAssertion createRepositoryPageAssertion;
    private final PullRequestDetailsPage pullRequestDetailsPage;
    private final PullRequestDetailsPageAssertion pullRequestDetailsPageAssertion;

    public RepositoryDetailsManager(Page page, ApplicationProperties applicationProperties) {
        repositoryDetailsPage = new RepositoryDetailsPage(page, applicationProperties);
        repositoryDetailsPageAssertion = new RepositoryDetailsPageAssertion(repositoryDetailsPage);
        createRepositoryDetailsPage = new CreateRepositoryPage(page);
        createRepositoryPageAssertion = new CreateRepositoryPageAssertion(createRepositoryDetailsPage);
        pullRequestDetailsPage = new PullRequestDetailsPage(page);
        pullRequestDetailsPageAssertion = new PullRequestDetailsPageAssertion(pullRequestDetailsPage);
    }

    public RepositoryDetailsPage repositoryDetailsPage() {
        return repositoryDetailsPage;
    }

    public RepositoryDetailsPageAssertion assertDetailsPage() {
        return repositoryDetailsPageAssertion;
    }

    public CreateRepositoryPage createRepositoryPage() {
        return createRepositoryDetailsPage;
    }

    public CreateRepositoryPageAssertion assertCreateRepositoryPage() {
        return createRepositoryPageAssertion;
    }

    public PullRequestDetailsPage pullRequestDetailsPage() {
        return pullRequestDetailsPage;
    }

    public PullRequestDetailsPageAssertion assertPullRequestDetailsPage() {
        return pullRequestDetailsPageAssertion;
    }

    public void assertAll() {
        createRepositoryPageAssertion.assertAll();
        pullRequestDetailsPageAssertion.assertAll();
        repositoryDetailsPageAssertion.assertAll();
    }
}
