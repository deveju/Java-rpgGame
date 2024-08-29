import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Save {
    private final Game game;

    public Save(Game game) {
        this.game = game;
    }

    public static void listSaves() {
        System.out.println("\n# Saves List: ");

        try(BufferedReader reader = new BufferedReader(new FileReader("gameSettings/saves.txt"))) {
            String allSaves = "";
            String line;

            while((line = reader.readLine()) != null) {
                allSaves += line.trim();
            }

            String[] saves = allSaves.split(";");
            for(String save : saves) {
                if(!save.isEmpty()) {
                    String[] attributes = save.split(",");

                    for(String attribute : attributes) {
                        if(attribute.startsWith("saveName:")) {
                            String saveName = attribute.substring("saveName:".length());
                            System.out.println(String.format(">> %s", saveName));
                        }
                    }
                }
            }
            System.out.println("");
        } catch (IOException e) {

        }
    }

    public void saveGame() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nATTENTION: WRITING A SAVE NAME THAT ALREADY EXISTS WILL OVERWRITE THE EXISTING SAVE");
        System.out.print("# Type the save name: ");
        String saveName = sc.nextLine().split(" ")[0].trim();
        List<String> saves = new ArrayList<>();
        String newSave = String.format("saveName:%s,\nplayerHealth:%d,\nplayerAtkDmg:%d,\nplayerHealthPotions:%d,\nplayerEnemiesKilled:%d,\n;\n",
                saveName,
                game.getPlayerHealth(),
                game.getPlayerAttackDamage(),
                game.getHealthPotionAmount(),
                game.getEnemiesKilled());

        try (BufferedReader reader = new BufferedReader(new FileReader("gameSettings/saves.txt"))) {
            String line;
            StringBuilder currentSave = new StringBuilder();
            boolean saveUpdated = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("saveName:")) {
                    if (currentSave.toString().contains("saveName:" + saveName + ",")) {
                        saves.add(newSave);
                        saveUpdated = true;
                        currentSave.setLength(0);
                    } else {
                        if (currentSave.length() > 0) {
                            saves.add(currentSave.toString().trim());
                        }
                        currentSave.setLength(0); 
                    }
                }
                currentSave.append(line).append("\n");
            }

            if (currentSave.length() > 0) {
                if (currentSave.toString().contains("saveName:" + saveName + ",")) {
                    saves.add(newSave);
                    saveUpdated = true;
                } else {
                    saves.add(currentSave.toString().trim());
                }
            }
            if (!saveUpdated) {
                saves.add(newSave);
            }
            sc.close();
        } catch (IOException e) {

        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("gameSettings/saves.txt"))) {
            for (String save : saves) {
                writer.write(save);
                writer.write("\n");
            }
        } catch (IOException e) {

        }
    }

    public void resaveGame(String saveName) {
        System.out.println("\nGAME SAVE OVERWROTE");
        List<String> saves = new ArrayList<>();
        String newSave = String.format("saveName:%s,\nplayerHealth:%d,\nplayerAtkDmg:%d,\nplayerHealthPotions:%d,\nplayerEnemiesKilled:%d,\n;\n",
                saveName,
                game.getPlayerHealth(),
                game.getPlayerAttackDamage(),
                game.getHealthPotionAmount(),
                game.getEnemiesKilled());

        try (BufferedReader reader = new BufferedReader(new FileReader("gameSettings/saves.txt"))) {
            String line;
            StringBuilder currentSave = new StringBuilder();
            boolean saveUpdated = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("saveName:")) {
                    if (currentSave.toString().contains("saveName:" + saveName + ",")) {
                        saves.add(newSave);
                        saveUpdated = true;
                        currentSave.setLength(0);
                    } else {
                        if (currentSave.length() > 0) {
                            saves.add(currentSave.toString().trim());
                        }
                        currentSave.setLength(0); 
                    }
                }
                currentSave.append(line).append("\n");
            }

            if (currentSave.length() > 0) {
                if (currentSave.toString().contains("saveName:" + saveName + ",")) {
                    saves.add(newSave);
                    saveUpdated = true;
                } else {
                    saves.add(currentSave.toString().trim());
                }
            }

            if (!saveUpdated) {
                saves.add(newSave);
            }

        } catch (IOException e) {

        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("gameSettings/saves.txt"))) {
            for (String save : saves) {
                writer.write(save);
                writer.write("\n");
            }
        } catch (IOException e) {

        }
    }
}