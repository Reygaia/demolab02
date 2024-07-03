package com.PLTH4575.demolab02.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "khoas")
public class Khoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "makhoa", length = 7, unique = true)
    private int makhoa;
    @NotBlank(message = "Tên khoa là bắt buộc")
    private String tenkhoa;
    @NotBlank(message = "Trường là bắt buộc")
    private String truong;
}
