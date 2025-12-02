import java.util.*;

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> parent = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();

        // 1. 초기화: 모든 이메일을 자기 자신의 부모로 설정
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                parent.put(email, email);
                emailToName.put(email, name);
            }
        }

        // 2. Union: 같은 계정 내의 이메일들을 병합
        for (List<String> account : accounts) {
            String firstEmail = account.get(1);
            for (int i = 2; i < account.size(); i++) {
                union(parent, firstEmail, account.get(i));
            }
        }

        // 3. 그룹화: 같은 루트를 가진 이메일들을 모음
        Map<String, List<String>> mergedAccounts = new HashMap<>();
        for (String email : parent.keySet()) {
            String root = find(parent, email);
            mergedAccounts.computeIfAbsent(root, k -> new ArrayList<>()).add(email);
        }

        // 4. 결과 정리: 정렬하고 이름 추가
        List<List<String>> result = new ArrayList<>();
        for (String root : mergedAccounts.keySet()) {
            List<String> emails = mergedAccounts.get(root);
            Collections.sort(emails);
            String name = emailToName.get(root);
            emails.add(0, name);
            result.add(emails);
        }

        return result;
    }

    private String find(Map<String, String> parent, String s) {
        if (parent.get(s).equals(s)) {
            return s;
        }
        String root = find(parent, parent.get(s));
        parent.put(s, root);
        return root;
    }

    private void union(Map<String, String> parent, String s1, String s2) {
        String root1 = find(parent, s1);
        String root2 = find(parent, s2);
        if (!root1.equals(root2)) {
            parent.put(root2, root1);
        }
    }
}