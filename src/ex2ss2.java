class Student {
    private String name;
    private int age;
    private double grade;

    public Student(String name, int age, double grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public String getName() { return name; }
    public double getGrade() { return grade; }

    @Override
    public String toString() {
        return String.format("Tên: %-12s | Tuổi: %-3d | Điểm: %.1f", name, age, grade);
    }
}
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentStreamProcessing {
    public static void main(String[] args) {
        // 1. Tạo danh sách 10 sinh viên
        List<Student> students = new ArrayList<>();
        students.add(new Student("An", 20, 8.5));
        students.add(new Student("Bình", 21, 6.0));
        students.add(new Student("Chi", 19, 9.2));
        students.add(new Student("Dũng", 22, 7.5));
        students.add(new Student("Giang", 20, 5.5));
        students.add(new Student("Hạnh", 21, 8.0));
        students.add(new Student("Khôi", 19, 6.8));
        students.add(new Student("Linh", 22, 7.1));
        students.add(new Student("Minh", 20, 4.5));
        students.add(new Student("Nam", 21, 8.8));

        System.out.println("--- DANH SÁCH SINH VIÊN ĐIỂM > 7.0 (SẮP XẾP THEO TÊN) ---");

        // 2 & 3. Sử dụng Stream để lọc, sắp xếp và in ra
        students.stream()
                .filter(s -> s.getGrade() > 7.0) // Lọc điểm > 7.0
                .sorted(Comparator.comparing(Student::getName)) // Sắp xếp theo tên A-Z
                .forEach(System.out::println); // In kết quả
    }
}