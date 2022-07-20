package common.utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import net.serenitybdd.core.Serenity;

import static org.codehaus.groovy.runtime.StringGroovyMethods.padRight;

/**
 * @Description Table data, Excel data utility for data table of cucumber
 * feature file and CRUD from Excel File(.xls only) for using in
 * everywhere of FW. WARNING: for more performance and easy to
 * maintain data, please follow the rule: 1. All column data must
 * have unique name 2. Data ranger in each sheet must be
 * defined(Example : Set format to text) 3. Key File Name must be
 * unique.
 */
public class SessionData {

    // Name key contains excel file in session variable
    private static final String TABLE_DATA_LIST = "#TableDatas#";
    private static final String DATA_LIST = "Data List";

    /**
     * Store data table (description in feature file) to Session Data variable.
     *
     * @param _keyDataTableName
     * @param _dataTable
     */
    public static void saveDataTable(String _keyDataTableName, List<List<String>> _dataTable, boolean bOnceTime) {
        // Get session data
        HashMap<String, LinkedHashMap<Integer, List<String>>> hSession_DataTable = new HashMap<>();
        hSession_DataTable = Serenity.sessionVariableCalled(TABLE_DATA_LIST);
        // If session data not exist then initialize
        if (hSession_DataTable == null) {
            HashMap<String, LinkedHashMap<Integer, List<String>>> hDataTable = new HashMap<>();
            Serenity.setSessionVariable(TABLE_DATA_LIST).to(hDataTable);
            hSession_DataTable = Serenity.sessionVariableCalled(TABLE_DATA_LIST);
        }
        // Check Data Table exist or not in Session Data
        boolean bExist = true;
        if (hSession_DataTable.get(_keyDataTableName) == null) {
            bExist = false;
        }

        if (((bExist) && (!bOnceTime)) || (!bExist)) {
            // Add data for session data
            LinkedHashMap<Integer, List<String>> mAddDataTable = new LinkedHashMap<>();
            System.out.println("***** INFO ***** Loading data from Data Table into [" + _keyDataTableName + "]");
            mAddDataTable = loadDataTable(_dataTable);
            hSession_DataTable.put(_keyDataTableName, mAddDataTable);
            Serenity.setSessionVariable(TABLE_DATA_LIST).to(hSession_DataTable);
        }
    }

    public static void addToHashMap(String dataKey, String mapKey, Object value) {
        HashMap<String, Object> exitHashMap = Serenity.sessionVariableCalled(dataKey);
        if (exitHashMap == null) {
            exitHashMap = new HashMap<>();
        }
        exitHashMap.put(mapKey, value);
        System.out.println(mapKey + "=" + value);
        Serenity.setSessionVariable(dataKey).to(exitHashMap);
    }

    public static void saveDataByKey(String key, Object value) {
        addToHashMap(DATA_LIST, key, value);
        System.out.println("SAVE_DATA_LIST:[" + key + "]=[" + value + "]");
    }

    public static Object getDataByKey(String key) {
        try {
            HashMap<String, Object> exitHashMap = Serenity.sessionVariableCalled(DATA_LIST);
            return exitHashMap.get(key);
        } catch (NullPointerException e) {
            System.out.println("***** WARNING ***** : Session Data List String does not exist: " + key);
            return null;
        }

    }

    public static String generateTbDataReport(List<List<String>> rawData) {
        // Re create raw data (defend update raw data)
        List<List<String>> data = new ArrayList<List<String>>();
        for (List<String> list : rawData) {
            List<String> newList = new ArrayList<String>();
            newList.addAll(list);
            data.add(newList);
        }
        // Get max length of each column
        List<Integer> columnLength = new ArrayList<Integer>();
        for (List<String> row : data) {
            // Set max length for each column
            for (int i = 0; i < row.size(); i++) {
                int length = 0;
                if (row.get(i) != null) {
                    length = row.get(i).length();
                }

                if (columnLength.size() < i + 1) {// New column
                    columnLength.add(length);
                } else {// existed column
                    if (columnLength.get(i) < length) {
                        columnLength.set(i, length);
                    }
                }
            }
        }
        // Set table data as String
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        for (List<String> row : data) {
            String rowContent = "";
            // Set max length for each column
            for (int i = 0; i < row.size(); i++) {
                String cell = row.get(i);
                if (cell == null) {
                    cell = "null";
                }
                int length = columnLength.get(i);
                if (i == 0) {
                    row.set(i, "|" + padRight(cell, length) + " | ");
                } else {
                    row.set(i, padRight(cell, length) + " | ");
                }
                rowContent += row.get(i);
            }
            sb.append(rowContent);
            sb.append(System.lineSeparator());

        }
        return sb.toString();
    }

