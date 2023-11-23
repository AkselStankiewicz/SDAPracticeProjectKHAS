package org.example.api;

public class Menu {
    public static void showWelcomeMenu() {
        System.out.println("""
                ----------------------
                WELCOME!
                type X to quit
                type Y to get a weather stats
                type A to show all saved weathers
                type AD to show all saved weathers with details
                ----------------------
                """);
    }
}
