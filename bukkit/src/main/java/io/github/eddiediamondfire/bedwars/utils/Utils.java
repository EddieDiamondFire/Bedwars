package io.github.eddiediamondfire.bedwars.utils;

import io.github.eddiediamondfire.bedwars.arenadata.GameInstance;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class Utils {

    public static int getRandomNumber(int min, int max){

        if(min >= max){
            throw new IllegalArgumentException("There is no way you have a minimum number bigger than the maximum number!");
        }

        Random random = new Random();
        return random.nextInt((max-min) + 1) + min;
    }
}
