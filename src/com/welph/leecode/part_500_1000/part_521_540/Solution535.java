package com.welph.leecode.part_500_1000.part_521_540;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * TinyURL 是一种 URL 简化服务， 比如：当你输入一个 URL https://leetcode.com/problems/design-tinyurl 时，
 * 它将返回一个简化的URL http://tinyurl.com/4e9iAk 。请你设计一个类来加密与解密 TinyURL 。
 * <p>
 * 加密和解密算法如何设计和运作是没有限制的，你只需要保证一个 URL 可以被加密成一个 TinyURL ，并且这个 TinyURL 可以用解密方法恢复成原本的 URL 。
 * <p>
 * 实现 Solution 类：
 * Solution() 初始化 TinyURL 系统对象。
 * String encode(String longUrl) 返回 longUrl 对应的 TinyURL 。
 * String decode(String shortUrl) 返回 shortUrl 原本的 URL 。题目数据保证给定的 shortUrl 是由同一个系统对象加密的。
 * <p>
 * 示例：
 * 输入：url = "https://leetcode.com/problems/design-tinyurl"
 * 输出："https://leetcode.com/problems/design-tinyurl"
 * 解释：
 * Solution obj = new Solution();
 * string tiny = obj.encode(url); // 返回加密后得到的 TinyURL 。
 * string ans = obj.decode(tiny); // 返回解密后得到的原本的 URL 。
 * <p>
 * 提示：
 * 1 <= url.length <= 10^4
 * 题目数据保证 url 是一个有效的 URL
 */
public class Solution535 {

    public static void main(String[] args) {
        Codec codec = new Codec();
        String encode = codec.encode("https://leetcode.com/problems/design-tinyurl");
        System.out.println(encode);
        System.out.println(codec.decode(encode));
    }

    /**
     * 短连接
     */
    static public class Codec {

        static String prefix = "http://tinyurl.com/";
        Map<String, String> encode = new HashMap<>();
        Map<String, String> decode = new HashMap<>();

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            //生成一个key
            if (encode.containsKey(longUrl)) {
                return prefix + encode.get(longUrl);
            }
            Integer hashCode = longUrl.hashCode();
            String key = Integer.toHexString(hashCode);
            while (decode.containsKey(key)) {//开放寻址法
                key = Integer.toHexString(++hashCode);
            }
            encode.put(longUrl, key);
            decode.put(key, longUrl);
            return prefix + key.toUpperCase();
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return decode.get(shortUrl.substring(prefix.length()).toLowerCase());
        }
    }
}
