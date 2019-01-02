import java.util.Arrays;
import java.util.List;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

import processing.core.PApplet;

import javax.sound.midi.MidiChannel;

/**
 * A little example showing how to play a tune in Java.
 * 
 * Inputs are not sanitized or checked, this is just to show how simple it is.
 * 
 * @author Peter
 */
public class Sounds {
	private static MidiChannel[] channels;
	private String[] notes = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
	private static int INSTRUMENT = 7; // 0 is a piano, 9 is percussion, other channels are for other instruments
	private static int VOLUME = 80; // between 0 et 127
	private PApplet p;
	private String[] list;
	private int[] seq;
	
	public Sounds(PApplet p, String[] list) {
		this.p = p;
		this.list = list;
		seq = new int[list.length];
		play();
	}
	
	public void play() {
		for (int i = 0; i < list.length; i++) {
			seq[i] = Integer.parseInt(list[i]);
		}
		
		try {
			// * Open a synthesizer
			Synthesizer synth = MidiSystem.getSynthesizer();
			synth.open();
			channels = synth.getChannels();

			for (int i = 0; i < seq.length; i++) {
				play((seq[i] % 36) + 36,(int)(Math.random()*750+250));
				rest((int)(Math.random()*100));
				p.fill(0);
				p.text(notes[i % 12], p.width/2, p.height/2);
			}
			
			// * finish up
			synth.close();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Plays the given note for the given duration
	 */
	private static void play(int note, int duration) throws InterruptedException {
			// * start playing a note
			channels[INSTRUMENT].noteOn(note, VOLUME );
			// * wait
			Thread.sleep( duration );
			// * stop playing a note
			channels[INSTRUMENT].noteOff(note);
	}
	
	/**
	 * Plays nothing for the given duration
	 */
	private static void rest(int duration) throws InterruptedException {
		Thread.sleep(duration);
	}
	
}