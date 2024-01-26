package com.welph.leecode.part_1_500.part_241_260;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。
 * 你需要给出所有可能的组合的结果。有效的运算符号包含 +, - 以及 * 。
 * <p>
 * 示例 1:
 * 输入: "2-1-1"
 * 输出: [0, 2]
 * 解释:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * <p>
 * 示例 2:
 * 输入: "2*3-4*5"
 * 输出: [-34, -14, -10, -10, 10]
 * 解释:
 * 2*(3-(4*5)) = -34
 * (2*3)-(4*5) = -14
 * (2*(3-4))*5 = -10
 * 2*((3-4)*5) = -10
 * ((2*3)-4)*5 = 10
 */
public class Solution241 {

    public static void main(String[] args) {
        // System.out.println(diffWaysToCompute("2-1-1"));
        System.out.println(diffWaysToCompute("2*3-4*5"));
    }

    // + - *
    // 分治算法 假定每个运算符的前后都是合法的()
    public static List<Integer> diffWaysToCompute(String expression) {
        return compute(expression.toCharArray(), 0, expression.length());
    }

    public static List<Integer> compute(char[] chars, int start, int end) {
        // 这里应该有个记忆化搜索 性能会更好(空间换时间)
        List<Integer> res = new ArrayList<>();
        char c;
        int total = 0;
        boolean allNum = true;
        for (int i = start; i < end; i++) {
            c = chars[i];
            if ('+' == c || '-' == c || '*' == c) {
                allNum = false;
                List<Integer> l = compute(chars, start, i);
                List<Integer> r = compute(chars, i + 1, end);
                for (Integer ll : l) {
                    for (Integer rr : r) {
                        if ('+' == c) {
                            res.add(ll + rr);
                        } else if ('-' == c) {
                            res.add(ll - rr);
                        } else {
                            res.add(ll * rr);
                        }
                    }
                }
            } else if (allNum) {
                total = 10 * total + (c - '0');
            }
        }
        if (allNum) {
            res.add(total);
        }
        return res;
    }

    /* 官方题解 */
    static final int ADDITION = -1;
    static final int SUBTRACTION = -2;
    static final int MULTIPLICATION = -3;

    // 动态规划
    @SuppressWarnings("unchecked")
    public List<Integer> diffWaysToCompute2(String expression) {
        List<Integer> ops = new ArrayList<Integer>();// 构建数字和字符
        for (int i = 0; i < expression.length();) {
            if (!Character.isDigit(expression.charAt(i))) {
                if (expression.charAt(i) == '+') {
                    ops.add(ADDITION);
                } else if (expression.charAt(i) == '-') {
                    ops.add(SUBTRACTION);
                } else {
                    ops.add(MULTIPLICATION);
                }
                i++;
            } else {
                int t = 0;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    t = t * 10 + expression.charAt(i) - '0';
                    i++;
                }
                ops.add(t);
            }
        }
        // 开始动态规划
        List<Integer>[][] dp = new List[ops.size()][ops.size()];
        for (int i = 0; i < ops.size(); i++) {
            for (int j = 0; j < ops.size(); j++) {
                dp[i][j] = new ArrayList<Integer>();
            }
        }
        for (int i = 0; i < ops.size(); i += 2) {
            dp[i][i].add(ops.get(i));
        }
        // for (int i = 3; i <= ops.size(); i++) { //官方这里使用了++ 但使用+=2 更好, 直接跳过运算符
        for (int i = 3; i <= ops.size(); i += 2) {
            for (int j = 0; j + i <= ops.size(); j += 2) {// 计算小范围的可能值 每次+2 避开运算符的开始
                int l = j;
                int r = j + i - 1;
                for (int k = j + 1; k < r; k += 2) { // 一段小范围内, 按照那些拆分括号 同样是+2 确定运算符
                    List<Integer> left = dp[l][k - 1];
                    List<Integer> right = dp[k + 1][r];
                    for (int num1 : left) {
                        for (int num2 : right) {
                            if (ops.get(k) == ADDITION) {
                                dp[l][r].add(num1 + num2);
                            } else if (ops.get(k) == SUBTRACTION) {
                                dp[l][r].add(num1 - num2);
                            } else if (ops.get(k) == MULTIPLICATION) {
                                dp[l][r].add(num1 * num2);
                            }
                        }
                    }
                }
            }
        }
        return dp[0][ops.size() - 1];
    }

}
