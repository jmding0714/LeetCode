import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jmding on 2/20/17.
 */
public class GroupAnagrams {

    // Given an array of strings, group anagrams together.

    // For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
    // Return:

    //  [
    //   ["ate", "eat","tea"],
    //   ["nat","tan"],
    //   ["bat"]
    //  ]

    public static List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> result = new ArrayList<>();
        // Create a haspmap to store the string value of sorted character array key, and strings as the value
        HashMap<String,List<String>> map = new HashMap<>();
        // Iterate through the string list
        for (int i = 0 ; i < strs.length; i++){
            // Convert the string to character array
            char[] array = strs[i].toCharArray();
            // Sort the character array
            Arrays.sort(array);
            // Add the string value of the sorted character array, and the string to the map
            List<String> list = map.getOrDefault(String.valueOf(array),new ArrayList<>());
            list.add(strs[i]);
            map.put(String.valueOf(array),list);
        }

        // For each value in the hashmap, add the list to the result
        for (List<String> list : map.values()){
            result.add(list);
        }

        return result;
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = groupAnagrams(strs);
        for (List<String > list : result){
            for (String s : list){
                System.out.print(s + ", ");
            }
            System.out.println();
        }
    }
}
