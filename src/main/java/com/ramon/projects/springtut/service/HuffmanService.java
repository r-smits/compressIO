package com.ramon.projects.springtut.service;


import com.ramon.projects.springtut.dto.huffman.Node;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


@Service
public class HuffmanService {

    public Node getHuffmanTree(HashMap<Character, Double> probabilities) {
        List<Node> nodes = toHuffmanNodes(probabilities);
        return constructTree(nodes);
    }

    public List<String> getHuffmanEncoding(HashMap<Character, Double> probabilities) {
        List<Node> nodes = toHuffmanNodes(probabilities);
        Node root = constructTree(nodes);
        ArrayList<String> encodings = new ArrayList<>();
        depthFirstSearch(root, encodings, "");
        System.out.println(encodings);
        return encodings;
    }

    private void depthFirstSearch(Node node, ArrayList<String> encodings, String encoding) {

        if (node == null) {
            if (!encodings.contains(encoding)) encodings.add(encoding);
            return;
        }

        encoding += node.getBit().toString();
        depthFirstSearch(node.zero, encodings, encoding);
        depthFirstSearch(node.one, encodings, encoding);
    }

    private Node constructTree(List<Node> nodes) {

        Node root = new Node("Ω", 0.0, 1);

        while (nodes.size() > 2) {
            System.out.println("nodes size: " + nodes.size());

            Node child1 = nodes.get(0);
            Node child2 = nodes.get(1);
            nodes.remove(0);
            nodes.remove(0);

            String compoundLabel = child1.getLabel() + child2.getLabel();
            Double compoundProbability = child1.getProb() + child2.getProb();

            Node parent = new Node(compoundLabel, compoundProbability, 1);

            parent.zero = (child1.getProb() >= child2.getProb()) ? child1 : child2;
            parent.one = (child1.getProb() < child2.getProb()) ? child1 : child2;
            parent.zero.setBit(0);

            nodes.add(parent);
            Collections.sort(nodes);
        }

        System.out.println(nodes);

        root.zero = (nodes.get(0).getProb() >= nodes.get(1).getProb()) ? nodes.get(0) : nodes.get(1);
        root.one = (nodes.get(0).getProb() < nodes.get(1).getProb()) ? nodes.get(0) : nodes.get(1);
        root.setLabel(root.zero.getLabel() + root.one.getLabel());
        root.zero.setBit(0);
        System.out.println(root);
        return root;
    }

    private List<Node> toHuffmanNodes(HashMap<Character, Double> probabilities) {
        List<Node> nodes = new ArrayList<>();
        probabilities.forEach((key, value) -> nodes.add(new Node(key.toString(), value, 1)));
        Collections.sort(nodes);
        return nodes;
    }
}
