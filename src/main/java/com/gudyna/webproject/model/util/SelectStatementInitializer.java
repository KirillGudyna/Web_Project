package com.gudyna.webproject.model.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface SelectStatementInitializer<P, O, I> {
    public void apply(P p, O o, I i) throws NoSuchFieldException, IllegalAccessException, SQLException, NoSuchMethodException, InvocationTargetException;
}
