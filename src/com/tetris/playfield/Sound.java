package com.tetris.playfield;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound implements AutoCloseable{
	
	private static Sound _inst;
	public static Sound Inst() {
		if (_inst == null)
			_inst = new Sound();
		return _inst;
	}
	
	public static Media[] sounds;
	static {
		String[] musicFiles = new String[]{
				"resource/sound/game_over.mp3",
				"resource/sound/background.mp3",
				"resource/sound/rows_completed.mp3",
				"resource/sound/shape_locked.mp3"
		};
		sounds = new Media[musicFiles.length];

		//Media sound = new Media(new File(musicFile).toURI().toString());
		for (int i = 0; i < musicFiles.length; i++) {
			Media sound = new Media(new File(musicFiles[i]).toURI().toString());
			
			sounds[i] = sound;
		}
	}
	
	int currentID;
	public double[] volumns = new double[sounds.length];
	double rate = initialRate;
	public static double initialRate = 0.5;
	MediaPlayer mediaPlayer;
	
	public Sound() {
		for (int i = 0; i < volumns.length; i++)
			volumns[i] = 0.5;
		mediaPlayer = new MediaPlayer(sounds[currentID]);
		mediaPlayer.setVolume(volumns[currentID]);
		mediaPlayer.setRate(rate);
		mediaPlayer.setAutoPlay(true);
	}
	
	// Volumn is [0.0 to 1.0]
	public void SetVolumn(int id, double volumn) {
		volumns[id] = volumn;

		//System.out.println(volumns[id]);
		if (id == currentID)
			mediaPlayer.setVolume(volumn);
	}
	
	public void SetSong(int id) {
		SetSong(id, true);
	}
	public void SetSong(int id, boolean autoplay) {
		if (id >= sounds.length || id < 0)
			id = 0;
		mediaPlayer.stop();
		mediaPlayer.dispose();
		currentID = id;
		
		mediaPlayer = new MediaPlayer(sounds[currentID]);
		mediaPlayer.setVolume(volumns[currentID]);
		mediaPlayer.setRate(rate);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.setOnEndOfMedia(() -> SetSong(currentID, autoplay));
		
		if (autoplay)
			mediaPlayer.play();
	}
	
	// Speed is (0.0 to 8.0]
	public void SetPlaybackRate(double speed) {
		rate = speed;
		mediaPlayer.setRate(rate);
	}
	
	public void Pause() {
		mediaPlayer.pause();
	}
	
	public void Play() {
		mediaPlayer.play();
	}
	
	public void Stop() {
		mediaPlayer.stop();
	}
	
	public void PlayOnce(int id) {
		MediaPlayer temp = new MediaPlayer(sounds[id]);
		temp.setVolume(volumns[id]);
		
		temp.play();
		temp.setOnStopped(() -> {
			temp.dispose();
		});
	}

	public void close() {
		mediaPlayer.dispose();
	}
}
