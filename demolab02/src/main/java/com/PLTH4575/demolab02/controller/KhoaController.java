package com.PLTH4575.demolab02.controller;
import com.PLTH4575.demolab02.repository.KhoaRepository;
import com.PLTH4575.demolab02.service.KhoaService;
import com.PLTH4575.demolab02.model.Category;
import com.PLTH4575.demolab02.model.Khoa;
import com.PLTH4575.demolab02.service.CategoryService;
import com.PLTH4575.demolab02.service.SinhVienService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import java.io.IOException;
@Controller
@RequiredArgsConstructor
public class KhoaController {
    @Autowired
    private final KhoaService khoaService;
    @GetMapping("/khoas/add")
    public String showAddForm(Model model) {
        model.addAttribute("khoa", new Khoa());
        return "/khoas/add-khoa";
    }
    @PostMapping("/khoas/add")
    public String addKhoa(@Valid Khoa khoa, BindingResult result) {
        if (result.hasErrors()) {
            return "/khoas/add-khoa";
        }
        khoaService.addKhoa(khoa);
        return "redirect:/khoas";
    }
    @GetMapping("/khoas")
    public String listKhoas(Model model) {
        List<Khoa> khoas = khoaService.getAllKhoa();
        model.addAttribute("khoas", khoas);
        return "/khoas/khoas-list";
    }
    @GetMapping("/khoas/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Khoa khoa = khoaService.getKhoaById(id).orElseThrow(() -> new IllegalArgumentException("Id của khoa không hợp lệ:" + id));
        model.addAttribute("khoa", khoa);
        return "/khoas/update-khoa";
    }
    // POST request to update khoa
    @PostMapping("/khoas/update/{id}")
    public String updateKhoa(@PathVariable("id") Long id, @Valid Khoa khoa,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            khoa.setId(id);
            return "/khoas/update-khoa";
        }
        khoaService.updateKhoa(khoa);
        model.addAttribute("khoa", khoaService.getAllKhoa());
        return "redirect:/khoas";
    }
    // GET request for deleting khoa
    @GetMapping("/khoas/delete/{id}")
    public String deleteKhoa(@PathVariable("id") Long id, Model model) {
        Khoa khoa = khoaService.getKhoaById(id).orElseThrow(() -> new IllegalArgumentException("Id của khoa không hợp lệ:" + id));
        khoaService.deleteKhoaById(id);
        model.addAttribute("khoa", khoaService.getAllKhoa());
        return "redirect:/khoas";
    }
}
