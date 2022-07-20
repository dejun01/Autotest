package com.opencommerce.shopbase.dashboard.products.steps;

import au.com.bytecode.opencsv.CSVWriter;
import com.opencommerce.shopbase.dashboard.products.pages.ProductFeedDetailPages;
import net.thucydides.core.annotations.Step;

import java.io.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class ProductFeedDetailSteps {
    ProductFeedDetailPages productFeedDetailPages;

    @Step
    public void clickAddProductFeed() {
        productFeedDetailPages.clickBtn("Add product feed");
    }

    @Step
    public void clickAddCollection() {
        productFeedDetailPages.clickBtn("Add");
    }

    @Step
    public void enterFeedName(String feedName) {
        productFeedDetailPages.enterInputFieldWithLabel("Feed name", feedName);
    }

    @Step
    public void checkUncheckElement(String radioButtonLabel) {
        productFeedDetailPages.selectRadioButtonWithLabel(radioButtonLabel, true);
    }

    @Step
    public void enterGoogleProductCategory(String googleProductCategory) {
        productFeedDetailPages.enterInputFieldWithLabel("Google product category", googleProductCategory);
    }

    @Step
    public void clickSaveButton() {
        productFeedDetailPages.clickBtn("Save");
        productFeedDetailPages.waitForEverythingComplete();
    }

    @Step
    public void chooseProductFeed(String feedName) {
        productFeedDetailPages.chooseProductFeed(feedName);
    }

    @Step
    public void searchProductFeed(String feedName) {
        productFeedDetailPages.searchProductFeed(feedName);
    }

    @Step
    public String getName() {
        return productFeedDetailPages.getName();
    }

    @Step
    public void searchCollection(String colName) {
        productFeedDetailPages.searchCollection(colName);
    }

    @Step
    public void chooseCollection() {
        productFeedDetailPages.chooseCollection();
    }

    @Step
    public void addFeedInfoToCSV(String fileName, String name, String totalProduct) {
        File file = new File(fileName);
        try {
            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile, ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            String[] header = {"NAME", "TOTAL PRODUCT"};
            writer.writeNext(header);

            String[] feedInfo = {name, totalProduct};
            writer.writeNext(feedInfo);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Step
    public void clickBreadProductFeeds() {
        productFeedDetailPages.clickBreadProductFeeds();
    }

    @Step
    public void typeFeedName(String feedName) {
        productFeedDetailPages.typeFeedName(feedName);
    }

}
