package library;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
    private Properties properties;
    private String fileName;

    public PropertiesManager() {
		super();
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public PropertiesManager(FileManager fileManager) {
        this.fileName = fileManager.getFILE_PATH();
        this.properties = new Properties();
        loadProperties();
    }

    private void loadProperties() {
        try (FileInputStream fileInput = new FileInputStream(fileName)) {
            properties.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
        saveProperties();
    }

    public void saveProperties() {
        try (FileOutputStream fileOutput = new FileOutputStream(fileName)) {
            properties.store(fileOutput, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listProperties() {
        properties.list(System.out);
    }
}
