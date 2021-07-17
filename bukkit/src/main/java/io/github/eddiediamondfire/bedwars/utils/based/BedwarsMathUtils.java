package io.github.eddiediamondfire.bedwars.utils.based;

import io.github.eddiediamondfire.bedwars.arenadata.GameInstance;

import java.util.Map;
import java.util.Random;

public class BedwarsMathUtils {

    public static int getRandomNumber(int min, int max, Map<Integer, GameInstance> gameInstanceMap){

        if(min >= max){
            throw new IllegalArgumentException("There is no way you have a minimum number bigger than the maximum number!");
        }

        Random random = new Random();
        for(int i=0; i < gameInstanceMap.size(); i++){
            int randomID = random.nextInt((max-min) + 1) + min;

            if(gameInstanceMap.containsKey(randomID)){
                continue;
            }else if(!gameInstanceMap.containsKey(randomID)){
                return randomID;
            }
        }
        return 0;
    }
}
