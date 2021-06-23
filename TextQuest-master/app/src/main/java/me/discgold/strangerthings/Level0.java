package me.discgold.strangerthings;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Level0 extends AppCompatActivity {
    private long backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // сообщает телефону, что у того есть экран activity_main
        // и выполняет xml
        setContentView(R.layout.level_0);
        // находит кнопку с id buttonStart
        startService(new Intent(this, MyService.class));
        final Button buttonStart = (Button)findViewById(R.id.buttonStart);
        // нажимает на кнопку
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                buttonStart.setTextColor(getResources().getColor(R.color.blue));

                    Intent intent = new Intent (Level0.this, Level1.class);
                    // переход с главной страницы на level1
                    startActivity(intent);finish();

            }
        });

        Window w =getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onBackPressed(){
        try{
            Intent intent = new Intent (Level0.this, MainActivity.class);
            startActivity(intent);finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*@Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            stopService(new Intent(this, MyService.class));
            super.onBackPressed();
            return;
        }else{
            backToast =Toast.makeText(getBaseContext(), "Press again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime = System.currentTimeMillis();
    }*/
}
