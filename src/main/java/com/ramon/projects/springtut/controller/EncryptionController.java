package com.ramon.projects.springtut.controller;

import com.ramon.projects.springtut.dto.arithmetic.Bounds;
import com.ramon.projects.springtut.dto.EncryptedMessage;
import com.ramon.projects.springtut.dto.UnencryptedMessage;
import com.ramon.projects.springtut.dto.huffman.Node;
import com.ramon.projects.springtut.service.ArithmeticService;
import com.ramon.projects.springtut.service.EncryptionService;
import com.ramon.projects.springtut.service.HuffmanService;
import com.ramon.projects.springtut.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class EncryptionController {

    EncryptionService encryptionService;
    HuffmanService huffmanService;
    ArithmeticService arithmeticService;

    @Autowired
    EncryptionController(EncryptionService encryptionService, HuffmanService huffmanService,
                         ArithmeticService arithmeticService) {
        this.encryptionService = encryptionService;
        this.huffmanService = huffmanService;
        this.arithmeticService = arithmeticService;
    }

    @PostMapping("tut/binary/encode")
    public EncryptedMessage getBinaryEncoding(@RequestBody UnencryptedMessage message) {
        return this.encryptionService.applyBinaryEncoding(message);
    }

    @PostMapping("/tut/probability/distribution")
    public EncryptedMessage getProbabilityDistribution(@RequestBody UnencryptedMessage message) {
        return this.encryptionService.getProbabilityDistribution(message);
    }

    @PostMapping("/tut/huffman/tree")
    public Node getHuffmanTree(@RequestBody UnencryptedMessage message) {
        HashMap<Character, Double> probabilities = EncryptionUtil.computeProbabilities(message);
        return this.huffmanService.getHuffmanTree(probabilities);
    }

    @PostMapping("/tut/huffman/encode")
    public EncryptedMessage getHuffmanEncoding(@RequestBody UnencryptedMessage message) {
        return this.encryptionService.applyHuffmanEncoding(message);
    }

    @PostMapping("/tut/shannon/entropy")
    public EncryptedMessage getShannonEntropy(@RequestBody UnencryptedMessage message) {
        return this.encryptionService.computeShannonEntropy(message);
    }

    @PostMapping("/tut/arithmetic/bounds")
    public Bounds getArithmeticBounds(@RequestBody UnencryptedMessage message) {
        return this.arithmeticService.computeBounds(message);
    }

    @PostMapping("/tut/arithmetic/encode")
    public EncryptedMessage getArithmeticEncoding(@RequestBody UnencryptedMessage message) {
        return this.encryptionService.applyArethmaticEncoding(message);
    }
}
