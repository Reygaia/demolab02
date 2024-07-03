package com.PLTH4575.demolab02.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Tên bắt buộc phải có")
    private String name;
    private double price;
    @Column(nullable = true, length = 64)
    private String thumbnail;
    private String description;
    private String publisher;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}