package Phonebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
    // Data fields
    public Map<Character, BSFamilyTree> directory;

    /**
     * Creates a new phone book with an empty directory.
     */
    public PhoneBook() {
        directory = new HashMap<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            directory.put(c, new BSFamilyTree());
        }
    }

    /*
     * Returns the instance of BSFamilyTree at the indicated letter
     * Must accept lowercase letters as well as uppercase letters
     */
    public BSFamilyTree getFamilyTree(char letter) {
        char c = Character.toUpperCase(letter);
        return directory.get(c);
    }

    /*
     * Adds a FamilyTreeNode to the PhoneBook
     */
    public void addFamily(String lastName) {
        char firstInitial = Character.toUpperCase(lastName.charAt(0));
        BSFamilyTree selectedTree = directory.get(firstInitial);
        selectedTree.addFamilyTreeNode(lastName);
        directory.put(firstInitial, selectedTree);
    }

    /*
     * Adds a Person to the PhoneBook
     * If a FamilyTreeNode with the given last name doesn't currently exist, create the FamilyTreeNode
     */
    public void addPerson(String lastName, String firstName, String phoneNumber) {
        char firstInitial = Character.toUpperCase(lastName.charAt(0));
        BSFamilyTree selectedTree = directory.get(firstInitial);
        for (Map.Entry<Character, BSFamilyTree> entry : directory.entrySet()) {
            if (entry.getValue().doesNumberExist(phoneNumber)) {
                throw new IllegalArgumentException("Conflicting phone number.");
            }
        }
        if (selectedTree.doesFamilyExist(lastName)) {
            selectedTree.getFamilyTreeNode(lastName).addFamilyMember(new Person(lastName, firstName, phoneNumber));
        } else {
            selectedTree.addFamilyTreeNode(lastName);
            selectedTree.getFamilyTreeNode(lastName).addFamilyMember(lastName, firstName, phoneNumber);
        }
        directory.put(firstInitial, selectedTree);
    }

    /*
     * Finds the phone number of a person
     * Returns 'Does not exist.' if not found.
     */
    public String getPhoneNumber(String lastName, String firstName) {
        for (Map.Entry<Character, BSFamilyTree> entry : directory.entrySet()) {
            if (entry.getValue().doesFamilyExist(lastName)) {
                if (entry.getValue().getFamilyTreeNode(lastName).doesFamilyMemberExist(lastName, firstName)) {
                    return entry.getValue().getFamilyTreeNode(lastName).getPhoneNumberOfFamilyMember(lastName, firstName);
                }
            }
        }
        return "Does not exist.";
    }

    /**
     * String representation of PhoneBook
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Map.Entry<Character, BSFamilyTree> entry : directory.entrySet()) {
            s.append(entry.getKey());
            s.append("\n");
            s.append(entry.getValue().toString());
        }
        return s.toString();
    }
}
