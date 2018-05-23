package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class BoardCreator {
	public static SoundBoard soundBoard;
	public static SoundBoard newBoard;
	public static ArrayList<String[]> pathlist;

	public static void saveBoard(String boardname) throws IOException {
		String boardPath = "res/soundboards/"+boardname;
		String imagepath = "res/soundboards/"+boardname+"/images";
		String soundpath = "res/soundboards/"+boardname+"/sounds";

		File board = new File(boardPath);
		if (board.exists()) {
			// remove all children
			for (File f : board.listFiles()) {
				for(File data : f.listFiles()) {
					data.delete();
				}
			}
		} else {
			// create it
			//board.mkdirs();
			Files.createDirectories(board.toPath());
		}
		File images = new File(imagepath);
		File sounds = new File(soundpath);
		Files.createDirectories(images.toPath());
		Files.createDirectories(sounds.toPath());

		for (String[] paths : pathlist) {

			String name = getName(paths[0]);
			System.out.println(name);
			File originalImage = new File(paths[0]);
			File originalSound = new File(paths[1]);

			File newImage = new File(imagepath + "/" + name + ".jpg");
			File newSound = new File(soundpath + "/" + name + ".wav");
			//newImage.createNewFile();
			//newSound.createNewFile();
			Files.copy(originalImage.toPath(), newImage.toPath());
			Files.copy(originalSound.toPath(), newSound.toPath());

		}
	}

	private static String getName(String loc) {
		return loc.split("/")[4].split("\\.")[0];
	}
}
