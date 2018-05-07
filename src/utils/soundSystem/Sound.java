package utils.soundSystem;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	Clip clip;
boolean alive=true;
	public Sound(String path) {
	
		try {
			// Open an audio input stream.
			File f = new File(path);
			URL url = new URL(f.toURI().toURL().toString());
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			// Get a sound clip resource.
			clip = AudioSystem.getClip();
			
			// Open audio clip and load samples from the audio input stream.
			
			clip.open(audioIn);
			

		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	
			
		
		
	}

	public void start() {
		clip.start();
		
		alive=true;
	}
	public void start(int frame) {
		clip.setFramePosition(frame);
		clip.start();
		alive=true;
	}
	public void stop() {
		clip.stop();
		clip.close();
		clip=null;
		alive=false;
	}
	
	
	

	
}