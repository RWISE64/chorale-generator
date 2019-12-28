package chordProgression;

import static jm.constants.Chords.*;
import jm.gui.show.ShowScore;
import jm.music.data.CPhrase;
import jm.music.data.Part;
import jm.music.data.Score;
import jm.util.Write;

public abstract class ChordProgression {
	// Single note key, since implementing classes will designate major/minor
	protected int key;
	protected int[][] progression;
	protected int length;
	protected final int OCTAVE = 12;
	
	// Should generate progression based on key and length
	public ChordProgression(int key, int length) {
		// Reduce key to min value
		this.key = key % OCTAVE;
		this.length = length;
		this.generate();
	}
	
	// Initialize progression from existing chords
	public ChordProgression(int key, int[][] progression) {
		// Reduce key to min value
		this.key = key % OCTAVE;
		this.progression = progression;
		this.length = progression.length;
	}
	
	// Returns int[] of intervals based on Roman numeral cur
	protected int[] getIntervals(String cur) {
		String[] parts = cur.split("_");
		// Major if first char is I or V
		if (cur.charAt(0) == 'I' || cur.charAt(0) == 'V') {
			// Only one section => major triad
			if (parts.length == 1)
				return MAJOR;
			else if (parts[1].equals("m7"))
				return SEVENTH;
			else 
				return MAJOR_SEVENTH;
		}
		// Otherwise, minor/diminished (i or v)
		else {
			// Only one section => minor triad
			if (parts.length == 1)
				return MINOR;
			// Currently ignoring 
			else if (parts.length == 2) {
				if (parts[1].equals("d"))
					return DIMINISHED;
				else
					return MINOR_SEVENTH;
			}
			// Only dim7 chords have 3 parts so far
			else {
				if (parts[2].equals("m7"))
					return MINOR_SEVENTH_FLAT_FIFTH;
				else
					return DIMINISHED_SEVENTH;
			}
		}
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
