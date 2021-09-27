package Compart;

import java.util.Comparator;

public class Comparator1 implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
//        Pojo p1=(Pojo) o1;
//        Pojo p2=(Pojo) o2;
        return ((Pojo)o1).getNumber() ;
    }
}
