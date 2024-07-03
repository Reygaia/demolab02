package com.PLTH4575.demolab02.service;
import com.PLTH4575.demolab02.model.Product;
import com.PLTH4575.demolab02.model.SinhVien;
import com.PLTH4575.demolab02.repository.SinhVienRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SinhVienService {

    private final SinhVienRepository sinhVienRepository;
    public List<SinhVien> getAllSinhVien() {
        return sinhVienRepository.findAll();
    }
    public Optional<SinhVien> getSinhVienById(Long id) {
        return sinhVienRepository.findById(id);
    }

    public SinhVien addSinhVien(SinhVien sinhVien) {
        return sinhVienRepository.save(sinhVien);
    }
    public SinhVien updateSinhVien(@NotNull SinhVien sinhVien) {
        SinhVien existingSinhVien = sinhVienRepository.findById(sinhVien.getId())
                .orElseThrow(() -> new IllegalStateException("Sinh viên với ID " + sinhVien.getId() + " không hề tồn tại."));
        existingSinhVien.setMssv(sinhVien.getMssv());
        existingSinhVien.setHoten(sinhVien.getHoten());
        existingSinhVien.setSdt(sinhVien.getSdt());
        existingSinhVien.setEmail(sinhVien.getEmail());
        existingSinhVien.setNgaysinh(sinhVien.getNgaysinh());
        existingSinhVien.setThumbnail(sinhVien.getThumbnail());
        existingSinhVien.setKhoa(sinhVien.getKhoa());
        return sinhVienRepository.save(existingSinhVien);
    }
    public void deleteSinhVienById(Long id) {
        if (!sinhVienRepository.existsById(id)) {
            throw new IllegalStateException("Sinh viên với ID" + id + "không hề tồn tại.");
        }
        sinhVienRepository.deleteById(id);
    }
}
