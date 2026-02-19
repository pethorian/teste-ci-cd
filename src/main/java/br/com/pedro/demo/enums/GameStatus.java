package br.com.pedro.demo.enums;

public enum GameStatus {
    VAI_JOGAR("Vai Jogar", "Jogo planejado para jogar no futuro"),
    JOGANDO("Jogando", "Jogo atualmente em progresso"),
    ZERADO("Zerado", "Jogo completado/finalizado"),
    ABANDONADO("Abandonado", "Jogo iniciado mas não será continuado"),
    PAUSADO("Pausado", "Jogo temporariamente pausado");

    private final String descricao;
    private final String detalhes;

    GameStatus(String descricao, String detalhes) {
        this.descricao = descricao;
        this.detalhes = detalhes;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDetalhes() {
        return detalhes;
    }
}
