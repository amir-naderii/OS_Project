package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        UserInteraction userInteraction = new UserInteraction();
        userInteraction.inputTaker();
    }
}
