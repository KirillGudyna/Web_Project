package com.gudyna.webproject.model.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface StatementInitializer<P, I, O, S> {
    void apply(P p, I i, O o, S s) throws NoSuchFieldException, IllegalAccessException, SQLException, NoSuchMethodException, InvocationTargetException;
}
