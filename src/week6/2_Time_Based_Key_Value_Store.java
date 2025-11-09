// 풀이 두개 다 알아 놓자

// class TimeMap {
    
//     Map<String, TreeMap<Integer, String>> data;

//     public TimeMap() {
//         data = new HashMap<>();
//     }
    
//     public void set(String key, String value, int timestamp) {
//         data.putIfAbsent(key, new TreeMap<>());
//         data.get(key).put(timestamp, value);
//     }

//     public String get(String key, int timestamp) {
//         if (!data.containsKey(key)) {
//             return "";
//         }
        
//         TreeMap<Integer, String> treeMap = data.get(key);
//         // floorEntry: timestamp 이하의 가장 큰 키를 찾음
//         Map.Entry<Integer, String> entry = treeMap.floorEntry(timestamp);
        
//         return entry == null ? "" : entry.getValue();
//     }
// }

class TimeMap {

    // 타임스탬프와 값을 저장하는 내부 클래스
    private static class Pair {
        int timestamp;
        String value;

        public Pair(int timestamp, String value) {
            this.timestamp = timestamp;
            this.value = value;
        }
    }

    Map<String, List<Pair>> data;

    public TimeMap() {
        data = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        data.putIfAbsent(key, new ArrayList<>());
        data.get(key).add(new Pair(timestamp, value));
    }

    public String get(String key, int timestamp) {
        if (!data.containsKey(key)) {
            return "";
        }

        List<Pair> list = data.get(key);
        // 이진 탐색 구현
        int low = 0, high = list.size() - 1;
        String result = "";

        while (low <= high) {
            // 오버플로우 방지를 위해서 low + (high - low) / 2 사용
            // 수학적으로는 같음
            int mid = low + (high - low) / 2;
            Pair pair = list.get(mid);

            if (pair.timestamp == timestamp) {
                return pair.value;
            } else if (pair.timestamp < timestamp) {
                // 현재 타임스탬프가 목표보다 작으면, 이 값이 잠재적인 답이 될 수 있습니다.
                result = pair.value;
                // 더 큰 타임스탬프를 찾기 위해 오른쪽으로 이동합니다.
                low = mid + 1;
            } else {
                // 현재 타임스탬프가 목표보다 크면, 더 작은 타임스탬프를 찾기 위해 왼쪽으로 이동합니다.
                high = mid - 1;
            }
        }
        return result;
    }
}

// /**
//  * Your TimeMap object will be instantiated and called as such:
//  * TimeMap obj = new TimeMap();
//  * obj.set(key,value,timestamp);
//  * String param_2 = obj.get(key,timestamp);
//  */