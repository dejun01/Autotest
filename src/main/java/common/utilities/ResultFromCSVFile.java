package common.utilities;

import com.opencsv.exceptions.CsvValidationException;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class ResultFromCSVFile {

    public static ExpectedResult loadExpectedResult(String filePath) throws IOException, CsvValidationException {
        ExpectedResult expectedResult = new ExpectedResult();

        CSVReader reader = new CSVReader(new FileReader(filePath));//doc file csv

        String[] headers = reader.readNext();
        List<Map<String, Object>> listData = new ArrayList<>();
        String[] line;
        while ((line = reader.readNext()) != null) {
            Map<String, Object> record = new HashMap<>();
            for (int i = 0; i < headers.length; i++) {
                record.put(headers[i], line[i]);
            }
            listData.add(record);
        }

        expectedResult.setExpectedFields(headers);
        expectedResult.setResults(listData);
        return expectedResult;
    }

    public static List<Map<String, Object>> readActualResultByAPI(JsonPath json, String path, String... expectedFields) {
        //get ra list cac ket qua thoa man path
        List<Map<String, Object>> listResult = json.get(path);
        List<Map<String, Object>> variants = new ArrayList<>();

        for (Map<String, Object> v : listResult) {
            Map<String, Object> variant = new HashMap<>();//moi variant chi lay lay nhung thong tin co key=header trong csv file
            for (String f : expectedFields) {
                variant.put(f, v.get(f) + "");
            }
            variants.add(variant);
        }
        return variants;
    }

    public static void verifyResult(String fileName, Response resp, String path) throws IOException, CsvValidationException {
        String filePath = LoadObject.getFilePath(fileName);
        ExpectedResult expectedResult = loadExpectedResult(filePath);
        List<Map<String, Object>> actualResult = readActualResultByAPI(resp.then().extract().jsonPath(), path, expectedResult.expectedFields);

        System.out.println("actual: " + actualResult);
        System.out.println("expect: " + expectedResult.getResults());

        assertTrue(expectedResult.getResults().containsAll(actualResult));
        assertThat(expectedResult.getResults().size()).isEqualTo(actualResult.size());


    }
}