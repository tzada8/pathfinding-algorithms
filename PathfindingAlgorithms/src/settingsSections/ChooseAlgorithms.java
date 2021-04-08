package settingsSections;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JPanel;

public class ChooseAlgorithms extends JPanel implements ActionListener {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    private static final int NUMBER_OF_ALGORITHMS = 4;

    private CustomCheckBox breathFirstSearchCheckBox = new CustomCheckBox("Breath First Search");
    private CustomCheckBox depthFirstSearchCheckBox = new CustomCheckBox("Depth First Search");
    private CustomCheckBox aStarCheckBox = new CustomCheckBox("A*");
    private CustomCheckBox dijkstraCheckBox = new CustomCheckBox("Dijkstra");
    private boolean[] selectedAlgorithms = new boolean[NUMBER_OF_ALGORITHMS];

    // Panel with a section to choose which type of algorithms will be used
    public ChooseAlgorithms() {
	this.setBackground(null);

	// Label for this frame section
	this.add(new SectionHeader("Choose Algorithms:").getHeader());

	breathFirstSearchCheckBox.addActionListener(this);
	depthFirstSearchCheckBox.addActionListener(this);
	aStarCheckBox.addActionListener(this);
	dijkstraCheckBox.addActionListener(this);

	this.add(breathFirstSearchCheckBox);
	this.add(depthFirstSearchCheckBox);
	this.add(aStarCheckBox);
	this.add(dijkstraCheckBox);
    }

    // Instance method that returns which of the radio buttons was selected
    public boolean[] getSelectedAlgorithms() {
	return selectedAlgorithms;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == breathFirstSearchCheckBox) {
	    selectedAlgorithms[0] = breathFirstSearchCheckBox.isSelected();
	} else if (e.getSource() == depthFirstSearchCheckBox) {
	    selectedAlgorithms[1] = depthFirstSearchCheckBox.isSelected();
	} else if (e.getSource() == aStarCheckBox) {
	    selectedAlgorithms[2] = aStarCheckBox.isSelected();
	} else if (e.getSource() == dijkstraCheckBox) {
	    selectedAlgorithms[3] = dijkstraCheckBox.isSelected();
	}
    }

}
