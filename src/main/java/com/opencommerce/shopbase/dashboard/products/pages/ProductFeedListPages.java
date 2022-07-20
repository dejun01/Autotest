package com.opencommerce.shopbase.dashboard.products.pages;

import common.SBasePageObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductFeedListPages extends SBasePageObject {
    public ProductFeedListPages(WebDriver driver) {
        super(driver);
    }

    int i= 0;

    public void deleteProductFeed(String feedName) {
        String xPath = "//td[child::a[contains(text(),'"+feedName+"')]]//following-sibling::td[5]//span";
        waitElementToBeVisible(xPath);
        clickOnElement(xPath);
    }

    public void searchProductFeed(String feedName) {
        String xPath = "//input[@type='text' and @placeholder='Search product feed name']";
        waitElementToBeVisible(xPath);
        waitClearAndTypeThenEnter(xPath, feedName);
    }

    public String getUrl(String productFeed) {
        String xpath = "//td[child::a[contains(text(),'" + productFeed + "')]]//following-sibling::td[3]";
        waitElementToBeVisible(xpath);
        String url = getTextValue(xpath);
        return url;
    }

    public int getTotal(String productFeed) {
        int total = Integer.parseInt(getTextValue("//td[child::a[contains(text(),'" + productFeed + "')]]//following-sibling::td[1]"));
        return total;
    }

    public int countProduct(String linkText) throws IOException {
        URL url = new URL(linkText);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            InputStream in = response.body().byteStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line = "";
            int countVariant = 0;
            String variant = "variant=";
            while ((line = reader.readLine()) != null) {
                if (line.contains(variant)) {
                    countVariant++;
                }
            }
            response.body().close();
            return countVariant;
        }
    }

    public void verifyCountProduct(String productFeed,String linkText) throws IOException {
        int totalProduct = countProduct(linkText);
        assertThat(totalProduct).isEqualTo(getTotal(productFeed));
    }

    public ArrayList<String> storeInformationProduct(String linkText) throws IOException {
        URL url = new URL(linkText);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        ArrayList<String> listOfLines = new ArrayList<>();
        ArrayList<String> listOfLinesExpect = new ArrayList<>();
        try (Response response = client.newCall(request).execute()) {
            InputStream in = response.body().byteStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            String lineExpect = "";
            while ((line = reader.readLine()) != null) {
                if (!"".equals(line) && !line.matches("id(.*)vendor")) {
                    listOfLines.add(line);
                }
            }
            for (int i = 0; i < listOfLines.size(); i++) {
                if (listOfLines.get(i).contains("\"") && listOfLines.get(i).matches("100(.*)")) {
                    lineExpect = listOfLines.get(i) + listOfLines.get(i + 1);
                    listOfLinesExpect.add(lineExpect);
                } else if (listOfLines.get(i).matches("100(.*)")) {
                    lineExpect = listOfLines.get(i);
                    listOfLinesExpect.add(lineExpect);
                }
            }
            response.body().close();
            System.out.println("listOfLinesExpect = " + listOfLinesExpect);
            return listOfLinesExpect;
        }
    }

    public void verifyTitle(String title,String linkText) throws IOException {
        String titleFile = storeInformationProduct(linkText).get(i);
        assertThat(titleFile).contains(title);
    }

    public void verifyDescription(String description,String linkText) throws IOException {
        String descriptionFile = storeInformationProduct(linkText).get(i);
        assertThat(descriptionFile).contains(description);
    }

    public void verifyProductType(String productType,String linkText) throws IOException {
        String productTypeFile = storeInformationProduct(linkText).get(i);
        assertThat(productTypeFile).contains(productType);
    }

    public void verifyVendor(String vendor,String linkText) throws IOException {
        String vendorFile = storeInformationProduct(linkText).get(i);
        assertThat(vendorFile).contains(vendor);
    }

    public void verifyPrice(String price,String linkText) throws IOException {
        String titlePrice = storeInformationProduct(linkText).get(i);
        assertThat(titlePrice).contains(price);
    }

    public void verifyCompareAtPrice(String compareAtPrice,String linkText) throws IOException {
        String compareAtPriceFile = storeInformationProduct(linkText).get(i);
        assertThat(compareAtPriceFile).contains(compareAtPrice);
    }

    public void verifyBarcode(String barcode,String linkText) throws IOException {
        String barcodeFile = storeInformationProduct(linkText).get(i);
        assertThat(barcodeFile).contains(barcode);
    }

    public void verifyGender(String gender,String linkText) throws IOException {
        String gendertFile = storeInformationProduct(linkText).get(i);
        assertThat(gendertFile).contains(gender);
    }

    public boolean hasNoProductFeed() {
        String xpath = "//div[@class='text-center']";
        return isElementVisible(xpath, 3);
    }
}
