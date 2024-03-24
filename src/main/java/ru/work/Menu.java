package ru.work;

import java.util.Map;
import java.util.Scanner;

public class Menu {

    private ListContact lc;
    private Scanner in;

    public Menu() {
        this.in = new Scanner(System.in);
        this.lc = new ListContact();
    }

    public Integer inputCommand() {
        System.out.print("\nВыберите действие (введите номер): ");
        in.useDelimiter("");
        String snum = in.nextLine();
        if (snum.isEmpty()) {
            return 7;
        }

        for (char chr: snum.toCharArray()) {
            if (!(chr >= '0' && chr <= '9') ) {
                return 7;
            }
        }

        return Integer.parseInt(snum);

    }

    public void printHelp() {
        System.out.println("Добро пожаловать в приложение \"Список контактов\"!");
        System.out.println("1. Добавить контакт");
        System.out.println("2. Просмотреть список контактов");
        System.out.println("3. Найти контакт по имени");
        System.out.println("4. Удалить контакт");
        System.out.println("5. Выход");
    }

    public void addContact() {
        Contact contact = new Contact();

        try {
            System.out.print("Введите имя контакта: ");
            String firstname = in.nextLine();
            contact.setFirstName(firstname);
            System.out.print("Введите фамилию контакта: ");
            String lastName = in.nextLine();
            contact.setLastName(lastName);
            System.out.print("Введите номер телефона: ");
            String phone = in.nextLine();
            contact.setPhone(phone);
            System.out.print("Введите адрес электронной почты: ");
            String email = in.nextLine();
            contact.setEmail(email);
            lc.addContact(contact);
            System.out.println("Контакт успешно добавлен.");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void printAllContacts() {
        Map<Integer, Contact> result = lc.getAllContacts();
        if (!result.isEmpty()){
            System.out.println("Список контактов:");
            System.out.print(mapToString(result));
        } else {
            System.out.println("Список контактов: (пусто)");
        }
    }

    public void findByFirstName() {
        System.out.print("Введите имя для поиска: ");
        String firstname = in.nextLine();
        System.out.println("Результаты поиска:");
        Map<Integer, Contact> result = lc.findByFirstname(firstname);
        if (!result.isEmpty()) {
            System.out.print(mapToString(result));
        } else {
            System.out.println("Ничего не найдено.");
        }

    }

    public void deleteContact() {
        System.out.print("Введите номер контакта для удаления: ");

        try {
            String sCId = in.nextLine();
            Integer cId = Integer.parseInt(sCId);
            String fnameAndSname = lc.deleteContact(cId);
            System.out.printf("Контакт %s успешно удален.%n", fnameAndSname);
        } catch (Exception ex){
            System.out.println("Неверный номер контакта!");
        }
    }


    public void stopProgram() {
        in.close();
        System.out.println("До свидания!");
    }

    public void defaultCommand() {
        System.out.println("Команда не существует! Обратитесь с справке!");
    }

    private String mapToString(Map<Integer, Contact> mapContacts) {
        StringBuilder sb = new StringBuilder();
        for (Integer k: mapContacts.keySet()) {
            sb.append(k);
            sb.append(". ");
            sb.append(mapContacts.get(k));
            sb.append('\n');
        }
        return sb.toString();
    }

}
