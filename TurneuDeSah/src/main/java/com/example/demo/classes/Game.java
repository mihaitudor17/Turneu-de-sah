package com.example.demo.classes;

import javax.persistence.*;

@Entity
@Table(name="game")
public class Game {
    @Id
    @SequenceGenerator(
            name = "game_sequence",
            sequenceName = "game_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "game_sequence"
    )
    private Long id;
    @ManyToOne
    private Person white;
    @ManyToOne
    private Person black;
    @ManyToOne
    private Person winner;
    @ManyToOne
    private Tournament tournament;

    public Game(){}

    public Game(Person white, Person black, Person winner, Tournament tournament) {
        this.white = white;
        this.black = black;
        this.winner = winner;
        this.tournament = tournament;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getWhite() {
        return white;
    }

    public void setWhite(Person white) {
        this.white = white;
    }

    public Person getBlack() {
        return black;
    }

    public void setBlack(Person black) {
        this.black = black;
    }

    public Person getWinner() {
        return winner;
    }

    public void setWinner(Person winner) {
        this.winner = winner;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", white=" + white +
                ", black=" + black +
                ", winner=" + winner +
                ", tournament=" + tournament +
                '}';
    }
}
