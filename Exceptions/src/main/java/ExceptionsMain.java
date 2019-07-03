public class ExceptionsMain {


    static FileManager filey = new FileManager(2);

    public static void main(String[] args) {

        createFileTest("blabla");

        filey.login();

        createFileTest("blabla");

        createFileTest("bumbum");

        createFileTest("beebop");

        //filey.logout();

        getFileTest("blabla");

        getFileTest("beebop");


    }

    public static void createFileTest(String filename) {

        try {
            filey.createFile(filename);

        } catch (NotEnoughPermissionsException ex) {

            System.out.println(ex.getMessage());

        } catch (NotEnoughSpaceException ex) {

            System.out.println(ex.getMessage());

        }
    }

    public static void getFileTest(String filename) {

        try {
            filey.getFile(filename);

        } catch (NotEnoughPermissionsException ex) {

            System.out.println(ex.getMessage());

        } catch (FileNotFoundException ex) {

            System.out.println(ex.getMessage());

        }
    }


}
