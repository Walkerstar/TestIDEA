package mcw.test.leetcode.bzhan;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个长度为 n 的字符串数组 names 。你将会在文件系统中创建 n 个文件夹：在第 i 分钟，新建名为 names[i] 的文件夹。
 * <p>
 * 由于两个文件 不能 共享相同的文件名，因此如果新建文件夹使用的文件名已经被占用，系统会以 (k) 的形式为新文件夹的文件名添加后缀，其中 k 是能保证文件名唯一的 最小正整数 。
 * <p>
 * 返回长度为 n 的字符串数组，其中 ans[i] 是创建第 i 个文件夹时系统分配给该文件夹的实际名称。
 * <p>
 * 提示：
 * <p>
 * 1 <= names.length <= 5 * 10^4
 * 1 <= names[i].length <= 20
 * names[i] 由小写英文字母、数字和/或圆括号组成。
 *
 * @author mcw 2023/3/3 10:28
 */
public class leetCode1487 {

    /**
     * 对于需要被创建的文件名 name，如果文件系统中不存在名为 name 的文件夹，那么直接创建即可，
     * 否则我们需要从 k=1 开始，尝试使用添加后缀 k 的新文件名创建新文件夹。
     * <p>
     * 使用哈希表 index 记录已创建的文件夹的下一后缀序号，遍历 names 数组，记当前遍历的文件名为 name：
     * <p>
     * 1.如果 name 不在哈希表中，那么说明文件系统不存在名为 name 的文件夹，我们直接创建该文件夹，并且记录对应文件夹的下一后缀序号为 1。
     * <p>
     * 2.如果 name 在哈希表中，那么说明文件系统已经存在名为 name 的文件夹，我们在哈希表找到 name 的下一后缀序号 k，
     * 逐一尝试直到添加后缀 k 的新文件名不存在于哈希表中，然后创建该文件夹。
     * 需要注意的是，创建该文件夹后，有两个文件名的下一后缀序号需要修改，首先文件名 name 的下一后缀序号为 k+1，
     * 其次，文件名 name 添加后缀 k 的新文件名的下一后缀序号为 1。
     */
    public String[] getFolderNames(String[] names) {
        Map<String, Integer> index = new HashMap<>();
        int n = names.length;
        String[] res = new String[n];
        for (int i = 0; i < n; i++) {
            String name = names[i];
            if (!index.containsKey(name)) {
                res[i] = name;
                index.put(name, i);
            } else {
                int k = index.get(name);
                while (index.containsKey(addSuffix(name, k))) {
                    k++;
                }
                res[i] = addSuffix(name, k);
                index.put(name, k + 1);
                index.put(addSuffix(name, k), 1);
            }
        }
        return res;
    }

    public String addSuffix(String name, int k) {
        return name + "(" + k + ")";
    }

}
