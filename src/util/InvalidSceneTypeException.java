/**
 * 
 */
package util;

import view.SceneType;

/**
 * @author Lucas Robertson
 *
 */
public class InvalidSceneTypeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public InvalidSceneTypeException() {
	}

	/**
	 * @param type
	 *            The invalid SceneType provided
	 */
	public InvalidSceneTypeException(SceneType type) {
		super("Invalid scene type provided: " + type);
	}

}
