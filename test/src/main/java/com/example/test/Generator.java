package com.example.test;

import java.util.HashSet;
import java.util.Random;

public class Generator {
    public static String generateFiveNumbers(){
        Random random = new Random();
        String result="";
        HashSet<String> numbers = new HashSet<>();
        while (numbers.size() < 5) {
            int generatedNumber = random.nextInt(39) + 1;
            numbers.add(Integer.toString(generatedNumber));
        }
        for(String s:numbers){
            result+=s+",";
        }
        return result.substring(0,result.length()-1);
    }
}
