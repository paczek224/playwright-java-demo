package com.github.paczek224.qa.infrastructure.assertions;

import com.github.paczek224.qa.infrastructure.page.AbstractTestPage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.assertj.core.api.SoftAssertions;

import java.util.function.Function;
import java.util.regex.Pattern;

public class BaseAssertions<T extends AbstractTestPage> {

    public final T page;
    protected final SoftAssertions softly = new SoftAssertions();

    public BaseAssertions(T page) {
        this.page = page;
    }

    protected void softAssert(Runnable assertion) {
        try {
            assertion.run();
        } catch (AssertionError e) {
            softly.fail(e.getMessage());
        }
    }

    public void assertAll() {
        softly.assertAll();
    }

    public BaseAssertions<T> elementIsVisible(Function<T, Locator> getElement) {
        Locator locator = getElement.apply(page);
        softAssert(()-> PlaywrightAssertions.assertThat(locator).isVisible());
        return this;
    }

    public BaseAssertions<T> elementHasText(Function<T, Locator> getElement, String expectedText) {
        Locator locator = getElement.apply(page);
        softAssert(()-> PlaywrightAssertions.assertThat(locator).hasText(expectedText));
        return this;
    }

    public BaseAssertions<T> elementHasValue(Function<T, Locator> getElement, String expectedText) {
        Locator locator = getElement.apply(page);
        softAssert(()-> PlaywrightAssertions.assertThat(locator).hasValue(expectedText));
        return this;
    }

    public BaseAssertions<T> elementsCountEqualTo(Function<T, Locator> getElement, int expected) {
        Locator locator = getElement.apply(page);
        softAssert(()-> PlaywrightAssertions.assertThat(locator).hasCount(expected));
        return this;
    }

    public BaseAssertions<T> elementWithTextIsVisible(AriaRole role, String expectedText) {
        softAssert(()-> PlaywrightAssertions.assertThat(page.getElementByText(role, expectedText)).isVisible());
        return this;
    }

    public BaseAssertions<T> hasUrl(Pattern pattern) {
        softAssert(()-> PlaywrightAssertions.assertThat(page.page).hasURL(pattern));
        return this;
    }
}
