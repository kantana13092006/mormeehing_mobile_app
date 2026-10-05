package com.example.mormeehing.feature.jobs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ExampleJobListings {

    private ExampleJobListings() {
    }

    public static List<JobListing> create() {
        List<JobListing> listings = new ArrayList<>();
        listings.add(new JobListing(
                "พนักงานร้านกาแฟ",
                "Café Amazon",
                "100 บาท/ชม.",
                1,
                "จันทร์ - ศุกร์ • 17:00 - 21:00",
                "เปิดรับสมัคร",
                "https://images.unsplash.com/photo-1554118811-1e0d58224f24?auto=format&fit=crop&w=200&h=200&q=80",
                JobListing.Category.FOOD, 100, 4, true));
        listings.add(new JobListing(
                "พนักงานร้านค้า",
                "7-Eleven สาขามหาวิทยาลัย",
                "90 บาท/ชม.",
                2,
                "เสาร์ - อาทิตย์ • 09:00 - 16:00",
                "เปิดรับสมัคร",
                "https://images.unsplash.com/photo-1604719312566-8912e9c8a213?auto=format&fit=crop&w=200&h=200&q=80",
                JobListing.Category.RETAIL, 90, 7, false));
        listings.add(new JobListing(
                "ผู้ช่วยงานอีเวนต์",
                "Event Plus",
                "120 บาท/ชม.",
                3,
                "เสาร์ • 10:00 - 18:00",
                "เปิดรับสมัคร",
                "https://images.unsplash.com/photo-1492684223066-81342ee5ff30?auto=format&fit=crop&w=200&h=200&q=80",
                JobListing.Category.OTHER, 120, 8, false));
        listings.add(new JobListing(
                "แอดมินตอบแชต",
                "Good Food",
                "110 บาท/ชม.",
                4,
                "จันทร์ - ศุกร์ • 18:00 - 22:00",
                "เปิดรับสมัคร",
                "https://images.unsplash.com/photo-1556742049-0cfed4f6a45d?auto=format&fit=crop&w=200&h=200&q=80",
                JobListing.Category.FOOD, 110, 4, true));
        listings.add(new JobListing(
                "ติวเตอร์คณิตศาสตร์",
                "Smart Tutor",
                "200 บาท/ชม.",
                5,
                "อังคาร - พฤหัส • 16:00 - 19:00",
                "ใกล้เต็ม",
                "https://images.unsplash.com/photo-1503676260728-1c00da094a0b?auto=format&fit=crop&w=200&h=200&q=80",
                JobListing.Category.OTHER, 200, 3, true));
        listings.add(new JobListing(
                "พนักงานส่งเอกสาร",
                "Campus Express",
                "130 บาท/ชม.",
                6,
                "จันทร์ - ศุกร์ • 08:00 - 12:00",
                "เปิดรับสมัคร",
                "https://images.unsplash.com/photo-1586528116311-ad8dd3c8310d?auto=format&fit=crop&w=200&h=200&q=80",
                JobListing.Category.OTHER, 130, 4, true));
        listings.add(new JobListing(
                "ผู้ช่วยถ่ายภาพ",
                "M Studio",
                "150 บาท/ชม.",
                7,
                "อาทิตย์ • 10:00 - 17:00",
                "เปิดรับสมัคร",
                "https://images.unsplash.com/photo-1516035069371-29a1b244cc32?auto=format&fit=crop&w=200&h=200&q=80",
                JobListing.Category.OTHER, 150, 7, false));
        listings.add(new JobListing(
                "พนักงานคลังสินค้า",
                "Daily Mart",
                "105 บาท/ชม.",
                8,
                "เสาร์ - อาทิตย์ • 08:00 - 15:00",
                "ใกล้เต็ม",
                "https://images.unsplash.com/photo-1586528116493-da8b9a0d1e2b?auto=format&fit=crop&w=200&h=200&q=80",
                JobListing.Category.RETAIL, 105, 7, false));
        listings.add(new JobListing(
                "ผู้ช่วยดูแลเพจ",
                "Local Brand",
                "140 บาท/ชม.",
                9,
                "จันทร์ - ศุกร์ • 19:00 - 22:00",
                "เปิดรับสมัคร",
                "https://images.unsplash.com/photo-1556761175-b413da4baf72?auto=format&fit=crop&w=200&h=200&q=80",
                JobListing.Category.OTHER, 140, 3, true));
        listings.add(new JobListing(
                "พนักงานต้อนรับ",
                "The Study Space",
                "100 บาท/ชม.",
                10,
                "ทุกวัน • 09:00 - 13:00",
                "เปิดรับสมัคร",
                "https://images.unsplash.com/photo-1497366811353-6870744d04b2?auto=format&fit=crop&w=200&h=200&q=80",
                JobListing.Category.OTHER, 100, 4, true));
        return Collections.unmodifiableList(listings);
    }
}
