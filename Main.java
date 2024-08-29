import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();
        Boolean running = true;
        
        do { 
            Menu.showMainMenu();
            System.out.print("# Type an option: ");

            String option = sc.nextLine();
            option = option.split(" ")[0].trim().toLowerCase();

            switch(option) {
                case "exit" -> {
                    running = false;
                    sc.close();
                }

                case "new" -> {
                    int playerHealth = 100;
                    int playerAttackDamage = 5;
                    int healthPotionAmount = 2;
                    int enemiesKilled = 0;

                    Game game = new Game(playerHealth, playerAttackDamage, healthPotionAmount, enemiesKilled);

                    boolean enemyAlive = false;
                    Enemy enemy = null;

                    while (true) { 
                        System.out.println(game);
                        System.out.println(enemy == null? "No enemy alive.\n" : enemy);

                        if(enemyAlive) {
                            Menu.showGameEnemyMenu();
                        } else {
                            Menu.showGameMenu();
                        }

                        System.out.print("# Type an option: ");

                        String gameOption = sc.nextLine();
                        gameOption = gameOption.split(" ")[0].trim().toLowerCase();

                        switch(gameOption) {
                            case "fight" -> {
                                if(!enemyAlive) {
                                    enemy = new Enemy(rnd.nextInt(20, 36));
                                    enemyAlive = true;
                                } else {
                                    System.out.println("\n# There's already an enemy alive.");
                                }
                                
                            }

                            case "hit" -> {
                                if(enemy != null) {
                                    if(enemy.getHealth() > 0) {
                                        game.attack(enemy);
                                        game.setPlayerHealth(game.getPlayerHealth() - 10);

                                        if(enemy.getHealth() <= 0) {
                                            enemyAlive = false;
                                            enemy = null;
                                            game.incrementEnemiesKilled();
                                            game.setHealthPotionAmount(game.getHealthPotionAmount() + 1);
                                        }
                                    } else {
                                        
                                    }

                                    if(game.getPlayerHealth() <= 0) {
                                        System.out.println("\n# You died.");
                                        showStats(game, game.getEnemiesKilled());
                                        return;
                                    }
                                } else {
                                    System.out.println("\n# No enemy alive.");
                                }
                            }

                            case "heal" -> {
                                game.heal();
                            }

                            case "stats" -> {
                                showStats(game, game.getEnemiesKilled());
                            }

                            case "save" -> {
                                Save save = new Save(game);
                                save.saveGame();
                                return;
                            }

                            case "exit" -> {
                                return;
                            }
                        }
                    }
                }

                case "load" -> {
                    String saveName = "";
                    while(true) {
                        Save.listSaves();
                        System.out.print("# Type the save name: ");
                        
                        saveName = sc.nextLine();
                        saveName = saveName.split(" ")[0].trim().toLowerCase();

                        if(Load.returnAttributes(saveName).split(",")[0].equals("")) {
                            System.out.println("\n# Invalid save");
                        }  else {
                            break;
                        }
                    }

                    String[] stats = Load.returnAttributes(saveName).split(",");

                    int playerHealth = Integer.parseInt(stats[0]);
                    int playerAttackDamage = Integer.parseInt(stats[1]);
                    int healthPotionAmount = Integer.parseInt(stats[2]);
                    int enemiesKilled = Integer.parseInt(stats[3]);

                    Game game = new Game(
                        playerHealth, 
                        playerAttackDamage, 
                        healthPotionAmount, 
                        enemiesKilled
                    );

                    boolean enemyAlive = false;
                    Enemy enemy = null;

                    while (true) { 
                        System.out.println(game);
                        System.out.println(enemy == null? "No enemy alive.\n" : enemy);

                        if(enemyAlive) {
                            Menu.showGameEnemyMenu();
                        } else {
                            Menu.showGameMenu();
                        }

                        System.out.print("# Type an option: ");

                        String gameOption = sc.nextLine();
                        gameOption = gameOption.split(" ")[0].trim().toLowerCase();

                        switch(gameOption) {
                            case "fight" -> {
                                if(!enemyAlive) {
                                    enemy = new Enemy(rnd.nextInt(20, 36));
                                    enemyAlive = true;
                                } else {
                                    System.out.println("\n# There's already an enemy alive.");
                                }
                                
                            }

                            case "hit" -> {
                                if(enemy != null) {
                                    if(enemy.getHealth() > 0) {
                                        game.attack(enemy);
                                        game.setPlayerHealth(game.getPlayerHealth() - 10);

                                        if(enemy.getHealth() <= 0) {
                                            enemyAlive = false;
                                            enemy = null;
                                            game.incrementEnemiesKilled();
                                            game.setHealthPotionAmount(game.getHealthPotionAmount() + 1);
                                        }
                                    } else {
                                        
                                    }

                                    if(game.getPlayerHealth() <= 0) {
                                        System.out.println("\n# You died.");
                                        showStats(game, game.getEnemiesKilled());
                                        return;
                                    }
                                } else {
                                    System.out.println("\n# No enemy alive.");
                                }
                            }

                            case "heal" -> {
                                game.heal();
                            }

                            case "stats" -> {
                                showStats(game, game.getEnemiesKilled());
                            }

                            case "save" -> {
                                Save save = new Save(game);
                                save.resaveGame(saveName);
                                return;
                            }

                            case "exit" -> {
                                return;
                            }
                        }
                    }
                }

                case "list" -> {
                    Save.listSaves();
                }

                default -> {
                    System.out.println("\n# Incorrect option.\n");
                }
            }
        } while (running);
    }

    public static void showStats(Game game, int enemiesKilled) {
        System.out.println(String.format("\n# Player Stats: \nHealth: %d\nBase Damage: %d\nPotions: %d\n%s: %d", game.getPlayerHealth(), game.getPlayerAttackDamage(), game.getHealthPotionAmount(), game.getEnemiesKilled() > 1 ? "Enemy Killed" : "Enemies Killed", enemiesKilled));
    }
}