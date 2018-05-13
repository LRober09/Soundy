package view;

public enum SceneType {
	LOGIN (-1),
	LOADING (0),
	MAIN_MENU (1),
	CASUAL (2),
	GUESSING (3),
	MEMORY (4),
	SETTINGS (5),
	SANDBOX (6),
	STORE (7);
	
	private final int type;
	
	private SceneType(int type) {
		this.type = type;
	}
	
	public int getValue() {
		return this.type;
	}
}
