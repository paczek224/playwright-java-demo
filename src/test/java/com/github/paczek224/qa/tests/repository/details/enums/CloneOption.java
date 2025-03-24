package com.github.paczek224.qa.tests.repository.details.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CloneOption {
    HTTPS("HTTPS"),
    SSH("SSH"),
    GITHUB_CLI("GitHub CLI");

    private final String label;

}
