package com.practice.equipmentborrowingmanagement1.controller;

import com.practice.equipmentborrowingmanagement1.model.entity.Equipment;
import com.practice.equipmentborrowingmanagement1.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private EquipmentRepository repo;

    // 1. Xem danh sách yêu cầu mượn (Trang chủ Admin)
    @GetMapping("/dashboard")
    public String viewDashboard(Model model) {
        model.addAttribute("requests", repo.findAllBookings());
        return "admin/dashboard";
    }

    // 2. Xem danh sách thiết bị trong kho (Để thực hiện CRUD)
    @GetMapping("/inventory")
    public String viewInventory(Model model) {
        model.addAttribute("items", repo.findAll());
        return "admin/inventory";
    }

    // 3. Mở trang Form để THÊM mới
    @GetMapping("/add-page")
    public String showAddPage(Model model) {
        model.addAttribute("equipment", new Equipment());
        return "admin/edit-form";
    }

    // 4. Mở trang Form để SỬA
    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable int id, Model model) {
        model.addAttribute("equipment", repo.findById(id);
        return "admin/edit-form";
    }

    // 5. Xử lý LƯU (Thêm/Sửa)
    @PostMapping("/save")
    public String save(@ModelAttribute("equipment") Equipment equipment) {
        if (equipment.getId() == 0) {
            repo.add(equipment);
        } else {
            repo.update(equipment);
        }
        return "redirect:/admin/inventory";
    }

    // 6. XÓA thiết bị
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        repo.delete(id);
        return "redirect:/admin/inventory";
    }
}
