package de.svemass.rotomat.controller;

import de.svemass.rotomat.model.RotomatModel;
import de.svemass.rotomat.view.RotomatView;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class RotomatController {
  private static final int amountShelves = 46; // TODO Move elsewhere
  private static final int amountCompartmentsPerShelf = 6; // TODO Move elsewhere
  private RotomatModel model;

  private static void modelToXml(RotomatModel rotomatModel) {
    try {

      File file = new File("F:\\workspace\\Java\\rotomat\\file.xml");
      JAXBContext jaxbContext = JAXBContext.newInstance(RotomatModel.class);
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

      // output pretty printed
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

      jaxbMarshaller.marshal(rotomatModel, file);
      jaxbMarshaller.marshal(rotomatModel, System.out);

    } catch (JAXBException e) {
      e.printStackTrace();
    }
  }

  public void initializeModel(RotomatView view) {
    model = new RotomatModel(amountShelves, amountCompartmentsPerShelf, view);
  }

  public void toggleGridEditable() {
    model.toggleGridEditable();
  }

  public void updateField(
      Integer rowIndex, Integer columnIndex, String text, Integer caretPosition) {
    if (!model.isModelEditable()) {
      System.out.println("Error! Model not editable!"); // TODO Make real log entry
    }
    if (!model.renameSlot(rowIndex - 1, columnIndex - 1, text, caretPosition)) {
      // TODO Make real log entry
      System.out.println("Error! Index out of  bounds!");
    }
  }

  public void saveToFile() {
    System.out.println("Saving to File...");
    modelToXml(model);
  }
}
