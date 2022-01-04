package hellojpa;

import com.sun.security.jgss.GSSUtil;

public class ValueMain {
    public static void main(String[] args) {
        int a = 10;
        int b = 10;
        System.out.println(a==b); //true

        Address address1 = new Address("city", "street", "100");
        Address address2 = new Address("city", "street", "100");

        System.out.println(address1==address2); //false
        System.out.println(address1.equals(address2)); //true
    }
}
