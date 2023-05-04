class LinkedList {
    Node head;
    static class Node {
        int data;
        Node next;
        Node(int d) { data = d; }
    }

    public static LinkedList addNode(LinkedList list, int data)
    {
        Node new_node = new Node(data);
        if (list.head == null) {
            list.head = new_node;
        }
        else {
            Node last = list.head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = new_node;
        }
        return list;
    }
    public static Node deleteN(LinkedList linkedList, int position) {
        Node temp = linkedList.head;
        Node prev = linkedList.head;
        Node head = linkedList.head;
        for (int i = 0; i < position; i++) {
            if (i == 0 && position == 1) {
                head =  head.next;
            } else {
                if (i == position - 1 && temp != null) {
                    prev.next = temp.next;
                } else {
                    prev = temp;
                    if (prev == null)
                        break;
                    temp = temp.next;
                }
            }
        }
        return head;
    }
//    public static LinkedList removeNodeGreaterThanValue(LinkedList list, int value)
//    {
//        int position = 0;
//        if (list.head == null) {
//            list.head = null;
//        }
//        else {
//            Node last = list.head;
//            while (last.next != null) {
//                if(last.data>value){
//                    list = deleteN(list,position);
//                }
//                position++;
//            }
//        }
//        return list;
//    }
    public static LinkedList deleteNode(LinkedList list,int key)
    {
        Node temp = list.head, prev = null;

        if (temp != null && temp.data > key) {
            temp = temp.next;
            list.head = temp;
        }
while(temp !=null) {

    while (temp != null && temp.data <= key) {
        prev = temp;
        temp = temp.next;
    }

    if (temp == null)
        return list;

    prev.next = temp.next;
    temp = prev.next;
}
        return list;
    }

    public static void printList(LinkedList list)
    {
        Node currNode = list.head;

        System.out.print("LinkedList: ");

        // Traverse through the LinkedList
        while (currNode != null) {
            // Print the data at current node
            System.out.print(currNode.data + " ");

            // Go to next node
            currNode = currNode.next;
        }
    }

    // Driver code
    public static void main(String[] args)
    {
        LinkedList list = new LinkedList();
        list = addNode(list, 1);
        list = addNode(list, 2);
        list = addNode(list, 3);
        list = addNode(list, 4);
        list = addNode(list, 5);
        list = addNode(list, 6);
        list = addNode(list, 7);
        list = addNode(list, 9);
        list = addNode(list, 8);
        list = deleteNode(list,7);
        printList(list);
    }
}