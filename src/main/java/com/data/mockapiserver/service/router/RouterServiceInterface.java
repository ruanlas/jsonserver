package com.data.mockapiserver.service.router;

public interface RouterServiceInterface<F> {
    public F getRoutes();
    public F createRoutes(F f);
}
