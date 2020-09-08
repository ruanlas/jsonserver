package com.data.jsonserver.service.router;

public interface RouterServiceInterface<F> {
    public F getRoutes();
    public F createRoutes(F f);
}
