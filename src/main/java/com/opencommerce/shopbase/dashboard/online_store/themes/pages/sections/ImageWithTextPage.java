package com.opencommerce.shopbase.dashboard.online_store.themes.pages.sections;

import common.SBasePageObject;
import common.utilities.LoadObject;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.in;

public class ImageWithTextPage extends SBasePageObject {
    public ImageWithTextPage(WebDriver driver) {
        super(driver);
    }
    String theme = LoadObject.getProperty("theme");
    String xpathBlock = "//*[contains(@class,'image-with-text')]//*[contains(@class,'feature-set-content-wrap')]";
    String xpathSection = "//section[contains(@data-id,'image_with_text') or @type='image-with-text']";
    String xpathBlockActive = "//div[contains(@class,'is-active')]";

    public void selectLayout(String layout){
        clickOnElementByJs("//div[descendant::div[normalize-space()='Layout']]//p[normalize-space()='"+layout+"']");
    }

    public void selectTextAlignment(String textAlignment){
        clickOnElementByJs("//div[@currentlayout and descendant::label[normalize-space()='Text alignment']]//p[normalize-space()='"+textAlignment+"']");
    }

    public int countElementContent(String label){
        return countElementByXpath("//div[@class='s-collapse-item draggable-item' and descendant::*[normalize-space()='"+label+"']]");
    }

    public void openContent(int index){
        clickOnElementByJs("(//div[contains(@class,'draggable-item')])[1]//span[contains(@class,'arrow')]");
    }

    public void clickRemoveButton(){
        clickOnElementByJs(xpathBlockActive + "//button[normalize-space()='Remove content' or normalize-space()='Remove section']");
    }

    public void addContent(){
        clickOnElementByJs("//div[normalize-space()='Add image with text']//span[@class='s-icon is-small']");
    }

    public void inputImage(String path){
        chooseAttachmentFile("//div[contains(@class,'is-active')]//input", path);
    }

    public void checkCheckBoxContentActive(String label, boolean isCheck){
        checkCheckbox(xpathBlockActive + "//label[span[normalize-space()='"+label+"']]", isCheck);
    }

    public void inputTextBox(String label, String text){
        String xpath = xpathBlockActive + "//div[contains(@class,'card__section') or contains(@class,'is-active')]//div[@currentlayout and descendant::p[normalize-space()='"+label+"']]//input";
        waitClearAndType(xpath, text);
        clickOnElement(xpath);
    }

    public boolean isExistLink(String label) {
        return isElementExist(xpathBlockActive + "//div[child::label[normalize-space()='"+label+"']]//a[@class='s-delete is-small']", 5);
    }


    public void chooseTypeLinkPageByLabel(String typeLink, String dataLink, String label) {
        clickOnElementByJs(xpathBlockActive + "//div[child::label[normalize-space()='"+label+"']]//input");
        clickOnElementByJs(xpathBlockActive + "//div[child::label[normalize-space()='"+label+"']]//span[normalize-space()='"+typeLink+"']");
        waitForEverythingComplete(5);
        if(!dataLink.isEmpty()){
            clickOnElementByJs(xpathBlockActive + "//span[normalize-space()=\""+dataLink+"\"]");
        }
    }

    public void removeLink(String label) {
        String xpath = xpathBlockActive + "//div[child::label[normalize-space()='"+label+"']]//a[@class='s-delete is-small']";
        clickOnElement(xpath);
    }

    public void addLink(String link, String label) {
        String xpath = xpathBlockActive + "//div[child::label[normalize-space()='"+label+"']]//input";
        waitClearAndTypeThenEnter(xpath, link);
        clickOnElement(xpathBlockActive + "//div[child::label[normalize-space()='"+label+"']]//strong[text()='Add']");
    }

    public void inputTextContentWithIframe(String text){
        switchToIFrame(xpathBlockActive + "//iframe");
        waitClearAndType("//body[@id='tinymce']", text);
        switchToIFrameDefault();
    }

    public void verifyLayout(String layout){
        if(theme.equalsIgnoreCase("Inside") || theme.equalsIgnoreCase("Insidev2")) {
            if (layout.equals("Default")) {
                layout = "even_and_odd";
            }
            verifyElementPresent(xpathSection + "//div[contains(@class,'" + layout.toLowerCase() + "')]", true);
        }else {
            if(layout.equalsIgnoreCase("Image with text above")){
                verifyElementPresent("//section[@class='section feature-block-1 special-block']",true);
            }else {
                verifyElementPresent("//section[@class='section feature-set']",true);
            }
        }
    }

    public void verifyFullWidth(String label, boolean isFullWidth){
        verifyElementPresent(xpathSection + "//div[contains(@class,'is-full-width')]", isFullWidth);
    }

