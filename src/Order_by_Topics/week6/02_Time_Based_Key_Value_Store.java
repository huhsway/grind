// 풀이 두개 다 알아 놓자

// class TimeMap {
    
//     Map<String, TreeMap<Integer, String>> data;

//     public TimeMap() {
//         data = new HashMap<>();
//     }
    
//     public void set(String key, String value, int timestamp) {
// data.putIfAbsent(key, new TreeMap<>());
// data.get(key).put(timestamp, value); 
// 위 두 줄을 아래 한 줄로 대체 가능
// data.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);

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
        data.computeIfAbsent(key, k -> new ArrayList<>()).add(new Pair(timestamp, value));
    }
    
    public String get(String key, int timestamp) {
        if (!data.containsKey(key)) {
            return "";
        }

        List<Pair> list = data.get(key);
        int low = 0, high = list.size() - 1;
        String result = "";

        while (low <= high) {
            int mid = low + (high - low) / 2;
            Pair pair = list.get(mid);

            if (pair.timestamp == timestamp) {
                return pair.value;
            } else if (pair.timestamp < timestamp) {
                result = pair.value;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
