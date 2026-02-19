package br.com.pedro.demo.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false)
    private String senha;

    @CreationTimestamp
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    // Método para definir data de criação antes de persistir
    // @jakarta.persistence.PrePersist
    // protected void onCreate() {
    // dataCriacao = LocalDateTime.now();
    // dataAtualizacao = LocalDateTime.now();
    // } -> não funciona com @CreationTimestamp, pois os dois fazem a mesma coisa.
    // Prefiro o @CreationTimestamp por ser mais curto.

    // Método para atualizar data de modificação antes de atualizar
    // @jakarta.persistence.PreUpdate
    // protected void onUpdate() {
    // dataAtualizacao = LocalDateTime.now();
    // } -> não funciona com @UpdateTimestamp, pois os dois fazem a mesma coisa.
    // Prefiro o @UpdateTimestamp por ser mais curto.
}
