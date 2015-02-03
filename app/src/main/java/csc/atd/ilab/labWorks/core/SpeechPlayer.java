package csc.atd.ilab.labWorks.core;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

public class SpeechPlayer
{
    private TextToSpeech textToSpeech;
    private static SpeechPlayer player;
    private SpeechPlayer(Context appContext){

        textToSpeech=new TextToSpeech(appContext,
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            int result = textToSpeech.setLanguage(Locale.US);

                            if (result == TextToSpeech.LANG_MISSING_DATA
                                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                                Log.e("error", "This Language is not supported");
                            }else {
                                textToSpeech.speak("Speech engine initialized", TextToSpeech.QUEUE_ADD, null);
                            }
                        } else {
                            Log.e("error", "Speech initialization engine failed");
                        }
                    }
                });
    }

    public static SpeechPlayer getInstance(Context appContext)
    {
        if(player == null)
            player = new SpeechPlayer(appContext);

        return player;
    }

    public void play(String textToPlay)
    {
        textToSpeech.speak(textToPlay, TextToSpeech.QUEUE_ADD, null);
    }
}