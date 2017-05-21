package com.aps.main;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import com.aps.elementos_jogo.*;

public class Audio {
	
	private Clip clip;
	private AudioInputStream inputStream;
	
	public Audio(String path) {
		try {
			inputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(path));
			
			AudioFormat baseFormat = inputStream.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,baseFormat.getSampleRate(),16,baseFormat.getChannels(),
					baseFormat.getChannels()*2,baseFormat.getSampleRate(),false);
			
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, inputStream);
			
			clip = AudioSystem.getClip();
			clip.open(dais);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		if(clip==null) return;
		clip.stop();
		
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void play_more() {
		if(clip==null) return;
		clip.stop();
		
		clip.setFramePosition(0);
		clip.start();
		clip.loop(10);
	}
	
	public void close() {
		stop();
		clip.close();
	}
	
	public boolean stop() {
		if(clip.isRunning()) clip.stop();
		return true;
	}
	
	

}