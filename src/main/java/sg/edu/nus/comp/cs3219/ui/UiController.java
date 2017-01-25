package sg.edu.nus.comp.cs3219.ui;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.swing.JTextArea;

import sg.edu.nus.comp.cs3219.control.MasterControl;

public class UiController {
	public interface KwicUi {
		List<String> getInput();
		String[] getInputArray();
		JTextArea getOutputTextArea();
		Set<String> getIgnoredWords();
		Set<String> getRequiredWords();
		void setResutls(List<String> results);
		void setController(UiController controller);
	}
	
	final private KwicUi view;
	
	private MasterControl controller;

	public UiController(KwicUi view) {
		this.view = view;
		controller = new MasterControl();
	}
	
	public void generateResult() {
		Set<String> ignoredWordsSet = view.getIgnoredWords();
		Set<String> requiredWordsSet = view.getRequiredWords();
		List<String> result = controller.run(view.getInput(), ignoredWordsSet, requiredWordsSet);
		view.setResutls(result);
	}

	public void exportResultToFile(String data) {
		BufferedWriter writer = null;
	    try {
	        writer = new BufferedWriter(new FileWriter("./output.txt"));
	        view.getOutputTextArea().write(writer);
	    } catch (IOException e) {
	        System.err.println(e);
	    } finally {
	        if (writer != null) {
	            try {
	                writer.close();
	            } catch (IOException e) {
	                System.err.println(e);
	            }
	        }
	    }
	}
}

