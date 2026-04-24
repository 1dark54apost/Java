package TEST;

public class test {

    private int heart = 10;
    private int hunger = 10;


    private void printHeart(){
        for (int k =1;k <= heart;k++){
            System.out.print("❤️");
        }
        System.out.println();
    }

    private void printHunger(){
        for (int k=1;k <= hunger;k++){
            System.out.print("🍕");
        }
        System.out.println();
    }





    public static void main(String[] args) {

        test a = new test();
        a.printHeart();
        a.printHunger();
    }

}
