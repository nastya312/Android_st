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

public class Level10 extends AppCompatActivity {
    TenTable tenTable = new TenTable();
    Delay delay = new Delay();
    public Animation a;
    public TextView textView1;
    public Button button_level10;
    public int line = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_10);
        final TextView textView1 = (TextView)findViewById(R.id.textView1);
        final Button button_level10 = (Button)findViewById(R.id.button_level10);

        textView1.setText(tenTable.onescenario_ru[0]);
        //внизу будет код, который прячет текст
        textView1.setVisibility(View.INVISIBLE);
        button_level10.setVisibility(View.INVISIBLE);
        //конец скрытия текста

        //команда, которая запускает AsyncTask
        delay.execute();
        //конец команды, которая запускает AsyncTask

        button_level10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_level10.setTextColor(getResources().getColor(R.color.blue));
                try{
                    Intent intent = new Intent (Level10.this, Level0.class);
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
            while (line<=2){

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
        final Button button_level10 = (Button)findViewById(R.id.button_level10);
        final Animation a = AnimationUtils.loadAnimation(Level10.this, R.anim.alpha);

            switch(line){
                case 1: textView1.setVisibility(View.VISIBLE); textView1.startAnimation(a); break;
                case 2: button_level10.setVisibility(View.VISIBLE); button_level10.startAnimation(a); break;
                default: break;
            }
        }

    }


}
