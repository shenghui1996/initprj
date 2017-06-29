package com.sas.pdg.mrr.exception;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * Read error messages from xml-files.
 */
public final class ErrorMessages
{
  public static final String[] xmlFiles = new String[]
  {"errormap/errorMessages_Service.xml", "errormap/errorMessages_Global.xml"};
  public static final String KEY_SEPARATOR = ":";
  public static final String KEY_MESSAGE_SEPARATOR = "##";

  private static final String COMPONENT_TAG = "component";
  private static final String COMPONENT_ATTR_NAME = "name";

  private static final String SUBCOMPONENT_TAG = "subcomponent";
  private static final String SUBCOMPONENT_ATTR_NAME = "name";

  private static final String ERROR_TAG = "errorMessage";
  private static final String ERROR_ATTR_SEVERITY = "severity";
  private static final String ERROR_ATTR_NAME = "id";

  // Hashtable where error messages are saved
  private static Hashtable errorMessageTable = new Hashtable();

  // Hashtable where error message severities are saved
  private static Hashtable errorMessageSeverityTable = new Hashtable();

  private static final ErrorMessages INSTANCE = new ErrorMessages();

  /**
   * ErrorMessages constructor
   */
  private ErrorMessages()
  {
    // get error message xml-files
    final List xmlList = getXmlList();
    importMessagesFromXML(xmlList);
  }

  /**
   * Access method for the singleton-object from error messages.
   * 
   * @return singleton-object for error messages
   */
  public static ErrorMessages getInstance()
  {
    return INSTANCE;
  }

  /**
   * this method returns a list with the error message files
   * 
   * @return ArrayList: list with the error message xml-files
   */
  private List getXmlList()
  {
    List xmlFileList = new ArrayList();

    for (int i = 0; i < xmlFiles.length; i++)
    {
      String cf = xmlFiles[i];
      xmlFileList.add(this.getClass().getResourceAsStream(cf));
    }
    // return list with xml-files
    return xmlFileList;
  }

  /**
   * Read all xml-files from the error messages-directory and write all error
   * messages to a hashtable.
   */
  private void importMessagesFromXML(List p_xmlList)
  {
    if (p_xmlList.size() == 0)
    {
      throw new BusinessException("Error message configuration file is empty");
    }
    // initialising Parser
    DocumentBuilder lParser = null;
    try
    {
      lParser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }
    catch (ParserConfigurationException exc)
    {
      throw new BusinessException("ParserConfigurationException", exc);
    }

    for (int i = 0; i < p_xmlList.size(); i++)
    {
      InputStream inputStream = (InputStream) p_xmlList.get(i);
      Document lDocument = null;
      try
      {
        // 2. parse the document
        lDocument = lParser.parse(inputStream);
      }
      catch (IOException e)
      {
        throw new BusinessException("IOException file name: " + inputStream, e);
      }
      catch (Exception e)
      {
        throw new BusinessException("Parse malformed XML element (" + inputStream + ")", e);
      }
      if (lDocument == null)
      {
        throw new BusinessException("Error message configuration file is empty");
      }

      // get the first element
      Element lDocRoot = (Element) lDocument.getDocumentElement();
      if (lDocRoot == null)
      {
        throw new BusinessException("Error message configuration file is empty");
      }

      // Iterate throught nodes
      for (Node compNode = lDocRoot.getFirstChild(); compNode != null; compNode = compNode.getNextSibling())
      {
        if (compNode.getNodeType() != Node.ELEMENT_NODE || !compNode.getNodeName().equals(COMPONENT_TAG))
        {
          continue;
        }
        String prefixComponent = compNode.getAttributes().getNamedItem(COMPONENT_ATTR_NAME).getNodeValue();
        // Component node found. search subcomponents
        for (Node subNode = compNode.getFirstChild(); subNode != null; subNode = subNode.getNextSibling())
        {
          if (subNode.getNodeType() != Node.ELEMENT_NODE || !subNode.getNodeName().equals(SUBCOMPONENT_TAG))
          {
            continue;
          }
          // Subcomponent node found. Scan for ErrorMesasges
          scanErrorMessages(prefixComponent, subNode);
        }
      }
    }
  }

