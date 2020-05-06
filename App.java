import CollectionElements.Dragon;
import WorkUtils.CommandHandler;
import WorkUtils.CommandReader;
import WorkUtils.DragonCollection;
import exception.IncorrectValue;
import exception.NoArgument;
import exception.NoCorrectValue;
import exception.NullValueException;
import Input.TerminalInput;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class App {
    private CommandReader reader;
    private CommandHandler handler;

    public void begin(String file) throws NoCorrectValue, NullValueException, JAXBException, IOException, NoArgument, IncorrectValue {
        Scanner in = new Scanner(new File(file));
        StringBuffer data = new StringBuffer();

        while(in.hasNext()) {
            data.append(in.nextLine());
        }

        JAXBContext context1 = JAXBContext.newInstance(DragonCollection.class);
        Unmarshaller jaxbUnmarshaller = context1.createUnmarshaller();
        DragonCollection dragonCollection = (DragonCollection)jaxbUnmarshaller.unmarshal(new File(file));
        Iterator var7 = dragonCollection.getDragons().iterator();

        while(var7.hasNext()) {
            Dragon d = (Dragon)var7.next();
            if (d.getName().trim().equals("")) {
                throw new NullValueException("name");
            }

           //  if (null == d.getCoordinates().getX()) {
           //    throw new NullValueException("x");
            //}

            if (d.getCoordinates().getY() == null) {
                throw new NullValueException("y");
            }

            if (d.getCoordinates().getY() > 649.0) {
                throw new NoCorrectValue("Максимальное значение поля y - 649");
            }

            if (d.getCoordinates().getX() < -671) {
                throw new NoCorrectValue("X должен быть больше -671");
            }

            if (d.getCreationDate() == null) {
                throw new NullValueException("date");
            }

            if (d.getColor() == null) {
                throw new NullValueException("RealHero");
            }

            if (d.getId() <= 0) {
                throw new NoCorrectValue("Id should be > 0");
            }
        }

        dragonCollection.setDate(new Date());
        this.handler = new CommandHandler(dragonCollection, file);
        TerminalInput terminal = new TerminalInput();
        terminal.output("Салам бандит, жми help чтоб я тебе показал че могу");

        while(!terminal.getNextInput().equals("exit")) {
            this.handler.doCommand(terminal);
        }

    }
}