package com.company.test;

import com.company.Game;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    Game gg = new Game();

    @Test
    public void gameTest() {
        String input = "взять ведро\nидти запад\nвзять цепь\nидти восток\nидти вверх\nиспользовать ведро на горелка\n" +
                "идти вниз\nидти запад\nиспользовать ведро на колодец\nидти восток\nиспользовать ведро на волшебник";
        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);


        gg.game();
        assertEquals(true, gg.isWin());
    }
}
