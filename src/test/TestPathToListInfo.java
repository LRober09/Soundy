package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import model.SoundBoard;
import model.SoundInformation;

public class TestPathToListInfo {

	@Test
	public void testSize1() {
		String test1;
		String[] a1 = {"man", "bear"};
		ArrayList<String[]> list1 = new ArrayList<>();
		list1.add(a1);
		List<SoundInformation> soundInfo1 = SoundBoard.pathListToInfo(list1);
		test1 = soundInfo1.get(0).getImage() + soundInfo1.get(0).getSound();
		assertEquals("manbear", test1);
	}
	
	@Test
	public void testSize2() {
		StringBuilder sb = new StringBuilder();
		String[] a1 = {"man", "bear"};
		String[] a2 = {"pig", "man"};
		List<String[]> list2 = Arrays.asList(a1, a2);
		List<SoundInformation> soundInfo2 = SoundBoard.pathListToInfo(list2);
		for (int i = 0; i < soundInfo2.size(); i++) {
			sb.append(soundInfo2.get(i).getImage() + soundInfo2.get(i).getSound());
		}
		assertEquals("manbearpigman", sb.toString());
	}
	
	@Test
	public void testSize15() {
		StringBuilder sb = new StringBuilder();
		String[] a1 = {"man", "bear"};
		String[] a2 = {"pig", "man"};
		String[] a3 = {"mag", "ban"};
		String[] a4 = {"mag", "ban"};
		String[] a5 = {"mag", "ban"};
		String[] a6 = {"mag", "ban"};
		String[] a7 = {"mag", "ban"};
		String[] a8 = {"mag", "ban"};
		String[] a9 = {"mag", "ban"};
		String[] a10 = {"mag", "ban"};
		String[] a11 = {"mag", "ban"};
		String[] a12 = {"mag", "ban"};
		String[] a13 = {"mag", "ban"};
		String[] a14 = {"mag", "ban"};
		String[] a15 = {"mag", "ban"};
		List<String[]> list15 = Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15);
		List<SoundInformation> soundInfo15 = SoundBoard.pathListToInfo(list15);
		for (int i = 0; i < soundInfo15.size(); i++) {
			sb.append(soundInfo15.get(i).getImage() + soundInfo15.get(i).getSound());
		}
		assertEquals("manbearpigmanmagbanmagbanmagbanmagbanmagbanmagbanmagbanmagbanmagbanmagbanmagbanmagbanmagban", sb.toString());
	}

}
