package com.tetris.playfield;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
	
	private static Sound _inst;
	public static Sound Inst() {
		if (_inst == null)
			_inst = new Sound();
		return _inst;
	}
	
	public static List<MediaPlayer> sounds = new ArrayList<MediaPlayer>();
	static {
		String[] musicFiles = new String[]{
				"resource/sound/game_over.mp3",
				"resource/sound/background.mp3",
				"resource/sound/rows_completed.mp3",
				"resource/sound/shape_locked.mp3"
		};

		//Media sound = new Media(new File(musicFile).toURI().toString());
		for (int i = 0; i < musicFiles.length; i++) {
			Media sound = new Media(new File(musicFiles[i]).toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.setAutoPlay(true);
			mediaPlayer.stop();
			
			sounds.add(mediaPlayer);
		}
	}
	
	int currentID;
	
	// Volumn is [0.0 to 1.0]
	public void SetVolumn(double volumn) {
		for (int i = 0; i < sounds.size(); i++)
			sounds.get(i).setVolume(volumn);
	}
	
	public void SetSong(int id) {
		SetSong(id, true);
	}
	public void SetSong(int id, boolean autoplay) {
		if (id >= sounds.size() || id < 0)
			id = 0;
		sounds.get(currentID).stop();
		currentID = id;
		if (autoplay)
			sounds.get(currentID).play();
	}
	
	// Speed is (0.0 to 8.0]
	public void SetPlaybackRate(double speed) {
		for (int i = 0; i < sounds.size(); i++)
			sounds.get(i).setRate(speed);
	}
	
	public void Pause() {
		sounds.get(currentID).pause();
	}
	
	public void Play() {
		sounds.get(currentID).play();
	}
	
	public void Stop() {
		sounds.get(currentID).stop();
	}
	
	public void PlayOnce(int id) {
		sounds.get(id).setAutoPlay(false);
		sounds.get(id).play();
		sounds.get(id).setOnEndOfMedia(() -> sounds.get(id).setAutoPlay(true));
	}
}
