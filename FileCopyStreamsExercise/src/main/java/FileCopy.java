import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {

    private String sourceFilePath = "/Users/codeforallsintra/Desktop/Projects/Repositories/personal/FileCopyStreamsExercise/src/main/resources/text.txt";
    private String destinationFilePath = "/Users/codeforallsintra/Desktop/Projects/Repositories/personal/FileCopyStreamsExercise/src/main/resources/text_copy3.txt";
    private String destination2FilePath = "/Users/codeforallsintra/Desktop/Projects/Repositories/personal/FileCopyStreamsExercise/src/main/resources/text_copy4.txt";

    private String sourceFilePath2 = "/Users/codeforallsintra/Desktop/Projects/Repositories/personal/FileCopyStreamsExercise/src/main/resources/Clean_Code.pdf";
    private String destinationFilePath3 = "/Users/codeforallsintra/Desktop/Projects/Repositories/personal/FileCopyStreamsExercise/src/main/resources/book_copy2.pdf";

    FileInputStream inputStream;
    FileOutputStream outputStream;


    public void byteByByte() {

        try {

            inputStream = new FileInputStream(sourceFilePath);
            outputStream = new FileOutputStream(destinationFilePath);

            int b = inputStream.read();

            while (b != -1) {

                outputStream.write(b);
                b = inputStream.read();
            }

        } catch (FileNotFoundException e) {

            System.out.println(e.getMessage());

        } catch (IOException e) {

            System.err.println(e.getMessage());

        } finally {

            try {

                if (inputStream != null) {

                    inputStream.close();

                }
                if (outputStream != null) {

                    outputStream.close();
                }

            } catch (IOException e) {

                System.out.println(e.getMessage());

            }

        }
    }

    public void copyByBuffer() {

        byte[] buffer = new byte[1024];

        try {

            inputStream = new FileInputStream(sourceFilePath2);
            outputStream = new FileOutputStream(destinationFilePath3);

            int readNum;

            while ((readNum = inputStream.read(buffer)) != -1) {

                outputStream.write(buffer, 0, readNum);
            }

        } catch (FileNotFoundException e) {

            System.out.println(e.getMessage());

        } catch (IOException e) {

            System.out.println(e.getMessage());

        } finally {

            try {

                inputStream.close();
                outputStream.close();

            } catch (IOException e) {

                System.out.println(e.getMessage());

            }

        }


    }
}

