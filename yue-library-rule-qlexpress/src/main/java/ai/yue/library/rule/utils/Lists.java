package ai.yue.library.rule.utils;

import java.util.ArrayList;
import java.util.List;

public class Lists {

    public static <T> List<T> asList(T... ts){
        ArrayList<T> list = new ArrayList<>();
        if (ts != null) {
            for (T t : ts) {
                list.add(t);
            }
        }
        return list;
    }
}
