import java.util.*;

class AhoCorasick {
    
    // 노드 클래스
    static class Node {
        Map<Character, Node> children = new HashMap<>();
        Node failLink = null;
        Node outputLink = null;
        String pattern = null;  // 이 노드에서 끝나는 패턴
    }
    
    Node root = new Node();
    
    // 1. 패턴 삽입
    void insert(String pattern) {
        Node cur = root;
        
        for (char c : pattern.toCharArray()) {
            if (!cur.children.containsKey(c)) {
                cur.children.put(c, new Node());
            }
            cur = cur.children.get(c);
        }
        
        cur.pattern = pattern;
    }
    
    // 2. Failure Link + Output Link 구축
    void build() {
        Queue<Node> queue = new LinkedList<>();
        
        // 깊이 1: fail은 root
        for (Node child : root.children.values()) {
            child.failLink = root;
            queue.add(child);
        }
        
        // BFS로 나머지 처리
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            
            for (char c : cur.children.keySet()) {
                Node next = cur.children.get(c);
                
                // Failure Link 계산
                Node f = cur.failLink;
                while (f != null && !f.children.containsKey(c)) {
                    f = f.failLink;
                }
                next.failLink = (f == null) ? root : f.children.get(c);
                
                // Output Link 계산
                if (next.failLink.pattern != null) {
                    next.outputLink = next.failLink;
                } else {
                    next.outputLink = next.failLink.outputLink;
                }
                
                queue.add(next);
            }
        }
    }
    
    // 3. 검색
    List<Result> search(String text) {
        List<Result> results = new ArrayList<>();
        Node cur = root;
        
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            
            // 갈 수 있는 곳 찾기
            while (cur != root && !cur.children.containsKey(c)) {
                cur = cur.failLink;
            }
            
            if (cur.children.containsKey(c)) {
                cur = cur.children.get(c);
            }
            
            // 매칭 확인
            Node temp = cur;
            while (temp != null) {
                if (temp.pattern != null) {
                    int startPos = i - temp.pattern.length() + 1;
                    results.add(new Result(temp.pattern, startPos));
                }
                temp = temp.outputLink;
            }
        }
        
        return results;
    }
    
    // 결과 클래스
    static class Result {
        String pattern;
        int position;
        
        Result(String pattern, int position) {
            this.pattern = pattern;
            this.position = position;
        }
    }
}

public static void main(String[] args) {
    AhoCorasick ac = new AhoCorasick();
    
    // 패턴 삽입
    ac.insert("he");
    ac.insert("she");
    ac.insert("his");
    ac.insert("hers");
    
    // 빌드
    ac.build();
    
    // 검색
    List<AhoCorasick.Result> results = ac.search("ushers");
    
    for (AhoCorasick.Result r : results) {
        System.out.println(r.pattern + " at " + r.position);
    }
}