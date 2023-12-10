/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.mantv.vehiclemanagement.helper;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu extends ArrayList<String> {
    public static final boolean MENU = false;
    public static final boolean SUB_MENU = true;

    private String msg = "";
    private boolean isSubMenu;
    private static final Scanner sc = new Scanner(System.in);

    public Menu(String msg, boolean isSubMenu) {
        super();
        this.msg = msg;
        this.isSubMenu = isSubMenu;
    }

    public int getUserChoice() {
        String tabSpace = isSubMenu ? "\t" : " ";
        System.out.println(msg);
        for (int i = 0; i < this.size(); i++) {
            System.out.println(tabSpace + (i + 1) + "-" + this.get(i));
        }
        System.out.println("Other to Quit.");
        return ScannerHelper.getUserInputInt("Your Choice: ", "Input must be an Integer",  Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean isIsSubMenu() {
        return isSubMenu;
    }

    public void setIsSubMenu(boolean isSubMenu) {
        this.isSubMenu = isSubMenu;
    }
}
