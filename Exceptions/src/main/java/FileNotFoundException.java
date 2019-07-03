public class FileNotFoundException extends FileException {

    public FileNotFoundException(){
        super("\nThe file you were looking for could not be found. Try another directory.");
    }

}
