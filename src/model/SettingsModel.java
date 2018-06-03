package model;

public class SettingsModel {
	private SettingsModel() {
		
	}
	public static SoundBoard getSoundboard() {
		return soundboard;
	}
	public static SoundBoard setSoundboard(SoundBoard soundboard) {
		SettingsModel.soundboard = soundboard;
		return soundboard;
	}
	private static SoundBoard soundboard = null;
}
