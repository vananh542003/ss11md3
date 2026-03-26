public class Product {
    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getter & Setter để Stream có thể truy cập
    public String getName() { return name; }
    public double getPrice() { return price; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return String.format("ID: %-5s | Tên: %-15s | Giá: %,.2f", id, name, price);
    }
}
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProductManagement {
    private static Map<String, Product> productMap = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n********* QUẢN LÝ SẢN PHẨM (HASHMAP) *********");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Sửa sản phẩm");
            System.out.println("3. Xóa sản phẩm");
            System.out.println("4. Hiển thị danh sách");
            System.out.println("5. Lọc sản phẩm (Giá > 100)");
            System.out.println("6. Tính tổng giá trị");
            System.out.println("7. Thoát");
            System.out.print("Lựa chọn của bé: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1: addProduct(); break;
                    case 2: updateProduct(); break;
                    case 3: deleteProduct(); break;
                    case 4: displayAll(); break;
                    case 5: filterHighPrice(); break;
                    case 6: calculateTotal(); break;
                    case 7:
                        System.out.println("Tạm biệt bé yêu!");
                        return;
                    default: System.out.println("Bé chọn từ 1-7 thôi nha!");
                }
            } catch (Exception e) {
                System.err.println("Lỗi: Bé nhập dữ liệu chưa đúng rồi!");
            }
        }
    }

    private static void addProduct() {
        System.out.print("Nhập ID sản phẩm: ");
        String id = sc.nextLine();
        if (productMap.containsKey(id)) {
            System.err.println("Lỗi: ID này đã tồn tại rồi bé ơi!");
            return;
        }
        System.out.print("Nhập tên sản phẩm: ");
        String name = sc.nextLine();
        System.out.print("Nhập giá sản phẩm: ");
        double price = Double.parseDouble(sc.nextLine());

        productMap.put(id, new Product(id, name, price));
        System.out.println("=> Thêm thành công!");
    }

    private static void updateProduct() {
        System.out.print("Nhập ID cần sửa: ");
        String id = sc.nextLine();
        if (productMap.containsKey(id)) {
            Product p = productMap.get(id);
            System.out.print("Nhập tên mới (đang là " + p.getName() + "): ");
            p.setName(sc.nextLine());
            System.out.print("Nhập giá mới: ");
            p.setPrice(Double.parseDouble(sc.nextLine()));
            System.out.println("=> Đã cập nhật xong!");
        } else {
            System.out.println("Không tìm thấy sản phẩm này!");
        }
    }

    private static void deleteProduct() {
        System.out.print("Nhập ID cần xóa: ");
        String id = sc.nextLine();
        if (productMap.remove(id) != null) {
            System.out.println("=> Đã xóa sản phẩm thành công!");
        } else {
            System.out.println("ID không tồn tại!");
        }
    }

    private static void displayAll() {
        if (productMap.isEmpty()) {
            System.out.println("Kho hàng đang trống trơn!");
        } else {
            productMap.values().forEach(System.out::println);
        }
    }

    private static void filterHighPrice() {
        System.out.println("--- CÁC SẢN PHẨM CÓ GIÁ > 100 ---");
        productMap.values().stream()
                .filter(p -> p.getPrice() > 100)
                .forEach(System.out::println);
    }

    private static void calculateTotal() {
        double total = productMap.values().stream()
                .mapToDouble(Product::getPrice)
                .sum();
        System.out.printf("=> Tổng giá trị tất cả sản phẩm: %,.2f\n", total);
    }
}