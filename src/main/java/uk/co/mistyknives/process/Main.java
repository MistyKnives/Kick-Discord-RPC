package uk.co.mistyknives.process;

import uk.co.mistyknives.kickrpc.KickRPC;
import uk.co.mistyknives.kickrpc.util.Config;

import java.io.IOException;

/**
 * Copyright MistyKnives © 2022-2023
 * <br>
 * ---------------------------------------
 * <br>
 * All Projects are located on my GitHub
 * <br>
 * Please provide credit where due :)
 * <br>
 * ---------------------------------------
 * <br>
 * https://github.com/MistyKnives
 */
public class Main {

    public static void main(String[] args) throws Exception {
        KickRPC kickRPC = new KickRPC();
        kickRPC.setup();
    }
}