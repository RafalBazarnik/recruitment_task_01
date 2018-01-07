package com.xsolve.common;

import java.sql.Timestamp;
import java.util.Random;
import java.util.UUID;

public class Helpers {
    public static Random random = new Random();

    public static String generateUniqueText() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return "dummy_".concat(Long.toString(timestamp.getTime()));
    }
    public static String generateText() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getRandomSmiley() {
        String[] smileys = PropertiesLoader.get("smileysList").split(", ");
        int randomIndex = random.nextInt(smileys.length);
        return smileys[randomIndex];
    }
}
