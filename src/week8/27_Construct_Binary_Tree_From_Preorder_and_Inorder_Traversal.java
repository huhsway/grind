/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int val) { this.val = val; }
 * }
 */
import java.util.HashMap;
import java.util.Map;

class Solution {
    // 중위 순회 값과 해당 인덱스를 매핑하는 HashMap
    private Map<Integer, Integer> inorderMap = new HashMap<>();
    
    // 전위 순회 배열에서 현재 루트로 사용할 요소의 인덱스를 추적 (전역 변수로 관리)
    private int preorderIndex = 0;

    /**
     * 메인 함수
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 중위 순회 배열의 모든 값과 인덱스를 맵에 저장
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        
        // 전위 순회 배열, 중위 순회 범위(시작/끝)를 사용하여 재귀 호출 시작
        return build(preorder, 0, inorder.length - 1);
    }

    /**
     * 재귀적으로 트리를 구성하는 함수
     * @param preorder 전위 순회 배열
     * @param inorderStart 중위 순회 부분 배열의 시작 인덱스
     * @param inorderEnd 중위 순회 부분 배열의 끝 인덱스
     * @return 현재 서브트리의 루트 노드
     */
    private TreeNode build(int[] preorder, int inorderStart, int inorderEnd) {
        // 1. 기저 조건: 중위 순회 범위가 유효하지 않으면 null 반환
        if (inorderStart > inorderEnd) {
            return null;
        }

        // 2. 루트 노드 생성
        // 전위 순회 배열의 현재 위치(preorderIndex)의 값이 현재 서브트리의 루트입니다.
        int rootVal = preorder[preorderIndex];
        TreeNode root = new TreeNode(rootVal);
        preorderIndex++; // 다음 재귀 호출을 위해 전위 순회 인덱스를 1 증가

        // 3. 중위 순회에서 루트의 위치 찾기 (HashMap으로 O(1)에 검색)
        int rootInorderIndex = inorderMap.get(rootVal);

        // 4. 왼쪽 서브트리 재귀 호출
        // 중위 순회 범위: [inorderStart, rootInorderIndex - 1]
        root.left = build(preorder, inorderStart, rootInorderIndex - 1);

        // 5. 오른쪽 서브트리 재귀 호출
        // 중위 순회 범위: [rootInorderIndex + 1, inorderEnd]
        root.right = build(preorder, rootInorderIndex + 1, inorderEnd);

        return root;
    }
}