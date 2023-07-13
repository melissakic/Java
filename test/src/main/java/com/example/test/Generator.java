package com.example.test;

import jakarta.ejb.Stateless;

import java.util.HashSet;
import java.util.Random;

@Stateless
public class Generator {
    public String generateFiveNumbers(){
        Random random = new Random();
        String result;
        HashSet<String> numbers = new HashSet<>();
        while (numbers.size() < 5) {
            int generatedNumber = random.nextInt(39) + 1;
            numbers.add(Integer.toString(generatedNumber));
        }
        StringBuilder resultBuilder = new StringBuilder();
        for(String s:numbers){
            resultBuilder.append(s).append(",");
        }
        result = resultBuilder.toString();
        return result.substring(0,result.length()-1);
    }
}
