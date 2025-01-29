package com.pragma.plazoleta.infraestructura.out.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dishes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DishEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name="id_category")
    private CategoryEntity categoryId;

    private String description;

    private Long price;

    @ManyToOne
    @JoinColumn(name="id_restaurant")
    private RestaurantEntity restaurantId;

    @Column(name="url_image")
    private String urlImage;

    private Boolean active;
}
