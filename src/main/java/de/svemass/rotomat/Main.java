package de.svemass.rotomat;

import de.svemass.rotomat.controller.RotomatController;

public class Main {

  public static void main(String[] args) {
    RotomatController rotomatController = new RotomatController();
    rotomatController.startApplication(args);
  }

  //  private File getPreferences() { // TODO
  //    Preferences prefs = Preferences.userNodeForPackage(RotomatController.class);
  //    String filePath = prefs.get("filePath", null);
  //    if (filePath != null) {
  //      return new File(filePath);
  //    } else {
  //      return null;
  //    }
  //  }
  //
  //  public void setPreferences(File file) { // TODO
  //    Preferences prefs = Preferences.userNodeForPackage(RotomatController.class);
  //    prefs.put("filePath", file.getPath());
  //  }

}
