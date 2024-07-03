package com.PLTH4575.demolab02.repository;

import com.PLTH4575.demolab02.model.SinhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SinhVienRepository extends JpaRepository<SinhVien,Long> {
}
