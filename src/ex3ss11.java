import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private String sender;
    private String content;
    private LocalDateTime timestamp;

    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timestamp = LocalDateTime.now(); // Tự động lấy giờ hiện tại
    }

    public String getSender() { return sender; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("[%s] %s: %s", timestamp.format(formatter), sender, content);
    }
}
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChatApp {
    private static List<Message> chatHistory = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n========= MINI CHAT CONSOLE =========");
            System.out.println("1. Gửi tin nhắn");
            System.out.println("2. Xem toàn bộ lịch sử");
            System.out.println("3. Lọc tin nhắn theo người gửi");
            System.out.println("4. Lọc tin nhắn theo ngày (dd/MM/yyyy)");
            System.out.println("5. Thoát");
            System.out.print("Bé chọn số mấy nè: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1: sendMessage(); break;
                    case 2: displayHistory(chatHistory); break;
                    case 3: filterBySender(); break;
                    case 4: filterByDate(); break;
                    case 5:
                        System.out.println("Tạm biệt bé yêu! Hẹn gặp lại trên kênh chat nha!");
                        return;
                    default: System.out.println("Bé chọn từ 1-5 thôi nhé!");
                }
            } catch (NumberFormatException e) {
                System.err.println("Lỗi: Bé phải nhập con số cho menu!");
            }
        }
    }

    private static void sendMessage() {
        System.out.print("Tên người gửi: ");
        String name = sc.nextLine();
        System.out.print("Nội dung: ");
        String msg = sc.nextLine();
        chatHistory.add(new Message(name, msg));
        System.out.println("=> Đã gửi!");
    }

    private static void displayHistory(List<Message> list) {
        if (list.isEmpty()) {
            System.out.println("Chưa có tin nhắn nào hết bé ơi!");
        } else {
            list.forEach(System.out::println);
        }
    }

    private static void filterBySender() {
        System.out.print("Nhập tên người gửi cần tìm: ");
        String name = sc.nextLine();
        List<Message> filtered = chatHistory.stream()
                .filter(m -> m.getSender().equalsIgnoreCase(name))
                .collect(Collectors.toList());
        displayHistory(filtered);
    }

    private static void filterByDate() {
        System.out.print("Nhập ngày muốn xem (định dạng dd/MM/yyyy): ");
        String dateStr = sc.nextLine();
        try {
            // Chuyển chuỗi nhập vào thành đối tượng ngày
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate targetDate = LocalDate.parse(dateStr, formatter);

            List<Message> filtered = chatHistory.stream()
                    .filter(m -> m.getTimestamp().toLocalDate().equals(targetDate))
                    .collect(Collectors.toList());
            displayHistory(filtered);

        } catch (DateTimeParseException e) {
            System.err.println("Lỗi: Bé nhập sai định dạng ngày rồi! Phải là dd/MM/yyyy (VD: 26/03/2026)");
        }
    }
}
