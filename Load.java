import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Load {
    public static void loadGame() {
        
    }
    
    public static String returnAttributes(String saveName) {
        try(BufferedReader reader = new BufferedReader(new FileReader("gameSettings/saves.txt"))) {
            String allSaves = "";
            String line;
            String attributeString = "";

            while((line = reader.readLine()) != null) {
                allSaves += line.trim();
            }

            String[] saves = allSaves.split(";");
            
            for (String save : saves) {
                String[] attributes = save.split(",");
            
                if (attributes.length > 0 && attributes[0].startsWith("saveName:")) {
                    String currentSaveName = attributes[0].substring("saveName:".length());
            
                    if (currentSaveName.equals(saveName)) {
                        boolean firstAttribute = true;
                        for (String attribute : attributes) {
                            if(attribute.startsWith("saveName:")) continue;
                            if (!firstAttribute) {
                                attributeString += ",";
                            }
            
                            attributeString += attribute.split(":")[1];
                            firstAttribute = false;
                        }
                    }
                }
            }
            return attributeString;
        } catch (IOException e) {

        }
        return "";
    }
}
