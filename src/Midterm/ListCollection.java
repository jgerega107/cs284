package Midterm;
import java.util.ArrayList;
import java.util.List;

public class ListCollection<E> {
    private int nodeCount;
    protected List<SingleLL<E>> collection;

    /**
     * Base constructor, initializes an empty ListCollection.
     */
    public ListCollection() {
        collection = new ArrayList<>();
        nodeCount = 0;
    }

    /**
     * Initializes a ListCollection with `numLists` lists.
     *
     * @param numLists
     */
    public ListCollection(int numLists) {
        collection = new ArrayList<>();
        nodeCount = 0;
        for (int i = 0; i < numLists; i++) {
            collection.add(new SingleLL<>());
        }
    }

    /**
     * @return The size of the `ListCollection`
     */
    public int size() {
        return collection.size();
    }

    /**
     * Sets the nodeCount
     *
     * @param nodeCount
     */
    public void setNodeCount(int nodeCount) {
        this.nodeCount = nodeCount;
    }

    /**
     * @return the nodeCount
     */
    public int getNodeCount() {
        return nodeCount;
    }

    /**
     * Adds the specified `SingleLL` to the end of the `ListCollection`
     *
     * @param list
     */
    public void addList(SingleLL<E> list) {
        collection.add(collection.size() - 1, list);
        nodeCount+=list.size();
    }

    /**
     * Adds the specified `List` to the `ListCollection`
     *
     * @param list
     * @complexity Runtime of N: This is because the method only utilizes one for loop that increments at a constant rate.
     *
     */
    public void addList(List<E> list) {
        SingleLL<E> sllist = new SingleLL<>();
        for (int i = 0; i < list.size(); i++) {
            sllist.append(list.get(i));
        }
        collection.add(sllist);
        nodeCount+=sllist.size();
    }

    /**
     * Returns the list at the specified index
     *
     * @param listIndex
     * @return the list
     * @throws IllegalArgumentException when index out of bounds
     */
    public SingleLL<E> getList(int listIndex) {
        if(listIndex < 0 || listIndex >= collection.size()){
            throw new IllegalArgumentException("Index out of bounds.");
        }
        return collection.get(listIndex);
    }

    /**
     * Adds an element to the `elemIndex` position of the list at `listIndex`
     *
     * @param listIndex
     * @param elemIndex
     * @param elem
     * @throws IllegalArgumentException when index out of bounds
     * @complexity Runtime N: No loops or lines in this method, however, by calling get() and insert() from the SingleLList class, they both utilize loops.
     */
    public void addElem(int listIndex, int elemIndex, E elem) {
        if(listIndex < 0 || listIndex >= collection.size() || elemIndex < 0 || elemIndex >= collection.get(listIndex).size()){
            throw new IllegalArgumentException("Index out of bounds.");
        }
        collection.get(listIndex).insert(elemIndex, elem);
        nodeCount++;
    }

    /**
     * Sets the element at the `elemIndex` position of the list at `listIndex`
     *
     * @param listIndex
     * @param elemIndex
     * @param elem
     * @throws IllegalArgumentException when index out of bounds
     */
    public void setElem(int listIndex, int elemIndex, E elem) {
        if(listIndex < 0 || listIndex >= collection.size() || elemIndex < 0 || elemIndex >= collection.get(listIndex).size()){
            throw new IllegalArgumentException("Index out of bounds.");
        }
        collection.get(listIndex).set(elemIndex, elem);
    }

    /**
     * Returns the element at the `elemIndex` position of the list at `listIndex`
     *
     * @param listIndex
     * @param elemIndex
     * @return
     * @throws IllegalArgumentException when index out of bounds
     */
    public E getElem(int listIndex, int elemIndex) {
        if(listIndex < 0 || listIndex >= collection.size() || elemIndex < 0 || elemIndex >= collection.get(listIndex).size()){
            throw new IllegalArgumentException("Index out of bounds.");
        }
        return collection.get(listIndex).get(elemIndex);
    }

    /**
     * Returns the `ListCollection` in string form, following STRICTLY the rule of
     * "[LIST1, LIST2, LIST3, ... ]" Ex: [[0, 1, 2], [3, 4, 5] [6, 7, 8]]
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if(collection.size() == 1){
            sb.append(collection.get(0).toString());
        }
        else{
            int i = 0;
            while(i < collection.size()-1){
                sb.append(collection.get(i).toString());
                sb.append(",");
                i++;
            }
            sb.append(collection.get(i).toString());
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args){
        ListCollection<Integer> list = new ListCollection<>();
        ArrayList<Integer> l1 = new ArrayList<>();
        l1.add(5);
        l1.add(3);
        l1.add(6);

        ArrayList<Integer> l2 = new ArrayList<>();
        l2.add(43);
        l2.add(2);
        l2.add(1);

        list.addList(l1);
        list.addList(l2);
        System.out.println(list);
    }

}
