package library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import exception.*;
import library.Printer;

/**
 * Note: An instance of this class must be created for each file you want to handle
 *
 * @author Juan David Guzman Gallego
 * 		   Code: 1.092.850.749
 * 		   Programming 3
 * 		   Period: 2023-2
 *
 * This class has methods focused on the manipulation of simple text files and includes functions such as:
 * * Create a copy
 * * Create a file
 * * Add text to the file
 * * Replace the text in the file
 * * Get text from the file
 *
 */
public class FileManager {
    private String FILE_PATH;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String text;
    private Printer printer;

    /**
     * @param Path
     * @throws FileManagerException
     */
    public FileManager(String Path) throws FileManagerException {
        super();
        FILE_PATH = Path;
        initializeData();

    }
    public FileManager() {
        super();
    }

    private void initializeData() throws FileManagerException {
        try{
        	printer=new Printer<>("PrinterFileManager");
            reader=new BufferedReader(new FileReader(FILE_PATH));
            text=getText();
            writer=new BufferedWriter(new FileWriter(FILE_PATH));
            setText(text);
        }catch (Exception e) {
            throw new FileManagerException("Please check the path because it could not access the specified file: "+FILE_PATH+"\n"+e.getStackTrace());
        }
    }
    /**
     *
     * @param path
     * @param name
     * @param ext
     * @throws FileExistsException
     * @throws FileManagerException
     */
    public void create(String path,String name,String ext) throws FileExistsException, FileManagerException{
        if(FILE_PATH==null){
            File newFile=new File(path+name+"."+ext);
            if(!newFile.exists())
            {
                try {
                    newFile.createNewFile();

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                FILE_PATH= path + name+"."+ext;
                try{
                    reader=new BufferedReader(new FileReader(FILE_PATH));
                    writer=new BufferedWriter(new FileWriter(FILE_PATH));
                    printer.println("The file " + name + " has been created successfully" );
                }catch (Exception e) {
                    throw new FileManagerException("Please check the path because it could not access the specified file: "+FILE_PATH+"\n"+e.getStackTrace());
                }

            }else{
                throw new FileExistsException("Could not create because the file with the name " + name + " already exists");
            }
        }else{
            throw new FileExistsException("Could not create because there is already a defined path for this FileManager");

        }
    }
    /**
     *
     * @throws IOException
     */
    public void newLine() throws IOException{
        writer.newLine();
        printer.println("New line added successfully to the file " + getName() );
    }
    /**
     *
     * @param msg
     * @throws IOException
     */
    public void addText(String msg) throws IOException{
        // Write to the file
        writer.write(msg);
        writer.flush();
        printer.println("Text added successfully to the file " + getName() );
    }
    /**
     *
     * @param msg
     * @throws IOException
     */
    public void addTextln(String msg) throws IOException{
        // Write to the file
        newLine();
        writer.write(msg);
        writer.flush();
        printer.println("Text added successfully to the file " + getName() );
    }
    /**
     *
     * @param msg
     * @throws IOException
     */
    public void setText(String msg) throws IOException{
        // Write the text to the file
        reader=new BufferedReader(new FileReader(FILE_PATH));
        writer=new BufferedWriter(new FileWriter(FILE_PATH));
        writer.write(msg);
        writer.flush();
        printer.println("Text updated successfully to the file " + getName() );
    }
    /**
     *
     * @return
     * @throws IOException
     */
    public String getText() throws IOException {
        // Read the file and display it on the screen
        String line=reader.readLine();
        String txt="";
        while(line!=null){
            txt+=line+"\n";
            line=reader.readLine();        }

        printer.println("Text obtained successfully from the file " + getName() );
        return txt;
    }
    /**
     *
     * @param newPath
     * @throws FileManagerException
     */
    public void saveCopy(String newPath) throws FileManagerException{
        String txt;
        FileManager newFile;
        String newName;


        // First create the new FileManager
            newFile = new FileManager();


        // get a valid name
            newName = "copy"+"_"+getName()+"."+getExtension();
            File file = new File(newPath,newName);
            if(file.exists()){
            newName = getValidName(newPath,getName(),getExtension(),1);
            }


        // then create the file in the source path
            try {
                newFile.create(newPath, newName.substring(0, newName.indexOf(".")), getExtension());
            } catch (FileExistsException e) {
                e.printStackTrace();
            } catch (FileManagerException e) {
                e.printStackTrace();
                // Exception in case it cannot access the specified path
                throw new FileManagerException(e.getMessage() + "\nor could not access the path where the copy of the document was to be saved");
            }


        // then read the original file and extract the text
            try {
                txt = getText();
            } catch (IOException e) {
                e.printStackTrace();
                throw new FileManagerException("Could not read the information from the original file");
            }

        // and finally write to the new file
            try {
                newFile.setText(txt);
            } catch (IOException e) {
                e.printStackTrace();
                throw new FileManagerException("Could not write to the new file "+ newName);
            }
            printer.println("copy " +newName.substring(0, newName.indexOf(".")) + " created successfully to the file " + getName() );

    }
    /**
     *
     * @param newPath
     * @param name
     * @param ext
     * @param copyNum
     * @return
     */
    private String getValidName(String newPath, String name,String ext,int copyNum) {
        String newName = "copy"+copyNum+"_"+name+"."+ext;
        File file = new File(newPath,newName);
        // stopping condition
        if(!file.exists())
            return newName;
        else{
            return getValidName(newPath, name, ext, copyNum+1);
        }
    }
    public String getName() {
        return FILE_PATH.substring(FILE_PATH.lastIndexOf("/")+1,FILE_PATH.indexOf("."));
    }
    public String getExtension() {
        return FILE_PATH.substring(FILE_PATH.indexOf(".")+1);
    }
    public String getFILE_PATH() {
        return FILE_PATH;
    }
    public void setFILE_PATH(String FILE_PATH) throws FileManagerException {
        this.FILE_PATH = FILE_PATH;
        initializeData();
    }
    public BufferedReader getReader() {
        return reader;
    }
    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }
    public BufferedWriter getWriter() {
        return writer;
    }
    public void setWriter(BufferedWriter writer) {
        this.writer = writer;
    }

}
