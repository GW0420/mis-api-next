package com.example.common.utils;

import cn.hutool.core.util.RandomUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 密码生成工具类
 * 功能：生成包含大小写字母、数字、特殊符号的安全密码
 */
public class PasswordUtils {

    // 默认字符池定义
    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER_CASE = LOWER_CASE.toUpperCase();
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*_";

    /**
     * 生成默认8位密码（包含大小写、数字、符号）
     */
    public static String generate() {
        return generate(8, true, true, true, true);
    }

    /**
     * 自定义密码生成
     * @param length     密码长度
     * @param useLower   是否包含小写字母
     * @param useUpper   是否包含大写字母
     * @param useNumber  是否包含数字
     * @param useSymbol  是否包含符号
     */
    public static String generate(int length, boolean useLower, boolean useUpper,
                                  boolean useNumber, boolean useSymbol) {
        if (length < 4) {
            throw new IllegalArgumentException("密码长度至少4位");
        }

        // 1. 构建字符池
        StringBuilder charPool = new StringBuilder();
        List<Character> mandatoryChars = new ArrayList<>();

        if (useLower) {
            charPool.append(LOWER_CASE);
            mandatoryChars.add(RandomUtil.randomChar(LOWER_CASE));
        }
        if (useUpper) {
            charPool.append(UPPER_CASE);
            mandatoryChars.add(RandomUtil.randomChar(UPPER_CASE));
        }
        if (useNumber) {
            charPool.append(NUMBERS);
            mandatoryChars.add(RandomUtil.randomChar(NUMBERS));
        }
        if (useSymbol) {
            charPool.append(SYMBOLS);
            mandatoryChars.add(RandomUtil.randomChar(SYMBOLS));
        }

        if (charPool.length() == 0) {
            throw new IllegalArgumentException("至少启用一种字符类型");
        }

        // 2. 确保每种类型至少一个字符
        StringBuilder password = new StringBuilder();
        for (Character ch : mandatoryChars) {
            password.append(ch);
        }

        // 3. 填充剩余随机字符
        int remaining = length - mandatoryChars.size();
        for (int i = 0; i < remaining; i++) {
            password.append(RandomUtil.randomChar(charPool.toString()));
        }

        // 4. 打乱顺序避免固定模式
        List<Character> chars = new ArrayList<>();
        for (char c : password.toString().toCharArray()) {
            chars.add(c);
        }
        Collections.shuffle(chars);

        StringBuilder finalPassword = new StringBuilder();
        for (char c : chars) {
            finalPassword.append(c);
        }

        return finalPassword.toString();
    }
}
