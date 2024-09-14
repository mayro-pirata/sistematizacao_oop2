import java.io.*;
import java.util.*;

public class ContactList {
    private Node head;
    private Set<Integer> usedIds;
    private int nextId;

    public ContactList() {
        this.head = null;
        this.usedIds = new HashSet<>();
        this.nextId = 1; // Começar a partir de 1
        loadFromFile();
    }

    public void addContact(String name, String phoneNumber, String email) {
        int id = getNextId();
        Contact contact = new Contact(id, name, phoneNumber, email);
        Node newNode = new Node(contact);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        usedIds.add(id);
        saveToFile();
    }

    public List<Contact> searchContacts(String searchTerm) {
        List<Contact> results = new ArrayList<>();
        Node current = head;
        while (current != null) {
            Contact contact = current.getContact();
            if (contact.getName().contains(searchTerm) ||
                contact.getPhoneNumber().contains(searchTerm) ||
                contact.getEmail().contains(searchTerm) ||
                Integer.toString(contact.getId()).contains(searchTerm)) {
                results.add(contact);
            }
            current = current.getNext();
        }
        return results;
    }

    public boolean removeContact(int id) {
        Node current = head;
        Node previous = null;
        while (current != null) {
            Contact contact = current.getContact();
            if (contact.getId() == id) {
                if (previous == null) {
                    head = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
                usedIds.remove(id);
                updateNextId();
                saveToFile();
                return true;
            }
            previous = current;
            current = current.getNext();
        }
        return false;
    }

    public void listContacts() {
        Node current = head;
        while (current != null) {
            System.out.println(current.getContact());
            current = current.getNext();
        }
    }

    private int getNextId() {
        // Se houver IDs disponíveis, retorna o menor ID disponível
        if (usedIds.isEmpty()) {
            return nextId++;
        }
        return nextId++;
    }

    private void updateNextId() {
        // Atualiza o próximo ID com base no maior ID usado
        if (usedIds.isEmpty()) {
            nextId = 1;
            return;
        }
        nextId = Collections.max(usedIds) + 1;
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("contacts.txt"))) {
            Node current = head;
            while (current != null) {
                Contact contact = current.getContact();
                writer.println(contact.getId() + "," + contact.getName() + "," + contact.getPhoneNumber() + "," + contact.getEmail());
                current = current.getNext();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar no arquivo: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("contacts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String phoneNumber = parts[2];
                    String email = parts[3];
                    Contact contact = new Contact(id, name, phoneNumber, email);
                    // Adicionar o contato à lista
                    Node newNode = new Node(contact);
                    if (head == null) {
                        head = newNode;
                    } else {
                        Node current = head;
                        while (current.getNext() != null) {
                            current = current.getNext();
                        }
                        current.setNext(newNode);
                    }
                    usedIds.add(id);
                    updateNextId(); // Atualizar o próximo ID
                }
            }
        } catch (IOException e) {
            // Handle the case where the file does not exist
        }
    }
}
