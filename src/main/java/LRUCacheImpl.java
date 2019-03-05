public class LRUCacheImpl {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache( 3 /* capacity */ );

        for(int i = 1; i <= 100; i++) {

           // System.out.println("----before put start---");
           // cache.printTable();
           // System.out.println("----before put end---");

           // System.out.println("----after put start---");
            cache.put(i, i + 1);

            if(i == 90) {
                cache.get(90);
                cache.get(88);
                cache.get(89);
            }

           // cache.printTable();
           // System.out.println("----after put end---");
        }
    }
}

class LRUCache {
    private Node[] table;
    private int capacity = 16; // power of 2
    private Node head;
    private int size;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.table = new Node[capacity];
        head = null;
    }
    public int get(int key) {
        int hash = hash(key);
        int tableIndex = hash & (table.length - 1);
        Node node = table[tableIndex];

        for(; node != null; node = node.next) {
            if(node.keyEquals(key)) {
                if(node != head) {
                    // remove node;
                    removeNode(node);

                    // set head
                    insertAfter(head, node);

                } else {
                    head = head.before;
                }
                return node.value;
            }
        }
        return -1;
    }

    private void removeByKey(int key) {
        int hash = hash(key);
        int tableIndex = hash & (table.length - 1);
        Node node = table[tableIndex];
        Node pre = node;

        for(; node != null; node = node.next) {
            if(node.keyEquals(key)) {
                if(pre == node) {
                    table[tableIndex] = node.next;
                } else {
                    pre.next = node.next;
                }
                return;
            }
            pre = node;
        }
    }

    public void put(int key, int value) {
        int hash = hash(key);
        int tableIndex = hash & (table.length - 1);
        Node node = table[tableIndex];

        for(; node != null; node = node.next) {
            if(node.keyEquals(key)) {
                if (node != head) {
                    // remove node;
                    removeNode(node);

                    // set head
                    insertAfter(head, node);

                } else {
                    head = head.before;
                }
                node.value = value;
                return;
            }
        }

        if(size == capacity) {
            removeByKey(head.key);
            if(head.before == head) {
                head = null;
            } else {
                Node headBefore = head.before;
                removeNode(head);
                head = headBefore;
            }
            size--;
        }

        // add Node
        Node newNode = new Node(key, value, null, null, table[tableIndex]);
        table[tableIndex] = newNode;
        size++;

        if(head == null) {
            head = newNode;
            head.before = head;
            head.after = head;
            return;
        }

        // add Node to double sequence link list head next
        newNode.before = head;
        newNode.after = head.after;
        head.after.before = newNode;
        head.after = newNode;
    }

    private void insertAfter(Node target, Node node) {
        node.after = target.after;
        node.before = target;
        target.after.before = node;
        target.after = node;
    }

    private void removeNode(Node node) {
        node.before.after = node.after;
        node.after.before = node.before;
    }

    private int hash(int key) {
        return key;
    }

    private class Node {
        public Node next;
        public Node before;
        public Node after;
        private int key;
        private int value;

        public Node() {}
        public Node(int key, int value, Node before, Node after, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.before = before;
            this.after = after;
        }

        public boolean keyEquals(int key) {
            return hash(this.key) == hash(key) && this.key == key;
        }
    }
}
