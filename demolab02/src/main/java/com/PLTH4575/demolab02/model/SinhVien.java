package com.PLTH4575.demolab02.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import java.lang.String;
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sinhviens")
public class SinhVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "mssv", length = 10, unique = true)
    @NotBlank(message = "Hãy nhập MSSV")
    private int mssv;
    @NotBlank(message = "Họ tên là bắt buộc")
    private String hoten;
    @Column(name = "phone", length = 10, unique = true)
    @Length(min = 10, max = 10, message = "Số điện thoại phải là 10 số")
    private int sdt;
    @Email
    private String email;
    /*
    @Column(name = "cccd",length = 12,unique = true)
    @Length(min = 12, max = 12, message = "Căn cước công dân phải là 12 số")
    private int cccd;

    */
    @DateTimeFormat
    @Min(value = 1/1/2000, message = "Tuổi phải lớn hơn hoặc bằng 18")
    @Max(value = 1/1/2025, message = "Tuổi phải nhỏ hơn hoặc bằng 100")
    private int ngaysinh;
    @Column(nullable = true, length = 64)
    private String thumbnail;
    @ManyToOne
    @JoinColumn
    private Khoa khoa;
}
