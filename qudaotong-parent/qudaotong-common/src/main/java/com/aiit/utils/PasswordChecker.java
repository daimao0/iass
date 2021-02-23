package com.aiit.utils;

import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 验证密码是否符合规则
 */
@Data
public class PasswordChecker {

    private boolean upperCase = false; // 包含大写字母
    private boolean lowerCase = false; // 包含小写字母
    private boolean letter = true; // 包含字母
    private boolean digit = true; // 包含数字
    private boolean special = true; // 包含特殊字符
    private Set<Character> specialCharSet = null; // 特殊字符集合
    private int minLength = 8; // 最小长度
    private int maxLength = 20; // 最大长度

    public PasswordChecker() {
        this.specialCharSet = defaultSpecialCharSet();
    }

    /**
     * 密码符合规则，返回true
     */
    public boolean check(String password) {

        if (password == null || password.length() < this.minLength || password.length() > this.maxLength) {
            // 长度不符合
            return false;
        }
        //密码必须同时拥有字母和数字,arr[0]代表字母是否存在，arr[1]代表数字是否存在
        boolean[] arr = {false, false};

        boolean containUpperCase = false;
        boolean containLowerCase = false;
        boolean containLetter = true;
        boolean containDigit = true;
        boolean containSpecial = true;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                containUpperCase = true;
                containLetter = true;
                arr[0] = true;
            } else if (Character.isLowerCase(ch)) {
                containLowerCase = true;
                containLetter = true;
                arr[0] = true;
            } else if (Character.isDigit(ch)) {
                containDigit = true;
                arr[1] = true;
            } else if (this.specialCharSet.contains(ch)) {
                containSpecial = true;
            } else {
                // 非法字符
                return false;
            }
        }

        //如果密码中不同时包含数字和字母返回错误
        if (!(arr[0] && arr[1])) {
            return false;
        }

        if (this.upperCase && !containUpperCase) {
            return false;
        }
        if (this.lowerCase && !containLowerCase) {
            return false;
        }
        if (this.letter && !containLetter) {
            return false;
        }
        if (this.digit && !containDigit) {
            return false;
        }
        if (this.special && !containSpecial) {
            return false;
        }
        return true;
    }

    public static Set<Character> defaultSpecialCharSet() {
        Set<Character> specialChars = new LinkedHashSet<>();
        // 键盘上能找到的符号
        specialChars.add(Character.valueOf('~'));
        specialChars.add(Character.valueOf('`'));
        specialChars.add(Character.valueOf('!'));
        specialChars.add(Character.valueOf('@'));
        specialChars.add(Character.valueOf('#'));
        specialChars.add(Character.valueOf('$'));
        specialChars.add(Character.valueOf('%'));
        specialChars.add(Character.valueOf('^'));
        specialChars.add(Character.valueOf('&'));
        specialChars.add(Character.valueOf('*'));
        specialChars.add(Character.valueOf('('));
        specialChars.add(Character.valueOf(')'));
        specialChars.add(Character.valueOf('-'));
        specialChars.add(Character.valueOf('_'));
        specialChars.add(Character.valueOf('+'));
        specialChars.add(Character.valueOf('='));
        specialChars.add(Character.valueOf('{'));
        specialChars.add(Character.valueOf('['));
        specialChars.add(Character.valueOf('}'));
        specialChars.add(Character.valueOf(']'));
        specialChars.add(Character.valueOf('|'));
        specialChars.add(Character.valueOf('\\'));
        specialChars.add(Character.valueOf(':'));
        specialChars.add(Character.valueOf(';'));
        specialChars.add(Character.valueOf('"'));
        specialChars.add(Character.valueOf('\''));
        specialChars.add(Character.valueOf('<'));
        specialChars.add(Character.valueOf(','));
        specialChars.add(Character.valueOf('>'));
        specialChars.add(Character.valueOf('.'));
        specialChars.add(Character.valueOf('?'));
        specialChars.add(Character.valueOf('/'));
        return specialChars;
    }
}
