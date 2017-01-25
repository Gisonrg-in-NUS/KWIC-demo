package sg.edu.nus.comp.cs3219.control;

import java.util.List;
import java.util.Set;

import sg.edu.nus.comp.cs3219.model.LineStorage;
import sg.edu.nus.comp.cs3219.module.Alphabetizer;
import sg.edu.nus.comp.cs3219.module.CircularShifter;
import sg.edu.nus.comp.cs3219.module.RequiredWordsFilter;

public class MasterControl {
	final private Alphabetizer alphabetizer;
	final private CircularShifter shifter;
	final private RequiredWordsFilter filter;

	private LineStorage rawInputLines;
	private LineStorage intermediateLines;
	private LineStorage resultLines;

	public MasterControl() {
		// Storage
		rawInputLines = new LineStorage();
		intermediateLines = new LineStorage();
		resultLines = new LineStorage();

		// Sub-modules
		shifter = new CircularShifter(intermediateLines);
		filter = new RequiredWordsFilter(resultLines);
		alphabetizer = new Alphabetizer();

		// Set up observation
		rawInputLines.addObserver(shifter);
		intermediateLines.addObserver(filter);
		resultLines.addObserver(alphabetizer);
	}

	public List<String> run(List<String> input, Set<String> ignoredWords, Set<String> requiredWords) {
		rawInputLines.clearLines();
		intermediateLines.clearLines();
		resultLines.clearLines();

		// Set ignore words
		shifter.setIgnoreWords(ignoredWords);
		
		// Set required words
		filter.setRequiredWords(requiredWords);
		
		// Add data line by line
		for (String line : input) {
			rawInputLines.addLine(line);
		}
		
		return resultLines.getAll();
	}
}
