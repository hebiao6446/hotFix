package hebiao.online.hot;

import android.content.Context;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Enumeration;

import dalvik.system.DexFile;

/**
 * Created by apple on 2018/3/17.
 */

public class DexFileManager {

    private Context context;

    public DexFileManager(Context c){
        this.context = c;
    }


    /**
     * 加载 dex文件
     */

    public void loadDexFile(File file) {
        try{
            /**
             * 加载dex文件 ，三个参数
             * 第一个参数 ： 文件绝对路径
             * 第二个参数： 临时缓存文件
             * 第三个参数：默认
             */
            DexFile dexFile = DexFile.loadDex(file.getAbsolutePath(),new File(context.getCacheDir(),"opt").getAbsolutePath(),Context.MODE_PRIVATE);




            /**
             * 遍历 dex中的类
             */
            Enumeration<String > entry = dexFile.entries();


            while (entry.hasMoreElements()){

                String className = entry.nextElement();
                System.out.println(" ++$########### " +className);
                Class fixClass = dexFile.loadClass(className,context.getClassLoader());


                fixMethonAction(fixClass);
            }

        }catch (Exception e){

        }


    }

    /**
     * 撸这个类，
     * @param fixClass  ，这个类是 dex 文件中的 ；注意！！！！！！
     */
    private void fixMethonAction(Class fixClass) throws Exception{

        /**
         * 找到 dex文件中类的所有方法
         */

        Method[] methods = fixClass.getDeclaredMethods();

        for (Method m: methods) {








//            HotFixInterface hf = m.getAnnotation(HotFixInterface.class);


            HotFixInterface hf = m.getAnnotation(HotFixInterface.class);
            if (hf == null){
                continue;
            }


            /**
             * 若找到 dex文件中类 的注解，则说明这个方法需要被修复或替换
             */
            String wrongClassName = hf.clazz();
            String wrongMethodName = hf.method();




            /**
             * 获取 旧的， 需要被修复的 方法
             */
            Class wrongClass = Class.forName(wrongClassName);
            Method wrongMethod = wrongClass.getDeclaredMethod(wrongMethodName,m.getParameterTypes());



            System.out.println( "   **************   "+wrongClass.getName()+"     wrongMethod ===  "+wrongMethod.getName());

           /**
             * 方法将 正确的方法和错误的方法交换 。。。
             */


            System.out.println("-------start---------111");
            MethodUtil.changeMethod(wrongMethod,m);




        }


    }
}
