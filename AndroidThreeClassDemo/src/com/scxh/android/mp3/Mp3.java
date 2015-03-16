package com.scxh.android.mp3;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.scxh.android.R;

public class Mp3 extends ListActivity{

	private static String MEDIA_PATH = "";
	private List<String> songs = new ArrayList<String>();
	private MediaPlayer mp = new MediaPlayer();
	private ListAdapter songList;
	private int currentPosition = 0;
	private final int SHOW_PLAYER = 1;
	private ListView listView;
	private HashMap hashMap = new HashMap();
	private boolean isEmpty = false;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		File sdcardFile = Environment.getExternalStorageDirectory();
		
		MEDIA_PATH = sdcardFile.getAbsolutePath();
		Log.v("TAG", "getAbsolutePath  :"+MEDIA_PATH + " \n  getPath() :"+sdcardFile.getPath());
		
		updateSongList();
		
		listView = (ListView) getListView();
		listView.setItemsCanFocus(false);
		listView.setClickable(true);
		if (songs.size() == 0) {
			isEmpty = true;
			songs.add("no mp3 files in sdcard");
			songList = new ListAdapter(this,android.R.layout.simple_list_item_1, songs);
			setListAdapter(songList);
		}
	}

	public void updateSongList() {

		findNewSongs(MEDIA_PATH);
		
		songList = new ListAdapter(this,android.R.layout.simple_list_item_1, songs);
		setListAdapter(songList);
	}

	public void findNewSongs(String mediaPath) {

		File home = new File(mediaPath);
		
		if (home.listFiles() == null){
			return;
		}
		if (home.listFiles().length > 0) {
			for (File file : home.listFiles()) {
				if (file.isDirectory()) {
					findNewSongs(file.getAbsolutePath() + "/");
				}else{
					if(file.getName().endsWith("mp3")){
						songs.add(file.getName());
						hashMap.put(file.getName(), file.getAbsolutePath());
					}
				}
			}
		}
	}

	private void nextSong() {
		if (++currentPosition >= songs.size()) {
			// Last song, just reset currentPosition
			currentPosition = 0;
		}
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {

		if (isEmpty == false) {
			currentPosition = position;

			String songPath = (String) hashMap.get(songs.get(currentPosition));

			Intent intent = new Intent(Mp3.this, ShowPlayer.class);

			Bundle bundle = new Bundle();

			bundle.putString("songPath", songPath);

			intent.putExtra("playPara", bundle);

			this.startActivityForResult(intent, SHOW_PLAYER);

		}
	}

	public class ListAdapter extends ArrayAdapter {
		public ListAdapter(final Context context, final int resource,final List<String> string) {
			super(context, resource, string);
		}

		public final View getView(final int position, View convertView,final ViewGroup parent) {

			final LayoutInflater inflater = (LayoutInflater) getLayoutInflater();
			convertView = inflater.inflate(R.layout.arrayset, parent, false);

			TextView textView = (TextView) convertView.findViewById(R.id.EditText);

			textView.setText(Mp3.this.songs.get(position));

			return convertView;
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {

		case 1:

			if (resultCode == 2) {

				nextSong();

				String songPath = (String) hashMap.get(songs.get(currentPosition));

				Intent intent = new Intent(Mp3.this, ShowPlayer.class);

				Bundle bundle = new Bundle();

				bundle.putString("songPath", songPath);

				intent.putExtra("playPara", bundle);

				this.startActivityForResult(intent, SHOW_PLAYER);

			} else {

				nextSong();

			}
			break;

		default:

			break;

		}

	}

}