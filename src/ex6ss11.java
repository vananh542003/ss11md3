public class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return String.format("Sản phẩm: %-15s | Giá: %,.2f", name, price);
    }
}
import java.util.List;

public interface ProductProcessor {
    // 1. Phương thức abstract
    double calculateTotalValue(List<Product> products);

    // 2. Phương thức static (Gọi bằng ProductProcessor.printProductList)
    static void printProductList(List<Product> products) {
        System.out.println("--- DANH SÁCH CHI TIẾT SẢN PHẨM ---");
        products.forEach(p -> System.out.println(p.toString()));
    }

    // 3. Phương thức default (Lớp implement sẽ tự có phương thức này)
    default boolean hasExpensiveProduct(List<Product> products) {
        return products.stream().anyMatch(p -> p.getPrice() > 100);
    }
}
import java.util.List;

public class ProductProcessorImpl implements ProductProcessor {
    @Override
    public double calculateTotalValue(List<Product> products) {
        // Sử dụng Stream để tính tổng cho chuyên nghiệp nè bé
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }
}
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Tạo danh sách sản phẩm
        List<Product> list = new ArrayList<>();
        list.add(new Product("Laptop", 1200.0));
        list.add(new Product("Chuột", 25.5));
        list.add(new Product("Bàn phím", 150.0));
        list.add(new Product("Lót chuột", 10.0));
        list.add(new Product("Tai nghe", 80.0));

        ProductProcessor processor = new ProductProcessorImpl();

        // 2. Kiểm tra sản phẩm đắt tiền (> 100)
        System.out.println("=== KIỂM TRA SẢN PHẨM ĐẮT TIỀN ===");
        if (processor.hasExpensiveProduct(list)) {
            System.out.println("Các sản phẩm đắt tiền gồm:");
            list.stream()
                    .filter(p -> p.getPrice() > 100)
                    .forEach(System.out::println);
        } else {
            System.out.println("Không có sản phẩm đắt tiền.");
        }

        // 3. Tính tổng giá trị
        double total = processor.calculateTotalValue(list);
        System.out.printf("\n=> Tổng giá trị tất cả sản phẩm: %,.2f\n\n", total);

        // 4. In danh sách bằng phương thức static của Interface
        ProductProcessor.printProductList(list);
    }
}
