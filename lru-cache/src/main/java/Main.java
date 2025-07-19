import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

// LRU Cache Implementation
class Cache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public Cache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter cache capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Cache<Integer, String> cache = new Cache<>(capacity);

        System.out.println("Type commands:\n  - put <key> <value>\n  - get <key>\n  - exit");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            if (parts.length == 0) continue;

            String command = parts[0].toLowerCase();

            switch (command) {
                case "put":
                    if (parts.length != 3) {
                        System.out.println("Usage: put <key> <value>");
                        break;
                    }
                    try {
                        int key = Integer.parseInt(parts[1]);
                        String value = parts[2];
                        cache.put(key, value);
                        System.out.println("Added to cache.");
                    } catch (NumberFormatException e) {
                        System.out.println("Key must be an integer.");
                    }
                    break;

                case "get":
                    if (parts.length != 2) {
                        System.out.println("Usage: get <key>");
                        break;
                    }
                    try {
                        int key = Integer.parseInt(parts[1]);
                        String value = cache.get(key);
                        if (value == null) {
                            System.out.println("Not found.");
                        } else {
                            System.out.println("Value: " + value);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Key must be an integer.");
                    }
                    break;

                case "exit":
                    System.out.println("Exiting.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Unknown command.");
            }
        }
    }
}
