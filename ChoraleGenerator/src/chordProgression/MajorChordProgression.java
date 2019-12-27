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
		progression = new int[length][4];
		// Generate progression backwards to ensure ending on I
		// Bass note = key + 4 octaves to be around middle C
		progression[length - 1][0] = key + octave * 4;
		for (int i = 0; i < MAJOR.length; i++)
			progression[length - 1][1 + i] = progression[length - 1][0] + MAJOR[i];
		progression[length - 1][3] = progression[length - 1][0] + octave;
		// Fill in rest of progression
		for (int i = length - 2; i >= 0; i--) {
			// Get next chord and corresponding intervals
			progression[i][0] = getPrevious(progression[i + 1][0]) + octave * 4;
			int[] intervals = getIntervals(progression[i][0]);
			for (int j = 0; j < intervals.length; j++)
				progression[i][1 + j] = progression[i][0] + intervals[j];
			if (intervals.length < 4) 
				progression[i][3] = progression[i][0] + octave;
		}
	}
	
	// Gets inner intervals of cur
	private int[] getIntervals(int cur) {
		switch (cur % 12) {
			case (7):
				return SEVENTH;
			case (11):
				return DIMINISHED;
			case (0):
			case (5):
				return MAJOR;
			case (2):
			case (4):
			case (9):
				return MINOR;
			default:
				return MAJOR;
		}
	}
	
	// Gets a random chord that could lead into cur
	private int getPrevious(int cur) {
		// TODO Check this / convert to Roman numerals
		switch (cur % octave) {
			case (0): {
				int[] opt = {5, 7, 11};
				return opt[(int) (Math.random() * opt.length)];
			}
			case (2): {
				int[] opt = {0, 5, 9};
				return opt[(int) (Math.random() * opt.length)];
			}
			case (4): {
				return 0;
			}
			case (5): {
				int[] opt = {0, 4, 9};
				return opt[(int) (Math.random() * opt.length)];
			}
			case (7): {
				int[] opt = {0, 2, 5};
				return opt[(int) (Math.random() * opt.length)];
			}
			case (9): {
				int[] opt = {0, 4, 7};
				return opt[(int) (Math.random() * opt.length)];
			}
			case (11): {
				int[] opt = {0, 2, 5};
				return opt[(int) (Math.random() * opt.length)];
			}
			default: {
				return 0;
			}
		}
	}
	
	// For testing purposes
	public static void main(String[] args) {
//		int[][] prog = new int[5][4];
//		
//		// I = C
//		prog[0][0] = 60;
//		for (int i = 0; i < MAJOR.length; i++)
//			prog[0][1 + i] = prog[0][0] + MAJOR[i];
//		prog[0][3] = prog[0][0] + 12;
//		
//		// IV = F
//		prog[1][0] = 65;
//		for (int i = 0; i < MAJOR.length; i++)
//			prog[1][1 + i] = prog[1][0] + MAJOR[i];
//		prog[1][3] = prog[1][0] + 12;
//		
//		// ii = Dm
//		prog[2][0] = 62;
//		for (int i = 0; i < MINOR.length; i++)
//			prog[2][1 + i] = prog[2][0] + MINOR[i];
//		prog[2][3] = prog[2][0] + 12;
//		
//		// V7 = G7
//		prog[3][0] = 67;
//		for (int i = 0; i < SEVENTH.length; i++)
//			prog[3][1 + i] = prog[3][0] + SEVENTH[i];
//		
//		// I = C
//		prog[4][0] = 60;
//		for (int i = 0; i < MAJOR.length; i++)
//			prog[4][1 + i] = prog[4][0] + MAJOR[i];
//		prog[4][3] = prog[4][0] + 12;
//		
//		ChordProgression chords = new MajorChordProgression(60, prog);
//		chords.play();
		ChordProgression chords = new MajorChordProgression(60, 16);
		chords.play();
	}
}