    /**
     * Load data from data table in feature file to raw data
     *
     * @param _dataTable
     * @return
     */
    private static LinkedHashMap<Integer, List<String>> loadDataTable(List<List<String>> _dataTable) {
        // Used the LinkedHashMap to maintain the order
        LinkedHashMap<Integer, List<String>> hashMap = new LinkedHashMap<Integer, List<String>>();
        // Add
        for (int i = 0; i < _dataTable.size(); i++) {
            hashMap.put(i, _dataTable.get(i));
        }
        System.out.println("***** INFO ***** Load data successfully!");
        return hashMap;
    }

    /**
     * Get data rows (data table description in feature file) from Session Data
     * variable by given Value equals in specific Column Name.
     *
     * @param dataTable
     * @param _colName
     * @param _givenVal
     * @return
     */
    public static LinkedHashMap<Integer, List<String>> getDataTbRowsByValEqualInCol(List<List<String>> dataTable, String _colName, String _givenVal) {
        LinkedHashMap<Integer, List<String>> lhTempSessionDataTable = loadDataTable(dataTable);
        LinkedHashMap<Integer, List<String>> lhOuput = new LinkedHashMap<>();
        int colIndex = lhTempSessionDataTable.get(0).indexOf(_colName);
        if (colIndex == -1) {
            System.out.println("***ERROR*** Column [" + _colName + "] not found in Data Table .");
            throw new IllegalArgumentException("Column [" + _colName + "] not found in Data Table]. Re check data !");
        } else {
            // Loop through data in data table and add match data row
            for (int key : lhTempSessionDataTable.keySet()) {
                List<String> lTemp = lhTempSessionDataTable.get(key);
                if (!(lTemp.size() == 0)) {
                    if (lTemp.get(colIndex).equals(_givenVal)) {
                        lhOuput.put(key, lTemp);
                    }
                } else {
                    System.out.println("***ERROR*** Data Row not found in Data Table.[" + key + "].");
                    throw new IllegalArgumentException("Data Row not found in Data Table [" + key + "]. Re check data !");
                }
            }
        }
        return lhOuput;
    }

    /**
     * Get data table rows (data table description in feature file) without
     * header (without first row) from Session Data variable by added Data Table
     * Key.
     *
     * @param dataTable
     * @return
     */
    public static LinkedHashMap<Integer, List<String>> getDataTbRowsNoHeader(List<List<String>> dataTable) {
        LinkedHashMap<Integer, List<String>> ret = loadDataTable(dataTable);
        ret.remove(0);
        return ret;
    }

    /**
     * Get cell data value (data table description in feature file) from Session
     * Data variable by index of Row and Column Name.
     *
     * @param dataTable
     * @param _rowIndex
     * @param _colName
     * @return
     */
    public static String getDataTbVal(List<List<String>> dataTable, int _rowIndex, String _colName) {
        String ret = "";
        int colIndex = dataTable.get(0).indexOf(_colName);
        if (colIndex == -1) {
            System.out.println("***WARNING*** Column [" + _colName + "] not found in Data Table.");
            return ret;
        } else {
            try {
                ret = dataTable.get(_rowIndex).get(colIndex);
            } catch (Exception e) {
                ret = "";
                System.out.println("***WARNING*** Row [" + _rowIndex + "] not found in Data Table.");
            }
        }
        return ret;
    }

    /**
     * Load all data in CSV
     *
     * @param _fileName
     */

    public static List<List<String>> loadDataTableByCSV(String _fileName) throws IOException, CsvValidationException {
        List<List<String>> table = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader(_fileName));//doc file csv
        String[] line;

        while ((line = reader.readNext()) != null) {
            List<String> record = new ArrayList<>();
            record = Arrays.asList(line);
            table.add(record);
        }
        return table;

    }

    public static Object getHashMapValueByKey(String dataKey, String mapKey) {
        try {
            HashMap<String, Object> exitHashMap = Serenity.sessionVariableCalled(dataKey);
            return exitHashMap.get(mapKey);
        } catch (NullPointerException e) {
            System.out.println("***** WARNING ***** : Session Data List String does not exist: " + dataKey);
            return null;
        }
    }


}