package common.utilities;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoadObject {
    static String fseparator = File.separator;
    static EnvironmentVariables ENV = SystemEnvironmentVariables.createEnvironmentVariables();

    public static String getFilePath(String fileName) {
        String _filePath = System.getProperty("user.dir") + fseparator + "src" + fseparator + "test" + fseparator + "resources" + fseparator + "dataset" + fseparator + fileName.replaceAll("/", fseparator + fseparator);
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("Windows")) {
            _filePath = System.getProperty("user.dir") + fseparator + "src" + fseparator + "test" + fseparator + "resources" + fseparator + "dataset" + fseparator + fileName.replaceAll("/", fseparator + fseparator);
        }
        return _filePath;
    }

    public static String getProperty(String property) {
        String value = "";
        try {
            value = EnvironmentSpecificConfiguration.from(ENV).getProperty(property);
        } catch (Exception e) {
        }

        return value;
    }

    public static boolean convertStatus(String value) {
        if (value.toLowerCase().contains("y") || value.toLowerCase().equals("true")) {
            return true;
        }
        return false;
    }

    public static boolean isContainCurrencySymbol(String str) {
        Pattern pattern = Pattern.compile("\\p{Sc}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
}

