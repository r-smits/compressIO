package com.ramon.projects.springtut.util;

import com.ramon.projects.springtut.dto.UnencryptedMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EncryptionUtil {

    public static ArrayList<Character> characterArrayToCharList(char[] array) {
        ArrayList<Character> arrayList = new ArrayList<>();
        for (char character : array) arrayList.add(character);
        return arrayList;
    }

    public static HashMap<Character, Double> computeProbabilities(UnencryptedMessage message) {
        List<Character> characters = EncryptionUtil.characterArrayToCharList(message.getPayload().toCharArray());
        HashMap<Character, Double> probabilities = new HashMap<>();
        Double probCompound = (1 / (double) characters.size());

        for (Character character : characters) {
            probabilities.putIfAbsent(character, 0.0);
            probabilities.put(character, probabilities.get(character) + probCompound);
        }
        return probabilities;
    }

    public static double log2(double N) {
        return (Math.log(N) / Math.log(2));
    }
}
