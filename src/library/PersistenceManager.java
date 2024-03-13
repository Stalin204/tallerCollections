package library;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import exception.FileManagerException;
import exception.FileNotFoundException;
import exception.InvalidOperationException;


public class PersistenceManager {

    // Manages the file information
    private FileManager file = new FileManager();

    static String systemDate = "";

    public PersistenceManager() {
        super();
    }

    public PersistenceManager(FileManager file) {
        super();
        this.file = file;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((file == null) ? 0 : file.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PersistenceManager other = (PersistenceManager) obj;
        if (file == null) {
            if (other.file != null)
                return false;
        } else if (!file.equals(other.file))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PersistenceManager [file=" + file + "]";
    }

    public static String getSystemDate() {
        return systemDate;
    }

    public static void setSystemDate(String systemDate) {
        PersistenceManager.systemDate = systemDate;
    }

    public FileManager getFile() {
        return file;
    }

    public void setFile(FileManager file) {
        this.file = file;
    }

    public void loadFromXMLFile() throws IOException, FileManagerException {
        if(file.getExtension().equals("xml")) {
            loadSerializedResourceXML(file.getFILE_PATH());
        } else {
            throw new FileManagerException("the file is not an XML");
        }
    }

    public void logSystemActions(String logMessage, EnumLevel level, String action) throws FileManagerException {
        System.out.println(file.getExtension());
        if(file.getExtension().equals("log")) {
            saveLogRecord(logMessage, level, action);
        } else {
            throw new FileManagerException("the file is not a log");
        }
    }

    public void saveToXMLFile(Object object) throws IOException {
        saveSerializedResourceXML(file.getFILE_PATH(), object);
    }

    public void loadFromBinaryFile() throws Exception {
        if(file.getExtension().equals("bin")) {
            loadSerializedResource(file.getFILE_PATH());
        } else {
            throw new FileManagerException("the file is not a binary file");
        }
    }

    public void saveToBinaryFile(Object object) throws Exception {
        if(file.getExtension().equals("bin")) {
            saveSerializedResource(file.getFILE_PATH(), object);
        } else {
            throw new FileManagerException("the file is not a binary file");
        }
    }

    public ArrayList<ArrayList<String>> loadFromTextFile(String separator) throws FileManagerException {
        try {
            return new CodingTextManager(file.getFILE_PATH(), separator).getObjectList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean saveToTextFile(String separator, ArrayList<ArrayList<String>> objectList) {
        try {
            new CodingTextManager(file.getFILE_PATH(), separator).setObjectList(objectList);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void saveLogRecord(String logMessage, EnumLevel level, String action) {
        String log = "";
        Logger LOGGER = Logger.getLogger(action);
        FileHandler fileHandler =  null;
        loadSystemDate();
        try {
            fileHandler = new FileHandler(file.getFILE_PATH(), true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            switch (level) {
                case INFO:
                    LOGGER.log(Level.INFO, action + "," + logMessage + "," + systemDate);
                    break;

                case WARNING:
                    LOGGER.log(Level.WARNING, action + "," + logMessage + "," + systemDate);
                    break;

                case SEVERE:
                    LOGGER.log(Level.SEVERE, action + "," + logMessage + "," + systemDate);
                    break;

                default:
                    break;
            }

        } catch (SecurityException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            e.printStackTrace();
        } finally {
            fileHandler.close();
        }
    }

    private void loadSystemDate() {
        String dayStr = "";
        String monthStr = "";
        String yearStr = "";

        Calendar cal1 = Calendar.getInstance();

        int day = cal1.get(Calendar.DATE);
        int month = cal1.get(Calendar.MONTH) + 1;
        int year = cal1.get(Calendar.YEAR);

        if(day < 10) {
            dayStr += "0" + day;
        } else {
            dayStr += "" + day;
        }
        if(month < 10) {
            monthStr += "0" + month;
        } else {
            monthStr += "" + month;
        }

        systemDate = year + "-" + monthStr + "-" + dayStr;
    }

    //------------------------------------Serialization and XML-------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    private Object loadSerializedResource(String filePath) throws Exception {
        Object aux = null;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(filePath));
            aux = ois.readObject();
        } catch (Exception e2) {
            throw e2;
        } finally {
            if (ois != null)
                ois.close();
        }
        return aux;
    }

    private void saveSerializedResource(String filePath, Object object) throws Exception {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(object);
        } catch (Exception e) {
            throw e;
        } finally {
            if (oos != null)
                oos.close();
        }
    }

    private Object loadSerializedResourceXML(String filePath) throws IOException {
        XMLDecoder xmlDecoder;
        Object xmlObject;

        xmlDecoder = new XMLDecoder(new FileInputStream(filePath));
        xmlObject = xmlDecoder.readObject();
        xmlDecoder.close();
        System.out.println(xmlObject.toString());
        return xmlObject;
    }

    private void saveSerializedResourceXML(String filePath, Object object) throws IOException {
        XMLEncoder xmlEncoder;

        xmlEncoder = new XMLEncoder(new FileOutputStream(filePath));
        xmlEncoder.writeObject(object);
        xmlEncoder.close();
    }

}
