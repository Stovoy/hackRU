package Audio;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio 
{
	private static Clip wakawaka = null;
	public static void playBeginning()
	{
		play("pacman_beginning.wav");
	}

	public static void playDeath()
	{
		play("pacman_death.wav");
	}
	
	public static void playWakaWaka()
	{
		if (wakawaka == null || !wakawaka.isActive())
			wakawaka = play("waka_waka.wav");
	}
	
	private static Clip play(String audioClip)
	{
        Clip clip = null;
		try
		{
			clip = AudioSystem.getClip();
		}
		catch (LineUnavailableException e)
		{
		}
		if (clip == null) return null;
		try
		{
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(Thread.currentThread().getContextClassLoader().getResourceAsStream(audioClip));
	        clip.open(inputStream);
		}
		catch (LineUnavailableException e)
		{
		}
		catch (IOException e)
		{
		}
		catch (UnsupportedAudioFileException e)
		{
		}
        clip.start();
        return clip;
	}
}
