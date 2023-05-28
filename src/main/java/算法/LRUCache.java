package 算法;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wzx
 * @date: 2023/5/28
 * LRU正确实现
 */
class LRUCache {
    int capacity;
    Map<Integer,ListNode> map=new HashMap<>();;
    ListNode dumpyHead;
    ListNode dumpyTail;

    class ListNode{
        ListNode pre;
        ListNode next;
        Integer val;
        Integer key;
        ListNode(Integer key,Integer value){
            this.key=key;
            this.val=value;
        }

    }
    public LRUCache(int capacity) {
        this.capacity=capacity;
        this.dumpyHead=new ListNode(-1,-1);
        this.dumpyTail=new ListNode(-1,-1);
        dumpyHead.next=dumpyTail;
        dumpyTail.pre=dumpyHead;
    }

    public int get(int key) {
        if(map.containsKey(key)){
            ListNode node=map.get(key);
            deleteNode(node);
            addHead(node);
            return map.get(key).val;
        }
        else{
            return -1;
        }


    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            //得到该node
            ListNode node=map.get(key);
            node.val=value;
            deleteNode(node);
            addHead(node);
        }
        else{
            if(map.size()==capacity){
                ListNode node=dumpyTail.pre;
                map.remove(node.key);
                deleteTail();
            }
            ListNode newNode=new ListNode(key,value);
            addHead(newNode);
            map.put(key,newNode);
        }
    }

    public void deleteTail(){
        dumpyTail.pre=dumpyTail.pre.pre;
        dumpyTail.pre.next=dumpyTail;
    }
    public void addHead(ListNode newNode){
        newNode.next=dumpyHead.next;
        dumpyHead.next.pre=newNode;
        dumpyHead.next=newNode;
        newNode.pre=dumpyHead;
    }
    public void deleteNode(ListNode node){
        node.pre.next=node.next;
        node.next.pre=node.pre;
        node.next=null;
        node.pre=null;
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        lRUCache.get(1);    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.get(2);    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lRUCache.get(1);    // 返回 -1 (未找到)
        lRUCache.get(3);    // 返回 3
        lRUCache.get(4);    // 返回 4
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */