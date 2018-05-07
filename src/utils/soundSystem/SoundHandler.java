package utils.soundSystem;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundHandler {
	public static ArrayList<Sound >sounds = new ArrayList<>();
	
	public static void createNewSound(String path ) {
		Sound s = new Sound(path);
		sounds.add(s);
		s.start();
		
	}
	
}
