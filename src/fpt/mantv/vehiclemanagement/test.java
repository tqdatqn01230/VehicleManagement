package fpt.mantv.vehiclemanagement;

import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9 ]+");
        System.out.println(pattern.matcher("Dat Trinh 8").matches());
    }
}
