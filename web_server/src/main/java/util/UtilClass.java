package util;

import java.util.Scanner;

/**
 * Created by Nikolion on 17.05.2017.
 */
public class UtilClass {
    public static Object castToType(String s){
        Scanner sc = new Scanner(s);
        return
                sc.hasNextInt() ? sc.nextInt() :
                        sc.hasNextDouble() ? sc.nextDouble() :
                                sc.hasNext() ? sc.next() :
                                        s;
    }
}
