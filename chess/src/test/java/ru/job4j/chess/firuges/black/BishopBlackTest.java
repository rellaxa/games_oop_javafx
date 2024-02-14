package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class BishopBlackTest {

    @Test
    public void whenPositionC8isCellC8() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        Cell result = bishopBlack.position();
        assertThat(result).isEqualTo(Cell.C8);
    }

    @Test
    public void whenCopyFromF8ToC5() {
        BishopBlack bishopBlack = new BishopBlack(Cell.F8);
        Figure result = bishopBlack.copy(Cell.C5);
        assertThat(result.position()).isEqualTo(Cell.C5);
    }

    @Test
    public void whenWayFromC1ToG5() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        Cell[] result = bishopBlack.way(Cell.G5);
        Cell[] expected = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenIsNotDiagonal() {
        Cell source = Cell.E6;
        Cell dest = Cell.C2;
        BishopBlack bishopBlack = new BishopBlack(source);
        ImpossibleMoveException exception = assertThrows(
                ImpossibleMoveException.class,
                () -> {
                    bishopBlack.way(dest);
                }
        );
        assertThat(exception.getMessage()).isEqualTo(
                String.format("Could not move by diagonal from %s to %s", source, dest)
        );
    }

    @Test
    public void whenIsDiagonal() {
        Cell source = Cell.E1;
        Cell dest = Cell.B4;
        BishopBlack bishopBlack = new BishopBlack(source);
        assertThat(bishopBlack.isDiagonal(source, dest)).isTrue();
    }
}