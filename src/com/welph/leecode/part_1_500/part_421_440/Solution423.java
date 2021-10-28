package com.welph.leecode.part_1_500.part_421_440;

/**
 * 给定一个非空字符串，其中包含字母顺序打乱的英文单词表示的数字0-9。按升序输出原始的数字。
 * <p>
 * 注意:
 * 输入只包含小写英文字母。
 * 输入保证合法并可以转换为原始的数字，这意味着像 "abc" 或 "zerone" 的输入是不允许的。
 * 输入字符串的长度小于 50,000。
 * <p>
 * 示例 1:
 * 输入: "owoztneoer"
 * 输出: "012" (zeroonetwo)
 * <p>
 * 示例 2:
 * 输入: "fviefuro"
 * 输出: "45" (fourfive)
 */
public class Solution423 {

    public static void main(String[] args) {
        System.out.println(originalDigits("owoztneoer"));
    }

    //Map<String, Integer> map = new HashMap<>();
    //        map.put("zero", 0);  //z -0
    //        map.put("one", 1);
    //        map.put("two", 2);  //w -2
    //        map.put("three", 3);
    //        map.put("four", 4); //u-4
    //        map.put("five", 5);
    //        map.put("six", 6); //x-6
    //        map.put("seven", 7);
    //        map.put("eight", 8);//g -8
    //        map.put("nine", 9); //
    public static String originalDigits(String s) {
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }
        int[] ret = new int[10];
        ret[0] = arr['z' - 'a'];
        ret[2] = arr['w' - 'a'];
        ret[4] = arr['u' - 'a'];
        ret[6] = arr['x' - 'a'];
        ret[8] = arr['g' - 'a'];
        ret[3] = arr['h' - 'a'] - ret[8];
        ret[5] = arr['f' - 'a'] - ret[4];
        ret[7] = arr['s' - 'a'] - ret[6];
        ret[9] = arr['i' - 'a'] - ret[5] - ret[6] - ret[8];
        ret[1] = arr['o' - 'a'] - ret[0] - ret[2] - ret[4];
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < ret[i]; j++) {
                stringBuilder.append(i);
            }
        }
        return stringBuilder.toString();
    }
}
