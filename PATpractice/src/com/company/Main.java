package com.company;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
	// write your code here
        Scanner input = new Scanner(System.in);
        int a = input.nextInt();
        int b = input.nextInt();
        int c = input.nextInt();
        int d = input.nextInt();
        double sum = (a+b+c+d);
        double avg = sum / 4;
        DecimalFormat df = new DecimalFormat("#.0");
        String s1 = df.format(avg);
        df.applyPattern("#");
        String s2 = df.format(sum);
        System.out.print("Sum = "+ s2);
        System.out.print("; Average = " + s1);
    }
}
