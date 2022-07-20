package opencommerce.apps.printbase.library;
import com.opencommerce.shopbase.dashboard.apps.printbase.steps.library.ClipartFolderSteps;
import common.utilities.SessionData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class ClipartFolderDef {
    @Steps
    ClipartFolderSteps clipartFolderSteps;
    boolean isPass = true;
    String accToken = "";

    @And("click to button save change")
    public void clickToButtonSaveChange() {
        clipartFolderSteps.clickOnSaveChangeBtn();
    }

    @And("Delete all folders by API")
    public void deleteAllFolders() {
        if (isPass) {
            if (accToken.isEmpty())
                accToken = clipartFolderSteps.getAccessTokenShopBase();
            JsonPath listClipart = clipartFolderSteps.getClipart(accToken);
            Object result = listClipart.get("result");
            if (result != null) {
                List<Integer> results = listClipart.get("result.id");
                clipartFolderSteps.deleteClipartsByAPI(results, accToken);
            }
        }
        clipartFolderSteps.verifyAfterDeleteAllFolder();
    }

    @And("click delete all image")
    public void clickDeleteAllImage() {
        clipartFolderSteps.clickOnDeleteIcon();
    }

    @And("select any clipart folder")
    public void selectAnyClipartFolder() {
        clipartFolderSteps.chooseAClipartFolder();
    }

    @And("delete value in name field")
    public void deleteValueInNameField() {
        clipartFolderSteps.deleteNameFolder();
    }

    @Then("verify information in clipart detail as {string}")
    public void verifyInformationInClipartDetailAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sFolder = SessionData.getDataTbVal(dataTable, row, "Folder");
            String _sGroup = SessionData.getDataTbVal(dataTable, row, "Group");
            String _sImage = SessionData.getDataTbVal(dataTable, row, "Image");
            if (!_sFolder.isEmpty())
                clipartFolderSteps.nameFolderVery(_sFolder);
            if (!_sGroup.isEmpty())
                clipartFolderSteps.nameGroupVery(_sGroup);
            if (!_sImage.isEmpty())
                clipartFolderSteps.nameImageVery(_sImage);
        }
    }

    @Then("verify display message ERROR as {string}")
    public void verifyDisplayMessageERRORAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sFolder = SessionData.getDataTbVal(dataTable, row, "Folder");
            String _sClipart = SessionData.getDataTbVal(dataTable, row, "Clipart");
            String _sImage = SessionData.getDataTbVal(dataTable, row, "Image");
            if (!_sFolder.isEmpty())
                clipartFolderSteps.verifyNameFolder(_sFolder);
            if (!_sClipart.isEmpty())
                clipartFolderSteps.verifyImportClipart(_sClipart);
            if (!_sImage.isEmpty())
                clipartFolderSteps.verifyImportImage(_sImage);
        }
    }

    @And("create info clipart folder as {string}")
    public void createClipartFolderAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sFolder = SessionData.getDataTbVal(dataTable, row, "Folder");
            String _sGroup = SessionData.getDataTbVal(dataTable, row, "Group");
            String _sImage = SessionData.getDataTbVal(dataTable, row, "Image");
            if (!_sFolder.isEmpty())
                clipartFolderSteps.inputNameFolder(_sFolder);
            if (!_sGroup.isEmpty()) {
                clipartFolderSteps.chooseGroup(_sGroup);
            } else clipartFolderSteps.createGroup();
            if (!_sImage.isEmpty())
                clipartFolderSteps.inputImageClipart(_sImage);
        }
    }

    @And("edit info clipart folder as {string}")
    public void editClipartFolderAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _newNameImage = SessionData.getDataTbVal(dataTable, row, "newNameImage");
            String _sCurrenImage = SessionData.getDataTbVal(dataTable, row, "CurrenImage");
            String _sImage = SessionData.getDataTbVal(dataTable, row, "Image");
            String _sCurentThumbnail = SessionData.getDataTbVal(dataTable, row, "CurentThumbnail");
            String _sNewThumbnail = SessionData.getDataTbVal(dataTable, row, "newThumbnail");
            if (!_newNameImage.isEmpty())
                clipartFolderSteps.changeNameData(_sCurrenImage, _newNameImage);
            else
                clipartFolderSteps.deleteNameImage();
            if (!_sImage.isEmpty())
                clipartFolderSteps.changeImage(_sImage, _sCurentThumbnail);
            if (!_sNewThumbnail.isEmpty()) {
                clipartFolderSteps.changeImage(_sImage, _sNewThumbnail);
            }
        }
    }

    @And("delete info clipart folder as {string}")
    public void deleteInfoClipartFolderAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sImageThumbnail = SessionData.getDataTbVal(dataTable, row, "ImageThumbnail");
            String _sImageClipart = SessionData.getDataTbVal(dataTable, row, "ImageClipart");
            if (!_sImageThumbnail.isEmpty())
                clipartFolderSteps.deleteImageThumbnail(_sImageThumbnail);
            if (!_sImageClipart.isEmpty()) {
                clipartFolderSteps.deleteImage(_sImageClipart);
                clipartFolderSteps.verifyAfterDeleteImage(_sImageClipart);
            }
        }
    }

    @And("assign Group for clipart as {string}")
    public void assignGroupForClipartAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sGroup1 = SessionData.getDataTbVal(dataTable, row, "Group1");
            String _sGroup2 = SessionData.getDataTbVal(dataTable, row, "Group2");
            String _sGroup3 = SessionData.getDataTbVal(dataTable, row, "Group3");
            if (!_sGroup1.isEmpty())
                clipartFolderSteps.clickOnAnyClipartFolderCheckbox(_sGroup1);
            if (!_sGroup2.isEmpty())
                clipartFolderSteps.assignGroupToFolderAndVerifyGroup(_sGroup2);
            if (!_sGroup3.isEmpty())
                clipartFolderSteps.assignGroupWhenClickCancel(_sGroup3);
        }
    }

    @And("delete clipart folder as {string}")
    public void deleteClipartFolderAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sKey04 = SessionData.getDataTbVal(dataTable, row, "04");
            String _sKey05 = SessionData.getDataTbVal(dataTable, row, "05");
            if (!_sKey04.isEmpty())
                clipartFolderSteps.deleteClipartFolder();
            if (!_sKey05.isEmpty())
                clipartFolderSteps.deleteClipartFolderWhenClickCancel();
        }
    }
    @And("open folder clipart detail as {string}")
    public void openFolderClipartDetailAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sFolder = SessionData.getDataTbVal(dataTable, row, "Folder");
            if (!_sFolder.isEmpty()) {
                clipartFolderSteps.openClipartDetail(_sFolder);
            }
        }
    }

    @Then("sort clipart and verify after sort")
    public void sortClipartAndVerifyAfterSort(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _sClipart = SessionData.getDataTbVal(dataTable, row, "Clipart");
            String _sAction = SessionData.getDataTbVal(dataTable, row, "Action");
            String _sPosition = SessionData.getDataTbVal(dataTable, row, "Position");
            String _sListClipart = SessionData.getDataTbVal(dataTable, row, "List clipart");
            if (!_sClipart.isEmpty()) {
                clipartFolderSteps.selectClipartInFolder(_sClipart);
                clipartFolderSteps.clickActionBtn();
                clipartFolderSteps.clickInActionMenu(_sAction, _sPosition);
                clipartFolderSteps.clickOnSaveChangeBtn();
                clipartFolderSteps.verifyListClipart(_sListClipart);
            }
        }
    }

    @And("create info clipart folder")
    public void createInfoClipartFolder(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String folder = SessionData.getDataTbVal(dataTable, row, "Folder");
            String group = SessionData.getDataTbVal(dataTable, row, "Group");
            String image = SessionData.getDataTbVal(dataTable, row, "Image");
            if (!folder.isEmpty())
                clipartFolderSteps.inputNameFolder(folder);
            if (!group.isEmpty()) {
                clipartFolderSteps.chooseGroup(group);
            } else clipartFolderSteps.createGroup();
            if (!image.isEmpty())
                clipartFolderSteps.inputImageClipart(image);
        }
        clipartFolderSteps.clickOnSaveChangeBtn();
    }

    @And("edit info clipart folder in dashboard as {string}")
    public void editClipartFolderInDashboardAs(String dataKey, List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsByValEqualInCol(dataTable, "KEY", dataKey).keySet()) {
            String _sCurrentName = SessionData.getDataTbVal(dataTable, row, "Current name");
            String _sThumbnailUpdate = SessionData.getDataTbVal(dataTable, row, "Thumbnail update");
            String _sNameUpdate = SessionData.getDataTbVal(dataTable, row, "Name update");
            String _sNewThumbnail = SessionData.getDataTbVal(dataTable, row, "Thumbnail update");
            Integer index = clipartFolderSteps.getIndexClipartInFolder(_sCurrentName);
            boolean isThumbnailUpdate = _sThumbnailUpdate.equalsIgnoreCase("true");
            if (isThumbnailUpdate)
                clipartFolderSteps.deleteNameImage();
            else {
                clipartFolderSteps.changeNameClipart(index - 1, _sNameUpdate);
                if (!_sNewThumbnail.isEmpty()) {
                    clipartFolderSteps.changeImageThumWithIndex(index, _sNewThumbnail);
                }
            }
        }
    }

    @And("open folder clipart {string} detail")
    public void openFolderClipartDetail(String nameclipart) {
        clipartFolderSteps.openFolderClipart(nameclipart);
    }

    @Then("add image clipart folder")
    public void addImageClipartFolder(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String _sImage = SessionData.getDataTbVal(dataTable, row, "Image");
            clipartFolderSteps.inputImageClipart(_sImage);
        }
        clipartFolderSteps.clickOnSaveChangeBtn();
    }
}