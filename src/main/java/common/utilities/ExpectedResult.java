package common.utilities;


import java.util.List;
import java.util.Map;

public class ExpectedResult {

    String[] expectedFields;
    List<Map<String, Object>> results;

    public String[] getExpectedFields() {
        return expectedFields;
    }

    public void setExpectedFields(String[] expectedFields) {
        this.expectedFields = expectedFields;
    }

    public List<Map<String, Object>> getResults() {
        return results;
    }

    public void setResults(List<Map<String, Object>> results) {
        this.results = results;
    }
}