package dev.bstk.gameokkmatematica.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
/// @Entity
@NoArgsConstructor
@AllArgsConstructor
/// @Table(name = "DESAFIO_TENTATIVA_RESPOSTA")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DesafioTentativaResposta {

    /// @Id
    /// @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    /// @JoinColumn(name = "USUARIO_ID")
    /// @ManyToOne(fetch = FetchType.LAZY)
    /// private Usuario usuario;

    /// @Column(name = "FATOR_A")
    private int fatorA;

    /// @Column(name = "FATOR_B")
    private int fatorB;

    /// @Column(name = "RESULTADO")
    private int resultado;

    /// @Column(name = "CORRETA")
    private boolean correta;

    /// @Column(name = "OPERACAO")
    private String operacao;
}
