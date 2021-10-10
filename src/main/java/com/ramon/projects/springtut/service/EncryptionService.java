package com.ramon.projects.springtut.service;


import com.ramon.projects.springtut.dto.arithmetic.Bounds;
import com.ramon.projects.springtut.dto.EncryptedMessage;
import com.ramon.projects.springtut.dto.UnencryptedMessage;
import com.ramon.projects.springtut.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EncryptionService {

    private HuffmanService huffmanService;
    private ArithmeticService arithmeticService;

    @Autowired
    EncryptionService(HuffmanService huffmanService, ArithmeticService arithmeticService) {
        this.huffmanService = huffmanService;
        this.arithmeticService = arithmeticService;
    }

    public EncryptedMessage computeShannonEntropy(UnencryptedMessage message) {
        HashMap<Character, Double> probabilities = EncryptionUtil.computeProbabilities(message);
        double entropy = 0.0;
        for (Character letter : probabilities.keySet()) {
            entropy += -probabilities.get(letter) * EncryptionUtil.log2(probabilities.get(letter));
        }
        return new EncryptedMessage(List.of(Double.toString(entropy)));
    }

    public EncryptedMessage applyBinaryEncoding(UnencryptedMessage message) {
        List<Character> characters = EncryptionUtil.characterArrayToCharList(message.getPayload().toCharArray());
        List<String> binaryRepresentation = characters.stream()
                .map(character -> Integer.toBinaryString(character.hashCode()))
                .collect(Collectors.toList());
        return new EncryptedMessage(binaryRepresentation);
    }

    public EncryptedMessage applyHuffmanEncoding(UnencryptedMessage message) {
        HashMap<Character, Double> probabilities = EncryptionUtil.computeProbabilities(message);
        List<String> encodings = this.huffmanService.getHuffmanEncoding(probabilities);
        return new EncryptedMessage(encodings);
    }

    public EncryptedMessage getProbabilityDistribution(UnencryptedMessage message) {
        HashMap<Character, Double> probabilities = EncryptionUtil.computeProbabilities(message);
        List<String> doubles = probabilities.values().stream().map(Object::toString).collect(Collectors.toList());
        return new EncryptedMessage(doubles);
    }

    public EncryptedMessage applyArethmaticEncoding(UnencryptedMessage message) {
        Bounds bounds = this.arithmeticService.computeBounds(message);
        String binaryRep = this.arithmeticService.computeBinaryFromBounds(bounds);
        return new EncryptedMessage(List.of(binaryRep));
    }
}
