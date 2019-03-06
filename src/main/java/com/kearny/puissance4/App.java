package com.kearny.puissance4;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}

// alias mup="echo -e '\033[44;1;37m     CLEAN + PROCESS-RESOURCES     \033[0m' && mvn clean process-resources -DskipTests=true"