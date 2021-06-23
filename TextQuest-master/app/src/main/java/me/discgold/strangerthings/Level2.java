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

public class Level2 extends AppCompatActivity {
    private long backPressedTime;
    private Toast backToast;
    Delay delay = new Delay();
    public Animation a;
    public TextView textView0;
    public ImageView imageView0;
    public TextView textView1;
    public Button button_next_level1;
    public Button button_next_level2;
    public Button button_next_level3;

    public int line = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_2);

        SharedPreferences save=getSharedPreferences("Save", MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
        editor.putInt("Level",1);
        editor.commit();

        final TextView textView0 = (TextView)findViewById(R.id.textView0);
        final ImageView imageView0 = (ImageView) findViewById(R.id.imageView0);
        final TextView textView1 = (TextView)findViewById(R.id.textView1);

        final Button button_next_level1 = (Button)findViewById(R.id.button_next_level1);
        final Button button_next_level2 = (Button)findViewById(R.id.button_next_level2);
        final Button button_next_level3 = (Button)findViewById(R.id.button_next_level3);




        //внизу будет код, который прячет текст
        textView0.setVisibility(View.INVISIBLE);
        imageView0.setVisibility(View.INVISIBLE);
        textView1.setVisibility(View.INVISIBLE);

            button_next_level1.setVisibility(View.INVISIBLE);
            button_next_level2.setVisibility(View.INVISIBLE);
            button_next_level3.setVisibility(View.INVISIBLE);
            
            button_next_level1.setVisibility(View.GONE);
            button_next_level2.setVisibility(View.GONE);
            button_next_level3.setVisibility(View.GONE);




        //конец скрытия текста

        //команда, которая запускает AsyncTask
        delay.execute();
        //конец команды, которая запускает AsyncTask
        button_next_level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_next_level1.setTextColor(getResources().getColor(R.color.blue));
                try{
                    Intent intent = new Intent (Level2.this, Level1.class);
                    startActivity(intent);finish();
                } catch (Exception e) {

                }
            }
        });

        button_next_level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_next_level2.setTextColor(getResources().getColor(R.color.blue));
                try{
                    Intent intent = new Intent (Level2.this, Level3.class);
                    startActivity(intent);finish();
                } catch (Exception e) {

                }
            }
        });

        button_next_level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_next_level3.setTextColor(getResources().getColor(R.color.blue));
                try{
                    Intent intent = new Intent (Level2.this, Level4.class);
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
            while (line<=6){
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
        final TextView textView0 = (TextView)findViewById(R.id.textView0);
        final ImageView imageView0 = (ImageView) findViewById(R.id.imageView0);
        final TextView textView1 = (TextView)findViewById(R.id.textView1);
        final Button button_next_level1 = (Button)findViewById(R.id.button_next_level1);
        final Button button_next_level2 = (Button)findViewById(R.id.button_next_level2);
        final Button button_next_level3 = (Button)findViewById(R.id.button_next_level3);


        final Animation a = AnimationUtils.loadAnimation(Level2.this, R.anim.alpha);

            switch(line){
                case 1: textView0.setVisibility(View.VISIBLE); textView0.startAnimation(a); break;
                case 2: imageView0.setVisibility(View.VISIBLE); imageView0.startAnimation(a); break;
                case 3: textView1.setVisibility(View.VISIBLE); textView1.startAnimation(a); break;
                case 4: button_next_level1.setVisibility(View.VISIBLE); button_next_level1.startAnimation(a); break;
                case 5: button_next_level2.setVisibility(View.VISIBLE); button_next_level2.startAnimation(a); break;
                case 6: button_next_level3.setVisibility(View.VISIBLE); button_next_level3.startAnimation(a); break;
                default: break;
            }
        }

    }
    //с этого места заканчивается код AsyncTask

    //системная кнопка "Назад" - начало
    @Override
    public void onBackPressed(){
        try{
            Intent intent = new Intent (Level2.this, Level0.class);
            startActivity(intent);finish();
        } catch (Exception e) {

        }
    }
    //системная кнопка "Назад" - конец
}
