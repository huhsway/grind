import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

class RandomizedSet {
    // 값을 저장하는 리스트. O(1) getRandom을 위해 사용.
    private List<Integer> vals;
    // 값과 해당 값이 리스트에서 차지하는 인덱스를 매핑하는 맵. O(1) insert/remove 확인 및 인덱스 검색을 위해 사용.
    private Map<Integer, Integer> valToIndex;
    // 무작위 값 추출을 위한 Random 객체
    private Random rand;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        vals = new ArrayList<>();
        valToIndex = new HashMap<>();
        rand = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        // 이미 값이 존재하면 false 반환 (O(1))
        if (valToIndex.containsKey(val)) {
            return false;
        }

        // 1. 맵에 값과 현재 리스트의 마지막 인덱스를 저장 (O(1))
        valToIndex.put(val, vals.size());
        // 2. 리스트에 값 추가 (O(1))
        vals.add(val);

        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        // 값이 존재하지 않으면 false 반환 (O(1))
        if (!valToIndex.containsKey(val)) {
            return false;
        }

        // 삭제할 값의 현재 인덱스를 가져옴 (O(1))
        int indexToRemove = valToIndex.get(val);

        // O(1) 시간에 리스트 중간의 요소를 삭제하는 핵심 방법:
        // 1. 리스트의 마지막 요소를 가져옴 (O(1))
        int lastElement = vals.get(vals.size() - 1);

        // 2. 마지막 요소를 삭제할 위치(indexToRemove)로 이동시킴 (O(1))
        vals.set(indexToRemove, lastElement);

        // 3. 맵에서 마지막 요소의 인덱스를 새로운 위치(indexToRemove)로 업데이트 (O(1))
        valToIndex.put(lastElement, indexToRemove);

        // 4. 리스트에서 마지막 요소를 삭제 (O(1))
        vals.remove(vals.size() - 1);
        
        // 5. 맵에서 제거할 값(val)을 삭제 (O(1))
        valToIndex.remove(val);

        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        // 리스트의 크기(size)를 이용하여 0부터 size-1 사이의 무작위 인덱스를 생성 (O(1))
        int randomIndex = rand.nextInt(vals.size());
        
        // 해당 무작위 인덱스의 요소를 반환 (O(1))
        return vals.get(randomIndex);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */