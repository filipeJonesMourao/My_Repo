
public class FileManager {

    File[] fileArray;
    private int fileCounter = 0; //being static here is not good because if there is more than one filemanager then it would fuckup, but if filecounter was a property of file, then static might be good.
    boolean login = false;

    public FileManager(int idx) {
        fileArray = fileArrayCreator(idx);
    }

    private File[] fileArrayCreator(int idx) {
        System.out.println("\nInitializing File Manager...");
        return new File[idx];
    }

    public void login() {
        System.out.println("\nLogging in...");
        this.login = true;
    }

    public void logout() {
        this.login = false;
    }

    public void createFile(String fileName) throws NotEnoughPermissionsException, NotEnoughSpaceException {


        System.out.println("\nAttempting to create file...");

        if (!login) {
            throw new NotEnoughPermissionsException();
        }

        if (fileCounter >= fileArray.length) {
            throw new NotEnoughSpaceException();
        }

        fileArray[fileCounter] = new File(fileName);
        fileCounter++;
        System.out.println("File " + fileName + " created successfully!");
        this.getFileCounter();
    }


    public File getFile(String filename) throws FileNotFoundException, NotEnoughPermissionsException {

        System.out.println("\nAttempting to get file...");

        if (!login) {
            throw new NotEnoughPermissionsException();
        }

        for (File file : fileArray) {

            // if there is a remove function and its possible to have gaps in the array, there should be an if not null condition to check equals.getName otherwise NullPointer Exception)
            if (filename.equals(file.getFileName())) {

                System.out.println("File " + file.getFileName() + " is available for your perusal.");
                return file;
            }
        }

        throw new FileNotFoundException();

    }


    public boolean getLogin() {
        return this.login;
    }

    public void getFileCounter() {
        System.out.println("Files created: " + this.fileCounter);
    }


}
