package fpt.mantv.vehiclemanagement.helper;

public class RegexHelper {
    public static final String REGEX_USERNAME = "^[A-Za-z0-9_-]{5,15}$";
    public static final String REGEX_EMAIL = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public static final String REGEX_NAME = "^[A-Z][a-zA-Z]*( [A-Z][a-zA-Z]*)*$";
    public static final String REGEX_PASSWORD = "^[A-Za-z0-9_-]{6,15}$";
    public static final String REGEX_NORMAL = "[a-zA-Z0-9 ]+";
    public static final String REGEX_COLOR = "[a-zA-Z ]+";
}
