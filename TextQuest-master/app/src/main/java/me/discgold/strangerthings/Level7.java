package me.discgold.strangerthings;

import android.content.Intent;
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

import androidx.appcompat.app.AppCompatActivity;

public class Level7 extends AppCompatActivity {
    SevenTable sevenTable = new SevenTable();//сценарий
    Delay delay = new Delay();
    public Animation a;
    public TextView textView1;
    public TextView textView3;
    public ImageView imageView2;
    public Button buttonNextLevel8;
    public Button buttonNextLevel9;
    public int line = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_7);

        final TextView textView1 = (TextView)findViewById(R.id.textView1);
        final TextView textView3 = (TextView)findViewById(R.id.textView3);
        final ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        final Button buttonNextLevel8 = (Button)findViewById(R.id.button_pass_level8);
        final Button buttonNextLevel9 = (Button)findViewById(R.id.button_pass_level9);

        textView1.setText(sevenTable.onescenario_ru[0]);
        textView3.setText(sevenTable.onescenario_ru[2]);
        //buttonNextLevel8.setText(sevenTable.onescenario_ru[3]);
       // buttonNextLevel9.setText(sevenTable.onescenario_ru[4]);

        //внизу будет код, который прячет текст
        textView1.setVisibility(View.INVISIBLE);
        textView3.setVisibility(View.INVISIBLE);
        imageView2.setVisibility(View.INVISIBLE);
        buttonNextLevel8.setVisibility(View.INVISIBLE);
        buttonNextLevel9.setVisibility(View.INVISIBLE);
        buttonNextLevel8.setVisibility(View.GONE);
        buttonNextLevel9.setVisibility(View.GONE);
        //конец скрытия текста

        //команда, которая запускает AsyncTask
        delay.execute();
        //конец команды, которая запускает AsyncTask

        buttonNextLevel8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonNextLevel8.setTextColor(getResources().getColor(R.color.blue));
                try{
                    Intent intent;
                    intent = new Intent (Level7.this, Level8.class);
                    startActivity(intent);finish();
                } catch (Exception e) {

                }
            }
        });
        buttonNextLevel9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonNextLevel9.setTextColor(getResources().getColor(R.color.blue));
                try{
                    Intent intent;
                    intent = new Intent (Level7.this, Level9.class);
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
            while (line<=5){
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
            final TextView textView3 = (TextView)findViewById(R.id.textView3);
            final ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
            final Button buttonNextLevel8 = (Button)findViewById(R.id.button_pass_level8);
            final Button buttonNextLevel9 = (Button)findViewById(R.id.button_pass_level9);
            final Animation a = AnimationUtils.loadAnimation(Level7.this, R.anim.alpha);

            switch(line){
                case 1: textView1.setVisibility(View.VISIBLE); textView1.startAnimation(a); break;
                case 2: imageView2.setVisibility(View.VISIBLE); imageView2.startAnimation(a); break;
                case 3: textView3.setVisibility(View.VISIBLE); textView3.startAnimation(a); break;
                case 4: buttonNextLevel8.setVisibility(View.VISIBLE); buttonNextLevel8.startAnimation(a); break;
                case 5: buttonNextLevel9.setVisibility(View.VISIBLE); buttonNextLevel9.startAnimation(a); break;
                default: break;
            }
        }

    }
    //с этого места заканчивается код AsyncTask

}
