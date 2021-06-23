package me.discgold.strangerthings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Level3 extends AppCompatActivity {
    private long backPressedTime;
    private Toast backToast;
    Delay delay = new Delay();
    public Animation a;
    public TextView textView1;
    public ImageView imageView1;
    public Button button_1;


    public int line = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_3);
        stopService(new Intent(this, MyService.class));
        startService(new Intent(this, MusicService.class));
        SharedPreferences save=getSharedPreferences("Save", MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
        editor.putInt("Level",2);
        editor.commit();

        final TextView textView1 = (TextView)findViewById(R.id.textView1);
        final ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
        final Button button_1 = (Button)findViewById(R.id.button_1);




        //внизу будет код, который прячет текст
        textView1.setVisibility(View.INVISIBLE);
        imageView1.setVisibility(View.INVISIBLE);
        button_1.setVisibility(View.INVISIBLE);

        //конец скрытия текста

        //команда, которая запускает AsyncTask
        delay.execute();
        //конец команды, которая запускает AsyncTask
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_1.setTextColor(getResources().getColor(R.color.blue));
                stopService(new Intent(Level3.this, MusicService.class));
                try{
                    Intent intent = new Intent (Level3.this, Level0.class);
                    startActivity(intent);finish();
                } catch (Exception e) {

                }
            }
        });


        Window w =getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    //с этого места начинается код AsyncTask
    class Delay extends AsyncTask<Void, Integer, Void>{

        @Override
            protected Void doInBackground(Void... params){
            while (line<=3){
                publishProgress(line++);
                try{
                    Thread.sleep(2000);
                    if (isCancelled()) return null;
                }catch(Exception e){}
            }
            return null;
        }

        @Override
        protected void onProgressUpdate (Integer... values){
        final TextView textView1 = (TextView)findViewById(R.id.textView1);
        final ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);

        final Button button_1 = (Button)findViewById(R.id.button_1);


        final Animation a = AnimationUtils.loadAnimation(Level3.this, R.anim.alpha);

            switch(line){
                case 1: textView1.setVisibility(View.VISIBLE); textView1.startAnimation(a); break;
                case 2: imageView1.setVisibility(View.VISIBLE); imageView1.startAnimation(a); break;
                case 3: button_1.setVisibility(View.VISIBLE); button_1.startAnimation(a);break;

                default: break;
            }
        }

    }
    //с этого места заканчивается код AsyncTask

    //системная кнопка "Назад" - начало
    @Override
    public void onBackPressed(){
        try{
            Intent intent = new Intent (Level3.this, Level0.class);
            stopService(new Intent(this, MusicService.class));
            startActivity(intent);finish();
        } catch (Exception e) {

        }
    }
    //системная кнопка "Назад" - конец
}
