package me.discgold.strangerthings;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.animation.Animation;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;

public class Level1 extends AppCompatActivity {
    private long backPressedTime;
    private Toast backToast;
    Delay delay = new Delay();
    public Animation a;
    public TextView textView1;
    public ImageView imageView1;
    public TextView textView2;
    public Button buttonNextLevel;

    public int line = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_1);

        SharedPreferences save=getSharedPreferences("Save", MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
        editor.putInt("Level",0);
        editor.commit();

        final TextView textView1 = (TextView)findViewById(R.id.textView1);
        final ImageView imageView1 = (ImageView) findViewById(R.id.level_one_image);
        final TextView textView2 = (TextView)findViewById(R.id.textView2);
        final Button buttonNextLevel = (Button)findViewById(R.id.button_next_level);



        // прячет текст
        textView1.setVisibility(View.INVISIBLE);
        imageView1.setVisibility(View.INVISIBLE);
        textView2.setVisibility(View.INVISIBLE);
        buttonNextLevel.setVisibility(View.INVISIBLE);

        //команда, которая запускает AsyncTask
        delay.execute();

        // ПЕРЕДЕЛАТЬ ПЕРЕХОД НА ДРУГОЙ УРОВЕНЬ

        buttonNextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonNextLevel.setTextColor(getResources().getColor(R.color.blue));
                try{
                    Intent intent = new Intent (Level1.this, Level2.class);
                    startActivity(intent);finish();
                } catch (Exception e) {
                    e.printStackTrace();
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
                try {
                    Thread.sleep(2000);
                    if (isCancelled()) return null;
                } catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate (Integer... values){
            final TextView textView1 = (TextView)findViewById(R.id.textView1);

            final ImageView imageView1 = (ImageView) findViewById(R.id.level_one_image);
            final TextView textView2 = (TextView)findViewById(R.id.textView2);

            final Button buttonNextLevel = (Button)findViewById(R.id.button_next_level);
            final Animation a = AnimationUtils.loadAnimation(Level1.this, R.anim.alpha);
            switch(line)
            {
                case 1: textView1.setVisibility(View.VISIBLE); textView1.startAnimation(a); break;
                case 2: imageView1.setVisibility(View.VISIBLE); imageView1.startAnimation(a); break;
                case 3: textView2.setVisibility(View.VISIBLE); textView2.startAnimation(a); break;
                case 4: buttonNextLevel.setVisibility(View.VISIBLE); buttonNextLevel.startAnimation(a); break;
                default: break;
            }
        }

    }

    //системная кнопка "Назад" - начало
    @Override
    public void onBackPressed(){
        try{
            Intent intent = new Intent (Level1.this, Level0.class);
            startActivity(intent);finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
