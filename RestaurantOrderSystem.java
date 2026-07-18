import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class MenuItem {
    String name;
    double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " - LKR " + price;
    }
}

class Order {
    static int idCounter = 1;
    int orderId;
    // 4. List: Using an ArrayList to store multiple items per order
    List<MenuItem> items;
    String customerName;

    public Order(String customerName) {
        this.orderId = idCounter++;
        this.customerName = customerName;
        this.items = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order #").append(orderId).append(" | Customer: ").append(customerName).append("\n");
        double total = 0;
        for (MenuItem item : items) {
            sb.append("   - ").append(item.name).append("\n");
            total += item.price;
        }
        sb.append("   Total: LKR ").append(total);
        return sb.toString();
    }
}

public class RestaurantOrderSystem {
    // 1. Array: Storing the fixed menu
    private final MenuItem[] menu;
    
    // 2. Queue (First-In-First-Out): Managing incoming pending orders
    private final Queue<Order> pendingOrders;
    
    // 3. Stack (Last-In-First-Out): Keeping track of recently completed orders for potential undo
    private final Stack<Order> completedOrders;

    public RestaurantOrderSystem() {
        // Initialize Array with some dummy menu items
        menu = new MenuItem[]{
            new MenuItem("Hot Dog", 300),
            new MenuItem("Chicken Rice(Full)", 1200),
            new MenuItem("Biriyani", 900),
            new MenuItem("Salad", 250),
            new MenuItem("Orange Juice", 150)
        };
        
        // Initialize Queue and Stack
        pendingOrders = new LinkedList<>();
        completedOrders = new Stack<>();
    }

    public void displayMenu() {
        System.out.println("\n--- Cafe Menu ---");
        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + ". " + menu[i]);
        }
    }

    public void startNewOrder(Scanner scanner) {
        System.out.print("\nEnter customer name: ");
        String name = scanner.nextLine();
        Order newOrder = new Order(name);

        boolean addingItems = true;
        while (addingItems) {
            displayMenu();
            System.out.print("\nEnter item number to add (or 0 to finish order): ");
            try {
                int itemNum = Integer.parseInt(scanner.nextLine());
                if (itemNum == 0) {
                    if (newOrder.items.isEmpty()) {
                        System.out.println("Order cancelled (no items were added).");
                        return;
                    }
                    addingItems = false;
                } else if (itemNum > 0 && itemNum <= menu.length) {
                    newOrder.addItem(menu[itemNum - 1]);
                    System.out.println(">>> Added '" + menu[itemNum - 1].name + "' to the order.");
                } else {
                    System.out.println("Invalid menu item! Please pick a valid number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        
        pendingOrders.add(newOrder); // Enqueue
        System.out.println("\n[SUCCESS] Order finalized and added to the queue: \n" + newOrder);
    }

    public void serveNextOrder() {
        if (pendingOrders.isEmpty()) {
            System.out.println("\n[INFO] No pending orders to serve.");
            return;
        }
        
        Order servedOrder = pendingOrders.poll(); // Dequeue
        completedOrders.push(servedOrder);        // Push to Stack
        System.out.println("\n[SUCCESS] Order served and moved to completed: \n" + servedOrder);
    }

    public void undoLastCompletedOrder() {
        if (completedOrders.isEmpty()) {
            System.out.println("\n[INFO] No recently completed orders to undo.");
            return;
        }
        
        Order lastCompleted = completedOrders.pop(); // Pop from Stack
        pendingOrders.add(lastCompleted); // Re-queue
        System.out.println("\n[UNDO] Order reverted and put back into pending queue: \n" + lastCompleted);
    }

    public void displayPendingOrders() {
        System.out.println("\n--- Pending Orders (" + pendingOrders.size() + ") ---");
        if (pendingOrders.isEmpty()) {
            System.out.println("None.");
        } else {
            for (Order o : pendingOrders) {
                System.out.println(o);
                System.out.println("-------------------------");
            }
        }
    }

    public static void main(String[] args) {
        RestaurantOrderSystem system = new RestaurantOrderSystem();
        System.out.println("Welcome to the Restaurant Order Management System!");

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            while (running) {
                System.out.println("\n=================================");
                System.out.println("1. View Menu");
                System.out.println("2. Place New Order (Multiple Items)");
                System.out.println("3. View Pending Orders");
                System.out.println("4. Serve Next Order (Dequeue)");
                System.out.println("5. Undo Last Served Order (Pop Stack)");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");
                
                String choice = scanner.nextLine();
                
                switch (choice) {
                    case "1" -> system.displayMenu();
                    case "2" -> system.startNewOrder(scanner);
                    case "3" -> system.displayPendingOrders();
                    case "4" -> system.serveNextOrder();
                    case "5" -> system.undoLastCompletedOrder();
                    case "6" -> {
                        running = false;
                        System.out.println("Exiting system. Goodbye!");
                    }
                    default -> System.out.println("Invalid option, try again.");
                }
            }
        }
    }
}
