package com.BinhAn.View;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    private static final ContactManagement contactManagement = new ContactManagement();
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_RESET = "\u001B[0m";

    public static void main(String[] args) {
        int choice;
        boolean flag = true;
        while (flag) {
            try {
                do {
                    System.out.println();
                    System.out.println("ỨNG DỤNG QUẢN LÝ DANH BẠ");
                    System.out.println(" 1. HIỂN THỊ DANH SÁCH");
                    System.out.println("2. THÊM LIÊN HỆ MỚI");
                    System.out.println("3. XÓA LIÊN HỆ");
                    System.out.println("4. CẬP NHẬP LIÊN HỆ");
                    System.out.println("5. TÌM KIẾM LIÊN HỆ");
                    System.out.println("6. ĐỌC TỪ FILE");
                    System.out.println("7. GHI VÀO FILE");
                    System.out.println("0. THOÁT CHƯƠNG TRÌNH");
                    System.out.print("Nhập vào lựa chọn của bạn: ");
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            System.out.println("Hiển thị danh sách");
                            int size = contactManagement.size();
                            if (size == 0) {
                                System.out.println("Danh sách rỗng");
                            } else {
                                contactManagement.displayAll();
                            }
                            break;
                        case 2:
                            System.out.println("Thêm liên hệ mớI");
                            Contact contact = inputContac();
                            contactManagement.addContact(contact);
                            break;
                        case 3:
                            System.out.println("Xóa liên hệ");
                            String phoneNumberDelete;
                            System.out.println("Nhập vào số điện thoại cần xóa: ");
                            phoneNumberDelete = scanner.nextLine();
                            int choice1;
                            System.out.println("1. Xác nhận xoá");
                            System.out.println("0. Quay lại");
                            System.out.print("Nhập vào lựa chọn của bạn: ");
                            choice1 = Integer.parseInt(scanner.nextLine());
                            if (choice1 == 1) {
                                boolean isDeleted = contactManagement.deleteContact(phoneNumberDelete);
                                if (isDeleted) {
                                    System.out.println("Xóa thành công");
                                } else {
                                    System.out.println("Lỗi do SDT không tồn tại");
                                }
                            }
                            break;
                        case 4:
                            showUpdateContact();
                            break;
                        case 5:
                            System.out.println("Tìm kiếm danh bạ");
                            System.out.println("Nhập vào số điện thoại của liên hệ cần tìm kiếm: ");
                            String phoneNumber = scanner.nextLine();
                            int index = contactManagement.findContactByPhoneNumber(phoneNumber);
                            if (index != -1) {
                                System.out.println(" Thông tin liên hệ cần tìm là:");
                                System.out.println(contactManagement.getByPhoneNumber(phoneNumber));
                            } else {
                                System.out.println("Không tìm thấy danh bạ");
                            }
                            break;
                        case 6:
                            ArrayList<Contact> contactArrayList = contactManagement.readFileCSV("contact.csv");
                            contactArrayList.forEach(System.out::println);
                            break;
                        case 7:
                            contactManagement.writeFileCSV(contactManagement.getContactList(),"contact.csv");
                            break;
                        case 0:
                            flag = false;
                            break;
                        default:
                            System.out.println(TEXT_RED + "NHẬP TRONG KHOẢNG TỪ 0 ĐẾN 7" + TEXT_RESET);
                            break;
                    }
                } while (choice != 0);
            } catch (Exception e) {
                System.out.println(TEXT_RED + " XIN VUI LÒNG NHẬP SỐ" + TEXT_RESET);
            }
        }
    }

    private static void showUpdateContact() {
        System.out.println("Cập nhập liên hệ mới");
        System.out.println("Nhập vào số điện thoại của liên hệ cần cập nhập: ");
        String phoneNumberUpdate = scanner.nextLine();
        int index = contactManagement.findContactByPhoneNumber(phoneNumberUpdate);
        if (index != -1) {
            Contact contact1 = inputContac();
            contactManagement.updateContact(phoneNumberUpdate, contact1);
        } else {
            System.out.println("Cập nhập bị lỗi do không tìm thấy SDT");
        }
    }

    private static Contact inputContac() {
        System.out.println("Nhập họ tên:");
        String name = scanner.nextLine();

        String phoneNumber;
        do {
            System.out.print("Nhập số điện thoại: ");
            System.out.println("10 SỐ VÀ BẮT ĐẦU BẰNG SỐ 0");
            phoneNumber = scanner.nextLine();
        } while (contactManagement.validatePhoneNumber(phoneNumber));

        System.out.println("Nhập tên nhóm:");
        String group = scanner.nextLine();

        String gender;
        do {
            System.out.println("Nhập giới tính:");
            System.out.println("Chỉ được chọn NAM hoặc NU");
            gender = scanner.nextLine();
        } while (!gender.equals("NAM") && !gender.equals("NU"));

        System.out.println("Nhập địa chỉ:");
        String address = scanner.nextLine();
        System.out.println("Nhập ngày sinh:");
        String dateOfBirth = scanner.nextLine();

        String email;
        do {
            System.out.print("Nhập email: ");
            System.out.println("VD: abcxyz@gmail.com");
            email = scanner.nextLine();
        } while (contactManagement.validateEmail(email));
        Contact contact = new Contact(phoneNumber, group, name, gender, address, dateOfBirth, email);
        return contact;
    }
}