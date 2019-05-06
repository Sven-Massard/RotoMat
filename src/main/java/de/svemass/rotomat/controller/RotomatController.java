package de.svemass.rotomat.controller;

import de.svemass.rotomat.Main;
import de.svemass.rotomat.model.RotomatModel;
import de.svemass.rotomat.view.RotomatView;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class RotomatController {
  private static final int amountShelves = 46; // TODO Move elsewhere
  private static final int amountCompartmentsPerShelf = 6; // TODO Move elsewhere
  private static File file;
  private RotomatModel model;

  public RotomatController() {
    setFilePath();
  }

  private void setFilePath() {
    file =
        new File(
            new File(
                    Main.class
                        .getProtectionDomain()
                        .getCodeSource()
                        .getLocation()
                        .getPath()
                        .replaceAll("%20", " "))
                .getParent(),
            "rotomat.xml");
  }

  private static void modelToXml(RotomatModel rotomatModel) {
    try {

      JAXBContext jaxbContext = JAXBContext.newInstance(RotomatModel.class);
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

      // output pretty printed
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

      jaxbMarshaller.marshal(rotomatModel, file);

    } catch (JAXBException e) {
      e.printStackTrace();
    }
  }

  private RotomatModel xmlToModel() throws JAXBException {
    JAXBContext jaxbContext = JAXBContext.newInstance(RotomatModel.class);

    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

    model = (RotomatModel) unmarshaller.unmarshal(file);

    return model;
  }

  public void startApplication(String[] args) {
    RotomatView.setController(this);
    RotomatView.main(args);
  }

  public void initializeModel(RotomatView view) throws JAXBException {
    if (file.exists()) {
      model = new RotomatModel(xmlToModel(), view);
    } else {
      model = new RotomatModel(amountShelves, amountCompartmentsPerShelf, view);
    }
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
    modelToXml(model);
  }
}
