import java.io.*;

public class Menu {
    public static void showMainMenu() {
        try (BufferedReader reader = new BufferedReader(new FileReader("gameSettings/mainMenu.txt"))) {
            String line;
            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {

        }
    }

    public static void showGameMenu() {
        try (BufferedReader reader = new BufferedReader(new FileReader("gameSettings/gameMenu.txt"))) {
            String line;
            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {

        }
    }

    public static void showGameEnemyMenu() {
        try (BufferedReader reader = new BufferedReader(new FileReader("gameSettings/gameEnemyMenu.txt"))) {
            String line;
            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {

        }
    }
}
