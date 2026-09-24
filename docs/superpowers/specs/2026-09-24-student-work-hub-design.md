# Student Work Hub — Android UI Design

วันที่: 2026-09-24  
สถานะ: รออนุมัติเอกสารก่อนเริ่ม implementation

## เป้าหมาย

สร้างโครงหน้าจอ Android สำหรับ Student Work Hub ตามภาพอ้างอิง โดยเน้นการเชื่อมหน้าและโครงสร้าง UI ก่อนเชื่อม Backend จริง

ความสำเร็จของระยะแรก:

- ผู้ใช้กดปุ่มทั้งสองบน Splash แล้วไปหน้า Login ได้
- Login ใช้ Mock User และแสดง error เมื่อข้อมูลไม่ถูกต้อง
- Login สำเร็จแล้วเข้าสู่ Main ได้
- Main มี Bottom Menu รูปแบบใกล้เคียงภาพและกดเปลี่ยนหน้าได้
- หน้ารองมี Title ระบุหน้าชัดเจน แม้ยังไม่มีข้อมูลจริง
- Android Studio ไม่เตือนจากข้อความ สี ขนาด หรือ content description ที่ hardcode โดยไม่จำเป็น

## ขอบเขต

### อยู่ในระยะนี้

- Splash screen
- Login screen
- Main/Home screen
- Bottom menu และ navigation
- หน้าตัวอย่างสำหรับเมนูและหน้ารอง
- Material Theme และ resource แยกสำหรับข้อความ สี ขนาด และ style
- Mock authentication

### ยังไม่อยู่ในระยะนี้

- Backend หรือ API
- ฐานข้อมูล
- Google Login / Apple Login / third-party login
- ระบบสมัครสมาชิกจริง
- ข้อมูลรายการงานจริง
- การบันทึก session แบบถาวร

## โครงสร้างที่เลือก

ใช้ Activity เดียวร่วมกับ Android Navigation Component และ Fragment แยกตามหน้าจอ

```text
MainActivity
└── NavHostFragment
    ├── SplashFragment
    ├── LoginFragment
    └── MainFragment
        ├── HomeFragment
        ├── SearchFragment
        ├── CreateJobFragment
        ├── ScheduleFragment
        └── ProfileFragment
```

เหตุผล:

- มี Activity เดียว จึงควบคุม Theme และ system insets ได้จากจุดเดียว
- Fragment เหมาะกับหน้าที่เปลี่ยนผ่านในแอปเดียวกัน
- Navigation Component ช่วยจัดการ route และ back stack อย่างชัดเจน
- Bottom Menu ใช้เฉพาะส่วน Main และไม่แสดงบน Splash/Login

## Navigation

```text
Splash
  ├── เริ่มใช้งาน ─┐
  └── เข้าสู่ระบบ ──┴──> Login

Login (Mock User สำเร็จ) ──> Main/Home

Main/Home
  ├── หน้าหลัก       ──> HomeFragment
  ├── ค้นหางาน       ──> SearchFragment
  ├── ปุ่ม +          ──> CreateJobFragment
  ├── ตารางงาน       ──> ScheduleFragment
  └── โปรไฟล์        ──> ProfileFragment
```

หน้ารองที่เตรียม route หรือ placeholder title ไว้:

- รายละเอียดงาน
- ชั่วโมงทำงาน
- รายได้
- การสมัครงาน
- ตัวกรองการค้นหา

หน้ารองเหล่านี้จะแสดง Title ก่อน และสามารถเพิ่ม UI จริงภายหลังโดยไม่ต้องเปลี่ยนโครง navigation หลัก

## Mock Login

บัญชีทดสอบ:

- Email: `student@example.com`
- Password: `password`

กติกา:

- ตรวจว่าช่อง Email และ Password ไม่ว่าง
- ตรวจค่าตรงกับ Mock User
- ค่าถูกต้อง: นำทางไป Main/Home
- ค่าผิด: แสดง error ผ่าน Material TextInputLayout และไม่เปลี่ยนหน้า
- ไม่ log password และไม่ถือว่าเป็นระบบ authentication จริง

## UI และ Theme

- ใช้ Material Theme สำหรับสีพื้นฐาน typography ช่องกรอก และปุ่ม
- ใช้ Light Theme ตามภาพอ้างอิงในระยะนี้
- ข้อความทั้งหมดอยู่ใน `strings.xml`
- สีทั้งหมดอยู่ใน `colors.xml`
- ระยะห่างและขนาดที่ใช้ซ้ำอยู่ใน `dimens.xml`
- รูปแบบปุ่ม ช่องกรอก และ component อยู่ใน theme/style resource
- ไอคอนใช้ Material/vector drawable พร้อม `contentDescription` จาก resource
- หลีกเลี่ยง literal string, color, dimension และ drawable ที่ไม่จำเป็นใน layout

Bottom Menu จะประกอบด้วยเมนู 5 ตำแหน่ง และปุ่ม `+` ตรงกลาง โดยใช้ component ของ Material ที่เหมาะกับรูปแบบ bottom bar เพื่อให้ใกล้เคียงภาพอ้างอิง

## การเปลี่ยนแปลงที่คาดว่าจะทำ

- ปรับ `MainActivity` ให้เป็น host ของ Navigation
- เปลี่ยน `activity_main.xml` เป็น layout หลักที่มี NavHost และพื้นที่ของ Main shell
- เพิ่ม Fragment และ layout สำหรับ Splash, Login, Home, Search, Create Job, Schedule และ Profile
- เพิ่ม placeholder Fragment/layout สำหรับหน้ารองที่จำเป็น
- เพิ่ม navigation graph และ resource files ที่เกี่ยวข้อง
- เพิ่ม dependency ของ Navigation Component หากยังไม่มีในโปรเจกต์
- คง Java + XML ตามโครงสร้างโปรเจกต์ปัจจุบัน

## การทดสอบและเกณฑ์ยอมรับ

ตรวจด้วยการ build และทดสอบบน emulator/device:

1. แอปเปิดที่ Splash และปุ่มทั้งสองไป Login
2. Login ที่ข้อมูลว่างหรือไม่ตรงแสดง error
3. Login ด้วย `student@example.com` / `password` ไป Main/Home
4. กด Bottom Menu แล้วเปลี่ยนหน้าตามรายการได้
5. ปุ่ม `+` ไปหน้า Create Job
6. กด Back แล้วกลับ route ก่อนหน้าได้ตาม Navigation Component
7. หน้าจอไม่มีข้อความสำคัญหายเพราะ hardcode resource ไม่ครบ
8. `assembleDebug` ผ่าน

## ข้อสมมติที่ล็อกไว้

- ทั้งสองปุ่มบน Splash ไปหน้า Login เดียวกัน
- ระยะแรกใช้ Light Theme เท่านั้น
- Bottom Menu แสดงหลังเข้าสู่ Main เท่านั้น
- ปุ่ม `+` หมายถึงหน้า Create Job
- ข้อมูลทั้งหมดเป็น placeholder/mock จนกว่าจะมี requirement Backend

