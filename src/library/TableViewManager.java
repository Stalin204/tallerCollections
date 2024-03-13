package library;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TableViewManager<T> {
    private TableView<ArrayList<String>> tableView;
    private ObservableList<ArrayList<String>> data; // Uses an observable list

    public TableViewManager(TableView<ArrayList<String>> tableView) {
        this.tableView = tableView;
        this.data = FXCollections.observableArrayList(); // Initializes the observable list
        this.tableView.setItems(data); // Configures the TableView with the observable list
    }

    public void updateDataFromArrayList(ArrayList<ArrayList<String>> arrayList) {
        data.clear(); // Clears the current data in the TableView
        addData(arrayList); // Adds data from the ArrayList to the observable list
    }

    private void addData(ArrayList<ArrayList<String>> arrayList) {
        addData(arrayList, 0);
    }

    private void addData(ArrayList<ArrayList<String>> arrayList, int index) {
        if (index == arrayList.size() - 1) {
            this.data.add(arrayList.get(index));
        } else {
            this.data.add(arrayList.get(index));
            addData(arrayList, index + 1);
        }
    }

    public void addRow(ArrayList<String> item) {
        tableView.getItems().add(item);
    }

    public void deleteRow(T item) {
        tableView.getItems().remove(item);
    }

    public ObservableList<ArrayList<String>> getSelectedRows() {
        return tableView.getSelectionModel().getSelectedItems();
    }

    public void updateRow(int index, ArrayList<String> newItem) {
        tableView.getItems().set(index, newItem);
    }

    public void clearTableView() {
        tableView.getItems().clear();
    }

    public void selectRow(int index) {
        tableView.getSelectionModel().select(index);
    }

    public void searchAndSelectElement(int element) {
        tableView.getSelectionModel().select(element);
        tableView.scrollTo(element);
    }

    public void saveDataToFile(String fileName) {
        // Implement logic to save data to a file
    }

    public void loadDataFromFile(String fileName) {
        // Implement logic to load data from a file
    }
}
