package br.com.pedro.demo.entity;

import br.com.pedro.demo.enums.GameStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@SQLRestriction("ativo = true")
@Table(name = "user_games", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "game_id" }))
// UserGame é uma tabela associativa entre User e Game, trata do relacionamento
// entre essas tabelas
// e guarda informações específicas do usuário sobre o jogo
public class UserGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento com User
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Relacionamento com Game
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    // Status do jogo para este usuário
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GameStatus status;

    // Dados de progresso/tempo
    @Column(name = "horas_jogadas")
    private Integer horasJogadas;

    @Column(name = "porcentagem_completo")
    private Integer porcentagemCompleto; // 0-100

    // Avaliação pessoal do usuário
    @Column(name = "nota_pessoal")
    private Double notaPessoal; // 0.0 - 10.0

    @CreationTimestamp
    @Column(name = "data_adicionado", nullable = false)
    private LocalDateTime dataAdicionado;

    @UpdateTimestamp
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_termino")
    private LocalDate dataTermino;

    @Column(name = "data_ultima_sessao")
    private LocalDateTime dataUltimaSessao;

    // Flags e metadados
    private Boolean favorito;

    private Boolean ativo;

    @Column(length = 1000)
    private String observacoes;

    @Column(name = "numero_gameplays")
    private Integer numeroGameplays; // Quantas vezes jogou

    // Ao criar um UserGame, favorito é iniciado como false e o numero de vezes que
    // foi jogado também
    @jakarta.persistence.PrePersist
    protected void onCreate() {
        if (favorito == null) {
            favorito = false;
        }
        if (numeroGameplays == null) {
            numeroGameplays = 0;
        }
        if (ativo == null) {
            ativo = true;
        }
    }
}
