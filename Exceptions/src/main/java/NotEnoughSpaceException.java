public class NotEnoughSpaceException extends FileException {

    public NotEnoughSpaceException (){
        super("\nNot enough filespace to create a new file. You may still rename an existing file.");
    }

}
