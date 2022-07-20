package com.opencommerce.shopbase.hive_sbase.steps;

import com.opencommerce.shopbase.hive_sbase.pages.ContestPage;

public class ContestSteps {
    ContestPage contestPage;

    public void selectMetricsType(String label, String value) {
        contestPage.selectMetricsType(label, value);
    }

    public void inputPrize(String prize) {
        String pr[] = prize.split(",");
        for (int i = 0; i < pr.length; i++) {
            String price[] = pr[i].split(">");
            String threshold = price[0];
            String pri = price[1];
            contestPage.enterThreshold(threshold, i + 1);
            contestPage.enterPrize(pri, i + 1);
            if (i < pr.length - 1) {
                contestPage.clickButtonAddNewPrice();
            }
        }
    }

    public void selectTemplate(String template) {
        contestPage.selectTemplate(template);
    }

    public void uploadPreBackground(String image) {
        contestPage.uploadPreBackground(image);
    }

    public void enterPreMsgHeader(String preHeader) {
        String msg[] = preHeader.split(">");
//        for (int i = 0; i < msg.length; i++) {
        contestPage.enterInputByID("pre_contest_header_content", msg[0]);
        contestPage.enterInputByID("pre_contest_header_size", msg[1]);
        contestPage.enterInputByID("pre_contest_header_color", msg[2]);
//        }
    }

    public void enterPreSubtext(String preSub) {
        if (!preSub.isEmpty()) {
            String sub[] = preSub.split(">");
//            for (int i = 0; i < sub.length; i++) {
            contestPage.enterInputByID("pre_contest_sub_header_content", sub[0]);
            contestPage.enterInputByID("pre_contest_sub_header_size", sub[1]);
            contestPage.enterInputByID("pre_contest_sub_header_color", sub[2]);
//            }
        }
    }

    public void enterInMsgHeader(String inHeader) {
        if (!inHeader.isEmpty()) {
            String msg[] = inHeader.split(">");
            contestPage.enterInputByID("in_contest_header_content", msg[0]);
            contestPage.enterInputByID("in_contest_header_size", msg[1]);
            contestPage.enterInputByID("in_contest_header_color", msg[2]);
        }
    }

    public void enterInSubtext(String inSub) {
        String sub[] = inSub.split(">");
        contestPage.enterInputByID("in_contest_sub_header_content", sub[0]);
        contestPage.enterInputByID("in_contest_sub_header_size", sub[1]);
        contestPage.enterInputByID("in_contest_sub_header_color", sub[2]);
    }

    public void uploadInContestBackground(String image) {
        contestPage.uploadInContestBackground(image);
    }

    public void uploadInContestIconStart(String icon) {
        contestPage.uploadInContestIconStart(icon);
    }

    public void uploadInContestIconFinish(String icon) {
        contestPage.uploadInContestIconFinish(icon);
    }

    public void enterLearnMoreButton(String button) {
        String btn[] = button.split(">");
//        for (int i = 0; i < btn.length; i++) {
        contestPage.enterSizeButton(btn[0]);
        contestPage.enterInputFieldWithLabel("Primary Color", btn[1]);
        contestPage.enterInputFieldWithLabel("Secondary Color", btn[2]);
//        }
    }

    public void enterAfterMsgHeaderNoPrice(String afterHeaderNoPrize) {
        String sub[] = afterHeaderNoPrize.split(">");
//        for (int i = 0; i < sub.length; i++) {
        contestPage.enterInputByID("after_contest_no_price_header_content", sub[0]);
        contestPage.enterInputByID("after_contest_no_price_header_size", sub[1]);
        contestPage.enterInputByID("after_contest_no_price_header_color", sub[2]);
//        }
    }

    public void enterAfterSubNoPrice(String afterSubNoPrize) {
        String sub[] = afterSubNoPrize.split(">");
//        for (int i = 0; i < sub.length; i++) {
        contestPage.enterInputByID("after_contest_no_price_sub_header_content", sub[0]);
        contestPage.enterInputByID("after_contest_no_price_sub_header_size", sub[1]);
        contestPage.enterInputByID("after_contest_no_price_sub_header_color", sub[2]);
//        }
    }

    public void uploadAfterBGNoPrice(String image) {
        contestPage.uploadAfterBGNoPrice(image);

    }

    public void enterAfterMsgHeaderHadPrice(String afterHeaderHadPrize) {
        String sub[] = afterHeaderHadPrize.split(">");
//        for (int i = 0; i < sub.length; i++) {
        contestPage.enterInputByID("after_contest_had_price_header_content", sub[0]);
        contestPage.enterInputByID("after_contest_had_price_header_size", sub[1]);
        contestPage.enterInputByID("after_contest_had_price_header_color", sub[2]);
//        }
    }

    public void enterAfterSubHadPrice(String afterSubHadPrize) {
        String sub[] = afterSubHadPrize.split(">");
//        for (int i = 0; i < sub.length; i++) {
        contestPage.enterInputByID("after_contest_had_price_sub_header_content", sub[0]);
        contestPage.enterInputByID("after_contest_had_price_sub_header_size", sub[1]);
        contestPage.enterInputByID("after_contest_had_price_sub_header_color", sub[2]);
//        }
    }

    public void uploadAfterBGHadPrice(String image) {
        contestPage.uploadAfterBGHadPrice(image);

    }

    public void uploadAfterIcon1(String icon) {
        contestPage.uploadAfterIcon1(icon);
    }

    public void uploadAfterIcon2(String icon) {
        contestPage.uploadAfterIcon2(icon);
    }

    public void verifyContestInlist(String name) {
        contestPage.verifyContestInlist(name);
    }

    public void enterProgressbar(String inProgressbar) {
        String bar[] = inProgressbar.split(">");
        contestPage.enterInputByID("in_contest_primary_color", bar[0]);
        contestPage.enterInputByID("in_contest_secondary_color", bar[1]);
    }
}
