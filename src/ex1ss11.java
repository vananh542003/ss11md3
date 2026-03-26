import java.util.Arrays;
import java.util.List;

public class LambdaNumberProcessing {
    public static void main(String[] args) {
        // 1. Tạo một danh sách số nguyên
        List<Integer> numbers = Arrays.asList(10, 5, 8, 20, 15, 30, 7, 2);
        System.out.println("Danh sách gốc: " + numbers);

        // 2. Sử dụng Lambda Expression để in ra tất cả các số chẵn
        System.out.print("Các số chẵn trong danh sách: ");
        numbers.stream()
                .filter(n -> n % 2 == 0) // Lambda kiểm tra số chẵn
                .forEach(n -> System.out.print(n + " ")); // Lambda để in từng số

        System.out.println(); // Xuống dòng cho đẹp nè

        // 3. Sử dụng Lambda Expression để tính tổng tất cả các số
        // Cách 1: Sử dụng reduce (đúng chất Lambda thuần túy)
        int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b); // Lambda cộng dồn hai số

        // Cách 2: Sử dụng mapToInt và sum (cách này chuyên nghiệp hơn cho số)
        int sumOptimized = numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("Tổng của tất cả các số là: " + sum);
    }
}