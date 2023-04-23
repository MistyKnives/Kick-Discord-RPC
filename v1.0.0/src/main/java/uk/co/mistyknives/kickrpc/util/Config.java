package uk.co.mistyknives.kickrpc.util;

import lombok.Data;

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
@Data
public class Config {

    private String username;

    private boolean displayElapsed;

    private String host, password;

    private int port;
}
