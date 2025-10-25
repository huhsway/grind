import java.util.HashMap;
import java.util.Map;

class TrieNode {
    Map<Character, TrieNode> children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // 단어 삽입
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }
        node.isEndOfWord = true;
    }

    // 단어 검색
    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.children.get(c);
            if (node == null) return false;
        }
        return node.isEndOfWord;
    }

    // prefix 존재 여부
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            node = node.children.get(c);
            if (node == null) return false;
        }
        return true;
    }

    // 단어 삭제
    public boolean delete(String word) {
        return delete(root, word, 0);
    }

    // delete 재귀 헬퍼 메서드
    private boolean delete(TrieNode node, String word, int index) {
        // 단어 끝에 도달
        if (index == word.length()) {
            // 해당 단어가 존재하지 않으면 삭제 실패
            if (!node.isEndOfWord) {
                return false;
            }
            // 단어의 끝 표시 제거
            node.isEndOfWord = false;
            // 자식 노드가 없으면 현재 노드 삭제 가능
            return node.children.isEmpty();
        }

        char c = word.charAt(index);
        TrieNode childNode = node.children.get(c);
        
        // 해당 문자의 노드가 없으면 삭제 실패
        if (childNode == null) {
            return false;
        }

        // 재귀적으로 자식 노드 삭제
        boolean shouldDeleteCurrentNode = delete(childNode, word, index + 1);

        // 자식 노드를 삭제해야 하면
        if (shouldDeleteCurrentNode) {
            node.children.remove(c);
            // 현재 노드가 다른 단어의 끝이 아니고 자식도 없으면 삭제
            return node.children.isEmpty() && !node.isEndOfWord;
        }

        return false;
    }
}


/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */