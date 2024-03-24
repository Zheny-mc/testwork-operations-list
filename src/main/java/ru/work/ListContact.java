package ru.work;
import java.io.IOException;
import java.util.*;


public class ListContact {
    private Map<Integer, Contact> uidToContact;

    public ListContact() {
        this.uidToContact = new HashMap<>();
    }

    public void addContact(Contact newContact) {
        int contactId = (int) Math.round(Math.random() * 1000 + System.currentTimeMillis());
        while (uidToContact.containsKey(contactId)) {
            contactId = (int) Math.round(Math.random() * 1000 + System.currentTimeMillis());
        }
        contactId %= (int) Math.pow(10, 4);
        uidToContact.put(contactId, newContact);
    }

    public Map<Integer, Contact> getAllContacts() {
        return uidToContact;
    }

    public String deleteContact(Integer uid) throws IOException {
        if (!uidToContact.containsKey(uid)){
            throw new IOException("Неверный id номера!");
        }
        Contact contact = uidToContact.get(uid);
        String result = String.format("\"%s %s\"", contact.getFirstName(), contact.getLastName());
        uidToContact.remove(uid);
        return result;
    }

    public Map<Integer, Contact> findByFirstname(String firstname) {
        Map<Integer, Contact> result = new HashMap<>();
        for (Integer k: uidToContact.keySet()) {
            Contact c = uidToContact.get(k);
            if (c.getFirstName().equalsIgnoreCase(firstname)) {
                result.put(k, uidToContact.get(k));
            }
        }
        return result;
    }


    @Override
    public String toString() {
        return "ListContact{" +
                "uidToContact=" + uidToContact +
                '}';
    }
}
