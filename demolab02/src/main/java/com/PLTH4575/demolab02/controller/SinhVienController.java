package com.PLTH4575.demolab02.controller;

import com.PLTH4575.demolab02.model.Khoa;
import com.PLTH4575.demolab02.model.Product;
import com.PLTH4575.demolab02.model.SinhVien;
import com.PLTH4575.demolab02.service.CategoryService;
import com.PLTH4575.demolab02.service.KhoaService;
import com.PLTH4575.demolab02.service.ProductService;
import com.PLTH4575.demolab02.service.SinhVienService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/sinhviens")
public class SinhVienController {
    @Autowired
    private SinhVienService sinhVienService;
    @Autowired
    private KhoaService khoaService;
    @GetMapping
    public String showSinhVienList(Model model) {
        model.addAttribute("sinhviens", sinhVienService.getAllSinhVien());
        return "/sinhviens/sinhviens-list";
    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("sinhvien", new SinhVien());
        model.addAttribute("khoas", khoaService.getAllKhoa()); //Load khoa
        return "/sinhviens/add-sinhvien";
    }
    @PostMapping("/add")
    public String addSinhvien(@Valid SinhVien sinhVien, BindingResult result,@RequestParam("image") MultipartFile imageFile) {
        if (result.hasErrors()) {
            return "/sinhviens/add-sinhvien";
        }
        if(!imageFile.isEmpty()){
            try{
                String imageName = saveImageStatic(imageFile);
                sinhVien.setThumbnail("/images/" +imageName);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        sinhVienService.addSinhVien(sinhVien);
        return "redirect:/sinhviens";
    }
    private String saveImageStatic(MultipartFile image) throws IOException {
        File saveFile = new ClassPathResource("static/images").getFile();
        String fileName = UUID.randomUUID()+ "." + StringUtils.getFilenameExtension(image.getOriginalFilename());
        Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + fileName);
        Files.copy(image.getInputStream(), path);
        return fileName;
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        SinhVien sinhVien = sinhVienService.getSinhVienById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id của sinh viên không hợp lệ:" + id));
        model.addAttribute("sinhVien", sinhVien);
        model.addAttribute("khoas", khoaService.getAllKhoa()); //Load khoa
        return "/sinhviens/update-sinhvien";
    }
    @PostMapping("/update/{id}")
    public String updateSinhVien(@PathVariable Long id, @Valid SinhVien sinhVien,
                                BindingResult result) {
        if (result.hasErrors()) {
            sinhVien.setId(id); // set id to keep it in the form in case of errors
            return "/sinhviens/update-sinhvien";
        }
        sinhVienService.updateSinhVien(sinhVien);
        return "redirect:/sinhviens";
    }
    @GetMapping("/delete/{id}")
    public String deleteSinhVien(@PathVariable Long id) {
        sinhVienService.deleteSinhVienById(id);
        return "redirect:/sinhviens";
    }
}
