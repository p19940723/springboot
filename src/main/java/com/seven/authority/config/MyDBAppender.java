package com.seven.authority.config;

import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.db.DBAppenderBase;
import ch.qos.logback.core.db.DBHelper;
import com.seven.authority.common.enums.StatusCodeEnums;
import com.seven.authority.common.exception.CommonException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class MyDBAppender extends DBAppenderBase<ILoggingEvent> {

    private static final Method GET_GENERATED_KEYS_METHOD;

    //插入sql
    protected String insertSQL;
    //id
    static final int UUID_INDEX = 1;
    //请求地址
    static final int URL_INDEX = 2;
    //请求地址
    static final int REQUESTIP_INDEX = 3;
    //工程名
    static final int PROJECT_INDEX = 4;
    //类名
    static final int CLASS_INDEX = 5;
    //路径
    static final int CLASSPATH_INDEX = 6;
    //方法名
    static final int METHOD_INDEX = 7;
    //线程名
    static final int THREADNAME_INDEX = 8;
    //信息级别
    static final int MSGLEVEL_INDEX = 9;
    //日志信息
    static final int MSG_INDEX = 10;
    //行数
    static final int CALLER_LINE_INDEX = 11;
    //创建时间
    static final int CREATEDATE_INDEX = 12;

    static final StackTraceElement EMPTY_CALLER_DATA = CallerData.naInstance();

    //生成插入日志的sql
    public static String buildInsertSQL() {
        StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ");
        sqlBuilder.append("log").append(" (");
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
        System.out.println(sqlBuilder.toString());
        return sqlBuilder.toString();
    }

    static {
        // PreparedStatement.getGeneratedKeys() method was added in JDK 1.4
        Method getGeneratedKeysMethod;
        try {
            // the
            getGeneratedKeysMethod = PreparedStatement.class.getMethod("getGeneratedKeys", (Class[]) null);
        } catch (Exception ex) {
            getGeneratedKeysMethod = null;
        }
        GET_GENERATED_KEYS_METHOD = getGeneratedKeysMethod;
    }

    @Override
    public void start() {
        insertSQL = buildInsertSQL();
        super.start();
    }

    @Override
    protected Method getGeneratedKeysMethod() {
        return GET_GENERATED_KEYS_METHOD;
    }

    @Override
    protected String getInsertSQL() {
        return insertSQL;
    }

    @Override
    protected void subAppend(ILoggingEvent event, Connection connection, PreparedStatement insertStatement) throws Throwable {
        bindLoggingMyInfoWithPreparedStatement(insertStatement, event);
        bindLoggingEventWithInsertStatement(insertStatement, event);
        // This is expensive... should we do it every time?
        bindCallerDataWithPreparedStatement(insertStatement, event.getCallerData());
        int updateCount = insertStatement.executeUpdate();
        if (updateCount != 1) {
            addWarn("Failed to insert loggingEvent");
        }
    }

    @Override
    protected void secondarySubAppend(ILoggingEvent eventObject, Connection connection, long eventId) throws Throwable {

    }

    //安全验证及个性化的数据
    private void bindLoggingMyInfoWithPreparedStatement(PreparedStatement stmt, ILoggingEvent event) throws SQLException {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (null == requestAttributes) {
            stmt.setString(URL_INDEX, "系统日志");
            stmt.setString(REQUESTIP_INDEX, "系统日志");
            stmt.setString(PROJECT_INDEX, "系统日志");
        }else {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            stmt.setString(URL_INDEX, request.getRequestURL().toString());
            stmt.setString(REQUESTIP_INDEX, request.getRemoteAddr());
            stmt.setString(PROJECT_INDEX, request.getServletContext().getContextPath());
        }
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        stmt.setString(CREATEDATE_INDEX, df.format(day));
    }

    private void bindLoggingEventWithInsertStatement(PreparedStatement stmt, ILoggingEvent event) throws SQLException {
        stmt.setString(UUID_INDEX, null);
        stmt.setString(MSG_INDEX, event.getFormattedMessage());
        stmt.setString(MSGLEVEL_INDEX, event.getLevel().toString());
        stmt.setString(THREADNAME_INDEX, event.getThreadName());
    }

    private void bindCallerDataWithPreparedStatement(PreparedStatement stmt, StackTraceElement[] callerDataArray) throws SQLException {

        StackTraceElement caller = extractFirstCaller(callerDataArray);

        stmt.setString(CLASS_INDEX, caller.getFileName());
        stmt.setString(CLASSPATH_INDEX, caller.getClassName());
        stmt.setString(METHOD_INDEX, caller.getMethodName());
        stmt.setString (CALLER_LINE_INDEX ,Integer.toString(caller.getLineNumber()));
    }

    private StackTraceElement extractFirstCaller(StackTraceElement[] callerDataArray) {
        StackTraceElement caller = EMPTY_CALLER_DATA;
        if (hasAtLeastOneNonNullElement(callerDataArray)) {
            caller = callerDataArray[0];
        }
        return caller;
    }

    private boolean hasAtLeastOneNonNullElement(StackTraceElement[] callerDataArray) {
        return callerDataArray != null && callerDataArray.length > 0 && callerDataArray[0] != null;
    }

    /* (non-Javadoc)
     * @see ch.qos.logback.core.db.DBAppenderBase#append(java.lang.Object)
     */
    @Override
    public void append(ILoggingEvent eventObject) {
        Connection connection = null;
        try {
            connection = connectionSource.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement insertStatement;
            insertStatement = connection.prepareStatement(getInsertSQL());
            // inserting an event and getting the result must be exclusive
            synchronized (this) {
                subAppend(eventObject, connection, insertStatement);
            }

            // we no longer need the insertStatement
            if (insertStatement != null) {
                insertStatement.close();
            }
            connection.commit();
        } catch (Throwable sqle) {
            addError("problem appending event", sqle);
        } finally {
            DBHelper.closeConnection(connection);
        }
    }
}