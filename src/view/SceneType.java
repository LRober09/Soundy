package view;

public enum SceneType {
	LOADING (0),
	MAIN_MENU (1),
	CASUAL (2),
	GUESSING (3),
	SETTINGS (4),
	MEMORY (5),
	SANDBOX (6);
	
	private final int type;
	
	private SceneType(int type) {
		this.type = type;
	}
	
	public int getValue() {
		return this.type;
	}
}