  private void scanErrorMessages(String p_prefix, Node p_parentNode)
  {
    String errorName = p_prefix;
    String errorSeverity = null;
    NamedNodeMap attributes = p_parentNode.getAttributes();
    Node node = attributes.getNamedItem(ERROR_ATTR_NAME);
    if (node != null)
    {
      errorName = p_prefix + KEY_SEPARATOR + node.getNodeValue();
      node = attributes.getNamedItem(ERROR_ATTR_SEVERITY);
      if (node != null)
      {
        errorSeverity = node.getNodeValue();
        errorMessageSeverityTable.put(errorName, errorSeverity);
      }
    }
    else
    {
      node = attributes.getNamedItem(SUBCOMPONENT_ATTR_NAME);
      if (node != null)
      {
        errorName = p_prefix + KEY_SEPARATOR + node.getNodeValue();
      }
    }
    // iterate throught subnodes
    for (node = p_parentNode.getFirstChild(); node != null; node = node.getNextSibling())
    {
      if (node.getNodeType() != Node.ELEMENT_NODE)
      {
        continue;
      }
      if (node.getNodeName().equals(ERROR_TAG))
      {
        scanErrorMessages(errorName, node);
      }
      else if (node.getNodeName().equals("errorText"))
      {
        Node subNode = node.getAttributes().getNamedItem("language");
        if (subNode != null)
        {
          String language = subNode.getNodeValue().toLowerCase();
          subNode = node.getFirstChild();
          if (subNode != null)
          {
            String text = subNode.getNodeValue();
            String key = language + KEY_SEPARATOR + errorName;
            if (!errorMessageTable.containsKey(key))
            {
              errorMessageTable.put(key, key + KEY_MESSAGE_SEPARATOR + text);
            }
          }
        }
      }
    }
  }

  /**
   * Returns the message text. In order to be compatible with the old version,
   * have to reserve this method.
   * 
   * @param p_language
   *          language iso code for error message
   * @param p_keys
   *          array of keys for error
   * @return java.lang.String: message text
   */
  public String getErrorMessage(String p_language, String[] p_keys)
  {
    if (p_keys == null || p_keys.length == 0)
    {
      return null;
    }
    String foundMsg = null;
    StringBuffer sb = new StringBuffer(100);
    sb.append(p_language).append(KEY_SEPARATOR).append(p_keys[0]);
    int count = p_keys.length;
    for (int i = 1; i < count; i++)
    {
      sb.append(KEY_SEPARATOR).append(p_keys[i]);
    }
    int len = sb.length();
    int keyLen = 0;
    for (int i = count; i > 1; i--)
    {
      Object text = errorMessageTable.get(sb.substring(0, len - keyLen));
      if (text != null)
      {
        foundMsg = text.toString();
        break;
      }
      if (p_keys[i - 1] != null)
      {
        keyLen = keyLen + p_keys[i - 1].length() + 1;
      }
      else
      {
        keyLen = keyLen + 1;
      }
    }
    return foundMsg;
  }

  /**
   * Returns the message text.
   * 
   * @param p_messageKeys
   *          message ID
   * @return java.lang.String: message text
   */
  public String getErrorMessage(String p_language, String p_messageKeys)
  {
    if (p_messageKeys == null || p_messageKeys.length() == 0)
    {
      return null;
    }
    if (p_language == null || p_language.length() == 0)
    {
      p_language = "de";
    }
    String key = p_language + KEY_SEPARATOR;
    String token = p_messageKeys;
    int pos;
    Object text = null;
    while (token != null)
    {
      text = errorMessageTable.get(key + token);
      if (text != null)
      {
        break;
      }
      pos = token.lastIndexOf(KEY_SEPARATOR);
      if (pos > 0)
      {
        token = token.substring(0, pos);
      }
      else
      {
        break;
      }
    }
    if (text == null)
    {
      return "";
    }
    return text.toString();
  }

  /**
   * Returns the error message severity.
   * 
   * @param p_keys
   *          Key: consisting of errorID, component name and subsystem
   * 
   * @return java.lang.String: error message severity
   */
  public String getErrorMessageSeverity(String[] p_keys)
  {
    String foundMsg = null;
    String key = p_keys[0];
    int count = p_keys.length;
    for (int i = 1; i < count; i++)
    {
      key = key + KEY_SEPARATOR + p_keys[i];
      Object text = errorMessageSeverityTable.get(key);
      if (text == null)
      {
        break;
      }
      foundMsg = text.toString();
    }
    return foundMsg;
  }
}
