package mcw.test.leetcode.bzhan;

import java.util.*;

/**
 * 给定一个保存员工信息的数据结构，它包含了员工 唯一的 id ，重要度 和 直系下属的 id 。
 * <p>
 * 比如，员工 1 是员工 2 的领导，员工 2 是员工 3 的领导。他们相应的重要度为 15 , 10 , 5 。那么员工 1 的数据结构是 [1, 15, [2]] ，
 * 员工 2的 数据结构是 [2, 10, [3]] ，员工 3 的数据结构是 [3, 5, []] 。注意虽然员工 3 也是员工 1 的一个下属，但是由于并不是
 * 直系下属，因此没有体现在员工 1 的数据结构中。
 * <p>
 * 现在输入一个公司的所有员工信息，以及单个员工 id ，返回这个员工和他所有下属的重要度之和。
 *
 * @author mcw 2021\5\1 0001-14:30
 */
public class leetCode690 {
    Map<Integer, Employee> map = new HashMap<>();

    //深度优先
    public int getImportance(List<Employee> employees, int id) {
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        return dfs(id);
    }

    private int dfs(int id) {
        Employee employee = map.get(id);
        int total = employee.importance;
        List<Integer> subordinates = employee.subordinates;
        for (Integer subId : subordinates) {
            total += dfs(subId);
        }
        return total;
    }

    //广度优先
    public int getImportance1(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        int total = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(id);
        while (!queue.isEmpty()) {
            int curId = queue.poll();
            Employee employee = map.get(curId);
            total += employee.importance;
            List<Integer> subordinates = employee.subordinates;
            for (Integer subId : subordinates) {
                queue.offer(subId);
            }
        }
        return total;
    }
}

class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};
