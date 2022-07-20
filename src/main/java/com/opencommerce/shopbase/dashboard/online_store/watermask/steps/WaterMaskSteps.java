package com.opencommerce.shopbase.dashboard.online_store.watermask.steps;

import com.opencommerce.shopbase.dashboard.online_store.watermask.pages.WaterMaskPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class WaterMaskSteps extends ScenarioSteps {
    WaterMaskPage waterMaskPage;

    @Step
    public void setEnableWaterMask(boolean isWatermark) {
        waterMaskPage.clickCheckboxEnableWaterMask(isWatermark);
    }

    @Step
    public void configTypeAndInputValue(String sType) {
        String typeAndValue[] = sType.split(">");
        waterMaskPage.clickChooseType(typeAndValue[0]);
        if (typeAndValue.length > 1)
            switch (typeAndValue[0]) {
                case "text":
                    waterMaskPage.inputText(typeAndValue[1]);
                    break;
                case "image":
                    waterMaskPage.inputImageLogo(typeAndValue[1]);
            }
    }


    @Step
    public void chooseStyle(String sStyle) {
         waterMaskPage.clickStyle(sStyle);
    }

    public void clickSaveBtnAndVerifyMessageSuccess() {
       if(waterMaskPage.isDialogSaveShow()){
           waterMaskPage.clickBtn("Save changes");
           waterMaskPage.verifyMessageSuccess();
       }
    }
}

