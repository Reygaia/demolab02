package com.PLTH4575.demolab02.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import java.util.List;
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String deliveryAddress;
    private String phoneNumber;
    @Email
    private String email;
    private String note;
    private String paymentMethod;
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
}