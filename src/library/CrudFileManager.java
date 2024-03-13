package library;

import java.io.IOException;
import java.util.ArrayList;

import exception.CrudException;
import exception.DatoFaltanteException;
import exception.FileManagerException;
import exception.NoElementsException;
import exception.OutLimitException;

public class CrudFileManager {

    ArrayList<ArrayList<String>> objectList;
    CodingTextManager fileManager;
    // By convention, the ID is taken at position zero
    Integer idPosition;

    public CrudFileManager(String path) throws FileManagerException, IOException {
        objectList = new ArrayList<>();
        fileManager = new CodingTextManager(path);
        idPosition = 0;
        refreshList();
    }

    // Create
    public boolean createObject(ArrayList<String> object) throws CrudException, IOException {
        refreshList();
        // Check if the object already exists by ID
        if (checkExistingObject("" + object.get(idPosition))) {
            throw new CrudException("The object already exists.");
        }

        // Add the object to the list
        fileManager.addObject(object);
        objectList = fileManager.getObjectList();
        return true;
    }

    public void setSeparator(String separator) {
        fileManager.setSeparator(separator);
    }

    public String getSeparator() {
        return fileManager.getSeparator();
    }

    public ArrayList<ArrayList<String>> getObjectList() {
        return objectList;
    }

    public void setObjectList(ArrayList<ArrayList<String>> objectList) {
        this.objectList = objectList;
    }

    public CodingTextManager getFileManager() {
        return fileManager;
    }

    public void setFileManager(CodingTextManager fileManager) {
        this.fileManager = fileManager;
    }

    public Integer getIdPosition() {
        return idPosition;
    }

    public void setIdPosition(Integer idPosition) {
        this.idPosition = idPosition;
    }

    // Get
    public ArrayList<String> getObject(String id) throws CrudException {
        refreshList();
        // Find the object by ID
        try {
            int i = findObject("" + id, objectList);
            return objectList.get(i);
        } catch (Exception e) {
            throw new CrudException("Object not found.");
        }
    }

    public void refreshList() {
        objectList = fileManager.getObjectList();
    }

    // Update
    public boolean updateObject(ArrayList<String> object) throws CrudException {
        refreshList();
        // Find the object by ID
        try {
            int i = findObject("" + object.get(idPosition), objectList);
            objectList.set(i, object);
            return true;
        } catch (Exception e) {
            throw new CrudException("Object not found for update.");
        }
    }

    // Delete
    public boolean deleteObject(String id) throws CrudException {
        refreshList();
        // Find the object by ID and remove it
        try {
            int i = findObject("" + id, objectList);
            objectList.remove(i);
            fileManager.setObjectList(objectList);
            refreshList();
            return true;
        } catch (Exception e) {
            throw new CrudException("Object not found for deletion.");
        }
    }

    public boolean checkExistingObject(String id) {
        // Check if the object exists by ID
        refreshList();
        try {
            findObject("" + id, objectList);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int findObject(String id, ArrayList<ArrayList<String>> objectList) throws DatoFaltanteException, NoElementsException {
        refreshList();
        return findObject(id, objectList, 0);
    }

    // Search for object recursively
    private int findObject(String id, ArrayList<ArrayList<String>> objectList, int i) throws DatoFaltanteException, NoElementsException {
        try {
            Library.checkLimit(i, objectList.size());
        } catch (OutLimitException e) {
            throw new DatoFaltanteException("\nThe data was not found in the array");
        }
        if (("" + id).equals("" + objectList.get(i).get(idPosition))) { // Base case
            return i;
        } else { // Recursive case
            return findObject(id, objectList, i + 1);
        }
    }

    // Update ID
    public boolean updateObjectID(String oldID, String newID) throws CrudException {
        refreshList();
        // Find the object by ID
        try {
            int i = findObject("" + oldID, objectList);
            objectList.get(i).set(idPosition, newID);
            return true;
        } catch (Exception e) {
            throw new CrudException("Object not found for update.");
        }
    }
}
