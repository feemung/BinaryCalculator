
package com.feemung.binarycalculator.log;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.util.Log;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Logger {

    private static Hashtable<String, Logger> loggerTable = new Hashtable<String, Logger>();
    /**
     * log tag
     */
    private String tagName = "binaryCalculator";// tag name
    /**
     * debug or not
     */
    private static boolean displayFlag = true;

    private static int logLevel = Log.VERBOSE;

//    private static int logLevel = Log.WARN;


    private Lock lock;
    private int id=0;

    private Logger(String name)
    {
        tagName = name;
        lock = new ReentrantLock();
    }

    public static Logger getLogger()
    {
        String defaultTagName = "MoGuLogger";
        Logger classLogger = (Logger) loggerTable.get(defaultTagName);
        if (classLogger == null)
        {
            classLogger = new Logger(defaultTagName);
            loggerTable.put(defaultTagName, classLogger);
        }
        return classLogger;
    }

    public static Logger getLogger(String className)
    {
        Logger classLogger = (Logger) loggerTable.get(className);
        if (classLogger == null)
        {
            classLogger = new Logger(className);
            loggerTable.put(className, classLogger);
        }
        return classLogger;
    }

    public static Logger getLogger(Class<?> key)
    {
        String className = key.getName();
        Logger classLogger = (Logger) loggerTable.get(className);
        if (classLogger == null)
        {
            classLogger = new Logger(className);
            loggerTable.put(className, classLogger);
        }
        return classLogger;
    }

    private String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();

        if (sts == null) {
            return null;
        }

        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }

            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }

            if (st.getClassName().equals(this.getClass().getName())) {
                continue;
            }

            return "[" + st.getFileName() + ":" + st.getLineNumber() + "]";
        }

        return null;
    }

    private String createMessage(String msg) {
        String functionName = getFunctionName();
        long threadId = Thread.currentThread().getId();
        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
        String message = (functionName == null ? msg : (functionName + " - " + String.valueOf(threadId) + " - " +  msg));
        return currentTime + " - " +  message;
    }

    /**
     * log.i
     */
    public void i(String format, Object... args) {
        if (displayFlag && logLevel <= Log.INFO) {
            lock.lock();
            String message = createMessage(getInputString(format, args));
            Log.i(tagName, message);
            sendLog(Log.INFO,tagName,message);

            lock.unlock();
        }
           }

    /**
     * log.v
     */
    public void v(String format, Object... args) {
        if (displayFlag && logLevel <= Log.VERBOSE) {
            lock.lock();
            String message = createMessage(getInputString(format, args));
            Log.v(tagName, message);
            sendLog(Log.VERBOSE,tagName,message);

            lock.unlock();
        }
        sendLog(Log.VERBOSE,tagName,createMessage(getInputString(format, args)));
    }

    /**
     * log.d
     */
    public void d(String format, Object... args) {
        if (displayFlag && logLevel <= Log.DEBUG) {
            lock.lock();
            String message = createMessage(getInputString(format, args));
            Log.d(tagName, message);
            sendLog(Log.DEBUG,tagName,message);

            lock.unlock();
        }
        sendLog(Log.DEBUG,tagName,createMessage(getInputString(format, args)));
    }

    /**
     * log.e
     */
    public void e(String format, Object... args) {
        if (displayFlag && logLevel <= Log.ERROR) {
            lock.lock();
            String message = createMessage(getInputString(format, args));
            Log.e(tagName, message);
            sendLog(Log.ERROR,tagName,message);
            lock.unlock();
        }


    }
    
    private String getInputString(String format, Object... args) {
    	if (format == null) {
    		return "null log format";
    	}
    	
    	return String.format(format, args);
    }

    /**
     * log.error
     */
    public void error(Exception e) {
        if (displayFlag && logLevel <= Log.ERROR) {
            StringBuffer sb = new StringBuffer();
            lock.lock();
            String name = getFunctionName();
            StackTraceElement[] sts = e.getStackTrace();

            if (name != null) {
                sb.append(name + " - " + e + "\r\n");
            } else {
                sb.append(e + "\r\n");
            }
            if (sts != null && sts.length > 0) {
                for (StackTraceElement st : sts) {
                    if (st != null) {
                        sb.append("[ " + st.getFileName() + ":" + st.getLineNumber() + " ]\r\n");
                    }
                }
            }
            Log.e(tagName, sb.toString());
            sendLog(Log.VERBOSE, tagName, sb.toString());
            lock.unlock();
        }

    }

    /**
     * log.d
     */
    public void w(String format, Object... args) {
        if (displayFlag && logLevel <= Log.WARN) {
            lock.lock();
            String message = createMessage(getInputString(format, args));
            Log.w(tagName, message);
            sendLog(Log.WARN,tagName,message);
            lock.unlock();
        }
    }

    /**
     * set display flag
     */
    public static void setFlag(boolean d) {
        displayFlag = d;
    }

    /**
     * set log level
     */

    public void setLevel(int l) {
        lock.lock();
        logLevel = l;
        lock.unlock();
    }
    /**
     * send to DebugHelper(app)
     * */
     private void sendLogs(int priority,String tag,String msg){


         id++;
     }
    private void sendLog(int priority,String tag,String msg){
/*
        ContentResolver contentResolver = IMEntrance.getInstance().getContext(). getContentResolver();
        Uri insertUri = Uri.parse("content://com.feemung.DebugHelper.provider/logger");

        ContentValues values = new ContentValues();
        values.put("appName", "app");
        values.put("id", id);
        values.put("priority",priority);
        values.put("tag",tag);
        values.put("msg",msg);
        Uri uri = contentResolver.insert(insertUri, values);
        id++;*/
    }
 }
