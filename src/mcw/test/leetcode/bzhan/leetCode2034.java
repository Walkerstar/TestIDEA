package mcw.test.leetcode.bzhan;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 给你一支股票价格的数据流。数据流中每一条记录包含一个 时间戳 和该时间点股票对应的 价格 。<br/>
 * <p>
 * 不巧的是，由于股票市场内在的波动性，股票价格记录可能不是按时间顺序到来的。某些情况下，有的记录可能是错的。
 * 如果两个有相同时间戳的记录出现在数据流中，前一条记录视为错误记录，后出现的记录 更正 前一条错误的记录。<br/>
 * <p>
 * 请你设计一个算法，实现：
 * <li>更新 股票在某一时间戳的股票价格，如果有之前同一时间戳的价格，这一操作将 更正 之前的错误价格。</li>
 * <li>找到当前记录里 最新股票价格 。最新股票价格 定义为时间戳最晚的股票价格。</li>
 * <li>找到当前记录里股票的 最高价格 。</li>
 * <li>找到当前记录里股票的 最低价格 。</li><br/>
 * <p>
 * 请你实现 StockPrice 类：
 * <li>StockPrice() 初始化对象，当前无股票价格记录。</li>
 * <li>void update(int timestamp, int price) 在时间点 timestamp 更新股票价格为 price 。</li>
 * <li>int current() 返回股票 最新价格 。</li>
 * <li>int maximum() 返回股票 最高价格 。</li>
 * <li>int minimum() 返回股票 最低价格 。</li>
 *
 * @author MCW 2022/1/23
 */
public class leetCode2034 {

    class StockPrice {
        int maxTimestamp;//最新时间
        HashMap<Integer, Integer> timePriceMap;//存放各时间戳的价格
        TreeMap<Integer, Integer> prices;//有序存放价格出现的次数

        public StockPrice() {
            maxTimestamp = 0;
            timePriceMap = new HashMap<>();
            prices = new TreeMap<>();
        }

        public void update(int timestamp, int price) {
            maxTimestamp = Math.max(maxTimestamp, timestamp);
            int prevPrice = timePriceMap.getOrDefault(timestamp, 0);
            timePriceMap.put(timestamp, price);
            if (prevPrice > 0) {
                prices.put(prevPrice, prices.get(prevPrice) - 1);
                if (prices.get(prevPrice) == 0) {
                    prices.remove(prevPrice);
                }
            }
            prices.put(price, prices.getOrDefault(price, 0) + 1);
        }

        public int current() {
            return timePriceMap.get(maxTimestamp);
        }

        public int maximum() {
            return prices.lastKey();
        }

        public int minimum() {
            return prices.firstKey();
        }
    }

    class StockPrice1 {
        int maxTimestamp;
        HashMap<Integer, Integer> timePriceMap;
        PriorityQueue<int[]> pqMax;
        PriorityQueue<int[]> pqMin;

        public StockPrice1() {
            maxTimestamp = 0;
            timePriceMap = new HashMap<>();
            pqMax = new PriorityQueue<>((a, b) -> b[0] - a[0]);
            pqMin = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        }

        public void update(int timestamp, int price) {
            maxTimestamp = Math.max(maxTimestamp, timestamp);
            timePriceMap.put(timestamp, price);
            pqMax.offer(new int[]{price, timestamp});
            pqMin.offer(new int[]{price, timestamp});
        }

        public int current() {
            return timePriceMap.get(maxTimestamp);
        }

        public int maximum() {
            while (true) {
                int[] priceTime = pqMax.peek();
                int price = priceTime[0], timestamp = priceTime[1];
                if (timePriceMap.get(timestamp) == price) {
                    return price;
                }
                pqMax.poll();
            }
        }

        public int minimum() {
            while (true) {
                int[] priceTime = pqMin.peek();
                int price = priceTime[0], timestamp = priceTime[1];
                if (timePriceMap.get(timestamp) == price) {
                    return price;
                }
                pqMin.poll();
            }
        }
    }
}
