package hebiao.online.hot;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    private void doSomethingAction(){

        Toast.makeText(this,"我是 旧的 ，不解释 ",Toast.LENGTH_LONG).show();

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




    public void show(View v){
        doSomethingAction();
    }






}
