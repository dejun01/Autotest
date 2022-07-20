package com.opencommerce.shopbase.dashboard.apps.printbase.steps.profit;

import au.com.bytecode.opencsv.CSVWriter;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import com.opencommerce.shopbase.dashboard.apps.printbase.pages.profit.ProfitPages;

import java.io.*;
import java.util.ArrayList;


public class ProfitSteps extends ScenarioSteps {
    ProfitPages profitPages;

    @Step
    public float getBaseCostVariant(String products, String variants) {
        return profitPages.getBaseCostVariant(products, variants);

    }

    @Step
    public void clickOrderNumber(String numberOrder) {
        profitPages.clickOrderNumber(numberOrder);
    }

    @Step
    public void verifyLogProfitInBalance() {
        profitPages.waitForEverythingComplete();
        waitABit(300);
        profitPages.waitForTitleViewShopDisplay();
    }

    @Step
    public void verifyDetailField(String numberOrder, Float profit) {
        profitPages.verifyDetailField(numberOrder, profit);
    }

    @Step
    public void verifyAmount(Float profit) {
        profitPages.verifyAmount(profit);

    }


    public void clickBtnAddToCart() {
        profitPages.clickBtnAddToCart();
    }

    @Step
    public void addProductBaseInfoToCSV(String csvFilePath, String product_base, Float base_cost, Float price_product, Float price_compare) {
        File file = new File(csvFilePath);
        try {
            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile, ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            String[] header = {"product_base", "base_cost", "price_product", "price_compare"};
            writer.writeNext(header);

            String[] productBaseInfo = {product_base, base_cost.toString(), price_product.toString(), price_compare.toString()};
            writer.writeNext(productBaseInfo);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Step
    public ArrayList<String> readingPrductBaseInfor(String filePath) throws IOException {

        ArrayList<String> infor = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line = br.readLine(); //ignoring

        System.out.println("==============reading filePath " + filePath);

        while ((line = br.readLine()) != null && !line.isEmpty()) {
            String[] fields = line.split(",");
            for (int i = 0; i < fields.length; i++) {
                infor.add(fields[i]);
            }
            System.out.println("==============checkout infor: " + infor);
        }
        return infor;
    }

}
