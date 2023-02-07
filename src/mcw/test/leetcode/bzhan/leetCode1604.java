package mcw.test.leetcode.bzhan;

import java.util.*;

/**
 * 力扣公司的员工都使用员工卡来开办公室的门。每当一个员工使用一次他的员工卡，安保系统会记录下员工的名字和使用时间。如果一个员工在一小时时间内使用员工卡的次数大于等于三次，这个系统会自动发布一个 警告 。
 * <p>
 * 给你字符串数组 keyName 和 keyTime ，其中 [keyName[i], keyTime[i]] 对应一个人的名字和他在 某一天 内使用员工卡的时间。
 * <p>
 * 使用时间的格式是 24小时制 ，形如 "HH:MM" ，比方说 "23:51" 和 "09:49" 。
 * <p>
 * 请你返回去重后的收到系统警告的员工名字，将它们按 字典序升序 排序后返回。
 * <p>
 * 请注意 "10:00" - "11:00" 视为一个小时时间范围内，而 "23:51" - "00:10" 不被视为一小时内，因为系统记录的是某一天内的使用情况。
 * <p>
 * 提示：
 * <li>1 <= keyName.length, keyTime.length <= 105</li>
 * <li>keyName.length == keyTime.length</li>
 * <li>keyTime 格式为 "HH:MM" 。</li>
 * <li>保证 [keyName[i], keyTime[i]] 形成的二元对 互不相同 。</li>
 * <li>1 <= keyName[i].length <= 10</li>
 * <li>keyName[i] 只包含小写英文字母。</li>
 * <li></li>
 *
 * @author mcw 2023/2/7 14:29
 */
public class leetCode1604 {


    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, List<Integer>> timeMap = new HashMap<>();
        int n = keyName.length;
        //统计员工的所有打卡时间
        for (int i = 0; i < n; i++) {
            String name = keyName[i];
            String time = keyTime[i];
            timeMap.putIfAbsent(name, new ArrayList<>());
            int hour = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
            int minute = (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
            timeMap.get(name).add(hour * 60 + minute);
        }
        List<String> res = new ArrayList<>();
        Set<String> keySet = timeMap.keySet();

        //统计每个员工在打卡时间中是否出现三次警告
        for (String name : keySet) {
            List<Integer> list = timeMap.get(name);
            Collections.sort(list);
            int size = list.size();
            for (int i = 2; i < size; i++) {
                int time1 = list.get(i - 2), time2 = list.get(i);
                int difference = time2 - time1;
                if (difference <= 60) {
                    res.add(name);
                    break;
                }
            }
        }
        Collections.sort(res);
        return res;
    }
}