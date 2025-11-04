/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        
        // DFS 헬퍼 함수 호출: 루트 노드, 현재 레벨(0), 결과 리스트 전달
        dfs(root, 0, result);
        
        return result;
    }

    /**
     * DFS를 이용해 오른쪽에서 보이는 노드를 찾는 재귀 함수
     * 💡 핵심: 오른쪽 자식 노드를 먼저 탐색합니다.
     * @param node 현재 방문 중인 노드
     * @param level 현재 노드의 레벨(깊이)
     * @param result 오른쪽에서 보이는 노드들의 리스트
     */
    private void dfs(TreeNode node, int level, List<Integer> result) {
        // 1. 기본 조건
        if (node == null) {
            return;
        }

        // 2. 결과 추가 조건: 현재 레벨에 처음 도달했을 때만 노드 값을 추가합니다.
        //    (result.size()는 현재까지 발견한 가장 깊은 레벨 + 1)
        //    오른쪽 우선 탐색 덕분에, 이 노드가 그 레벨에서 가장 오른쪽 노드입니다.
        if (level == result.size()) {
            result.add(node.val);
        }

        // 3. 재귀 탐색: 오른쪽 우선으로 탐색
        //    오른쪽 자식 노드를 먼저 방문하여 해당 레벨의 "첫 번째 노드"가 되도록 합니다.
        dfs(node.right, level + 1, result);
        
        //    왼쪽 자식 노드는 나중에 방문합니다. (오른쪽 노드가 없을 때만 결과에 추가될 수 있습니다.)
        dfs(node.left, level + 1, result);
    }
}