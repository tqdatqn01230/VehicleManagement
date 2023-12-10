package fpt.mantv.vehiclemanagement.helper;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ScannerHelper {
    public static boolean isEmpty(String check) {
        return (check == null || check.trim().isEmpty());
    }

    public static Scanner scanner = new Scanner(System.in);

    // username
    public static String getUserInputString(String regex, String inputMessage, String errorMessage, String defaultWhenBlank) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        while (true) {
            System.out.print(inputMessage);
            String input = scanner.nextLine();

            if (input.trim().isEmpty()) {
                if (defaultWhenBlank != null) {
                    return defaultWhenBlank;
                } else {
                    System.out.println("Please enter input!");
                    continue;
                }
            }

            if (pattern.matcher(input).matches()) {
                return input;
            } else {
                System.out.println(errorMessage);
            }
        }
    }

    public static String getUserInputString(String regex, String inputMessage, String errorMessage) {
        return getUserInputString(regex, inputMessage, errorMessage, null);
    }

//    public static String getUserPassword(String regex, String inputMessage, String errorMessage, PasswordHelper.passwordHasherFunction<String, String> passwordEncrypted) throws Exception {
//        return passwordEncrypted.apply(getUserInputString(regex, inputMessage, errorMessage, null));
//    }
//
//    public static String getUserPassword(String regex, String inputMessage, String errorMessage, String defaultWhenBlank, PasswordHelper.passwordHasherFunction<String, String> passwordEncrypted) throws Exception {
//        String password = getUserInputString(regex, inputMessage, errorMessage, defaultWhenBlank);
//        if (!password.equals(defaultWhenBlank)) {
//            return passwordEncrypted.apply(password);
//        } else {
//            return password;
//        }
//    }

    public static int getUserInputInt(String inputMessage, String errorMessage, int minValue, int maxValue, Integer defaultValue) {
        while (true) {
            System.out.print(inputMessage);
            String input = scanner.nextLine();
            if (input.trim().isEmpty() && !Objects.isNull(defaultValue)) {
                return defaultValue;
            }
            try {
                int number = Integer.parseInt(input.trim());
                if (number < minValue || number > maxValue) {
                    throw new NumberFormatException();
                }
                return number;
            } catch (NumberFormatException exception) {
                System.out.println(errorMessage);
            }
        }
    }
    public static int getUserInputInt(String inputMessage, String errorMessage, int minValue, int maxValue) {
        return getUserInputInt(inputMessage,errorMessage,minValue,maxValue, null);
    }

    public static Double getUserInputDouble(String inputMessage, String errorMessage, double minValue, double maxValue, Double defaultValue) {
        while (true) {
            System.out.print(inputMessage);
            String input = scanner.nextLine();
            if (input.trim().isEmpty() && !Objects.isNull(defaultValue)) {
                return defaultValue;
            }
            try {
                double number = Double.parseDouble(input.trim());
                if (number < minValue || number > maxValue) {
                    throw new NumberFormatException();
                }
                return number;
            } catch (NumberFormatException exception) {
                System.out.println(errorMessage);
            }
        }
    }


    private static boolean getBoolean(String msg, boolean canBlank, boolean defaultWhenBlank) {
        do {
            System.out.print(msg + "-(T/F, Y/N, 1/0): ");
            String S;
            S = scanner.nextLine().trim().toUpperCase();
            if (!canBlank && S.isEmpty()) {
                System.out.println("---> Not empty field!!! Again Please.");
            } else if (canBlank && S.isEmpty()) {
                return defaultWhenBlank;
            } else {
                char c = S.charAt(0);
                if (c == 'T' || c == 'Y' || c == '1') {
                    return true;
                } else if (c == 'F' || c == 'N' || c == '0') {
                    return false;
                } else {
                    System.out.println("-> Enter (T/F, Y/N, 1/0) please !");
                }
            }
        } while (true);
    }

    public static boolean getBoolean(String msg) {
        return getBoolean(msg, false, false);
    }
}
