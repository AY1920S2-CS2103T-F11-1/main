package life.calgo.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import life.calgo.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path foodRecordFilePath = Paths.get("data" , "foodrecord.json");
    private Path consumptionRecordFilePath = Paths.get("data", "consumptionrecord.json");
    private Path goalFilePath = Paths.get("data", "goal.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setFoodRecordFilePath(newUserPrefs.getFoodRecordFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getFoodRecordFilePath() {
        return foodRecordFilePath;
    }

    public Path getConsumptionRecordFilePath() {
        return consumptionRecordFilePath;
    }

    public Path getGoalFilePath() {
        return goalFilePath;
    }

    public void setFoodRecordFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.foodRecordFilePath = addressBookFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && foodRecordFilePath.equals(o.foodRecordFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, foodRecordFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + foodRecordFilePath);
        return sb.toString();
    }

}