package com.ramon.projects.springtut.service;

import com.ramon.projects.springtut.dto.arithmetic.Bounds;
import com.ramon.projects.springtut.dto.UnencryptedMessage;
import com.ramon.projects.springtut.util.EncryptionUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


@Service
public class ArithmeticService {

    public Bounds computeBounds(UnencryptedMessage message) {
        List<Character> messageChars = EncryptionUtil.characterArrayToCharList(message.getPayload().toCharArray());
        HashMap<Character, Double> probabilities = EncryptionUtil.computeProbabilities(message);

        HashMap<Character, Integer> indices = new HashMap<>();
        List<Double> maticProb = new ArrayList<>(List.of(0.0));
        Iterator<Character> charIterator = probabilities.keySet().iterator();
        for (int i = 1; i < probabilities.keySet().size()+1; i ++) {
            Character currChar = charIterator.next();

            // Get lower and higher bounds; -> (a, b) Let char n represent maticProb[n-1], b maticProb[n].
            maticProb.add(maticProb.get(maticProb.size() - 1) + probabilities.get(currChar));
            indices.put(currChar, i);
        }

        double a = 0.0;
        double b = 1;
        for (Character msgChar : messageChars) {
            double w = b - a;
            Integer n = indices.get(msgChar);
            a = a + (w * maticProb.get(n-1));
            b = a + (w * maticProb.get(n));
        }

        return new Bounds(a, b);
    }

    /**
     * For more information on this method, watch:
     * https://www.youtube.com/watch?v=i_4suhZ2yNs&list=RDCMUCcAtD_VYwcYwVbTdvArsm7w&index=2
     */
    public String computeBinaryFromBounds(Bounds bounds) {

        String binaryRepresentation = "1";

        // Enlarge number until either lb goes above Q2, or up goes below Q2 (middle)
        while (bounds.upperBound < 0.5 || bounds.lowerBound > 0.5) {
            if (bounds.upperBound < 0.5) {
                binaryRepresentation += "0";
                bounds.lowerBound *= 2;
                bounds.upperBound *= 2;

            } else if (bounds.lowerBound > 0.5) {
                binaryRepresentation += "1";
                bounds.lowerBound = 2 * (bounds.lowerBound - 0.5);
                bounds.upperBound = 2 * (bounds.upperBound - 0.5);
            }
        }

        // At this point, both ub && lb are in a single quadrant (either both Q2, or both Q3)
        // Now, need to enlarge the number until ub && lb straddle a quadrant.
        int counter = 0;
        while (bounds.lowerBound > 0.25 && bounds.upperBound < 0.75) {
            counter += 1;
            bounds.lowerBound = 2 * (bounds.lowerBound - 0.25);
            bounds.upperBound = 2 * (bounds.upperBound - 0.25);
        }

        // ub and lb now straddle a quadrant (either Q2, or Q3). This will be represented in binary.
        // return binary representation
        counter += 1;
        if (bounds.lowerBound < 0.25) {
            binaryRepresentation += "0";
            for (int i = 0; i < counter; i ++) binaryRepresentation += "1";
        } else {
            binaryRepresentation += "1";
            for (int i = 0; i < counter; i ++) binaryRepresentation += "0";
        }

        return binaryRepresentation;
    }
}
