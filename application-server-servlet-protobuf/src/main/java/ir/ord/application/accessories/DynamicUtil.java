package ir.ord.application.accessories;

/**
 * Created by vahid on 8/2/17.
 */
public class DynamicUtil {
    public static void main(String[] args){
//        Thread thread = new Thread(() -> {
//            System.out.println("VVV");
//        });
//        thread.start();
//
        String inputStr = "salam ...";
//        proccessTest("salam osgol", ()->proccessTest(inputStr, ()->{
//            System.out.println(inputStr);
//        }));
    }


    public static void proccessTest(String input ,ProccessInterface proccessInterface){
        proccessInterface.proccess(input);
    }

}

interface ProccessInterface{
    String proccess(String input);
}
