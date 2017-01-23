package sg.edu.nus.comp.cs3219.control;

import java.util.List;
import java.util.Set;

import sg.edu.nus.comp.cs3219.model.LineStorage;
import sg.edu.nus.comp.cs3219.module.Alphabetizer;
import sg.edu.nus.comp.cs3219.module.CircularShifter;

public class MasterControl {
	final private Alphabetizer alphabetizer;
	final private CircularShifter shifter;

	private LineStorage rawInputLines;
	private LineStorage resultLines;

	public MasterControl() {
		// Storage
		rawInputLines = new LineStorage();
		resultLines = new LineStorage();
		
		// Sub-modules
		shifter = new CircularShifter(resultLines);
		alphabetizer = new Alphabetizer();

		// Set up observation
		rawInputLines.addObserver(shifter);
		resultLines.addObserver(alphabetizer);
	}

	public List<String> run(List<String> input, Set<String> ignoreWords) {
		rawInputLines.clearLines();
		resultLines.clearLines();

		// Set ignore words
		shifter.setIgnoreWords(ignoreWords);
		
		// Add data line by line
		for (String line : input) {
			rawInputLines.addLine(line);
		}
		
		return resultLines.getAll();
	}
}
