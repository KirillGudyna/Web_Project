package com.gudyna.webproject.model.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface ObjectInitializer<P, I, S> {
    void apply(P p, I i, S s) throws NoSuchFieldException, IllegalAccessException, SQLException, NoSuchMethodException, InvocationTargetException;
}
