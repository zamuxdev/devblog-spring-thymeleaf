package com.devent.blog.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El título es obligatorio")
    @Size(min = 3, max = 150, message = "El título debe tener entre 3 y 150 caracteres")
    @Column(nullable = false,length = 150)
    private String title;
    @NotBlank(message = "El contenido no puede estar vacío")
    @Column(nullable = false,columnDefinition = "TEXT")
    private String content;
    @Column(name = "fecha_creacion")
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    @PrePersist
    protected void onCreate() {
        this.createTime = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        this.updateTime = LocalDateTime.now();
    }
}
