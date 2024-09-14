public class Contact {
    private int id;
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(int id, String name, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Getters e toString
    public int getId() { return id; }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + name + ", Telefone: " + phoneNumber + ", Email: " + email;
    }
}
