package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    AudioManager mAudioManager;
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> arrayList = new ArrayList<Word>();
        arrayList.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        arrayList.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        arrayList.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        arrayList.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        arrayList.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        arrayList.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        arrayList.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        arrayList.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        arrayList.add(new Word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        arrayList.add(new Word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));

        WordAdapter itemsAdapter = new WordAdapter(this, arrayList, R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
// old one to ten shit - no adapter, w/ linear layout,
//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.word_list);
//        for (int i = 0; i < arrayList.size(); i++){
//            TextView textView = new TextView(this);
//            textView.setText(arrayList.get(i));
//            linearLayout.addView(textView);
//        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word wordAudioId = arrayList.get(position);

                Log.v("Numbr Activt", "current WORD:::" + wordAudioId); //Note: If you concatenate (with the “+” operator) a string with a Word object, then Java will implicitly call the toString() method on the object.

                // Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // WE HAVE AUDIO FOCUS
                    releaseMediaPlayer();

                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, wordAudioId.getSoundFile());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            releaseMediaPlayer();
                        }
                    });
                }
                mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
                    @Override
                    public void onAudioFocusChange(int focusChange) {
                        if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                            // Pause playback because your Audio Focus was
                            // temporarily stolen, but will be back soon.
                            // i.e. for a phone call
                            mediaPlayer.pause();
                            mediaPlayer.seekTo(0);
                        } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                            // Stop playback, because you lost the Audio Focus.
                            // i.e. the user started some other playback app
                            // Remember to unregister your controls/buttons here.
                            // And release the kra — Audio Focus!
                            // You’re done.
                            releaseMediaPlayer();
                        } else if (focusChange ==
                                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                            // Lower the volume, because something else is also
                            // playing audio over you.
                            // i.e. for notifications or navigation directions
                            // Depending on your audio playback, you may prefer to
                            // pause playback here instead. You do you.
                            mediaPlayer.pause();
                            mediaPlayer.seekTo(0);
                        } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                            // Resume playback, because you hold the Audio Focus
                            // again!
                            // i.e. the phone call ended or the nav directions
                            // are finished
                            // If you implement ducking and lower the volume, be
                            // sure to return it to normal here, as well.
                            mediaPlayer.start();
                        }
                    }
                };
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
