package model;

public class SoundInformation {

	private String image;
	private String sound;

	public SoundInformation(String image, String sound) {
		this.setImage(image);
		this.setSound(sound);
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}
}
