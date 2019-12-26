package chordProgression;

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
	abstract void generate();
	
	public void play() {
		// TODO generate chord progression midi
	}
}
