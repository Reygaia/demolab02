package com.PLTH4575.demolab02.service;

import com.PLTH4575.demolab02.model.Khoa;
import com.PLTH4575.demolab02.repository.KhoaRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class KhoaService {
    private final KhoaRepository khoaRepository;
    /**
     * Retrieve all categories from the database.
     * @return a list of categories
     */
    public List<Khoa> getAllKhoa() {
        return khoaRepository.findAll();
    }
    /**
     * Retrieve a category by its id.
     * @param id the id of the category to retrieve
     * @return an Optional containing the found category or empty if not found
     */
    public Optional<Khoa> getKhoaById(Long id) {
        return khoaRepository.findById(id);
    }
    /**
     * Add a new category to the database.
     * @param category the category to add
     */
    public Khoa addKhoa(Khoa khoa) {
        return khoaRepository.save(khoa);
    }
    /**
     * Update an existing category.
     * @param category the category with updated information
     */
    public void updateKhoa(@NotNull Khoa khoa) {
        Khoa existingKhoa = khoaRepository.findById(khoa.getId())
                .orElseThrow(() -> new IllegalStateException("Khoa với ID " +
                        khoa.getId() + " không hề tồn tại."));
        existingKhoa.setMakhoa(khoa.getMakhoa());
        existingKhoa.setTenkhoa(khoa.getTenkhoa());
        existingKhoa.setTruong(khoa.getTruong());
        khoaRepository.save(existingKhoa);
    }
    public void deleteKhoaById(Long id) {
        if (!khoaRepository.existsById(id)) {
            throw new IllegalStateException("Khoa với ID " + id + " không tồn tại.");
        }
        khoaRepository.deleteById(id);
    }
}
