import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * This class is responsible for keeping track of the rum barrels.
 * This store data as a doubly linked list.
 */
public class RumInventoryTunnel implements Tunnel{
    private Node head;
    private Node middle;        // This variable will keep track of the middle node of the list
    private Node tail;
    private int size;

    /**
     * It is a inner class to keep track of each node of the list
     */
    private class Node {
        public Object data;
        public Node next;
        public Node previous;


        /**
         * This is constructor of node class.
         * @param data the information which is store in the node
         */
        public Node (Object data) {
            this.data = data;

        }

    }
    /**
     * This is constructor of RumInventoryTunnel
     */
    public RumInventoryTunnel() {size =0;}

    /**
     * Determine if the list is empty or not
     * @return the size of the list
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * This method adds new node to the first position of the list
     * @param amtOfRum the amount of rum in this barrel.
     */
    @Override
    public void addA(int amtOfRum) {
        Node newNode = new Node(amtOfRum);

        if(isEmpty()) {
            tail = newNode;
            middle = newNode;        // as list is empty so head,tail and middle point to the same node
        } else {
            head.previous = newNode;
        }
        newNode.next = head;
        head = newNode;
        size++;


        /*
         For every odd number middle pointer move back direction
         when there is only one node there is no need to move the pointer
         */
        if(size > 1) {
            if (size % 2 != 0) {
                middle = middle.previous;
            }
        }

    }

    /**
     * This method deletes node from first position of the list
     * @return the amount of rum in the barrel that is removed
     */
    @Override
    public int removeA() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }

        Node temp = head;
        if(head == tail) {
            tail = null;
            middle = null;   // head,tail and middle point to one node when there is only one node in the list
        } else {
            head.next.previous = null;
        }
        head = head.next;
        temp.next = null;
        size--;

        //reverse effect of addFirst
        if (size > 1) {
            if (size() % 2 == 0) {
                middle = middle.next;
            }
        }
        return (int) temp.data;
    }

    /**
     * This method will add data at the middle position of the list
     * @param amtOfRum the amount of rum in this barrel.
     */
    @Override
    public void addB(int amtOfRum) {
        Node newNode = new Node(amtOfRum);

        if(isEmpty()) {
            head = newNode;
            tail = newNode;
            middle= newNode;
            size++;
        } else {
            // When the size is 1 and 2 before inserting the barrel the following code will insert new barrel manually.
            if(size == 1) {
                head.next = newNode;
                newNode.previous = head;
                middle=newNode;
                tail=newNode;
                size++;
            } else if (size == 2) {
                newNode.next =tail;
                tail.previous = newNode;
                head.next = newNode;
                newNode.previous = head;
                middle = newNode;
                size++;
            } else {
                // when the size is even before inserting the barrel, the "if" part will execute.
                if (size % 2 == 0) {
                    Node temp = middle.previous;
                    newNode.next = middle;
                    middle.previous = newNode;
                    newNode.previous = temp;
                    temp.next = newNode;
                    middle = newNode;
                    size++;
                } else {
                    Node temp = middle.next;
                    newNode.previous = middle;
                    middle.next = newNode;
                    newNode.next = temp;
                    temp.previous = newNode;
                    middle = newNode;
                    size++;
                }
            }
        }

    }

    /**
     * This method remove node from the middle of the list
     * @return the amount of rum in the barrel that is removed
     */
    @Override
    public int removeB() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        // When the size is 1 and 2 before removing the barrel the following code will remove the barrel manually.
        if(size == 1) {
            Node temp = middle;
            head = null;
            tail = null;
            middle = null;
            size--;
            return (int) temp.data;
        }
        else if (size ==2) {
            Node temp = middle;
            head.next = null;
            tail.previous = null;
            middle.previous = null;
            tail = head;
            middle = head;
            size--;
            return (int) temp.data;

        } else {
            Node temp = middle;

            Node middleNext = middle.next;
            Node middlePrev = middle.previous;

            middleNext.previous = middlePrev;
            middlePrev.next = middleNext;

            middle.next =null;
            middle.previous = null;
            size--;

            if (size()==2) {middle = tail;} // when there are only two barrels, the middle and tail pointer both point to the same node
            else if ((size() % 2 == 0)) {
                middle = middleNext;
            } else if (size() % 2!=0) {
                middle = middlePrev;
            }
            return (int) temp.data;
        }
    }

    /**
     * This method adds node at last position of the list
     * @param amtOfRum the amount of rum in this barrel.
     */
    @Override
    public void addC(int amtOfRum) {
        Node newNode = new Node(amtOfRum);

        if(isEmpty()) {
            head = newNode;
            middle = newNode;  // head, tail and middle point to same node
        } else {
            tail.next = newNode;
            newNode.previous = tail;
        }
        tail = newNode;
        size++;

        // For every even number middle move to forward direction
        if(size()%2 ==0) {
            middle = middle.next;
        }

    }

    /**
     * This method will remove data from the last position of the list.
     * @return the amount of rum in the barrel that is removed
     */
    @Override
    public int removeC() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }

        Node temp = tail;
        if (head == tail) {
            head = null;
            middle = null;
        } else {
            tail.previous.next = null;
        }
        tail = tail.previous;
        temp.previous = null;
        size--;
        if (size ==1) {
            middle = head;
        }
        // Reverse effect of addLast
        if (size > 1) {
            if (size() % 2 != 0) {
                middle = middle.previous;
            }
        }
        return (int) temp.data;
    }
    /**
     * This method return number of node in the DoublyLinkedList
     * @return size  the number of barrels of rum in the tunnel
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * This method returns the value of node at any given index
     * @param index the position in the list
     * @return the amount of rum in the barrel
     */
    @Override
    public int get(int index) {
        if(isEmpty()) throw new NoSuchElementException();
        if (index < 0 || index > size - 1) throw new IndexOutOfBoundsException();

        Node temp = head;
        int position = 0;

        while (temp != null) {
            if (position == index)
                return (int) temp.data;
            position++;
            temp = temp.next;
        }
        return 0;
    }

    /**
     * This method retrieve an iterator for the RumInventoryTunnel list
     * @return the iterator for the list
     */
    @Override
    public Iterator<Integer> iterator() {


        return new InventoryIterator();
    }

    /**
     * This is inner class for creating a iterator for the list
     */
    private class InventoryIterator implements ListIterator {
        private Node current = head;

        /**
         * This method checks if there is next node
         * @return the boolean value
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * This method return the next node data of current position
         * @return the node
         */
        @Override
        public Object next() {
            if (!hasNext()) throw new NoSuchElementException();

            Object temp = current.data;
            current = current.next;
            return temp;

        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public Object previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(Object o) {

        }

        @Override
        public void add(Object o) {

        }
    }
}
