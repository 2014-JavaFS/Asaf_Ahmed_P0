package com.revature.util;

import io.javalin.Javalin;

public interface Controller {
    void registerRoutes(Javalin app);

}
