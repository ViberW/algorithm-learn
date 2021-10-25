package com.welph.leecode.part_1_500.part_381_400;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个用字符串表示的整数的嵌套列表，实现一个解析它的语法分析器。
 * 列表中的每个元素只可能是整数或整数嵌套列表
 * 提示：你可以假定这些字符串都是格式良好的：
 * 字符串非空
 * 字符串不包含空格
 * 字符串只包含数字0-9、[、-、,、]
 * <p>
 * 示例 1：
 * 给定 s = "324",
 * 你应该返回一个 NestedInteger 对象，其中只包含整数值 324。
 * <p>
 * 示例 2：
 * 给定 s = "[123,[456,[789]]]",
 * 返回一个 NestedInteger 对象包含一个有两个元素的嵌套列表：
 * <p>
 * 1. 一个 integer 包含值 123
 * 2. 一个包含两个元素的嵌套列表：
 * .    i.  一个 integer 包含值 456
 * .    ii. 一个包含一个元素的嵌套列表
 * .         a. 一个 integer 包含值 789
 */
public class Solution385 {

    public static void main(String[] args) throws IOException {
        NestedInteger deserialize = deserialize("[123,[456,[789]]]");
        System.out.println(1);
    }

    /**
     * 应该是通过的, leetcode给出的例子,,,,没明白运作原理
     */
    public static NestedInteger deserialize(String s) {
        char[] chars = s.toCharArray();
        int[] ints = new int[1];
        NestedInteger parse = parse(chars, ints, chars.length);
        return parse.isInteger() ? parse : parse.getList().get(0);
    }

    private static NestedInteger parse(char[] chars, int[] ints, int length) {
        char c;
        NestedInteger ni = new MyNestedInteger();
        StringBuilder builder = null;
        int l = ints[0];
        for (; l < length; l++) {
            c = chars[l];
            if (c == '[') {
                ints[0] = l + 1;
                NestedInteger parse = parse(chars, ints, length);
                ni.add(parse);
                l = ints[0];
            } else if (c == ']') {
                break;
            } else if (c == ',') {
                //进入下一层级
                ni.setInteger(Integer.valueOf(builder.toString()));
                builder = null;
            } else {
                if (null == builder) {
                    builder = new StringBuilder();
                }
                builder.append(c);
            }
        }
        if (null != builder) {
            if (ni.isInteger()) {
                ni.setInteger(Integer.valueOf(builder.toString()));
            } else {
                ni.setInteger(Integer.valueOf(builder.toString()));
            }
        }
        ints[0] = l;
        return ni;
    }

    public static class MyNestedInteger implements NestedInteger {
        boolean integer = true;
        Integer val;
        List<NestedInteger> nis;

        public MyNestedInteger() {
        }

        public MyNestedInteger(Integer val) {
            this.val = val;
        }

        @Override
        public boolean isInteger() {
            return integer;
        }

        @Override
        public Integer getInteger() {
            return val;
        }

        @Override
        public void setInteger(int value) {
            this.val = value;
        }

        @Override
        public void add(NestedInteger ni) {
            if (nis == null) {
                nis = new ArrayList<>();
            }
            nis.add(ni);
            this.integer = false;
        }

        @Override
        public List<NestedInteger> getList() {
            return nis;
        }
    }

    public interface NestedInteger {
        boolean isInteger();

        Integer getInteger();

        void setInteger(int value);

        void add(NestedInteger ni);

        List<NestedInteger> getList();
    }
}
