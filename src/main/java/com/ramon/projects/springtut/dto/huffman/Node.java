package com.ramon.projects.springtut.dto.huffman;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node implements Comparable<Node> {

    public Node zero;
    public Node one;
    private String label;
    private Double prob;
    private Integer bit;

    public Node(String label, Double prob, Integer bit) {
        this.label = label;
        this.prob = prob;
        this.bit = bit;
    }

    @Override
    public int compareTo(Node o) {
        double outcome = this.prob - o.prob;
        if (outcome == 0) return 0;
        return (outcome > 0) ? 1 : -1;
    }

    @Override
    public String toString() {
        return "Node{" +
                "label='" + label + '\'' +
                ", prob=" + prob +
                ", bit=" + bit +
                ", zero=" + zero +
                ", one=" + one +
                '}';
    }
}
