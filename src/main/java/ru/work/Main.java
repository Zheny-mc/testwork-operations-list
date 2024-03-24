package ru.work;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.printHelp();

        boolean isRun = true;

        while (isRun) {
            int num = menu.inputCommand();
            switch (num) {
                case 1:
                    menu.addContact();
                    break;
                case 2:
                    menu.printAllContacts();
                    break;
                case 3:
                    menu.findByFirstName();
                    break;
                case 4:
                    menu.deleteContact();
                    break;
                case 5:
                    isRun = false;
                    menu.stopProgram();
                    break;
                default:
                    menu.defaultCommand();
                    menu.printHelp();
            }
        }
    }
}
