package br.com.pdrprojects.onebasketball.partida;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
public class Partida {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    String team1;

    @NotBlank
    String team2;

    @Positive(message="{partida.points.positive}")
    Integer pontosTeam1;

    @Positive(message="{partida.points.positive}")
    Integer pontosTeam2;

}
