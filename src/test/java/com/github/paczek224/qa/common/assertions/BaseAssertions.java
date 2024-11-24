package com.github.paczek224.qa.common.assertions;

import com.github.paczek224.qa.common.page.AbstractTestPage;
import org.assertj.core.api.SoftAssertions;

public class BaseAssertions {

    private final AbstractTestPage page;
    protected final SoftAssertions softly = new SoftAssertions();

    public BaseAssertions(AbstractTestPage page) {
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
}
