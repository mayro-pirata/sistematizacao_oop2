import java.util.*;

public class ContactManager {
    private static Scanner scanner = new Scanner(System.in);
    private static ContactList contactList = new ContactList();

    public static void main(String[] args) {
        while (true) {
            System.out.println("MENU SISTEMA DE GERENCIAMENTO DE CONTATOS:");
            System.out.println(); // Adicionar um espaço entre o título e as opções
            System.out.println("1. Adicionar Contato");
            System.out.println("2. Buscar Contatos");
            System.out.println("3. Remover Contato");
            System.out.println("4. Listar Contatos");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opcao: ");
            
            int choice = getValidInteger();
            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    searchContacts();
                    break;
                case 3:
                    removeContact();
                    break;
                case 4:
                    listContacts();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println(); // Adicionar uma linha de espaço após cada interação
        }
    }

    private static void addContact() {
        System.out.print("Digite o nome: ");
        String name = scanner.next();
        System.out.print("Digite o telefone: ");
        String phoneNumber = scanner.next();
        System.out.print("Digite o e-mail: ");
        String email = scanner.next();
        contactList.addContact(name, phoneNumber, email);
    }

    private static void searchContacts() {
        System.out.print("Digite o termo de busca: ");
        String searchTerm = scanner.next();
        List<Contact> results = contactList.searchContacts(searchTerm);
        if (results.isEmpty()) {
            System.out.println("Nenhum contato encontrado.");
        } else {
            for (Contact contact : results) {
                System.out.println(contact);
            }
        }
    }

    private static void removeContact() {
        System.out.print("Digite o ID do contato a ser removido: ");
        int id = getValidInteger(); // Utilizar método para obter um ID válido
        if (contactList.removeContact(id)) {
            System.out.println("Contato removido com sucesso.");
        } else {
            System.out.println("Contato não encontrado.");
        }
    }

    private static void listContacts() {
        System.out.println("- - - - - - - - - - - - - - - - -");
        contactList.listContacts();
        System.out.println("- - - - - - - - - - - - - - - - -");
    }

    private static int getValidInteger() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.next(); // Limpar o buffer do scanner
            }
        }
    }
}
