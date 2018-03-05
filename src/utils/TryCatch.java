package utils;

import java.util.Map;

public class TryCatch {
    public static <T,R> R run(Function<T,R> dothis, T params, Map<? extends Exception, Function<?,?>> excepts) {
        try {
            return dothis.run(params);
        } catch(Exception e) {
            // TODO: think & fix
            // for (Exception exc : excepts.keySet())
            //     if (e instanceof exc)
            //         excepts.get(exc).run(3);
        }
        
        // Nothing
        return null;
    }
}