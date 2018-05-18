package model;

import java.util.ArrayList;

import view.SoundBoardView;

public class SoundBoard {
	private SoundBoardView view;
	private ArrayList<SoundInformation> data;
	public SoundBoard(ArrayList<SoundInformation> data) {
		this.data = data;
		this.view = new SoundBoardView(data);
	}
	public SoundBoardView getView() {
		return view;
	}
}
