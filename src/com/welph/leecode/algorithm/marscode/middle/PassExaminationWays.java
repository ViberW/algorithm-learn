package com.welph.leecode.algorithm.marscode.middle;

/**
 * 小S在学校选择了3门必修课和n门选修课程来响应全面发展的教育政策。
 * 现在期末考核即将到来，小S想知道他所有课程的成绩有多少种组合方式能使他及格。
 *
 * 及格的条件是所有课程的平均分不低于60分。
 * 每门课程的成绩是由20道选择题决定，每题5分，答对得分，答错不得分。
 *
 * 为了计算方便，你需要将结果对202220222022取模。
 */
public class PassExaminationWays {

    public static String solution(int n) {
        // Please write your code here
        n = n + 3;
        int max = n * 20;
        long[] f = new long[max + 1];
        f[0] = 1;
        for (int course = 0; course < n; course++) {
            for (int score = max; score >= 1; score--) {
                long total = 0;
                for (int k = 0; k <= 20; k++) { //随便选择一个分数
                    if (score >= k) {
                        total = (total + f[score - k]) % 202220222022L;
                    }
                }
                f[score] = total;
            }
        }
        //再求的0到40
        long total = 0;
        for (int i = n * 12; i <= max; i++) {
            total = (total + f[i]) % 202220222022L;
        }
        return String.valueOf(total).intern();
    }

    public static void main(String[] args) {
        // You can add more test cases here
        System.out.println(solution(3).equals("19195617"));
        System.out.println(solution(6).equals("135464411082"));
        System.out.println(solution(49).equals("174899025576"));
        System.out.println(solution(201).equals("34269227409"));
        System.out.println(solution(888).equals("194187156114"));
    }
}
