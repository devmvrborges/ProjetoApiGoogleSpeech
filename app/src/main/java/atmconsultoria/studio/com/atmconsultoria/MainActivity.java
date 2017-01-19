package atmconsultoria.studio.com.atmconsultoria;

/*
Aplicativo desenvolvido por Maurício Valentim Rodrigues Borges dia 18/01/2017
estudante de Ciência da computação na Faculdade de Jaguariúna.

DESCRIÇÃO: Ideia de um aplicativo com a utilização dessa API Speech
*/

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.concurrent.RunnableFuture;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener, TextToSpeech.OnUtteranceCompletedListener{

        TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textToSpeech = new TextToSpeech(MainActivity.this,MainActivity.this);
        final Button SpkButton = (Button) findViewById(R.id.button);
        final TextView textView = (TextView) findViewById(R.id.textView);


        SpkButton.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                if(!textToSpeech.isSpeaking()) {
                    HashMap<String, String> stringStringHashMap = new HashMap<String, String>();
                    stringStringHashMap.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "ola você está ai?");
                    textToSpeech.speak(textView.getText().toString(), TextToSpeech.QUEUE_ADD, stringStringHashMap);
                    SpkButton.setVisibility(Button.GONE);
                }else{
                    textToSpeech.stop();

                }
            }
        });
    }
    @Override
    public void onInit(int status) {
textToSpeech.setOnUtteranceCompletedListener(this);
    }

    @Override
    public void onUtteranceCompleted(String s) {
runOnUiThread(new Runnable() {

    @Override
    public void run() {
        Toast.makeText(MainActivity.this,"COMPLETADO",Toast.LENGTH_SHORT).show();
        Button button = (Button) findViewById(R.id.button);
        button.setVisibility(Button.VISIBLE);
    }
});
    }

    protected void onDestroy(){
        if (textToSpeech!=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
            textToSpeech =null;

        }
    }

}
