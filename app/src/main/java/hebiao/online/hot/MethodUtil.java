package hebiao.online.hot;

import java.lang.reflect.Method;

/**
 * Created by apple on 2018/3/17.
 */

public class MethodUtil {

    static {
        System.loadLibrary("native-lib");
    }

    /**
     *
     * @param m1  原来的方法
     * @param m2  新的方法 ，dex 文件中的方法
     */
    public  static native void changeMethod(Method m1,Method m2);

}
