/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    // 원본 노드 -> 복제된 노드를 매핑하는 맵
    private HashMap<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        // 1. null 입력 처리
        if (node == null) {
            return null;
        }

        // 2. 이미 복제된 노드인지 확인
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // 3. 현재 노드 복제
        Node cloneNode = new Node(node.val);
        // 복제본을 맵에 추가 (재귀 호출 전에 추가해야 순환을 처리할 수 있음)
        visited.put(node, cloneNode);

        // 4. 이웃 노드 재귀적으로 복제
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }

        return cloneNode;
    }
}
