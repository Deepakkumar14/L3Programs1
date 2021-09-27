package Compart;

import java.util.*;

public class Main {
    public static void main(String[] args) {
      HashMap<Integer,Pojo> map=new LinkedHashMap<>();
        ArrayList<Pojo> list1=new ArrayList<>();
        Scanner input=new Scanner(System.in);
        int num=input.nextInt();
        input.nextLine();
        for (int i=1;i<=num;i++){
            Pojo po=new Pojo();
            po.setName(input.nextLine());

            po.setNumber(input.nextInt());
            input.nextLine();
            list1.add(po);
           map.put(i,po);
        }
        System.out.println(map);
        System.out.println(list1);
        Collections.sort(list1,new Comparator1());
        System.out.println(list1);
        List<Map.Entry<Integer, Pojo> > list =
                new ArrayList(map.entrySet());
        System.out.println(list.get(0).getValue());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Pojo>>() {
            @Override
            public int compare(Map.Entry<Integer, Pojo> o1, Map.Entry<Integer, Pojo> o2) {
                return o1.getValue().getNumber()-o2.getValue().getNumber();
            }
        });
        map.clear();
        for(Map.Entry<Integer,Pojo> a:list)
            map.put(a.getKey(), a.getValue());
        System.out.println(map);
    }
}
//4
//        zuka
//        34
//        frank
//        12
//        deepak
//        12
//        abi
//        13