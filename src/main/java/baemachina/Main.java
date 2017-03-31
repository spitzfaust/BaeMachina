package baemachina;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import baemachina.id3.DecisionTree;
import baemachina.id3.EntropyCalculator;
import baemachina.id3.GainCalculator;
import baemachina.id3.ID3;
import baemachina.id3.ID3Impl;
import baemachina.id3.Instance;
import baemachina.id3.InstanceImpl;
import baemachina.party.Deadline;
import baemachina.party.Lazy;
import baemachina.party.Party;
import baemachina.play.Humidity;
import baemachina.play.Outlook;
import baemachina.play.Temperature;
import baemachina.play.Windy;

/**
 * Created by tobias.
 */
public class Main {

    public static void printNode(final DecisionTree.Node node, final int level) {
        for (int i = 0; i < level; i++) {
            System.out.printf("  ");
        }
        System.out.println(node.getValue());
        if (node.getChildren() != null) {
            for (Map.Entry<Enum, DecisionTree.Node> child : node.getChildren().entrySet()) {
                for (int i = 0; i < level + 1; i++) {
                    System.out.printf("  ");
                }
                System.out.println(child.getKey());
                printNode(child.getValue(), level + 2);
            }
        }
    }

    public static void printTree(DecisionTree tree) {
        printNode(tree.getRoot(), 0);
    }


    public static void playDemo() throws IOException {
        File data;
        data = new File("./play.csv");
        List<Instance> instances = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(data))) {
            String line;
            boolean first = true;
            final List<String> attributeKeys = new LinkedList<>();
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    final String[] tokens = line.split(",");
                    if (first) {
                        attributeKeys.addAll(Arrays.asList(tokens).subList(0, tokens.length - 1));
                        first = false;
                    } else {
                        final Map<String, Enum> attributes = new HashMap<>();
                        for (int i = 0; i < tokens.length - 1; i++) {
                            Enum value;
                            switch (attributeKeys.get(i)) {
                                case "Outlook":
                                    value = Outlook.valueOf(tokens[i].trim());
                                    break;
                                case "Humidity":
                                    value = Humidity.valueOf(tokens[i].trim());
                                    break;
                                case "Temperature":
                                    value = Temperature.valueOf(tokens[i].trim());
                                    break;
                                case "Windy":
                                    value = Windy.valueOf(tokens[i].trim().toUpperCase());
                                    break;
                                default:
                                    throw new RuntimeException("Wrong file format");
                            }
                            attributes.put(attributeKeys.get(i), value);
                        }
                        instances.add(new InstanceImpl(tokens[tokens.length - 1], attributes));
                    }
                }
            }
        }
        ID3 id3 = new ID3Impl(new GainCalculator(), new EntropyCalculator());
        final DecisionTree decisionTree = id3.buildTree(instances);
        printTree(decisionTree);
    }

    public static void partyDemo() throws IOException {
        File data;
        data = new File("./party.csv");
        List<Instance> instances = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(data))) {
            String line;
            boolean first = true;
            final List<String> attributeKeys = new LinkedList<>();
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    final String[] tokens = line.split(";");
                    if (first) {
                        attributeKeys.addAll(Arrays.asList(tokens).subList(0, tokens.length - 1));
                        first = false;
                    } else {
                        final Map<String, Enum> attributes = new HashMap<>();
                        for (int i = 0; i < tokens.length - 1; i++) {
                            Enum value;
                            switch (attributeKeys.get(i)) {
                                case "deadline":
                                    value = Deadline.valueOf(tokens[i].trim());
                                    break;
                                case "party":
                                    value = Party.valueOf(tokens[i].trim());
                                    break;
                                case "lazy":
                                    value = Lazy.valueOf(tokens[i].trim());
                                    break;
                                default:
                                    throw new RuntimeException("Wrong file format");
                            }
                            attributes.put(attributeKeys.get(i), value);
                        }
                        instances.add(new InstanceImpl(tokens[tokens.length - 1], attributes));
                    }
                }
            }
        }
        ID3 id3 = new ID3Impl(new GainCalculator(), new EntropyCalculator());
        final DecisionTree decisionTree = id3.buildTree(instances);
        printTree(decisionTree);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--------------PLAY--------------");
        playDemo();
        System.out.println("--------------PARTY--------------");
        partyDemo();
    }
}
