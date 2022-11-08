package com.eymen;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;

@SpringBootApplication
public class ATM {

    public static void main(String[] args) throws IOException {
        OptionMenu optionMenu = new OptionMenu();
        introduction();
        optionMenu.mainMenu();
    }

    public static void introduction() {
        System.out.println("Welcome to Eymen Bank ATM Project");
    }
}
