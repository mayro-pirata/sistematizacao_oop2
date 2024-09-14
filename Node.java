public class Node {
    private Contact contact;
    private Node next;

    public Node(Contact contact) {
        this.contact = contact;
        this.next = null;
    }

    public Contact getContact() { return contact; }
    public Node getNext() { return next; }
    public void setNext(Node next) { this.next = next; }
}
