package akshay.unit.equalizerunit;

import android.media.MediaPlayer;
import android.media.audiofx.Equalizer;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnSeekBarChangeListener {

	Equalizer eq;
	int index = 15;
	TextView tv, tv1, tv2, tv3, tv4;
	SeekBar sb, sb1, sb2, sb3, sb4;
	MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sb = (SeekBar) findViewById(R.id.seekBar1);
		sb1 = (SeekBar) findViewById(R.id.seekBar2);
		sb2 = (SeekBar) findViewById(R.id.seekBar3);
		sb3 = (SeekBar) findViewById(R.id.seekBar4);
		sb4 = (SeekBar) findViewById(R.id.seekBar5);

		tv = (TextView) findViewById(R.id.textView1);
		tv1 = (TextView) findViewById(R.id.textView2);
		tv2 = (TextView) findViewById(R.id.textView3);
		tv3 = (TextView) findViewById(R.id.textView4);
		tv4 = (TextView) findViewById(R.id.textView5);

		mp = MediaPlayer.create(this, R.raw.emptiness);
		eq = new Equalizer(1, mp.getAudioSessionId());
		eq.setEnabled(true);
		short m = eq.getNumberOfPresets();

		String[] music_presets = new String[m];
		for (int i = 0; i < m; i++) {

			music_presets[i] = eq.getPresetName((short) i);
			Log.i("Preset Name", music_presets[i]);
		}
		short[] l = eq.getBandLevelRange();
		
		eq.setBandLevel((short) 0, (short) 650);
		eq.setBandLevel((short) 1, (short) 230);
		eq.setBandLevel((short) 2, (short) 950);
		eq.setBandLevel((short) 3, (short) 500);
		eq.setBandLevel((short) 4, (short) 200);

		sb.setOnSeekBarChangeListener(this);
		sb1.setOnSeekBarChangeListener(this);
		sb2.setOnSeekBarChangeListener(this);
		sb3.setOnSeekBarChangeListener(this);
		sb4.setOnSeekBarChangeListener(this);
		eq.usePreset((short) 6);

		Log.d("AudioFx", "Equalizer Initialized");
		mp.start();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		eq.release();
		mp.release();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
		// TODO Auto-generated method stub
		int db;

		switch (arg0.getId()) {
		case R.id.seekBar1:
			/*
			 * progress=progress-index; db=progress*100;
			 */
			progress = progress - index;
			db = progress * 100;
			// eq.setBandLevel((short)0, (short)db);
			setBandFrequency(0, db);
			tv.setText(progress + " db");
			break;
		case R.id.seekBar2:
			// progress=0;
			progress = progress - index;
			db = progress * 100;
			// eq.setBandLevel((short)1, (short)db);
			setBandFrequency(1, db);
			tv1.setText(progress + " db");
			Toast.makeText(getApplicationContext(), "Seekbar2",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.seekBar3:
			progress = progress - index;
			db = progress * 100;
			// 1eq.setBandLevel((short)2, (short)db);
			setBandFrequency(2, db);
			tv2.setText(progress + " db");

			break;
		case R.id.seekBar4:
			progress = progress - index;
			db = progress * 100;
			// eq.setBandLevel((short)3, (short)db);
			setBandFrequency(3, db);
			tv3.setText(progress + " db");
			break;
		case R.id.seekBar5:
			progress = progress - index;
			db = progress * 100;
			// eq.setBandLevel((short)4, (short)db);
			setBandFrequency(4, db);
			tv4.setText(progress + " db");

		default:
			break;
		}

	}

	public void setBandFrequency(int band, int freq) {
		eq.setBandLevel((short) band, (short) freq);
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

}
