package com.sas.pdg.mrr.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExceptionUtil
{
  /**
   * Return the Java class calling stack for trace.
   */
  public static String getStackTrace(Throwable p_throwable)
  {
    if (p_throwable == null)
    {
      return null;
    }
    StringWriter out = new StringWriter();
    PrintWriter writer = new PrintWriter(out);
    p_throwable.printStackTrace(writer);
    out.flush();

    return out.toString();
  }

  /**
   * Return the unique ID for the exception.
   */
  public static String getExceptionID()
  {
    return "" + System.currentTimeMillis();
  }

  /**
   * Return message by key, the message can be defined in file or database.
   */
  public static String getMessage(final String p_messageKey)
  {
    String language = "en";
    return getMessage(language, p_messageKey);
  }

  /**
   * Return message by key, the message can be defined in file or database.
   */
  public static String getMessage(final String p_messageKey, final Object[] p_parameter)
  {
    String language = "en";
    return getMessage(language, p_messageKey, p_parameter);
  }

  /**
   * <pre>
   * Format message with parameter
   * For example,
   * Object[] arguments = {
   *     new Integer(7)
   * };
   *
   * String result = formatMessage(
   *     " there was a disturbance in the Force on planet {0}."
   *    , arguments);
   * 
   * String result = formatMessage(
   *     " there was a disturbance in the Force on planet %0%."
   *    , arguments);
   *
   * output: there was a disturbance in the Force on planet 7.
   * </pre>
   */
  private static String formatMessage(final String p_messageKey, final Object[] p_parameter)
  {
    StringBuffer sb = new StringBuffer();
    char[] exp = p_messageKey.toCharArray();
    int pos = 0;
    int maxLen = exp.length;
    while (pos < maxLen)
    {
      if (exp[pos] == '%')
      {
        int len = getPos(exp, pos, '%');
        if (len > 0)
        {
          String num = new String(exp, pos + 1, len);
          int paramNumber = Integer.parseInt(num.trim());
          Object parameter = getParameter(p_parameter, paramNumber);
          if (parameter == null)
          {
            parameter = new String("%?%");
          }
          sb.append(parameter);
          pos = pos + len + 2;
          continue;
        }
      }
      if (exp[pos] == '{')
      {
        int len = getPos(exp, pos, '}');
        if (len > 0)
        {
          String num = new String(exp, pos + 1, len);
          int paramNumber = Integer.parseInt(num.trim());
          Object parameter = getParameter(p_parameter, paramNumber);
          if (parameter == null)
          {
            parameter = new String("%?%");
          }
          sb.append(parameter);
          pos = pos + len + 2;
          continue;
        }
      }
      sb.append(exp[pos]);
      pos = pos + 1;
    }
    return sb.toString();
  }

  private static int getPos(char[] p_charArray, int p_begin, char p_match)
  {
    int maxLen = p_charArray.length - p_begin;
    for (int i = 1; i < maxLen && i < 20; i++)
    {
      if (p_charArray[p_begin + i] == p_match)
      {
        return i - 1;
      }
    }
    return 0;
  }

  private static Object getParameter(final Object[] p_parameter, int p_num)
  {
    if (p_parameter == null || p_parameter.length == 0)
    {
      return null;
    }
    if (p_num > p_parameter.length || p_num == 0)
    {
      return null;
    }
    return p_parameter[p_num - 1];
  }

  /**
   * Return message by key, the message can be defined in file or database.
   */
  public static String getMessage(String p_language, String p_messageKey)
  {
    if (p_messageKey == null)
    {
      return null;
    }
    String message = ErrorMessages.getInstance().getErrorMessage(p_language, p_messageKey);

    return message;
  }

  /**
   * Return message by key, the message can be defined in file or database.
   */
  public static String getMessage(String p_language, String p_messageKey, Object[] p_parameter)
  {
    if (p_messageKey == null || p_messageKey.length() == 0)
    {
      return null;
    }
    String s1;
    s1 = getMessage(p_language, p_messageKey);
    if (s1 == null)
    {
      return null;
    }
    if (p_parameter == null || p_parameter.length == 0)
    {
      return s1;
    }
    List parameters = new ArrayList();
    for (int i = 0; i < p_parameter.length; i++)
    {
      Object obj1 = p_parameter[i];
      if (obj1 != null && obj1 instanceof Date)
      {
        Date date1 = (Date) obj1;
        SimpleDateFormat formatter = new SimpleDateFormat();
        obj1 = formatter.format(date1);
      }
      parameters.add(obj1);
    }
    return formatMessage(s1, parameters.toArray());
  }
}
