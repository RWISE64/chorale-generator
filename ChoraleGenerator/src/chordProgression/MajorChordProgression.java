package chordProgression;

import static jm.constants.Chords.*;

public class MajorChordProgression extends ChordProgression {
	MajorChordProgression(int key, int length) {
		super(key, length);
	}

	MajorChordProgression(int key, int[][] progression) {
		super(key, progression);
	}
	
	protected void generate() {
		// TODO generate progression automagically
	}
	
	// For testing purposes
	public static void main(String[] args) {
		int[][] prog = new int[5][4];
		
		// I = C
		prog[0][0] = 60;
		for (int i = 0; i < MAJOR.length; i++)
			prog[0][1 + i] = prog[0][0] + MAJOR[i];
		prog[0][3] = prog[0][0] + 12;
		
		// IV = F
		prog[1][0] = 65;
		for (int i = 0; i < MAJOR.length; i++)
			prog[1][1 + i] = prog[1][0] + MAJOR[i];
		prog[1][3] = prog[1][0] + 12;
		
		// ii = Dm
		prog[2][0] = 62;
		for (int i = 0; i < MINOR.length; i++)
			prog[2][1 + i] = prog[2][0] + MINOR[i];
		prog[2][3] = prog[2][0] + 12;
		
		// V7 = G7
		prog[3][0] = 67;
		for (int i = 0; i < SEVENTH.length; i++)
			prog[3][1 + i] = prog[3][0] + SEVENTH[i];
		
		// I = C
		prog[4][0] = 60;
		for (int i = 0; i < MAJOR.length; i++)
			prog[4][1 + i] = prog[4][0] + MAJOR[i];
		prog[4][3] = prog[4][0] + 12;
		
		ChordProgression chords = new MajorChordProgression(60, prog);
		chords.play();
	}
}
