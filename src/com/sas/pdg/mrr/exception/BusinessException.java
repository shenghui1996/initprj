package com.sas.pdg.mrr.exception;

public class BusinessException extends RuntimeException
{
  protected String userFriendlyErrorMessage = null;
  protected Object[] arguments = null;
  protected String uniqueId = ExceptionUtil.getExceptionID();
  // For JDK1.3
  protected Throwable originalThrowable = null;

  /**
   * Construct an exception with message key
   */
  public BusinessException(final String p_messageKey)
  {
    super(p_messageKey);
    if (p_messageKey != null && p_messageKey.length() > 0)
    {
      userFriendlyErrorMessage = p_messageKey;
    }
  }

  /**
   * Construct an exception with message key, and the original exception will be
   * stored.
   */
  public BusinessException(final String p_messageKey, Throwable p_throwable)
  {
    super(p_messageKey);
    if (p_messageKey != null && p_messageKey.length() > 0)
    {
      userFriendlyErrorMessage = p_messageKey;
    }
    originalThrowable = p_throwable;
  }

  /**
   * Construct an exception with message key and message parameters.
   */
  public BusinessException(final String p_messageKey, Object[] p_parameterList)
  {
    super(p_messageKey);
    if (p_messageKey != null && p_messageKey.length() > 0)
    {
      userFriendlyErrorMessage = p_messageKey;
      arguments = p_parameterList;
    }
  }

  /**
   * Construct an exception with message key and message parameters.
   */
  public BusinessException(final String p_messageKey, Object[] p_parameterList, Throwable p_throwable)
  {
    super(p_messageKey);
    if (p_messageKey != null && p_messageKey.length() > 0)
    {
      userFriendlyErrorMessage = p_messageKey;
      arguments = p_parameterList;
    }
    originalThrowable = p_throwable;
  }

  /**
   * Return original throwable object
   * 
   * @return
   */
  public Throwable getOriginalThrowable()
  {
    if (originalThrowable != null)
    {
      if (originalThrowable instanceof BusinessException)
      {
        // recurse to the root exception
        return extractOriginalThrowable((BusinessException) originalThrowable);
      }
      else
      {
        return originalThrowable;
      }
    }
    return this;
  }

  private Throwable extractOriginalThrowable(BusinessException p_exception)
  {
    Throwable original = p_exception.getOriginalThrowable();
    while (original != p_exception)
    {
      if (original == null)
      {
        original = p_exception;
        break;
      }
      if (original instanceof BusinessException)
      {
        p_exception = (BusinessException) original;
        original = p_exception.getOriginalThrowable();
      }
      else
      {
        break;
      }
    }
    return original;
  }

  /**
   * Return user-friendly error message.
   */
  public MultiLanguage getErrorMessage()
  {
    return new MultiLanguage(this);
  }

  /**
   * Return user-friendly error message.
   */
  public String getLocalizedMessage()
  {
    return getMessage();
  }

  /**
   * Return the original stack trace
   */
  public String getOriginalStackTrace()
  {
    if (originalThrowable == null)
    {
      return ExceptionUtil.getStackTrace(this);
    }
    else
    {
      if (originalThrowable instanceof BusinessException)
      {
        return ((BusinessException) originalThrowable).getOriginalStackTrace();
      }
      else
      {
        return ExceptionUtil.getStackTrace(originalThrowable);
      }
    }
  }

  /**
   * Return unique message id.
   * 
   * @return A string representing the unique error id.
   */
  public String getMessageId()
  {
    return this.uniqueId;
  }
}
