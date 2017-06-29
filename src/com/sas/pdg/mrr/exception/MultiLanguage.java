package com.sas.pdg.mrr.exception;

public class MultiLanguage
{
  private String en;
  private String de;
  private String zh_cn;
  private BusinessException be;
  public MultiLanguage()
  {

  }
  public MultiLanguage(BusinessException be)
  {
    this.be = be;
  }
  public String getDe()
  {
    return this.getLanguage("de");
  }
  public void setDe(String de)
  {
    this.de = de;
  }
  public String getEn()
  {
    return this.getLanguage("en");
  }
  public void setEn(String en)
  {
    this.en = en;
  }
  public String getZh_cn()
  {
    return this.getLanguage("zh_cn");
  }
  public void setZh_cn(String zh_cn)
  {
    this.zh_cn = zh_cn;
  }
  private String getLanguage(String p_lang)
  {
    StringBuffer sb = new StringBuffer();
    if (be == null) return "";
    try
    {
      if (be.arguments == null)
      {
        sb.append(ExceptionUtil.getMessage(p_lang, be.userFriendlyErrorMessage));
      }
      else
      {
        sb.append(ExceptionUtil.getMessage(p_lang, be.userFriendlyErrorMessage, be.arguments));
      }
    }
    catch (Throwable th)
    {
      sb.append(be.userFriendlyErrorMessage);
    }
    if (sb.length() == 0) return be.userFriendlyErrorMessage;
    else return sb.toString();
  }
}
