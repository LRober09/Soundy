package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class BoardCreator {
	public static SoundBoard soundBoard;
	public static SoundBoard newBoard;
	public static List<String[]> pathlist;
	private final static String base = "res/soundboards/";
	
	private BoardCreator() {
		
	}

	public static void saveBoard(String boardname) throws IOException {
		
		String boardPath = base + boardname;
		String imagepath = base + boardname + "/images";
		String soundpath = base + boardname + "/sounds";

		File board = new File(boardPath);
		if (board.exists()) {
			throw new IOException("Board Aready Exists");
		} else {
			Files.createDirectories(board.toPath());
		}
		File images = new File(imagepath);
		File sounds = new File(soundpath);
		Files.createDirectories(images.toPath());
		Files.createDirectories(sounds.toPath());

		for (String[] paths : pathlist) {

			String name = getName(paths[0]);
			File originalImage = new File(paths[0]);
			File originalSound = new File(paths[1]);

			File newImage = new File(imagepath, name + ".jpg");
			File newSound = new File(soundpath, name + ".wav");
			Files.copy(originalImage.toPath(), newImage.toPath());
			Files.copy(originalSound.toPath(), newSound.toPath());

		}
	}

	public static String getName(String loc) {
		return loc.split("/")[4].split("\\.")[0];
	}

	public static String[] getPaths(String loc) {
		String boardName = loc.split("/")[2];
		String objectName = loc.split("/")[4].split("\\.")[0];
		String[] paths = new String[2];
		paths[0] = base + boardName + "/images/" + objectName + ".jpg";
		paths[1] = base + boardName + "/sounds/" + objectName + ".wav";
		return paths;
	}

}
