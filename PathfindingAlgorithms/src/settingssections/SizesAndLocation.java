package settingssections;

import javax.swing.JPanel;

import customswing.CustomLabel;
import customswing.CustomLabelAndTextField;

public class SizesAndLocation extends JPanel {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Fields
    private CustomLabelAndTextField widthEntry = new CustomLabelAndTextField("Width");
    private CustomLabelAndTextField heightEntry = new CustomLabelAndTextField("Height");
    private CustomLabelAndTextField startPosEntry = new CustomLabelAndTextField("Start Point");
    private CustomLabelAndTextField endPosEntry = new CustomLabelAndTextField("End Point");

    // Panel with a section for all info regarding sizes/location of obstacles
    public SizesAndLocation() {
	this.setBackground(null);

	// Label for this frame section
	this.add(new CustomLabel("Sizes And Location", CustomLabel.HEADER_FONT_SIZE));

	this.add(widthEntry);
	this.add(heightEntry);
	this.add(startPosEntry);
	this.add(endPosEntry);
    }

    // Instance method that returns the value the user set for the WIDTH field
    public String getWidthField() {
	return widthEntry.getEntryTextField();
    }

    // Instance method that returns the value the user set for the HEIGHT field
    public String getHeightField() {
	return heightEntry.getEntryTextField();
    }

    // Instance method that returns the value the user set for the START POINT field
    public String getStartPosField() {
	return startPosEntry.getEntryTextField();
    }

    // Instance method that returns the value the user set for the END POINT field
    public String getEndPosField() {
	return endPosEntry.getEntryTextField();
    }

}
