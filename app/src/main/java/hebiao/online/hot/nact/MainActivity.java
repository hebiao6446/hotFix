package hebiao.online.hot.nact;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Method;

import hebiao.online.hot.DexFileManager;
import hebiao.online.hot.HotFixInterface;
import hebiao.online.hot.R;

/**
 * Created by apple on 2018/3/17.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @HotFixInterface(clazz = "hebiao.online.hot.MainActivity",method = "doSomethingAction")
    private void doSomethingAction(){

        Toast.makeText(this,"我是 新的 ，要去覆盖旧的",Toast.LENGTH_LONG).show();

    }

    public void xload(View v){
        Class clazz = this.getClass();

        Method[] methods = clazz.getDeclaredMethods();

        for (Method m: methods) {
            HotFixInterface hf = m.getAnnotation(HotFixInterface.class);
            if (hf!=null){
                System.out.println(" &&&&&&&&&&&&  "+hf.method());
            }

        }

    }

    public void load(View v){


        DexFileManager dexFileManager = new DexFileManager(this);
        String name = getSdCardPath()+"/aaa/hot.dex";
        File file = new File(name);
        if (file.exists()){
            System.out.println("xxxxxxxxxxxxx  文件存在");
            dexFileManager.loadDexFile(file);
        }else {
            System.out.println("瞎了 。。。。 ");
        }

    }

    public void show(View v){
        doSomethingAction();
    }


    public static boolean isSdCardExist() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }


    public static String getSdCardPath() {
        boolean exist = isSdCardExist();
        String sdpath = "";
        if (exist) {
            sdpath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath();
        }
        return sdpath;

    }




}
