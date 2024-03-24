package ru.work;

import java.io.IOException;
import java.util.Objects;

/**
 *   Класс для хранения данных контакта
 */
public class Contact
{
    // Имя
    private String firstName;
    // Фамилия
    private String lastName;
    // Телефон
    private String phone;
    // email
    private String email;

    public Contact() {}

    public Contact(String firstName, String lastName, String phone, String email) throws IOException {
        this.firstName = validFirstName(firstName);
        this.lastName = validLastName(lastName);
        this.phone = validPhone(phone);
        this.email = validEmail(email);
    }

    public String validFirstName(String inFirstName) throws IOException {
        if (inFirstName.length() < 3) {
            throw new IOException("Длина имени слишком маленькая!");
        }
        return inFirstName;
    }

    public String validLastName(String inLastName) throws IOException {
        if (inLastName.length() < 2) {
            throw new IOException("Длина фамилии слишком маленькая!");
        }
        return inLastName;
    }
    public String validPhone(String inPhone) throws IOException {
        if (inPhone.length() == 11) {
            int count_number = 0;
            int count_sep = 0;
            for (char chr: inPhone.toCharArray()) {
                if (chr >= '0' && chr <= '9') {
                    count_number += 1;
                } else if (chr == '-') {
                    count_sep += 1;
                }
            }
            if (count_number == 9 && count_sep == 2) {
                return inPhone;
            }
        }
        throw new IOException("Телефон введен в неверном формате!\n" +
                "Правильный формат: XXX-XXX-XXX\nНапример: 123-456-789");
    }


    public String validEmail(String inEmail) throws IOException {
        if (inEmail.length() > 254) {
            throw new IOException("Длина почты превысила 254 символа!");
        } else if (inEmail.split("@").length < 2) {
            throw new IOException("Почта указана в неверном формате!\n " +
                    "Правильный формат: <email>@<домен>\n Например: john.doe@example.com");
        }
        return inEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws IOException {
        this.firstName = validFirstName(firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws IOException {
        this.lastName = validLastName(lastName);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws IOException {
        this.phone = validPhone(phone);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws IOException {
        this.email = validEmail(email);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("%s - ", firstName));
        sb.append(String.format("%s - ", lastName));
        sb.append(String.format("%s - ", phone));
        sb.append(email);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(firstName, contact.firstName) && Objects.equals(lastName, contact.lastName) && Objects.equals(phone, contact.phone) && Objects.equals(email, contact.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phone, email);
    }
}
