import java.util.*;

    class Node implements Comparable<Node> {
        Character symbol;
        int frequency;
        Node left;
        Node right;

        public Node(Character symbol, int frequency, Node left, Node right) {
            this.symbol = symbol;
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {
            return this.left == null && this.right == null;
        }

        public int compareTo(Node other) {
            return Integer.compare(this.frequency, other.frequency);
        }
    }

    public class Question6a {

        private static Question6a Huffman;

        public static Map<Character, String> encode(String text) {
            Map<Character, Integer> freq = new HashMap<>();
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                freq.put(c, freq.getOrDefault(c, 0) + 1);
            }
            PriorityQueue<Node> pq = new PriorityQueue<>();
            for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
                pq.offer(new Node(entry.getKey(), entry.getValue(), null, null));
            }
            while (pq.size() > 1) {
                Node left = pq.poll();
                Node right = pq.poll();
                Node parent = new Node(null, left.frequency + right.frequency, left, right);
                pq.offer(parent);
            }
            Node root = pq.poll();
            Map<Character, String> code = new HashMap<>();
            buildCodes(root, new StringBuilder(), code);
            Map<Character, String> encodedText = new HashMap<>();
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                encodedText.put(c, code.get(c));
            }
            return encodedText;
        }

        private static void buildCodes(Node node, StringBuilder prefix, Map<Character, String> code) {
            if (node.isLeaf()) {
                code.put(node.symbol, prefix.toString());
            } else {
                prefix.append('0');
                buildCodes(node.left, prefix, code);
                prefix.deleteCharAt(prefix.length() - 1);
                prefix.append('1');
                buildCodes(node.right, prefix, code);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }

        public static String decode(Map<Character, String> code, String encodedText) {
            StringBuilder sb = new StringBuilder();
            StringBuilder buffer = new StringBuilder();
            for (int i = 0; i < encodedText.length(); i++) {
                buffer.append(encodedText.charAt(i));
                for (Map.Entry<Character, String> entry : code.entrySet()) {
                    if (entry.getValue().equals(buffer.toString())) {
                        sb.append(entry.getKey());
                        buffer.setLength(0);
                        break;
                    }
                }
            }
            return sb.toString();
        }

        public static void main(String[] args) {
            String text = "mississippi river";
            Map<Character, String> encodedText = Huffman.encode(text);
            System.out.println("Encoded text: " + encodedText);
            String decodedText = Huffman.decode(encodedText, "1010111001101101001110100101010111111101110111000111011011111001010000");
            System.out.println("Decoded text: " + decodedText);
        }

}
