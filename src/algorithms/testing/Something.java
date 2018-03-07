package algorithms.testing;

/**
 * Something
 */
public class Something {

    public static void main(String[] args) {
        System.out.println("I WORK!");
    }

    public static void helloWorld() {
        System.out.println("Hello World!");
    }

    public static String reverse(String s) {
        String out = "";
        for (int i=s.length()-1;i>0;i--) out += s.charAt(i);
        return out;
    }

    public static int countBigNums(int[] arr) {
        int count = 0;
        for (int i : arr) if (i > 100) count++;
        return count;
    }

    public static int add10(int x) {
        return x+10;
    }

}