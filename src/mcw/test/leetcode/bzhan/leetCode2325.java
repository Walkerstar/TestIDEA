package mcw.test.leetcode.bzhan;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你字符串 key 和 message ，分别表示一个加密密钥和一段加密消息。解密 message 的步骤如下：
 * <p>
 * 使用 key 中 26 个英文小写字母第一次出现的顺序作为替换表中的字母 顺序 。
 * 将替换表与普通英文字母表对齐，形成对照表。
 * 按照对照表 替换 message 中的每个字母。
 * 空格 ' ' 保持不变。
 * 例如，key = "happy boy"（实际的加密密钥会包含字母表中每个字母 至少一次），
 * 据此，可以得到部分对照表（'h' -> 'a'、'a' -> 'b'、'p' -> 'c'、'y' -> 'd'、'b' -> 'e'、'o' -> 'f'）。
 * 返回解密后的消息。
 * <p>
 * 提示：
 * <li>26 <= key.length <= 2000</li>
 * <li>key 由小写英文字母及 ' ' 组成</li>
 * <li>key 包含英文字母表中每个字符（'a' 到 'z'）至少一次</li>
 * <li>1 <= message.length <= 2000</li>
 * <li>message 由小写英文字母和 ' ' 组成</li>
 *
 * @author MCW 2023/2/1
 */
public class leetCode2325 {

    public String decodeMessage(String key, String message) {
        char cur = 'a';
        Map<Character, Character> rules = new HashMap<>();
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (c != ' ' && !rules.containsKey(c)) {
                rules.put(c, cur);
                ++cur;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (c != ' ') {
                c = rules.get(c);
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
