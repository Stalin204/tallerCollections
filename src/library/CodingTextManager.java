package library;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import exception.FileManagerException;

/**
 *
 * @author Juan David Guzman
 * 		   Code: 1.092.850.749
 * 		   Programming 3
 * 		   Period: 2023-2
 *
 * This class is responsible for performing conversions of objects to text strings and vice versa according to the received parameters.
 * Note: an instance of the class must be created for each file to be handled.
 */
public class CodingTextManager {
	// This list stores the attributes of each object in an ArrayList of strings and then stores it here to export it later
	private ArrayList<ArrayList<String>> objectList = new ArrayList<>();

	// It is the separator of each data, by default a comma (,) is declared but it can be changed as necessary
	private String separator = ",";

	// It is responsible for handling file information
	private FileManager fileManager = new FileManager();

	public CodingTextManager() {
		super();
	}

	public ArrayList<ArrayList<String>> getObjectList() {
		return objectList;
	}

	public void setObjectList(ArrayList<ArrayList<String>> objectList) throws IOException {
		updateObjectListFile(objectList);
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public FileManager getFileManager() {
		return fileManager;
	}

	public void setFileManager(FileManager fileManager) throws IOException {
		this.fileManager = fileManager;
		this.objectList = getObjectListFromFile();
	}

	public CodingTextManager(String path) throws FileManagerException, IOException {
		super();
		this.fileManager.setFILE_PATH(path);
		this.objectList = getObjectListFromFile();
	}

	public CodingTextManager(String path, String separator) throws FileManagerException, IOException {
		super();
		this.fileManager.setFILE_PATH(path);
		this.objectList = getObjectListFromFile();
		this.separator = separator;
	}

	private ArrayList<ArrayList<String>> getObjectListFromFile() throws IOException {
		// here I get the text from the file
		String text = fileManager.getText();

		// then I create an auxiliary list with each line of text thus separating the objects
		ArrayList<String> stringList = new ArrayList<>(Arrays.asList(text.split("\n")));

		// I get and return the list of objects
		return getObjectListFromFile(stringList, stringList.size(), new ArrayList<ArrayList<String>>(), 0);
	}

	private ArrayList<ArrayList<String>> getObjectListFromFile(ArrayList<String> stringList, int objectCount, ArrayList<ArrayList<String>> objectList, int index) {
		// stop condition
		if (index == objectCount - 1) {
			objectList.add(getObject(stringList, index));
			return objectList;
		} else {
			objectList.add(getObject(stringList, index));
			return getObjectListFromFile(stringList, objectCount, objectList, index + 1);
		}
	}

	private ArrayList<String> getObject(ArrayList<String> stringList, int index) {
		return new ArrayList<>(Arrays.asList(stringList.get(index).split(separator)));
	}

	private boolean updateObjectListFile(ArrayList<ArrayList<String>> list) throws IOException {
		this.objectList = list;
		fileManager.setText("");
		return updateObjectListFile(objectList.size(), 0);
	}

	private boolean updateObjectListFile(int size, int index) throws IOException {
		// stop condition
		if (index == size - 1) {
			addObject("", objectList.get(index), 0);
			return true;
		} else {
			addObject("", objectList.get(index), 0);
			return updateObjectListFile(size, index + 1);
		}
	}

	public boolean addObject(ArrayList<String> object) throws IOException {
		return addObject("", object, 0);
	}

	private boolean addObject(String line, ArrayList<String> object, int index) throws IOException {
		// stop condition
		if (index == object.size() - 1) {
			line += object.get(index);
			fileManager.addTextln(line);
			objectList.add(object);
			return true;
		} else {
			line += object.get(index) + separator;
			return addObject(line, object, index + 1);
		}
	}
}
