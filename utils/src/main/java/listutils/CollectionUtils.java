package listutils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 集合的工具类
 * */
public class CollectionUtils {

    /**
     *获取两个集合之和
     * */
    public static  <T> List<T> collectionUnion(List<T> list1,List<T>list2){
        list1.addAll(list2);
        return list1.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 获取两个集合非公共部分
     * */

    public static <T extends Comparable<? super T>> List<T> CollectionDifference(List<T> list1,List<T>list2){
        list1.addAll(list2);
        Collections.sort(list1);
        System.out.println(list1);
        List<T> list=new ArrayList<>();
        for (int t=0;t<list1.size()-1;t++){
            if (list1.get(t)==list1.get(t+1)){
                list.add(list1.get(t));
            }
        }

        list1.removeAll(list);
        return list1;
    }


}
