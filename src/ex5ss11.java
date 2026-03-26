import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event {
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Event(String name, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("Sự kiện: %-15s | Từ: %s | Đến: %s",
                name, startDate.format(fmt), endDate.format(fmt));
    }
}
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EventManager {
    private static List<Event> eventList = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    // Định dạng: Ngày/Tháng/Năm Giờ:Phút
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n========= HỆ THỐNG QUẢN LÝ SỰ KIỆN =========");
            System.out.println("1. Thêm sự kiện mới");
            System.out.println("2. Hiển thị danh sách sự kiện");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bé: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());
                if (choice == 1) {
                    addEvent();
                } else if (choice == 2) {
                    displayEvents();
                } else if (choice == 3) {
                    System.out.println("Tạm biệt bé yêu!");
                    break;
                } else {
                    System.out.println("Bé chọn từ 1-3 thôi nha!");
                }
            } catch (NumberFormatException e) {
                System.err.println("Lỗi: Bé phải nhập con số cho menu!");
            }
        }
    }

    private static void addEvent() {
        try {
            System.out.print("Nhập tên sự kiện: ");
            String name = sc.nextLine();

            System.out.print("Thời gian bắt đầu (dd/MM/yyyy HH:mm): ");
            LocalDateTime start = LocalDateTime.parse(sc.nextLine(), formatter);

            System.out.print("Thời gian kết thúc (dd/MM/yyyy HH:mm): ");
            LocalDateTime end = LocalDateTime.parse(sc.nextLine(), formatter);

            // Kiểm tra logic thời gian
            if (end.isBefore(start)) {
                System.err.println("Lỗi: Thời gian kết thúc không được trước thời gian bắt đầu bé ơi!");
            } else {
                eventList.add(new Event(name, start, end));
                System.out.println("=> Thêm sự kiện thành công!");
            }

        } catch (DateTimeParseException e) {
            System.err.println("Lỗi: Định dạng thời gian chưa đúng (Ví dụ đúng: 26/03/2026 15:30)");
        }
    }

    private static void displayEvents() {
        if (eventList.isEmpty()) {
            System.out.println("Chưa có sự kiện nào được lên lịch hết!");
        } else {
            System.out.println("--- DANH SÁCH SỰ KIỆN ---");
            eventList.forEach(System.out::println);
        }
    }
}
