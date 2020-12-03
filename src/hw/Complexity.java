package hw;

public class Complexity {
    /*
    Jacob Gerega
    I pledge my honor that I have abided by the Stevens Honor System.
    */

    //n
    public void method0(int n) {
        int counter = 0;
        for (int i = 0; i < n; i++) {
            System.out.println("Operation " + counter);
            counter++;
        }
    }

    //n2
    public void method1(int n) {
        int counter = 0;
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                System.out.println("Operation " + counter);
                counter++;
            }
        }
    }

    //n3
    public void method2(int n) {
        int counter = 0;
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                for (int j = 0; j < n; j++) {
                    System.out.println("Operation " + counter);
                    counter++;
                }
            }
        }
    }

    //log n
    public void method3(int n) {
        int counter = 0;
        for (int i = 1; i < n; i *= 2) {
            System.out.println("Operation " + counter);
            counter++;
        }
    }

    //n log n
    public void method4(int n) {
        int counter = 0;
        for (int k = 0; k < n; k++) {
            for (int i = 1; i < n; i *= 2) {
                System.out.println("Operation " + counter);
                counter++;
            }
        }
    }

    //log log n
    public void method5(int n) {
        int counter = 0;
        for (int k = 2; k < n; k *= k) {
            System.out.println("Operation " + counter);
            counter++;
        }
    }

    public static void main(String[] args) {
        Complexity c = new Complexity();
        c.method5(32);
    }

}
