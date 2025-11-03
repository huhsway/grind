/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Lowest_Common_Ancestor_of_a_Binary_Search_Tree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // p와 q의 값을 root의 값과 비교합니다.
        int pVal = p.val;
        int qVal = q.val;
        int rootVal = root.val;

        // Case 1: p와 q가 root의 양쪽에 위치하는 경우 (또는 p나 q가 root인 경우)
        // 이 때 root가 LCA가 됩니다.
        if ((pVal < rootVal && qVal > rootVal) || (pVal > rootVal && qVal < rootVal)) {
            return root;
        }

        // Case 2: p와 q가 모두 root보다 작은 경우
        // LCA는 왼쪽 서브트리에 있습니다.
        if (pVal < rootVal && qVal < rootVal) {
            return lowestCommonAncestor(root.left, p, q);
        }

        // Case 3: p와 q가 모두 root보다 큰 경우
        // LCA는 오른쪽 서브트리에 있습니다.
        if (pVal > rootVal && qVal > rootVal) {
            return lowestCommonAncestor(root.right, p, q);
        }
        
        // p와 q 중 하나가 root와 같은 경우, root가 LCA가 됩니다.
        // 예를 들어, p가 root이고 q는 root의 오른쪽 자손일 경우
        // 위 3가지 if문 중 어느 것에도 해당되지 않으므로, 이 부분을 추가해야 합니다.
        // 또는 Case 1에 이 조건을 포함시킬 수도 있습니다.
        return root;
    }
}