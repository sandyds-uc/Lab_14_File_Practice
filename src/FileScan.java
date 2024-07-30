import javax.swing.*; // import swing class
import java.io.*; // import class io
import java.nio.file.Files; // import class Files
import java.nio.file.Path; // import class Path
import java.util.ArrayList; // import class ArrayList
import java.util.Scanner;

/**
 *
 * @author Daniel Sandy sandyds@mail.uc.edu
 */

public class FileScan // class FileScan
{
    /**
     *
     * @param args
     */
    public static void main(String[] args) // start main()
    {
        JFileChooser chooser = new JFileChooser(); // store JFileChooser in chooser
        File selectedFile = null; // store File in selectedFile
        // Scanner in = new Scanner(System.in);
        String rec = ""; // each individual record
        ArrayList<String> lines = new ArrayList<>(); // array of records
        int line = 0; // number of lines
        int words = 0; // number of words
        int characters = 0; // number of characters

        System.out.println(""); // output empty line for visual effect
        try // do this unless there is an exception
        {
            if (args.length > 0) { // if there is an argument set
                selectedFile = new File(args[0]); // make that argument the file name
                // System.out.println("Found!!!!!\n");
                if (!selectedFile.exists()) { // if argument set in settings does not exist
                    System.out.println("File not found: " + args[0]); // output file not found
                    System.out.println("Run the program again."); // output need to re-start
                    System.exit(0); // exit the program
                } // end if
            } else { // if no argument set in settings, go through JFileChooser
                File workingDirectory = new File(System.getProperty("user.dir")); // store pwd
                chooser.setCurrentDirectory(workingDirectory); // start user in pwd
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) // if user chooses a valid file
                {
                    selectedFile = chooser.getSelectedFile(); // store selected file in selectedFile
                } else
                {
                    System.out.println("Did not choose a file."); // output file not chosen
                    System.out.println("Start over!"); // output need to restart
                    System.exit(01); // exit the program
                } // end if else
            } // end if else

            Path file = selectedFile.toPath(); // convert file to the actual file path
            InputStream in = new BufferedInputStream(Files.newInputStream(file)); // store Input Stream in in
            BufferedReader reader = new BufferedReader(new InputStreamReader(in)); // store Buffered Readed in reader
            while (reader.ready()) // while user is ready
            {
                rec = reader.readLine(); // rec is a line read from the file
                lines.add(rec); // add rec to lines array
                line++; // add 1 to total # of lines
                words += rec.split(",").length; // add 1 to words after splitting each line by commas
                characters += rec.length(); // length of rec is the total characters since there are no spaces, add total characters of each line to total characters
//              System.out.printf("\nLine %-4d %-60s", line, rec); // if you want to format with Line #'s next to each entry
                System.out.println(rec); // print each line
            }
            reader.close(); // close the reader
            System.out.println("\nSummary Report:"); // output header for the summary report
            System.out.println("-------------------------"); // output header for the summary report
            System.out.println("File Name: " + selectedFile.getName()); // output name of the file
            System.out.println("Total Lines: " + line); // output the total # of lines
            System.out.println("Total Words: " + words); // output the total # of word
            System.out.println("Total Characters: " + characters); // output the total # of characters
            }
        catch (FileNotFoundException e) // if file not found exception
        {
            System.out.println("File not found!"); // output that the file was not found
            e.printStackTrace(); // print stack trace
        } // end 1st catch
        catch (IOException e) // if other io exception
        {
            e.printStackTrace(); // print stack trace
        } // end 2nd catch
    } // return main()
} // end class FileScan