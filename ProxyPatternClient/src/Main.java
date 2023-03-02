public class Main {
    public static void main(String[] args) {
        MathHelper mh = new MathHelper();
        try{
            double op1 = 11;
            double op2 = 12;
            double result = mh.multiplyTwoNumbers(op1,op2);
            System.out.println(result);
        }
        catch (Exception e){

        }
    }
}