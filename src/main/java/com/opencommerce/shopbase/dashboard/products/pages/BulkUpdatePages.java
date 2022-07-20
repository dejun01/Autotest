package com.opencommerce.shopbase.dashboard.products.pages;

import common.SBasePageObject;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.assertj.core.api.Assertions.assertThat;

public class BulkUpdatePages extends SBasePageObject {
    public int MAX_RETRY_TIME = 10;

    public BulkUpdatePages(WebDriver driver) {
        super(driver);
    }

    public void selectDisjunctiveProduct(String disjunctiveProductFilter) {
        selectRadioButtonWithLabel(disjunctiveProductFilter, 1, true);
    }

    public void retrySelectDdlByXpath(String xpath, String value, int currentRetryTime) {
        try {
            Select ddl = new Select(getDriver().findElement(By.xpath(xpath)));
            ddl.selectByVisibleText(value);
            String actualValue = getSelectedValue(xpath);
            assertThat(actualValue).isEqualTo(value);
        } catch (Throwable t) {
            if (currentRetryTime < MAX_RETRY_TIME) {
                retrySelectDdlByXpath(xpath, value, currentRetryTime + 1);
            } else {
                System.out.println("Reached " + currentRetryTime + " times retried, but it is still failed");
            }
        }
    }

    public void selectFieldProduct(String fieldProductFilter, int index) {
        String xpath = "(//div[@class='card__section' and contains(.,'Products must match:')]//div[@class='s-select s-flex--fill']//select)[" + index + "]";
        waitElementToBeVisible(xpath);
        retrySelectDdlByXpath(xpath, fieldProductFilter, 0);
    }

    public void selectComparatorProduct(String comparatorProductFilter, int index) {
        String xpath = "(//div[@class='card__section' and contains(.,'Products must match:')]//div[@class='s-select condition-select s-flex--fill']//select)[" + index + "]";
        waitElementToBeVisible(xpath);
        retrySelectDdlByXpath(xpath, comparatorProductFilter, 0);
    }

    public void selectValueProduct(String valueProductFilter, int index) {
        String xpath = "(//div[@class='card__section' and contains(.,'Products must match:')]//div[@class='s-flex--fill s-input']//input)[" + index + "]";
        waitElementToBeVisible(xpath);
        waitClearAndType(xpath, valueProductFilter);
    }

    public void selectDisjunctiveVariant(String disjunctiveVariantFilter) {
        selectRadioButtonWithLabel(disjunctiveVariantFilter, 2, true);
    }

    public void selectFieldVariant(String fieldVariantFilter, int index) {
        String xpath = "(//div[@class='card__section' and contains(.,'Variants must match:')]//div[@class='s-select s-flex--fill']//select)[" + index + "]";
        waitElementToBeVisible(xpath);
        retrySelectDdlByXpath(xpath, fieldVariantFilter, 0);
    }

    public void selectComparatorVariant(String comparatorVariantFilter, int index) {
        String xpath = "(//div[@class='card__section' and contains(.,'Variants must match:')]//div[@class='s-select condition-select s-flex--fill']//select)[" + index + "]";
        waitElementToBeVisible(xpath);
        retrySelectDdlByXpath(xpath, comparatorVariantFilter, 0);
    }

    public void selectValueVariant(String valueVariantFilter, int index) {
        String xpath = "(//div[@class='card__section' and contains(.,'Variants must match:')]//div[@class='s-flex--fill s-input']//input)[" + index + "]";
        waitElementToBeVisible(xpath);
        waitClearAndType(xpath, valueVariantFilter);
    }

    public void selectAction(String action) {
        String xpath = "//div[@class='card__section' and contains(.,'How do you want to update the product?')]//div[@class='s-select condition-select s-flex--fill']//select";
        waitElementToBeVisible(xpath);
        retrySelectDdlByXpath(xpath, action, 0);
    }

    public void changeProductDescription(String value) {
        String xpath = "//div[@role='presentation' and contains(@class, 'redactor-styles')]";
        waitClearAndType(xpath, value);

    }

    public void clickPreviewBulkUpdate() {
        waitABit(2000);
        clickBtn("Preview Bulk Update");
        String xpath = "//h2[text()='Preview Bulk Update']";
        waitElementToBeVisible(xpath, 20);
    }