    public void verifyTextAlignment(String label, String text, int index){
        verifyElementPresent("(" + xpathSection + "//div[contains(@class,'text-align-"+text.toLowerCase()+"')])["+index+"]", true);
    }

    public void verifyAltText(String label, String text, int index){

    }

    public void clickOnImage(String label, int index){
        clickOnElementByJs("(" + xpathSection + "//img)["+index+"]");
    }

    public void verifyNavigationPage(String expectedLink, String actualLink){
        assertThat(expectedLink).isEqualTo(actualLink);
    }

    public void verifyHeadline(String label, String headline, int index){
        if(theme.equalsIgnoreCase("inside") || theme.equalsIgnoreCase("insidev2")) {
            verifyElementContainsText("(" +xpathSection+ "//h4[contains(@class,'title')])[" + index + "]", headline.toUpperCase());
        }else {
            verifyElementContainsText("(" +xpathSection+ "//h2)["+index+"]",headline);
        }
    }

    public void verifyText(String layout, String text, int index){
        if(!text.isEmpty()) {
            if (theme.equalsIgnoreCase("inside") || theme.equalsIgnoreCase("insidev2")) {
                verifyElementContainsText("(" +xpathSection+ "//div[contains(@class,'desc')])[" + index + "]", text);
            } else {
                if(layout.equalsIgnoreCase("Image with text above")) {
                    hoverOnElement("(//section[@class='section feature-block-1 special-block']//img)[" + index + "]");
                    String textAR = getTextContent("(" +xpathSection+ "//div[@class='hover-content']//p)[" + index + "]");
                    assertThat(textAR).contains(text);
                }else {
                    verifyElementContainsText("(" +xpathSection+ "//p)[" + index + "]", text);
                }
            }
        }
    }

    public void clickOnButton(String label, int index){
        clickOnElementByJs("(" +xpathSection+ "//a)["+index+"]");
    }

    public void verifyPromotionsAnimation(String promotionsAnimation, int index) {
        verifyElementPresent("(" +xpathSection+ "//div[contains(@class,'content-block')]//div[contains(@class,'"+promotionsAnimation+"')])["+index+"]",true);
    }

    public void verifyImageLink(String label, String link, int index) {
         String linkAR = getAttributeValue("(" +xpathSection+ "//a[child::img])["+index+"]","href");
         assertThat(linkAR).contains(link);
    }

    public void verifyButtonLink(String buttonLabel,String link, int index) {
        String linkAR = getAttributeValue("(" +xpathSection+ "//a[normalize-space()='"+buttonLabel+"'])["+index+"]","href");
        assertThat(linkAR).contains(link);
    }

    //Theme inside v2
    public void verifyImageExist(boolean isExisted, int indexBlock) {
        String xpath = "(" + xpathBlock+ ")[" + indexBlock + "]//img[contains(@srcset,'file')]";
        scrollIntoElementView(xpathBlock);
        verifyElementPresent(xpath, isExisted);
    }

    public void verifyimageLink(String imgLink, String shop, int index) {
        String href = getAttributeValue("(" + xpathSection + "//div[contains(@class,'feature-image')])["+ index +"]//ancestor::a", "href").replace("https://" + shop, "").trim();
        assertThat(href).isEqualTo(imgLink);
    }

    public void verifyAltText(String altText, int index) {
        String curAlt = getAttributeValue("(" + xpathBlock+ ")[" + index + "]//img", "alt");
        assertThat(curAlt).isEqualTo(altText);
    }

    public void verifyShowWidthSection(Boolean isFull) {
        verifyElementPresent(xpathSection + "//div[contains(@class,'full-width')]", isFull);
    }

    public void verifyTextAlignment(String textAlignment) {
        verifyElementPresent(xpathSection + "//*[contains(@class,'text-align-" + textAlignment.toLowerCase() + "')]", true);
    }

    public void verifyHeading(String heading, int indexBlock) {
        verifyElementText("(" + xpathBlock+ ")[" + indexBlock + "]//h4", heading.toUpperCase());
    }

    public void verifyDescription(String text, int indexBlock) {
        verifyElementText("(" + xpathBlock+ ")[" + indexBlock + "]//div[contains(@class,'desc')]", text);
    }

    public void verifyButton(String buttonLabel, String link, int index, String shop) {
        String href = getAttributeValue("(" + xpathBlock+ ")[" + index + "]//a[normalize-space()='"+buttonLabel+"']","href").replace("https://" + shop, "").trim();
        assertThat(href).contains(link);
    }

    public void verifyButtonType(boolean buttonType, int index) {
        verifyElementPresent("(" + xpathBlock+ ")[" + index + "]//a[contains(@class,'btn-view')]", buttonType);
    }
    public void isExistBlockSF(boolean status) {
        String xpath = xpathSection+ "//*[contains(@class, 'feature-set-content-wrap')]";
        verifyElementPresent(xpath, status);
    }

}
