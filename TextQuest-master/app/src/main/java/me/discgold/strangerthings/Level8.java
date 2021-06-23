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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Level8 extends AppCompatActivity {
    EightTable eightTable = new EightTable();
    Delay delay = new Delay();
    public Animation a;
    public TextView textView1;
    public TextView textView3;
    public ImageView imageView2;
    public Button button_level8;

    public int line = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_8);
        final TextView textView1 = (TextView)findViewById(R.id.textView1);
        final TextView textView3 = (TextView)findViewById(R.id.textView3);
        final ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        final Button button_level8 = (Button)findViewById(R.id.button_level8);


        textView1.setText(eightTable.onescenario_ru[0]);
        textView3.setText(eightTable.onescenario_ru[2]);
        //button_level8.setText(eightTable.onescenario_ru[4]);

        //внизу будет код, который прячет текст
        textView1.setVisibility(View.INVISIBLE);
        imageView2.setVisibility(View.INVISIBLE);
        textView3.setVisibility(View.INVISIBLE);
        button_level8.setVisibility(View.INVISIBLE);
        //конец скрытия текста

        //команда, которая запускает AsyncTask
        delay.execute();
        //конец команды, которая запускает AsyncTask
        button_level8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_level8.setTextColor(getResources().getColor(R.color.blue));
                try{//LEVEL 3
                    Intent intent = new Intent (Level8.this, Level3.class);
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
        final TextView textView3 = (TextView)findViewById(R.id.textView3);
        final ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        final Button button_level8 = (Button)findViewById(R.id.button_level8);


        final Animation a = AnimationUtils.loadAnimation(Level8.this, R.anim.alpha);

            switch(line){
                case 1: textView1.setVisibility(View.VISIBLE); textView1.startAnimation(a); break;
                case 2: imageView2.setVisibility(View.VISIBLE); imageView2.startAnimation(a); break;
                case 3: textView3.setVisibility(View.VISIBLE); textView3.startAnimation(a); break;
                case 4: button_level8.setVisibility(View.VISIBLE); button_level8.startAnimation(a);break;
                default: break;
            }
        }

    }
    //с этого места заканчивается код AsyncTask

}
