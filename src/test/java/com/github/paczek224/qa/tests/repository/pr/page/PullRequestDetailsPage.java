package com.github.paczek224.qa.tests.repository.pr.page;

import com.github.paczek224.qa.infrastructure.page.AbstractTestPage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.Getter;

@Getter
public class PullRequestDetailsPage extends AbstractTestPage {

    private final Locator baseBranch;
    private final Locator selectedBranch;
    private final Locator branchRows;

    public PullRequestDetailsPage(Page page) {
        super(page);

        branchRows = page.locator("table tbody tr");
        baseBranch = page.locator("#base-ref-selector span[data-menu-button]");
        selectedBranch = page.locator("#head-ref-selector span[data-menu-button]");
    }

    public PullRequestDetailsPage openBranchMenu(String branchName) {
        page.locator(String.format("//tr[.//a[normalize-space()='%s']]//button[@name='Branch menu']", branchName)).click();
        return this;
    }

    public PullRequestDetailsPage newPullRequest() {
        page.locator("//ul//li[.//*[normalize-space()='New pull request']]").click();
        return this;
    }

    public PullRequestDetailsPage goToAll() {
        page.getByRole(AriaRole.TAB).filter(new Locator.FilterOptions().setHasText("All")).click();
        return this;
    }
}
