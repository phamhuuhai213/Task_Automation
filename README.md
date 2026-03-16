# 🚀 TASH AUTOMATION

**Tash Automation** là một hệ thống quản lý công việc và tự động hóa toàn diện, được thiết kế theo kiến trúc Microservices thu nhỏ. Hệ thống kết hợp sức mạnh xử lý dữ liệu của **Spring Boot**, trải nghiệm người dùng mượt mà của **React Native**, và khả năng tự động hóa cảnh báo mạnh mẽ của **n8n**.

---

## 🛠️ Công nghệ sử dụng (Tech Stack)
* **Backend:** Java Spring Boot (RESTful API, Spring Security, Spring Data JPA, Cronjob).
* **Frontend:** React Native (Mobile App).
* **Database:** MySQL (Thiết kế chuẩn Relational Schema, tối ưu bằng Indexing).
* **Automation:** n8n (Tự động hóa luồng công việc & Bắn Email nhắc nhở).

---

## 🌟 Chức năng Cốt lõi (Core Features)

### 1. Phân quyền Hệ thống (RBAC)
* **User:** Quản lý Task cá nhân. Hệ thống tự động ẩn các Task đã bị `BLOCKED` khỏi luồng công việc thông thường.
* **Admin:** Quản trị viên có toàn quyền kiểm soát, xem được toàn bộ Task của hệ thống (kể cả Task `BLOCKED`).
* **Xác thực:** Hỗ trợ Đăng ký/Đăng nhập truyền thống và Social Login (Google, Facebook) qua JWT.

### 2. Quản lý Vòng đời Công việc (Task Lifecycle)
* **Workflow:** `TODO` → `IN_PROGRESS` → `REVIEW` → `DONE`.
* **Auto-Block Job:** Cronjob chạy ngầm mỗi 1 giờ. Tự động chuyển Task sang `BLOCKED` và ghi nhận `blockedAt` nếu `Deadline < CurrentTime` và `Status ≠ DONE`.

### 3. Thuật toán Tính điểm Ưu tiên (9 Levels Scoring Logic)
Hệ thống kết hợp ma trận **Độ Khẩn cấp (Priority)** và **Độ Khó (Importance)** để phân loại công việc tự động từ Backend:

| Cấp độ | Điều kiện (Priority + Importance) | Điểm | Label hiển thị |
| :--- | :--- | :--- | :--- |
| **Level 1** | HIGH + HARD | 8 | 🔥 [RẤT RẤT KHẨN CẤP] |
| **Level 2** | HIGH + (NORMAL/EASY) | 7 | ⚡ [KHẨN CẤP] |
| **Level 3** | (MEDIUM/LOW) + HARD | 6 | ⭐ [QUAN TRỌNG] |
| **Level 4** | MEDIUM + NORMAL | 5 | 📝 [CHÚ Ý] |
| **Level 5** | MEDIUM + EASY | 4 | [BÌNH THƯỜNG] |
| **Level 6** | LOW + NORMAL | 3 | [BÌNH THƯỜNG] |
| **Level 7** | LOW + EASY | 2 | [THẤP] |
| **Level 8** | LOW + (Các trường hợp còn lại) | 1 | [THẤP] |
| **Level 9** | EASY + (Các trường hợp còn lại) | 0 | [KHÔNG ƯU TIÊN] |

### 4. Tự động hóa Cảnh báo (n8n Engine)
* **Daily Reminder (8h00 sáng):** N8n gọi API lấy danh sách Task khẩn cấp, gom nhóm theo User và nhắc việc dựa trên Level ưu tiên.
* **Overdue Alert:** Báo cáo danh sách Task bị quá hạn (`BLOCKED`) trong ngày hôm trước cho Admin/Quản lý.
* **Anti-Spam:** Sử dụng cờ `isNotified` để đảm bảo mỗi task chỉ gửi mail cảnh báo 1 lần duy nhất.

---

## 🏗️ Kiến trúc Hệ thống (System Architecture)

### Cấu trúc Backend Spring Boot
Ứng dụng được chia thành các Layer chuẩn Enterprise:
* **Controller:** Tiếp nhận Request và điều hướng (`ResponseEntity`).
* **Service:** Xử lý nghiệp vụ lõi (Tính toán điểm số, thuật toán sắp xếp).
* **Repository:** Tương tác Database qua Spring Data JPA.
* **DTO & Mapper:** Ẩn giấu thông tin DB và chuyển đổi dữ liệu an toàn trước khi trả về Frontend.
* **Exception & Validator:** Ràng buộc đầu vào và bắt lỗi tập trung bằng `@ControllerAdvice`.

### Tối ưu hóa Database (Indexing)
Để đảm bảo hiệu năng khi lượng Task lớn:
* `CREATE INDEX idx_user_id ON tasks(user_id);` (Tăng tốc độ load danh sách Task cho User).
* `CREATE INDEX idx_status_deadline ON tasks(status, deadline);` (Tối ưu truy vấn cho Cronjob và n8n).

---
## 🛠 Setup Backend (Spring Boot)
1. Cài đặt **Java 17** và **Maven**.
2. Tạo file `.env` từ file `.env.example` và nhập mật khẩu Database Aiven (Liên hệ chủ Repo để lấy Pass).
3. Chạy lệnh: `mvn spring-boot:run`

## 📱 Setup Frontend (React Native - Expo)
1. Cài đặt **Node.js**.
2. Di chuyển vào thư mục: `cd frontend`
3. Cài đặt thư viện: `npm install`
4. Chạy app: `npx expo start`
5. Quét mã QR bằng app **Expo Go** trên điện thoại.

**Lưu ý:** Sửa `BASE_URL` trong code Frontend thành IP máy tính để gọi được API.