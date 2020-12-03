package Phonebook;

/**
 * BSFamilyTree creates a tree for specific families.
 */
public class BSFamilyTree {
    //Data Fields
    private FamilyTreeNode root;

    /**
     * Constructor: constructs an empty BSFamilyTree
     */
    public BSFamilyTree() {
        root = null;
    }

    /**
     * takes in the last name and returns true if there
     * is a FamilyTreeNode with the given last name.
     */
    public boolean doesFamilyExist(String lastName) {
        return familyExistHelper(root, lastName);
    }

    private boolean familyExistHelper(FamilyTreeNode current, String lastName) {
        if (current == null) {
            return false;
        } else {
            int comparator = lastName.compareTo(current.getLastName());
            if (comparator == 0) {
                return true;
            } else if (comparator < 0) {
                return familyExistHelper(current.left, lastName);
            } else {
                return familyExistHelper(current.right, lastName);
            }
        }
    }

    /**
     * Takes in a last name and creates a new instance of
     * FamilyTreeNode and adds it to the BSFamilyTree.
     */
    public void addFamilyTreeNode(String lastName) {
        root = addFamTreeHelper(root, lastName);
    }

    private FamilyTreeNode addFamTreeHelper(FamilyTreeNode current, String lastName) {
        if (current == null) {
            return new FamilyTreeNode(lastName);
        } else {
            int comparator = lastName.compareTo(current.getLastName());
            if (comparator == 0) {
                throw new IllegalArgumentException("Family already exists in tree");
            } else if (comparator < 0) {
                current.left = addFamTreeHelper(current.left, lastName);
            } else {
                current.right = addFamTreeHelper(current.right, lastName);
            }
            return current;
        }
    }

    /**
     * Takes a last name and then finds that specific
     * family tree and then returns that FamilyTreeNode
     * If last name is not in tree, throws an exception.
     */
    public FamilyTreeNode getFamilyTreeNode(String lastName) {
        return getFamHelper(root, lastName);
    }

    private FamilyTreeNode getFamHelper(FamilyTreeNode current, String lastName) {
        if (current == null) {
            throw new IllegalArgumentException("Does not exist.");
        } else {
            int comparator = lastName.compareTo(current.getLastName());
            if (comparator == 0) {
                return current;
            } else if (comparator < 0) {
                return getFamHelper(current.left, lastName);
            } else {
                return getFamHelper(current.right, lastName);
            }
        }
    }

    /**
     * Returns true if the input phone number exists in the BSFamilyTree
     * false otherwise.
     */
    public boolean doesNumberExist(String phoneNumber) {
        return numberExistHelper(root, phoneNumber);
    }

    private boolean numberExistHelper(FamilyTreeNode current, String phoneNumber) {
        if (current == null) {
            return false;
        } else if (current.doesNumberExist(phoneNumber)) {
            return true;
        } else {
            if (numberExistHelper(current.left, phoneNumber)) {
                return true;
            }
            return numberExistHelper(current.right, phoneNumber);
        }
    }

    /**
     * Returns the string representation of the BSFamilyTree
     */
    public String toString() {
        return toString(root, 0).toString();
    }

    private StringBuilder toString(FamilyTreeNode current, int i) {
        StringBuilder r = new StringBuilder();
        for (int j = 0; j < i; j++) {
            r.append("  ");
        }

        if (current == null) {
            r.append("null\n");
        } else {
            r.append(current.toString());
            r.append("\n");
            r.append(toString(current.left, i + 1));
            r.append(toString(current.right, i + 1));
        }
        return r;
    }
}