    public void verifyPreviewBulkUpdateProductOnPopupPreview(String disjunctiveProductFilter, String fieldProductFilter, String comparatorProductFilter, String valueProductFilter) {
        String xpathDisjunctiveProduct = "//section[@class='preview-filter']//p[1]";
        String xpathConditionProduct = "(//section[@class='preview-filter']//li)[1]";
        String disjunctiveProductExpect = "";

        switch (disjunctiveProductFilter) {
            case "All conditions":
                disjunctiveProductExpect = "ALL";
                break;
            case "Any condition":
                disjunctiveProductExpect = "ANY";
                break;
            default:
                fail();
        }

        waitUntilElementVisible(xpathDisjunctiveProduct, 15);
        assertThat(getText(xpathDisjunctiveProduct)).isEqualTo("Products that match " + disjunctiveProductExpect + " below conditions:");

        switch (fieldProductFilter) {
            case "Product price":
            case "Compare at price":
                assertThat(getText(xpathConditionProduct)).isEqualTo(fieldProductFilter + " " + comparatorProductFilter + " " + "\"" + valueProductFilter + " USD\"");
                break;
            default:
                assertThat(getText(xpathConditionProduct)).isEqualTo(fieldProductFilter + " " + comparatorProductFilter + " " + "\"" + valueProductFilter + "\"");
        }
    }

    public void verifyPreviewBulkUpdateVariantOnPopupPreview(String disjunctiveVariantFilter, String
            fieldVariantFilter, String comparatorVariantFilter, String valueVariantFilter) {
        String xpathDisjunctiveVariant = "//section[@class='preview-filter']//p[2]";
        String xpathConditionVariant = "(//section[@class='preview-filter']//li)[2]";
        String disjunctiveVariantExpect = "";

        switch (disjunctiveVariantFilter) {
            case "All conditions":
                disjunctiveVariantExpect = "ALL";
                break;
            case "Any condition":
                disjunctiveVariantExpect = "ANY";
                break;
            default:
                fail();
        }

        assertThat(getText(xpathDisjunctiveVariant)).isEqualTo("Only variants that match that match " + disjunctiveVariantExpect + " below conditions:");

        switch (fieldVariantFilter) {
            case "Variant price":
            case "Variant compare-at-price":
                assertThat(getText(xpathConditionVariant)).isEqualTo(fieldVariantFilter + " " + comparatorVariantFilter + " " + "\"" + valueVariantFilter + " USD\"");
                break;
            default:
                assertThat(getText(xpathConditionVariant)).isEqualTo(fieldVariantFilter + " " + comparatorVariantFilter + " " + "\"" + valueVariantFilter + "\"");
        }
    }

    public void verifyPreviewBulkUpdateActionOnPopupPreview(String actionSelected, String valueAction) {
        String xpathAction = "//section[@class='preview-action']//li";

        switch (actionSelected) {
            case "Change price to":
            case "Increase price by amount":
            case "Decrease price by amount":
            case "Change compare-at-price to":
            case "Round/ Beautify price":
                assertThat(getText(xpathAction)).isEqualTo((actionSelected + " " + valueAction + " USD").trim());
                break;
            case "Decrease compare-at-price by percentage":
                assertThat(getText(xpathAction)).isEqualTo((actionSelected + " " + valueAction + "%").trim());
                break;
            case "Delete custom options":
                assertThat(getText(xpathAction)).isEqualTo((actionSelected + " which their option name " + valueAction.split(">")[0].trim() + " '" + valueAction.split(">")[1].trim() + "'").trim());
                break;
            default:
                assertThat(getText(xpathAction)).isEqualTo((actionSelected + " " + valueAction).trim());
        }
    }

    public void verifyMessage(String message) {
        waitForTextToAppear(message, 10000);
        waitABit(5000);
    }

    public void verifyPreviewBulkUpdateProductOnBulkUpdateList(String disjunctiveProductFilter, String fieldProductFilter, String comparatorProductFilter, String valueProductFilter) {
        String xpathDisjunctive = "//table[@class='table table-hover']//tr[contains(.,'Just now')]/td[3]";

        switch (fieldProductFilter) {
            case "Product price":
            case "Compare at price":
                assertThat(getText(xpathDisjunctive)).isEqualTo(fieldProductFilter + " " + comparatorProductFilter + " " + "\"" + valueProductFilter + " USD\"");
                break;
            default:
                assertThat(getText(xpathDisjunctive)).isEqualTo(fieldProductFilter + " " + comparatorProductFilter + " " + "\"" + valueProductFilter + "\"");
        }
    }

