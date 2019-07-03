public class NotEnoughPermissionsException extends FileException {


    public NotEnoughPermissionsException(){
        super("\nYou do not currently have the required permission to perform this action. Please login to find out your permissions.");
    }


}
