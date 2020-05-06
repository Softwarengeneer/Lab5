import exception.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import javax.xml.bind.JAXBException;

public class Main {
    public static void main(String[] args) throws
            NullValueException, IOException, NoCorrectValue,
            NoArgument, IncorrectValue, JAXBException {
        try {
            String file =
                    Paths.get(args[0]).toAbsolutePath().toString();
            App app = new App();
            app.begin(file);
        } catch (NoSuchElementException var3) {
            System.exit(0);
        }

    }
}