    public void verifyPreviewBulkUpdateVariantOnBulkUpdateList(String bulkUpdateFor, String disjunctiveVariantFilter, String fieldVariantFilter, String comparatorVariantFilter, String valueVariantFilter) {
        String xpathUpdateFor = "//table[@class='table table-hover']//tr[contains(.,'Just now')]/td[4]";

        if (!"some variants".equals(bulkUpdateFor)) {
            assertThat(getText(xpathUpdateFor)).isEqualTo("All variants");
        } else {
            switch (fieldVariantFilter) {
                case "Variant price":
                case "Variant compare-at-price":
                    assertThat(getText(xpathUpdateFor)).isEqualTo(fieldVariantFilter + " " + comparatorVariantFilter + " " + "\"" + valueVariantFilter + " USD\"");
                    break;
                default:
                    assertThat(getText(xpathUpdateFor)).isEqualTo(fieldVariantFilter + " " + comparatorVariantFilter + " " + "\"" + valueVariantFilter + "\"");
            }
        }
    }

    public void verifyPreviewBulkUpdateActionOnBulkUpdateList(String actionSelected, String valueAction) {
        int indexAction = getIndexOfColumn("Action");
        String xpathAction = "//table[@class='table table-hover']//tr[contains(.,'Just now')]/td[" + indexAction + "]";
        int indexCreatedAt = getIndexOfColumn("Created at");
        String xpathTime = "//table[@class='table table-hover']//tr[contains(.,'Just now')]/td[" + indexCreatedAt + "]";

        switch (actionSelected) {
            case "Change price to":
            case "Increase price by amount":
            case "Decrease price by amount":
            case "Change compare-at-price to":
            case "Round/ Beautify price":
                assertThat(getText(xpathAction)).isEqualTo((actionSelected + " " + valueAction).trim() + " USD");
                break;
            case "Decrease compare-at-price by percentage":
                assertThat(getText(xpathAction)).isEqualTo((actionSelected + " " + valueAction).trim() + "%");
                break;
            case "Delete custom options":
                assertThat(getText(xpathAction)).isEqualTo((actionSelected + " which their option name " + valueAction.split(">")[0].trim() + " '" + valueAction.split(">")[1].trim() + "'").trim());
                break;
            default:
                assertThat(getText(xpathAction)).isEqualTo((actionSelected + " " + valueAction).trim());
        }

        refreshPage();
        assertThat(getText(xpathTime)).isEqualTo("Just now");


        String xpathProcessing = "(//table[@class='table table-hover']//tbody//tr)[1]//span[@data-label='Processing']";
        String xpathFinished = "(//table[@class='table table-hover']//tbody//tr)[1]//span[@data-label='Finished']";

        while (isElementVisible(xpathProcessing, 2)) {
            waitABit(5000);
            refreshPage();
            waitForEverythingComplete();
            try {
                waitElementToBeVisible(xpathProcessing);
            } catch (Exception e) {
                waitElementToBeVisible(xpathFinished);
            }
        }
        verifyElementVisible(xpathProcessing, false);
        verifyElementVisible(xpathFinished, true);
    }


    public void changeProductVendor(String valueAction) {
        String xpath = "//input[@type='text' and @placeholder=\"Nikola's Electrical Supplies\"]";
        waitClearAndType(xpath, valueAction);
    }

    public void replaceDecription(String valueOld, String valueUpdate) {
        String xpathFind = "//span[normalize-space()='Find']//following-sibling::div//input";
        String xpathReplace= "//span[normalize-space()='and replace with']//following-sibling::div//input";
        waitClearAndType(xpathFind, valueOld);
        waitClearAndType(xpathReplace, valueUpdate);
    }

    public void changeProductType(String valueOld, String valueUpdate) {
        String xpathOld = "//input[@type='text' and @placeholder='Product type']";
        String xpathNew= "//input[@type='text' and @placeholder='New product type']";
        waitClearAndType(xpathOld, valueOld);
        waitClearAndType(xpathNew, valueUpdate);
    }

    public void deleteVariant(String value) {
        String xpathOld = "//input[@type='text' and @placeholder='Option value']";
        waitClearAndType(xpathOld, value);
    }

