package com.opencommerce.shopbase.dashboard.risk_level.pages;

import common.SBasePageObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class RiskLevelPages extends SBasePageObject {
    public RiskLevelPages(WebDriver driver) {
        super(driver);
    }

    public void verifyRiskLevel(String riskLevel, String level) {
        assertThat(level).isEqualTo(riskLevel);
    }
}
