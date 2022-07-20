package com.opencommerce.shopbase.plusbase.pages;
import common.SBasePageObject;
import org.openqa.selenium.WebDriver;
public class OnboardingPage extends SBasePageObject {
    public OnboardingPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getSteps() {
        String xpath = "//div[@class='nextstep-box']//p[contains(@class,'title')]";
        String xpathOnboard = "//div[@class='onboarding-popup active']";
        if (!isElementExist(xpathOnboard)) {
            returnOnboardingSteps();
        }
        return getText(xpath);
    }

    public String getDescription() {
        String xpathOnboard = "//div[@class='onboarding-popup active']";
        if (!isElementExist(xpathOnboard)) {
            returnOnboardingSteps();
        }
        String xpath = "//div[@class='nextstep-box']//p[contains(@class,'desc ')]";
        return getText(xpath);
    }

    public String getPageCatalog() {
        String xpath = "//div[@class='plusbase-catalog']//ancestor::div[contains(@class,'sb-font')]//div";
        return getText(xpath);
    }

    public String getPageDomain() {
        String xpath = "//div[@class='page-domain']//h1[@class='fs-large']";
        return getText(xpath);
    }

    public String getPagePreference() {
        String xpath = "//div[@id='google-analytics-section']//h1";
        return getText(xpath);
    }

    public String getPageThemes() {
        String xpath = "//div[@class='page-themes']//h1[@class='title-bar__title']";
        return getText(xpath);
    }

    public String getPageAddProductFeed() {
        String xpath = "//div[contains(@class,'product-feed')]//h1";
        return getText(xpath);
    }

    public void returnOnboardingSteps() {
        String xpath = "//aside[@class='unite-ui-dashboard__aside']//p[contains(text(),'Get your shop ready')]";
        clickOnElement(xpath);
    }

    public void clickSkipThisStep() {
        String xpath = "//div[@class='onboarding-popup active']//a[text()='Skip this step']";
        if (!isElementExist(xpath)) {
            returnOnboardingSteps();
        }
        clickOnElement(xpath);
    }

    public boolean isShowOnboarding() {
        String xpath = "//div[@class='onboarding-popup active']";
        return isElementExist(xpath);
    }
}