    public void addTag(String valueAction, int currentRetryTime) {
        String xpathInput = "//input[@type='text' and @placeholder='reviewed, packed, delivered']";
        waitElementToBeVisible(xpathInput);
        waitForElementFinishRendering(xpathInput);
        XH(xpathInput).clear();
        inputSlowly(xpathInput, valueAction);
        XH(xpathInput).sendKeys(Keys.TAB);
        XH(xpathInput).sendKeys(Keys.ENTER);

        try {
            verifyTag(valueAction);
        } catch (Throwable t) {
            String xpath = "//div[contains(@class, 'tag-list-items tag-list-items--has-tooltip')]//a[@role='button']";

            while (isElementVisible(xpath, 2)) {
                clickOnElement(xpath);
            }

            if (currentRetryTime < MAX_RETRY_TIME) {
                addTag(valueAction, currentRetryTime + 1);
            } else {
                System.out.println("Reached " + currentRetryTime + " times retried, but it is still failed");
            }
        }
    }

    public void verifyTag(String tag) {
        String xpathTag = "";
        String[] tagList = tag.split(",");
        for (int i = 0; i < tagList.length; i++) {
            xpathTag = "//div[contains(@class, 'tag-list-items')]//span[text()[normalize-space()='" + tagList[i].trim() + "']]";
            waitElementToBeVisible(xpathTag);
            verifyElementVisible(xpathTag, true);
        }
    }

    public void changePriceTo(String valueAction) {
        String xpath = "//div[normalize-space()='USD']//following-sibling::input";
        waitClearAndType(xpath, valueAction);
    }

    public void increasePriceByAmount(String valueAction) {
        String xpath = "//div[normalize-space()='USD']//following-sibling::input";
        waitClearAndType(xpath, valueAction);
    }

    public void decreasePriceByAmount(String valueAction) {
        String xpath = "//div[normalize-space()='USD']//following-sibling::input";
        waitClearAndType(xpath, valueAction);
    }

    public void selectOnlyUpdateSomeVariants() {
        String xpathInput = "//span[normalize-space()='Only update some variants']//preceding-sibling::input";
        String xpathSpan = "//span[normalize-space()='Only update some variants']//preceding-sibling::span";
        verifyCheckedThenClick(xpathInput, xpathSpan, true);
    }

    public void changeCompareAtPriceTo(String valueAction) {
        String xpath = "//div[normalize-space()='USD']//following-sibling::input";
        waitClearAndType(xpath, valueAction);
    }

    public void decreaseCompareAtPriceByPercentage(String valueAction) {
        String xpath = "//div[normalize-space()='%']//following-sibling::input";
        waitClearAndType(xpath, valueAction);
    }

    public void roundBeautifyPrice(String valueAction) {
        String type = valueAction.split(" ")[0].trim();
        String value = valueAction.split(" ")[1].trim();
        String xpathDdl = "//div[@class='card__section' and contains(.,'How do you want to update the product?')]//div[@class='s-select s-flex--fill']//select";
        String xpathInput = "//div[normalize-space()='USD']//following-sibling::input";
        retrySelectDdlByXpath(xpathDdl, type, 0);
        waitClearAndType(xpathInput, value);
    }

    public void clickBtnUpdate() {
        String xpath = "//button[normalize-space()='Update']";
        waitElementToBeVisible(xpath);
        clickOnElement(xpath);
    }

    public void deleteCustomOption(String valueAction) {
        String type = valueAction.split(">")[0].trim();
        String value = valueAction.split(">")[1].trim();
        String xpath_condition = "(//div[parent::div[@class=\"row s-mt8\" ] and contains(.,\"Option label\")]//select)[2]";
        String xpathInput = "//input[@placeholder=\"Option name\"]";
        if(isElementExist(xpath_condition)){
            selectByText(xpath_condition, type);
        }else {
            xpath_condition = "(//div[parent::div[@class=\"row s-mt8\" ] and contains(.,\"Option name\")]//select)[2]";
            selectByText(xpath_condition, type);
        }
        waitClearAndType(xpathInput, value);
    }

    public boolean isBulkFinised() {
        String xpahtFinished = "//div[@class=\"page-bulk-updates\"]//table[@class=\"table table-hover\"]/tbody/tr[1]/td/span[@data-label=\"Finished\"]";
        int i = 0;
        while (!isElementExist(xpahtFinished)) {
            waitABit(5000);
            i++;
            refreshPage();
            if (i > 10) break;
        }
        return isElementExist(xpahtFinished);
    }

    public String getValueAfterBulkUpdate(int i) {
        String getText = getText("//div[@class=\"page-bulk-updates\"]//table[@class=\"table table-hover\"]/tbody/tr[1]/td[" + i + "]//p");
        return getText;
    }
    public void clickAddAction(){

    }
}
