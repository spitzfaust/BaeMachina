package baemachina.id3;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tobias.
 */
public class DecisionTree {
    private Node root;

    public DecisionTree(DecisionTree.Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public static class Node {
        private String value;
        private Node parent;
        private Map<Enum, Node> children;

        public Node() {
        }

        public Node(Node parent, String value) {
            this.value = value;
            this.parent = parent;
        }

        public void setChildren(Map<Enum, Node> children) {
            this.children = children;
        }

        public String getValue() {
            return value;
        }

        public Map<Enum, Node> getChildren() {
            return children;
        }
    }
}
