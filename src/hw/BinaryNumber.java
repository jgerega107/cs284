package hw;

import java.util.InputMismatchException;

/*
Author: Jacob Gerega
CS284
Professor Bonelli

I pledge my honor that I have abided by the Stevens Honor
*/

public class BinaryNumber {
    private int[] data;
    private int length;

    BinaryNumber(int length) {
        if (length <= 0) {
            throw new InputMismatchException("Length must be greater than zero.");
        }
        this.length = length;
        data = new int[length];
    }

    BinaryNumber(String str) {
        if (Integer.parseInt(str) < 0) {
            throw new InputMismatchException("Cannot be negative.");
        }
        length = str.length();
        data = new int[length];
        for (int x = 0; x < str.length(); x++) {
            data[x] = Character.getNumericValue(str.charAt(x));
        }
    }

    //returns length of binary number as an integer
    public int getLength() {
        return length;
    }

    //returns raw array data of binary number
    public int[] getInnerArray() {
        return data;
    }

    //returns digit of binary number at said index
    public int getDigit(int index) {
        //is the index out of bounds?
        if (index > length - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        //no, return the data
        else {
            return data[index];
        }
    }

    //converts binary number to base 10 representation (decimal)
    public int toDecimal() {
        int decimalNumber = 0;
        int indexTracker = data.length - 1;
        for (int x = 0; x < data.length; x++) {
            decimalNumber += (data[x] * Math.pow(2, indexTracker));
            indexTracker--;
        }
        return decimalNumber;
    }

    //shifts the binary number to left or right depending on direction and amount (-1 for left, 1 to right)
    public void bitShift(int direction, int amount) {
        if (amount < 0 || amount > data.length) {
            throw new InputMismatchException("Amount is either too long or negative.");
        } else if (amount == data.length && direction == 1) {
            data = new int[1];
        }
        //left shift
        else if (direction == -1) {
            int[] newData = new int[data.length + amount];
            int indexCounter = 0;
            while (indexCounter < newData.length) {
                if (indexCounter < data.length) {
                    newData[indexCounter] = data[indexCounter];
                } else {
                    newData[indexCounter] = 0;
                }
                indexCounter++;
            }
            data = newData;
        }

        //right shift
        else if (direction == 1) {
            int[] newData = new int[data.length - amount];
            int indexCounter = 0;
            while (indexCounter < newData.length) {
                if (indexCounter < data.length) {
                    newData[indexCounter] = data[indexCounter];
                } else {
                    newData[indexCounter] = 0;
                }
                indexCounter++;
            }
            data = newData;
        } else {
            throw new InputMismatchException("Direction must be either -1 or 1.");
        }
    }

    //bitwise or, goes by every index of a binary number and compares their values with OR operator. returns new binary number as an array with computed values
    public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2) {
        if (bn1.length == bn2.length) {
            int[] newArray = new int[bn1.length];
            for (int i = 0; i < newArray.length; i++) {
                if (bn1.getInnerArray()[i] == 1 || bn2.getInnerArray()[i] == 1) {
                    newArray[i] = 1;
                } else {
                    newArray[i] = 0;
                }
            }
            return newArray;
        } else {
            throw new IllegalArgumentException();
        }
    }

    //bitwise or, goes by every index of a binary number and compares their values with AND operator. returns new binary number as an array with computed values
    public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2) {
        if (bn1.length == bn2.length) {
            int[] newArray = new int[bn1.length];
            for (int i = 0; i < newArray.length; i++) {
                if (bn1.getInnerArray()[i] == 1 && bn2.getInnerArray()[i] == 1) {
                    newArray[i] = 1;
                } else {
                    newArray[i] = 0;
                }
            }
            return newArray;
        } else {
            throw new IllegalArgumentException();
        }
    }

    //helper method: adds zeros to a binary number to compensate for differing binary num lengths
    public void prepend(int amount) {
        int[] newArray = new int[data.length + amount];
        int i = 0;
        int x = 0;
        while (i < newArray.length) {
            if (i < data.length) {
                newArray[i] = 0;
                i++;
            } else {
                newArray[i] = data[x];
                i++;
                x++;
            }
        }
        data = newArray;
    }

    //adds two binary numbers together
    public void add(BinaryNumber aBinaryNumber) {
        int[] newArr;
        int diff = Math.abs(data.length - aBinaryNumber.length);
        //new array size determining
        if (data.length < aBinaryNumber.length) {
            this.prepend(diff);
            newArr = new int[aBinaryNumber.length + 1];
        } else if (aBinaryNumber.length < data.length) {
            aBinaryNumber.prepend(diff);
            newArr = new int[data.length + 1];
        } else {
            newArr = new int[aBinaryNumber.length + 1];
        }

        //smallest array was enlarged to match size of largest array, now begin operations
        int newArrLengthCounter = newArr.length - 1;
        for (int i = data.length - 1; i >= 0; i--) {
            if (aBinaryNumber.getInnerArray()[i] == 1 && data[i] == 1 && newArr[newArrLengthCounter] == 1) {
                newArr[newArrLengthCounter] = 1;
                newArr[newArrLengthCounter - 1] = 1;
            } else if (aBinaryNumber.getInnerArray()[i] == 1 && data[i] == 1 && newArr[newArrLengthCounter] == 0) {
                newArr[newArrLengthCounter] = 0;
                newArr[newArrLengthCounter - 1] = 1;
            } else if (aBinaryNumber.getInnerArray()[i] == 0 && data[i] == 1 && newArr[newArrLengthCounter] == 1) {
                newArr[newArrLengthCounter] = 0;
                newArr[newArrLengthCounter - 1] = 1;
            } else if (aBinaryNumber.getInnerArray()[i] == 1 && data[i] == 0 && newArr[newArrLengthCounter] == 1) {
                newArr[newArrLengthCounter] = 0;
                newArr[newArrLengthCounter - 1] = 1;
            } else if (aBinaryNumber.getInnerArray()[i] == 0 && data[i] == 0 && newArr[newArrLengthCounter] == 1) {
                newArr[newArrLengthCounter] = 1;
            } else if (aBinaryNumber.getInnerArray()[i] == 0 && data[i] == 1 && newArr[newArrLengthCounter] == 0) {
                newArr[newArrLengthCounter] = 1;
            } else if (aBinaryNumber.getInnerArray()[i] == 1 && data[i] == 0 && newArr[newArrLengthCounter] == 0) {
                newArr[newArrLengthCounter] = 1;
            }
            newArrLengthCounter--;
        }
        data = newArr;
    }

    //overriding toString(), prints a binary number as a string representation
    public String toString() {
        String returnS = "";
        for (int i = 0; i < data.length; i++) {
            returnS += data[i];
        }
        return returnS;
    }
}
