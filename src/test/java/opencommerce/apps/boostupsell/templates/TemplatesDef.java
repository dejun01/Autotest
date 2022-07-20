package opencommerce.apps.boostupsell.templates;

import com.opencommerce.shopbase.dashboard.apps.boostupsell.steps.templates.TemplatesSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import common.utilities.SessionData;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class TemplatesDef {
    @Steps
    TemplatesSteps templatesStep;

    @When("^click Create Template button")
    public void clickOnCreateButton() {
        templatesStep.clickOnCreateButton();
    }

    @When("^input data to create template \"([^\"]*)\"$")
    public void createTemplate(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String description = SessionData.getDataTbVal(dataTable, row, "Description");
            String offers = SessionData.getDataTbVal(dataTable, row, "Offers");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String productTypes = SessionData.getDataTbVal(dataTable, row, "Product Types");
            String businessTypes = SessionData.getDataTbVal(dataTable, row, "Business Types");
            boolean templatesGallery = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Templates Gallery"));
            boolean featuredTemplate = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "Featured Template"));
            String message = SessionData.getDataTbVal(dataTable, row, "Message");

            if (!title.isEmpty()) {
                templatesStep.inputTitle(title);
            }
            if (!description.isEmpty()) {
                templatesStep.inputDescription(description);
            }
            if (!offers.isEmpty()) {
                templatesStep.selectOffers(offers);
                templatesStep.clickOnSaveButtonOnPopup();
            }
            if (!image.isEmpty()) {
                templatesStep.uploadImage(image);
            }
            if (!productTypes.isEmpty()) {
                templatesStep.selectOrganization("Add product type", productTypes);
            }
            if (!businessTypes.isEmpty()) {
                templatesStep.selectOrganization("Add business type", businessTypes);
            }
            templatesStep.selectPublishAndShare(templatesGallery, featuredTemplate);
            templatesStep.clickOnSaveButton();
            templatesStep.verifyMessage(message);
        }
    }

    @When("^verify template on template list \"([^\"]*)\"$")
    public void verifyTemplateOnTemplateList(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            boolean isExist = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isExist"));

            templatesStep.verifyTemplateOnList(title, isExist);
        }
    }

    @When("^delete template \"([^\"]*)\"$")
    public void deleteTemplate(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String message = SessionData.getDataTbVal(dataTable, row, "Message");

            templatesStep.deleteTemplate(title);
            templatesStep.verifyMessage(message);
        }
    }

    @When("^verify template on template gallery page \"([^\"]*)\"$")
    public void verifyTemplateOnTemplateGallery(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");
            String description = SessionData.getDataTbVal(dataTable, row, "Description");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            String productTypes = SessionData.getDataTbVal(dataTable, row, "Product Types");
            String businessTypes = SessionData.getDataTbVal(dataTable, row, "Business Types");
            boolean isShow = Boolean.parseBoolean(SessionData.getDataTbVal(dataTable, row, "isShow"));

            if(isShow){
                templatesStep.verifyTemplateOnTemplateGallery(title, description);

                templatesStep.clickOnTemplate();
                templatesStep.verifyTitleOnTemplateGalleryDetail(title);
                if (!description.isEmpty()) {
                    templatesStep.verifyDescriptionOnTemplateGalleryDetail(description);
                }
                if (!businessTypes.isEmpty()) {
                    templatesStep.verifyOrganizationOnTemplateGalleryDetail("Recommended for these types of business", businessTypes);
                }
                if (!productTypes.isEmpty()) {
                    templatesStep.verifyOrganizationOnTemplateGalleryDetail("Recommended for these types of products", productTypes);
                }
            }else {
                templatesStep.checkShowTemplateOnTemplateGallery(title, isShow);
            }
        }
    }

    @When("^verify template's offers on template gallery page \"([^\"]*)\"$")
    public void verifyTemplateOffers(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String offersName = SessionData.getDataTbVal(dataTable, row, "Offers name");
            String strategy = SessionData.getDataTbVal(dataTable, row, "Strategy");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");

            templatesStep.verifyTemplateOffers(offersName, strategy, type);
        }
    }

    @When("^verify use template \"([^\"]*)\"$")
    public void verifyUseTemplate(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String offerName = SessionData.getDataTbVal(dataTable, row, "Offer name");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");
            String targetProduct = SessionData.getDataTbVal(dataTable, row, "Products/Collections");
            String status = SessionData.getDataTbVal(dataTable, row, "Status");
            String priority = SessionData.getDataTbVal(dataTable, row, "Priority");

            if(!offerName.isEmpty()){
                templatesStep.verifyUseTemplate(type, offerName, targetProduct, status, priority);
            }
        }
    }

    @When("^delete offers \"([^\"]*)\"$")
    public void deleteOffers(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String offersName = SessionData.getDataTbVal(dataTable, row, "Offers name");
            String type = SessionData.getDataTbVal(dataTable, row, "Type");

                templatesStep.deleteOffers(offersName, type);
        }
    }

    @When("^open template \"([^\"]*)\"$")
    public void clickOnTemplate(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String title = SessionData.getDataTbVal(dataTable, row, "Title");

            templatesStep.openTemplateOnTemplatePage(title);
        }
    }

    @When("^click on Use Template button$")
    public void clickOnButton() {
            templatesStep.clickOnUseTemplateButton();
    }


}
