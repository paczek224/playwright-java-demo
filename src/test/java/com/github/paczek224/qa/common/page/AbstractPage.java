package com.github.paczek224.qa.common.page;

import com.microsoft.playwright.Page;
import lombok.Getter;

@Getter
public abstract class AbstractPage {

    public final Page page;

    protected AbstractPage(Page page) {
        this.page = page;
    }
}
