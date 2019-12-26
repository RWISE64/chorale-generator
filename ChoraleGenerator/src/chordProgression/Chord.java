package chordProgression;

/**
 * Used to represent a basic chord from a chord type and root note.
 * 
 * @author RWISE64
 */
public class Chord {
	// Supported types of chords
	public enum ChordType {
		MAJ, MIN, DIM, MAJ_MIN_7
	};
	
	// Type of chord object
	private ChordType type;
	// Integer representation of each chord tone
	private int root, third, fifth, seventh;
	
	// Creates chord based on type and root
	public Chord(ChordType type, int root) {
		this.type = type;
		// Mod by 12 to use lowest possible values
		this.root = root % 12;
		this.initializeNotes();
	}
	
	// Initializes remaining chord tones based on root and chord type
	private void initializeNotes() {
		switch (type) {
			case MAJ: {
				third = root + 4;
				fifth = root + 7;
				seventh = -1;
				break;
			}
			case MIN: {
				third = root + 3;
				fifth = root + 7;
				seventh = -1;
				break;
			}
			case DIM: {
				third = root + 3;
				fifth = root + 6;
				seventh = -1;
				break;
			}
			case MAJ_MIN_7: {
				third = root + 4;
				fifth = root + 7;
				seventh = root + 10;
				break;
			}
		}
	}
	
	// Returns root of chord
	public int getRoot() {
		return this.root;
	}
	
	// Returns third of chord
	public int getThird() {
		return this.third;
	}
	
	// Returns fifth of chord
	public int getFifth() {
		return this.fifth;
	}
	
	// Returns seventh of chord
	public int getSeventh() {
		return this.seventh;
	}
}
