import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;
import javax.swing.JFileChooser;




public class ProductReader {

    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;


        try
        {

            File workingDirectory = new File(System.getProperty("user.dir"));


            chooser.setCurrentDirectory(workingDirectory);


            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));




                System.out.println(String.format("%-7s", "ID#")
                        + String.format("%-10s", "Name")
                        + String.format("%-25s", "Description")
                        + String.format("%-6s", "Cost"));
                System.out.println("==================================================");

                while(reader.ready())
                {
                    String rec = reader.readLine();
                    String[] inputData = rec.split(",");
                    System.out.println(String.format("%-6s", inputData[0])
                            + String.format("%-10s", inputData[1])
                            + String.format("%-25s", inputData[2])
                            + String.format("%-6s", inputData[3]));
                }
                reader.close(); // must close the file to seal it and flush buffer
                System.out.println("\n\nData file read!");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
