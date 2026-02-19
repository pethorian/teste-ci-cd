package br.com.pedro.demo.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long igdbId; // ID original do jogo no IGDB
    private String nome;
    private String slug;

    @Column(length = 4000)
    private String descricao; // Summary

    @Column(length = 4000)
    private String historia; // Storyline

    private String imgUrl; // Cover URL
    private String urlCapaGrande; // Big Cover URL

    private LocalDate dataLancamento;
    private Integer ano;

    private Double pontuacao;
    private Integer totalAvaliacoes;

    private String desenvolvedora;
    private String publicadora;

    // @JoinTable: Cria a tabela intermediária no banco
    // name: nome da tabela; joinColumns: ID do Game; inverseJoinColumns: ID do
    // É criado uma tabela chamada game_generos com as colunas game_id e genero_id
    // Como eu nao irei adicionar colunas extras na tabela intermediária, eu nao
    // preciso criar uma classe para ela
    @ManyToMany
    @JoinTable(name = "game_generos", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "genero_id"))
    private List<Genero> generos;

    @ManyToMany
    @JoinTable(name = "game_plataformas", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "plataforma_id"))
    private List<Plataforma> plataformas;

    @ManyToMany
    @JoinTable(name = "game_modos_de_jogo", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "modo_id"))
    private List<ModoDeJogo> modosDeJogo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "game_id")
    private List<Screenshot> screenshots;
}
