package dev.bstk.gameokk.plataforma.jogos.domain;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JogoService {

    public List<Jogo> jogos() {
        List<Jogo> jogos = new ArrayList<>();
        jogos.add(new Jogo("jogo-da-velha", "jogo da velha", "O jogo da velha ou jogo do galo ou três em linha é um jogo e/ou passatempo popular. É um jogo de regras extremamente simples, que não traz grandes dificuldades para seus jogadores e é facilmente aprendido.", "https://cdn3.iconfinder.com/data/icons/brain-games/128/Tic-Tac-Toe-Game-red.png"));
        jogos.add(new Jogo("jogo-da-forca", "Jogo da Forca", "O jogo da forca é um jogo em que o jogador tem que acertar qual é a palavra proposta, tendo como dica o número de letras e o tema ligado à palavra. A cada letra errada, é desenhado uma parte do corpo do enforcado.", "https://cdn3.iconfinder.com/data/icons/brain-games/128/Hangman-Game-red.png"));
        jogos.add(new Jogo("jogo-da-memoria", "Jogo da Memória", "O jogo da memória é um clássico jogo formado por peças que apresentam uma figura em um dos lados. Cada figura se repete em duas peças diferentes.", "https://www.noas.com.br/jogos/jogo-da-memoria/img/titulo.png"));
        jogos.add(new Jogo("matematica-play", "Matemática Play", "Jogos com números para crianças, para rever a numeração online, juntar números em ordem, encontrá-los escondidos na cena, aprender números em inglês, números que fazem parte de frações, em dominó, seqüências matemáticas, ou jogar bingo educativo…", "https://cdn2.iconfinder.com/data/icons/shopping-and-commerce-43/66/Calculations-512.png"));

        return jogos;
    }
}
