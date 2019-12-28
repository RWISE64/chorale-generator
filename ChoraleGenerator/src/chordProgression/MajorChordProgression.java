package chordProgression;

import static jm.constants.Chords.DIMINISHED;
import static jm.constants.Chords.MAJOR;
import static jm.constants.Chords.MINOR;
import static jm.constants.Chords.SEVENTH;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MajorChordProgression extends ChordProgression {
	// Maps each Roman numeral to number of half steps from root of key
	private static final Map<String, Integer> numeralRoots = initMap();
	
	/**
	 * Initializes a constant mapping of Roman numerals to steps from root of key.
	 * Currently manually define every case to avoid exceptions
	 * @return Unmodifiable map mapping Roman numerals to number of half steps
	 */
	private static Map<String, Integer> initMap() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		// TODO Add more secondary dominants
		map.put("I", 0);
		map.put("I_m7", 0);
		map.put("ii", 2);
		map.put("iii", 4);
		map.put("IV", 5);
		map.put("V", 7);
		map.put("V_m7", 7);
		map.put("vi", 9);
		map.put("vii_d", 11);
		map.put("vii_d_m7", 11);
		return Collections.unmodifiableMap(map);
	}
	
	MajorChordProgression(int key, int length) {
		super(key, length);
	}

	MajorChordProgression(int key, int[][] progression) {
		super(key, progression);
	}
	
	protected void generate() {
		progression = new int[length][4];
		// Generate progression backwards to ensure ending on I
		String cur = "I";
		String res = "I";
		// Bass note = key + 4 octaves to be around middle C
		progression[length - 1][0] = key + OCTAVE * 4;
		for (int i = 0; i < MAJOR.length; i++)
			progression[length - 1][1 + i] = progression[length - 1][0] + MAJOR[i];
		// Fill in rest of progression
		for (int i = length - 2; i >= 0; i--) {
			// Get next chord and corresponding intervals
			cur = getPrevious(cur);
			res = cur + "-" + res;
			// Bass note = key + half steps up + 4 octaves
			progression[i][0] = key + numeralRoots.get(cur) + OCTAVE * 4;
			int[] intervals = getIntervals(cur);
			for (int j = 0; j < intervals.length; j++)
				progression[i][1 + j] = progression[i][0] + intervals[j];
		}
		System.out.println(res);
	}
	
	// Gets a random chord that could lead into Roman numeral cur
	private String getPrevious(String cur) {
		// TODO Check this
		switch (cur) {
			case ("I"): {
				String[] opt = {"IV", "V", "V_m7", "vii_d", "vii_d_m7"};
				return opt[(int) (Math.random() * opt.length)];
			}
			case ("I_m7"): {
				String[] opt = {"I", "IV", "V", "V_m7", "vii_d", "vii_d_m7"};
				return opt[(int) (Math.random() * opt.length)];
			}
			case ("ii"): {
				String[] opt = {"IV", "vi", "I_m7"};
				return opt[(int) (Math.random() * opt.length)];
			}
			case ("iii"): {
				return "I";
			}
			case ("IV"): {
				String[] opt = {"I", "iii", "vi", "I_m7"};
				return opt[(int) (Math.random() * opt.length)];
			}
			case ("V"): {
				String[] opt = {"I", "ii", "IV"};
				return opt[(int) (Math.random() * opt.length)];
			}
			case ("V_m7"): {
				String[] opt = {"I", "ii", "IV", "V"};
				return opt[(int) (Math.random() * opt.length)];
			}
			case ("vi"): {
				String[] opt = {"I", "iii", "V", "V_m7"};
				return opt[(int) (Math.random() * opt.length)];
			}
			case ("vii_d"): {
				String[] opt = {"I", "ii", "IV"};
				return opt[(int) (Math.random() * opt.length)];
			}
			case ("vii_d_m7"): {
				String[] opt = {"I", "ii", "IV", "vii_d"};
				return opt[(int) (Math.random() * opt.length)];
			}
			default: {
				return "I";
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
