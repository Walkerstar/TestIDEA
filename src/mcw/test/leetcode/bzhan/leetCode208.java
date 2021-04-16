package mcw.test.leetcode.bzhan;

/**
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 *
 * 请你实现 Trie 类：
 *
 *
 * void insert(String word)
 * boolean search(String word)
 * boolean startsWith(String prefix) 。
 *
 * @author mcw 2021\4\14 0014-18:23
 */
public class leetCode208 {
    static class Trie{
        private Trie[] children;
        private boolean isEnd;

        /**
         * Trie() 初始化前缀树对象。
         */
        public Trie(){
            children=new Trie[26];
            isEnd=false;
        }

        /**
         * 向前缀树中插入字符串 word 。
         */
        public void insert(String word) {
            Trie node=this;
            for (int i = 0; i < word.length(); i++) {
                char ch= word.charAt(i);
                int index=ch-'a';
                if (node.children[index]==null){
                    node.children[index]=new Trie();
                }
                node=node.children[index];
            }
            node.isEnd=true;
        }

        /**
         * 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
         */
        public boolean search(String word){
            Trie node=searchPrefix(word);
            return node!=null && node.isEnd;
        }

        /**
         * 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false
         */
        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;
        }

        private Trie searchPrefix(String prefix) {
            Trie node=this;
            for (int i = 0; i < prefix.length(); i++) {
                char ch=prefix.charAt(i);
                int index=ch-'a';
                if (node.children[index]==null){
                    return null;
                }
                node= node.children[index];
            }
            return node;
        }
    }


    class TrieNode {
        boolean isWord;//是否是单词
        TrieNode[] children;//26个小写字母

        public TrieNode() {
            isWord = true;
            children = new TrieNode[26];
        }
    }

    public class Triee {
        //根节点，根节点是不存储任何字母的，从根节点的
        //子节点开始存储
        private TrieNode root;

        public Triee() {
            root = new TrieNode();
        }

        //插入字符串
        public void insert(String word) {
            TrieNode current = root;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                //判断字符有没有创建，如果没有创建就创建
                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                    //中间的字符不是完整的单词
                    current.children[index].isWord = false;
                }
                current = current.children[index];
            }
            //最后一个字符才能构成一个完整的单词
            current.isWord = true;
        }

        public boolean search(String word) {
            TrieNode current = find(word);
            return current != null && current.isWord;
        }

        public boolean startsWith(String prefix) {
            return find(prefix) != null;
        }

        private TrieNode find(String str) {
            TrieNode current = root;
            int length = str.length();
            for (int i = 0; i < length; i++) {
                int index = str.charAt(i) - 'a';
                if ((current = current.children[index]) == null)
                    return null;
            }
            return current;
        }
    }

//    作者：sdwwld
//    链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/shu-ju-jie-gou-he-suan-fa-zi-dian-shu-de-6t43/
}

