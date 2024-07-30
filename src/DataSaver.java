import java.io.*; // import class
import java.nio.file.Files; // import class
import java.nio.file.Path; // import class
import java.nio.file.Paths; // import class
import java.util.ArrayList; // import class
import java.util.Scanner; // import class
import static java.nio.file.StandardOpenOption.CREATE; // import class

/**
 *
 * @author Daniel Sandy sandyds@mail.uc.edu
 */

public class DataSaver // class DataSaver
{
    /**
     * 
     * @param args
     */
    public static void main(String[] args) // start main()
    {
        File workingDirectory = new File(System.getProperty("user.dir")); // store pwd
        Path file = Paths.get(workingDirectory.getPath()+ "\\src\\data.csv"); // add path to present working directory to store new csv file
        ArrayList<String> recs = new ArrayList<>(); // store array<>
        Scanner in = new Scanner(System.in); // store Scanner in in
        boolean done = false; // variable to continue or stop loop
        String firstName = ""; // variable to store firstName
        String lastName = ""; // variable to store lastName
        String email = ""; // variable to store email
        String ID = "000000"; // variable to store employee ID
        String birthYear = "0000"; // variable to store year of birth
        String indRecord = ""; // variable to store each individual record to the array
        // firstName, lastName, ID w/ 6 digits, Email, BirthYear
        do // continue looping until done
        {
            firstName = SafeInput.getNonZeroLenString(in, "Enter First Name"); // prompt & input first name
            lastName = SafeInput.getNonZeroLenString(in, "Enter Last Name"); // prompt & input last name
            ID = SafeInput.getRegExString(in, "Enter ID Number", "\\d{6}"); // prompt & input employee ID
            email = SafeInput.getRegExString(in, "Enter Email", "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z]{2,6}"); // prompt & input email
            birthYear = SafeInput.getRegExString(in, "Enter Year of Birth", "[0-9]{4}"); // prompt & input year of birth
            indRecord = firstName + "," + lastName + "," + ID + "," + email + "," + birthYear; // store the entire individual record
            recs.add(indRecord); // add individual recor to the array<>
            done = SafeInput.getYNConfirm(in, "Are you done?"); // prompt & input if done
        }while(!done); // continue looping until user is done
        try // run as long as there is no exception
        {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE)); // store new OutputStream in out
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out)); // store new BufferedWriter in writer

            for (String rec : recs) // for each rec in recs
            {
                writer.write(rec, 0, rec.length()); // write the record
                writer.newLine(); // add new line
            }
            writer.close(); // close the writer
            System.out.println("Data file written!"); // Let user know the file was written

        }
        catch (IOException e) // catch exception
        {
            e.printStackTrace(); // print stack trace
        }
        System.out.println(recs); // output the array
    } // return main()
    
} // end class DataSaver
