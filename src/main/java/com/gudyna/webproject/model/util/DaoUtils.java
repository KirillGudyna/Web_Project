package com.gudyna.webproject.model.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DaoUtils {
    private static final Map<String, StatementInitializer<PreparedStatement, Integer, Object, String>> STRING_STATEMENT_UPDATE_INITIALIZER_MAP = new HashMap<>();
    private static final Map<String, SelectStatementInitializer<PreparedStatement, Integer, Object>> STRING_STATEMENT_SELECT_INITIALIZER_MAP = new HashMap<>();
    private static final Map<String, ObjectInitializer<ResultSet, Object, String>> STRING_OBJECT_INITIALIZER_MAP = new HashMap<>();


    private static final String GET = "get";
    private static final String SET = "set";

    static {
        STRING_STATEMENT_UPDATE_INITIALIZER_MAP.put("int", (ps, i, o, s) -> ps.setInt(i + 1, (Integer) o.getClass().getDeclaredMethod(GET + s).invoke(o)));
        STRING_STATEMENT_UPDATE_INITIALIZER_MAP.put("String", (ps, i, o, s) -> ps.setString(i + 1, (String) o.getClass().getDeclaredMethod(GET + s).invoke(o)));
        STRING_STATEMENT_UPDATE_INITIALIZER_MAP.put("Date", (ps, i, o, s) -> ps.setDate(i + 1, (Date) o.getClass().getDeclaredMethod(GET + s).invoke(o)));
        STRING_STATEMENT_UPDATE_INITIALIZER_MAP.put("boolean", (ps,i,o,s) -> ps.setBoolean(i+1,(Boolean) o.getClass().getDeclaredMethod(GET+s).invoke(o)));
    }

    static {
        STRING_STATEMENT_SELECT_INITIALIZER_MAP.put("int", (ps, i, o) -> ps.setInt(i + 1, Integer.parseInt((String) o)));
        STRING_STATEMENT_SELECT_INITIALIZER_MAP.put("String", (ps, i, o) -> ps.setString(i + 1, (String) o));
        STRING_STATEMENT_SELECT_INITIALIZER_MAP.put("Date", (ps, i, o) -> ps.setDate(i + 1, Date.valueOf((String) o)));
        STRING_STATEMENT_SELECT_INITIALIZER_MAP.put("boolean",(ps,i,o)->ps.setBoolean(i+1,Boolean.parseBoolean((String)o)));
    }

    static {
        STRING_OBJECT_INITIALIZER_MAP.put("int", (rs, o, s) -> o.getClass().getDeclaredMethod(SET + toCamelCase(s), int.class).invoke(o, rs.getInt(s)));
        STRING_OBJECT_INITIALIZER_MAP.put("String", (rs, o, s) -> o.getClass().getDeclaredMethod(SET + toCamelCase(s), String.class).invoke(o, rs.getString(s)));
        STRING_OBJECT_INITIALIZER_MAP.put("Date", (rs, o, s) -> o.getClass().getDeclaredMethod(SET + toCamelCase(s), Date.class).invoke(o, rs.getDate(s)));
        STRING_OBJECT_INITIALIZER_MAP.put("boolean",(rs,o,s)->o.getClass().getDeclaredMethod(SET+toCamelCase(s),boolean.class).invoke(o,rs.getBoolean(s)));
    }

    private DaoUtils() {

    }

    public static PreparedStatement getStatement(String query, Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        return connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    }

    public static ResultSet executeStatement(Object data, List<String> list, PreparedStatement statement) throws SQLException {
        try {
            for (int i = 0; i < list.size(); i++) {
                String modifiedName = modifyName(list.get(i));
                String simpleClassName = data.getClass().getDeclaredField(modifiedName).getType().getSimpleName();
                STRING_STATEMENT_UPDATE_INITIALIZER_MAP.get(simpleClassName).apply(statement, i, data, list.get(i));
            }
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            return resultSet;
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new SQLException("Some problems with fields!!", e);
        }
    }

    public static int executeUpdateStatement(Object data, List<String> list, PreparedStatement statement) throws SQLException {
        try {
            for (int i = 0; i < list.size(); i++) {
                String modifiedName = modifyName(list.get(i));
                String simpleClassName = data.getClass().getDeclaredField(modifiedName).getType().getSimpleName();
                STRING_STATEMENT_UPDATE_INITIALIZER_MAP.get(simpleClassName).apply(statement, i, data, list.get(i));
            }
            return statement.executeUpdate();
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new SQLException("Some problems with fields!!", e);
        }
    }

    public static int executeDeleteStatement(int id, PreparedStatement statement) throws SQLException {
        statement.setInt(1, id);
        return statement.executeUpdate();

    }

    public static ResultSet executeSelectStatement(List<String> list, PreparedStatement statement) throws SQLException {
        try {
            for (int i = 0; i < list.size(); i++) {
                String[] tmp = list.get(i).split(":");
                STRING_STATEMENT_SELECT_INITIALIZER_MAP.get(tmp[1]).apply(statement, i, tmp[0]);
            }
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet;
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new SQLException("Some problems with fields!!", e);
        }
    }

    public static <T> List<T> initObjectTypeList(List<T> typeList, Class<T> type, ResultSet resultSet) throws SQLException {
        try {
            do {
                typeList.add(initObjectType(type.getConstructor().newInstance(), resultSet));
            } while (resultSet.next());
            return typeList;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new SQLException("Some problems with fields!!", e);
        }
    }

    public static <T> T initObjectType(T type, ResultSet resultSet) throws SQLException {
        try {
            int count = resultSet.getMetaData().getColumnCount();
            for (int i = 0; i < count; i++) {
                String columnName = resultSet.getMetaData().getColumnName(i + 1);
                String fieldTypeSimpleName = type.getClass().getDeclaredField(modifyName(toCamelCase(columnName))).getType().getSimpleName();
                STRING_OBJECT_INITIALIZER_MAP.get(fieldTypeSimpleName).apply(resultSet, type, columnName);
            }
            return type;
        } catch (NoSuchMethodException | NoSuchFieldException | IllegalAccessException | InvocationTargetException e) {
            throw new SQLException("Some problems with fields!!", e);
        }
    }

    public static void commitConnection(Connection connection) throws SQLException {
        try {
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }

    private static String modifyName(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    private static String toCamelCase(String str) {
        return Arrays.stream(str.split("_"))
                .map(token -> token.substring(0, 1).toUpperCase() + token.substring(1))
                .collect(Collectors.joining());
    }



}
