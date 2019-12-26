package chordProgression;

import jm.gui.show.ShowScore;
import jm.music.data.CPhrase;
import jm.music.data.Part;
import jm.music.data.Score;
import jm.util.Write;

public abstract class ChordProgression {
	// Single note key, since implementing classes will designate major/minor
	private int key;
	private int[][] progression;
	private int length;
	
	// Should generate progression based on key and length
	public ChordProgression(int key, int length) {
		// Reduce key to min value
		this.key = key % 12;
		this.length = length;
		this.generate();
	}
	
	// Initialize progression from existing chords
	public ChordProgression(int key, int[][] progression) {
		// Reduce key to min value
		this.key = key % 12;
		this.progression = progression;
		this.length = progression.length;
	}
	
	// Should generate a progression based on the key and length
	protected abstract void generate();
	
	// Generates basic midi file based on progression
	public void play() {
		// Creates a CPhrase based off the given pitches
		CPhrase chords = new CPhrase();
		for (int[] pitches : progression)
			chords.addChord(pitches, 1);
		
		// Create score and add chords
		Score s = new Score();
		Part p = new Part();
		p.addCPhrase(chords);
		s.addPart(p);
		
		// Show score and produce midi track
		new ShowScore(s);
		Write.midi(s, "ChordProgression.midi");
	}
}
