package mcw.test.leetcode.bzhan;

import java.util.*;

/**
 * 实现RandomizedSet 类：
 *
 * RandomizedSet() 初始化 RandomizedSet 对象
 * bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
 * bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
 * int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
 * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
 *
 * @author mcw 2022/4/13 11:32
 */
public class leetCode380 {

    /**
     * 变长数组 + 哈希表
     */
    class RandomizeSet{
        List<Integer> nums;
        Map<Integer,Integer> indices;
        Random random;

        public RandomizeSet(){
            nums=new ArrayList<>();
            indices=new HashMap<>();
            random=new Random();
        }

        /**
         * 插入操作时，首先判断 val 是否在哈希表中，如果已经存在则返回 false，如果不存在则插入 val，操作如下：
         *
         * 在变长数组的末尾添加 val；
         * 在添加 val 之前的变长数组长度为 val 所在下标 index，将 val 和下标 index 存入哈希表；
         * 返回 true。
         */
        public boolean insert(int val){
            if (indices.containsKey(val)){
                return false;
            }
            int index=nums.size();
            nums.add(val);
            indices.put(val,index);
            return true;
        }

        /**
         *删除操作时，首先判断 val 是否在哈希表中，如果不存在则返回 false，如果存在则删除 val，操作如下：
         *
         * 从哈希表中获得 val 的下标 index；
         * 将变长数组的最后一个元素 last 移动到下标 index 处，在哈希表中将 last 的下标更新为 index；
         * 在变长数组中删除最后一个元素，在哈希表中删除 val；
         * 返回 true。
         *
         * 删除操作的重点在于将变长数组的最后一个元素移动到待删除元素的下标处，然后删除变长数组的最后一个元素。
         * 该操作的时间复杂度是 O(1)，且可以保证在删除操作之后变长数组中的所有元素的下标都连续，方便插入操作和获取随机元素操作。
         */
        public boolean remove(int val){
            if(!indices.containsKey(val)){
                return false;
            }
            int index=indices.get(val);
            int last=nums.get(nums.size()-1);
            nums.set(index,last);
            indices.put(last,index);
            nums.remove(nums.size()-1);
            indices.remove(val);
            return true;
        }

        /**
         * 获取随机元素操作时，由于变长数组中的所有元素的下标都连续，因此随机选取一个下标，返回变长数组中该下标处的元素。
         */
        public int getRandom(){
            int randomIndex=random.nextInt(nums.size());
            return nums.get(randomIndex);
        }
    }
}
