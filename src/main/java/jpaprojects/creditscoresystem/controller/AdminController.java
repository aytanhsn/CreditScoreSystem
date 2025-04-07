package jpaprojects.creditscoresystem.controller;

import jpaprojects.creditscoresystem.service.AdminService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/credit")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/{customerId}/update")
    public String updateCreditScore(@PathVariable Long customerId, @RequestParam Double newScore) {
        return adminService.updateCreditScore(customerId, newScore);
    }
}
