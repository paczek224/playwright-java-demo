package com.github.paczek224.qa.infrastructure.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class AbstractTestPage extends AbstractPage {

    protected AbstractTestPage(Page page) {
        super(page);
    }

    public Locator getElementByText(AriaRole role, String text) {
        return page.getByRole(role).filter(new Locator.FilterOptions().setHasText(text));
    }

    public AbstractTestPage clickCreateNewButton() {
        page.locator("#global-create-menu-anchor").click();
        return this;
    }

    public AbstractTestPage clickCreateRepository() {
        page.locator("//ul[@role='menu']//li[normalize-space()='New repository']").click();
        return this;
    }

    public AbstractTestPage searchFor(String text) {
        page.locator("#qb-input-query").click();
        Locator searchInput = page.locator("#query-builder-test");
        searchInput.clear();
        searchInput.pressSequentially(text);
        return this;
    }

    public AbstractTestPage openSearchResult(String text) {
        page.locator(String.format("//li//a[.//span[normalize-space()='%s']]", text)).click();
        return this;
    }

}
