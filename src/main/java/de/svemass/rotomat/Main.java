package de.svemass.rotomat;

import de.svemass.rotomat.controller.RotomatController;

public class Main {

  public static void main(String[] args) {
    System.setProperty("com.sun.xml.bind.v2.bytecode.ClassTailor.noOptimize", "true");
    RotomatController rotomatController = new RotomatController();
    rotomatController.startApplication(args);
  }
}
