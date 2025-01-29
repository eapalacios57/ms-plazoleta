package com.pragma.plazoleta.infraestructura.out.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "restaurants")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String name;

    private String  address;

    @Column(name="owner_id")
    private Long ownerId;

    private String phone;

    @Column(name="url_logo")
    private String urlLogo;

    private String nit;
}
