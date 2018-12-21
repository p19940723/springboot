package com.seven.authority.common.utils.logs;

/**
 * @author seven
 */
public class LogsSqlBuilder {

    /**
     * 生成插入日志的sql
     *
     * @return
     */
    public static String buildInsertSQL() {
        StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ");
        sqlBuilder.append("sys_log").append(" (");
        sqlBuilder.append("id").append(", ");
        sqlBuilder.append("url").append(", ");
        sqlBuilder.append("request_ip").append(", ");
        sqlBuilder.append("project").append(", ");
        sqlBuilder.append("class").append(", ");
        sqlBuilder.append("classpath").append(", ");
        sqlBuilder.append("method").append(", ");
        sqlBuilder.append("thread_name").append(", ");
        sqlBuilder.append("msg_level").append(", ");
        sqlBuilder.append("msg").append(", ");
        sqlBuilder.append("line").append(", ");
        sqlBuilder.append("create_date").append(") ");
        sqlBuilder.append("VALUES (?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?)");
        return sqlBuilder.toString();
    }
}