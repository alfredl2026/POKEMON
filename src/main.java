import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Pokemon Game!");

        // Create a player and choose a starter Pokemon
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();
        System.out.println("Hello, " + playerName + "!");

        System.out.println("Choose your starter Pokemon:");
        System.out.println("1. Charmander");
        System.out.println("2. Squirtle");
        System.out.println("3. Bulbasaur");
        System.out.print("Enter the number of your choice: ");

        int choice = scanner.nextInt();
        Pokemon starterPokemon;

        switch (choice) {
            case 1:
                starterPokemon = new Pokemon("Charmander", 100, "Fire");
                break;
            case 2:
                starterPokemon = new Pokemon("Squirtle", 100, "Water");
                break;
            case 3:
                starterPokemon = new Pokemon("Bulbasaur", 100, "Grass");
                break;
            default:
                System.out.println("Invalid choice. Charmander is selected as your starter Pokemon.");
                starterPokemon = new Pokemon("Charmander", 100, "Fire");
                break;
        }

        System.out.println("Congratulations! You received a level 5 " + starterPokemon.getName() + "!");

        // Create the player's party and add the starter Pokemon
        ArrayList<Pokemon> party = new ArrayList<>();
        party.add(starterPokemon);

        // Main game loop
        while (true) {
            System.out.println("\nWhat do you want to do?");
            System.out.println("1. Explore");
            System.out.println("2. Check Party");
            System.out.println("3. Quit");
            System.out.print("Enter the number of your choice: ");

            int action = scanner.nextInt();

            if (action == 1) {
                // Simulate exploring and encountering a wild Pokemon
                Pokemon wildPokemon = generateWildPokemon();

                System.out.println("\nA wild " + wildPokemon.getName() + " appeared!");

                // Battle loop
                while (true) {
                    System.out.println("\nWhat do you want to do?");
                    System.out.println("1. Attack");
                    System.out.println("2. Switch Pokemon");
                    System.out.println("3. Catch");
                    System.out.println("4. Run");
                    System.out.print("Enter the number of your choice: ");

                    int battleAction = scanner.nextInt();

                    if (battleAction == 1) {
                        // Simulate a battle turn
                        simulateBattleTurn(party.get(0), wildPokemon);
                    } else if (battleAction == 2) {
                        // Simulate switching Pokemon
                        System.out.println("Your current Pokemon is " + party.get(0).getName() + ".");
                        System.out.println("Choose a Pokemon from your party to switch to:");

                        for (int i = 1; i < party.size(); i++) {
                            System.out.println((i + 1) + ". " + party.get(i).getName());
                        }

                        System.out.print("Enter the number of your choice: ");
                        int switchChoice = scanner.nextInt();

                        if (switchChoice >= 2 && switchChoice <= party.size()) {
                            Pokemon temp = party.get(0);
                            party.set(0, party.get(switchChoice - 1));
                            party.set(switchChoice - 1, temp);

                            System.out.println("You switched to " + party.get(0).getName() + "!");
                        } else {
                            System.out.println("Invalid choice. Please try again.");
                        }
                    } else if (battleAction == 3) {
                        // Simulate catching the wild Pokemon
                        if (wildPokemon.getHP() < 30) {
                            if (party.size() < 6) {
                                party.add(wildPokemon);
                                System.out.println("You caught the wild " + wildPokemon.getName() + "!");
                                break;
                            } else {
                                System.out.println("Your party is full! You cannot catch more Pokemon.");
                            }
                        } else {
                            System.out.println("The wild " + wildPokemon.getName() + " is too strong to catch!");
                        }
                    } else if (battleAction == 4) {
                        // Simulate running away from the battle
                        System.out.println("You ran away successfully!");
                        break;
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }

                    // Check if either Pokemon has fainted (HP <= 0)
                    if (party.get(0).getHP() <= 0) {
                        System.out.println("Your " + party.get(0).getName() + " fainted. Game over!");
                        return;
                    } else if (wildPokemon.getHP() <= 0) {
                        System.out.println("You defeated the wild " + wildPokemon.getName() + "!");
                        break;
                    }
                }
            } else if (action == 2) {
                // Display the player's party
                System.out.println("\nYour Party:");
                for (int i = 0; i < party.size(); i++) {
                    System.out.println((i + 1) + ". " + party.get(i).getName() + " (HP: " + party.get(i).getHP() + ")");
                }
            } else if (action == 3) {
                System.out.println("Thanks for playing! Goodbye, " + playerName + "!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    // Function to generate a random wild Pokemon
    public static Pokemon generateWildPokemon() {
        String[] pokemonNames = { "Pikachu", "Eevee", "Jigglypuff", "Snorlax", "Mewtwo" };
        String[] pokemonTypes = { "Electric", "Normal", "Fairy", "Normal", "Psychic" };
        int[] pokemonHP = { 50, 60, 40, 80, 100 };

        int randomIndex = (int) (Math.random() * pokemonNames.length);

        return new Pokemon(pokemonNames[randomIndex], pokemonHP[randomIndex], pokemonTypes[randomIndex]);
    }

    // Function to simulate a battle turn between two Pokemon
    public static void simulateBattleTurn(Pokemon attacker, Pokemon defender) {
        System.out.println(attacker.getName() + " used Tackle!");
        System.out.println(defender.getName() + " lost 10 HP.");

        defender.setHP(defender.getHP() - 10);

        System.out.println("Current HP - " + defender.getName() + ": " + defender.getHP());
    }
}

class Pokemon {
    private String name;
    private int hp;
    private String type;

    public Pokemon(String name, int hp, String type) {
        this.name = name;
        this.hp = hp;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getHP() {
        return hp;
    }

    public void setHP(int hp) {
        this.hp = hp;
    }

    public String getType() {
        return type;
    }
}