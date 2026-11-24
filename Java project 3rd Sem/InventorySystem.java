import java.util.*;

class Product {
    String id;
    String name;
    double price;
    int quantity;

    Product(String id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | ₹" + price + " | Stock: " + quantity;
    }
}

public class InventorySystem {
    static HashMap<String, Product> map = new HashMap<>();
    static ArrayList<Product> list = new ArrayList<>();

    static void addProduct(Product p) {
        map.put(p.id.toUpperCase(), p);
        list.add(p);
    }

    static Product searchById(String id) {
        return map.get(id.toUpperCase());
    }

    static Product binarySearchByName(String targetName) {
        list.sort((a, b) -> a.name.compareToIgnoreCase(b.name));

        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = list.get(mid).name.compareToIgnoreCase(targetName);

            if (cmp == 0) {
                return list.get(mid);
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    static Product binarySearchByPrice(double targetPrice) {
        list.sort((a, b) -> Double.compare(a.price, b.price));

        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (list.get(mid).price == targetPrice) {
                return list.get(mid);
            } else if (list.get(mid).price < targetPrice) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        addProduct(new Product("P101", "Mouse", 500, 20));
        addProduct(new Product("P102", "Keyboard", 1500, 10));
        addProduct(new Product("P103", "Monitor", 8000, 5));

        while (true) {
            System.out.println("\n1. View All  2. Add  3. Search ID  4. Search Name  5. Search Price  6. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                for (Product p : list) {
                    System.out.println(p);
                }
            } else if (choice == 2) {
                System.out.print("ID: ");
                String id = sc.nextLine();
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Price: ");
                double price = sc.nextDouble();
                System.out.print("Quantity: ");
                int qty = sc.nextInt();

                addProduct(new Product(id, name, price, qty));
                System.out.println("Added!");
            } else if (choice == 3) {
                System.out.print("Enter ID: ");
                Product p = searchById(sc.nextLine());
                System.out.println(p != null ? p : "Not found");
            } else if (choice == 4) {
                System.out.print("Enter Name: ");
                Product p = binarySearchByName(sc.nextLine());
                System.out.println(p != null ? p : "Not found");
            } else if (choice == 5) {
                System.out.print("Enter Price: ");
                Product p = binarySearchByPrice(sc.nextDouble());
                System.out.println(p != null ? p : "Not found");
            } else if (choice == 6) {
                break;
            }
        }
       
    }
}
