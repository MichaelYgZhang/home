package com.ist.common.util;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;

public class StringUtils
{
  private static final char[] QUOTE_ENCODE = "&quot;".toCharArray();
  private static final char[] AMP_ENCODE = "&amp;".toCharArray();
  private static final char[] LT_ENCODE = "&lt;".toCharArray();
  private static final char[] GT_ENCODE = "&gt;".toCharArray();

  private static Random randGen = new Random();

  private static char[] numbersAndLetters = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    .toCharArray();

  public static String null2String(String str)
  {
    if (str == null) str = "";
    return str.trim();
  }

  public static String empty2null(String str)
  {
    if ((str != null) && 
      (str.trim().equals(""))) {
      str = null;
    }

    return str;
  }

  public static double null2double(String s)
  {
    double d = 0.0D;
    try {
      d = Double.parseDouble(s);
    }
    catch (Exception e) {
      d = 0.0D;
    }
    return d;
  }

  public static int null2int(String s)
  {
    int i = 0;
    try {
      i = Integer.parseInt(s);
    }
    catch (Exception e) {
      i = 0;
    }
    return i;
  }

  public static Integer null2Integer(String s)
  {
    Integer i = new Integer(0);
    try {
      i = new Integer(Integer.parseInt(s));
    }
    catch (Exception e)
    {
      i = new Integer(0);
    }
    return i;
  }

  public static long null2Long(String s) {
    long i = 0L;
    try {
      i = Long.parseLong(s);
    }
    catch (Exception e) {
      i = 0L;
    }
    return i;
  }

  public static String changeIntoInt(String s)
  {
    if ((s == null) || (s.length() == 0)) {
      return "0";
    }
    String i = getFormatStr(s, 0);

    return i;
  }

  public static String changeIntoZifu(String s)
  {
    if ((s == null) || (s.equals("")) || (s.equals("null")))
      s = "";
    return s;
  }

  public static String changeIntoFudian(String str, String s)
  {
    if ((str == null) || (str.length() == 0)) {
      str = "0";
    }
    int num = Integer.parseInt(s);
    return getFormatStr(str, num);
  }

  public static String nullObject2String(Object s)
  {
    String str = "";
    try {
      str = s.toString();
    }
    catch (Exception e) {
      str = "";
    }
    return str;
  }

  public static String nullObject2String(Object s, String chr) {
    String str = chr;
    try {
      str = s.toString();
    }
    catch (Exception e) {
      str = chr;
    }
    return str;
  }

  public static int nullObject2int(Object s)
  {
    String str = "";
    int i = 0;
    try {
      str = s.toString();
      i = Integer.parseInt(str);
    }
    catch (Exception e) {
      i = 0;
    }
    return i;
  }

  public static int nullObject2int(Object s, int in)
  {
    String str = "";
    int i = in;
    try {
      str = s.toString();
      i = Integer.parseInt(str);
    }
    catch (Exception e) {
      i = in;
    }
    return i;
  }

  public static String firstToUpperCase(String string)
  {
    String post = string.substring(1, string.length());
    String first = String.valueOf(string.charAt(0)).toUpperCase();
    return first + post;
  }

  public static ArrayList TokenizerString(String str, String dim) {
    return TokenizerString(str, dim, false);
  }

  public static ArrayList TokenizerString(String str, String dim, boolean returndim)
  {
    str = null2String(str);
    dim = null2String(dim);
    ArrayList strlist = new ArrayList();
    StringTokenizer strtoken = new StringTokenizer(str, dim, returndim);
    while (strtoken.hasMoreTokens()) {
      strlist.add(strtoken.nextToken());
    }
    return strlist;
  }

  public static String[] TokenizerString2(String str, String dim)
  {
    return TokenizerString2(str, dim, false);
  }

  public static String[] TokenizerString2(String str, String dim, boolean returndim)
  {
    ArrayList strlist = TokenizerString(str, dim, returndim);
    int strcount = strlist.size();
    String[] strarray = new String[strcount];
    for (int i = 0; i < strcount; i++) {
      strarray[i] = ((String)strlist.get(i));
    }
    return strarray;
  }

  public static final String getRandomString(int length)
  {
    if (length < 1) {
      return null;
    }

    char[] randBuffer = new char[length];
    for (int i = 0; i < randBuffer.length; i++) {
      randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
    }
    return new String(randBuffer);
  }

  public static String IsoToGBKTool(String strIn, String dbcode)
  {
    String strOut = null;
    if (strIn == null) return "";
    if ((dbcode == null) || (dbcode.equals(""))) return strIn; try
    {
      byte[] b = strIn.getBytes(dbcode);
      strOut = new String(b, "GBK");
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException) {
    }
    return strOut;
  }

  public static String GBKToIsoTool(String strIn, String dbcode)
  {
    String strOut = null;
    if (strIn == null) return "";
    if ((dbcode == null) || (dbcode.equals(""))) return strIn; try
    {
      byte[] b = strIn.getBytes("GBK");
      strOut = new String(b, dbcode);
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException) {
    }
    return strOut;
  }

  public static String strToUTF(String str)
  {
    if ((str == null) || ("".equals(str))) {
      return str;
    }
    String retVal = str;

    return retVal;
  }

  public static String replaceString(String mainString, String oldString, String newString)
  {
    if (mainString == null) {
      return null;
    }
    if ((oldString == null) || (oldString.length() == 0)) {
      return mainString;
    }
    if (newString == null) {
      newString = "";
    }
    int i = mainString.lastIndexOf(oldString);
    if (i < 0) return mainString;
    StringBuffer mainSb = new StringBuffer(mainString);
    while (i >= 0) {
      mainSb.replace(i, i + oldString.length(), newString);
      i = mainString.lastIndexOf(oldString, i - 1);
    }
    return mainSb.toString();
  }

  public static final String replaceIgnoreCase(String line, String oldString, String newString)
  {
    if (line == null) {
      return null;
    }
    String lcLine = line.toLowerCase();
    String lcOldString = oldString.toLowerCase();
    int i = 0;
    if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
      char[] line2 = line.toCharArray();
      char[] newString2 = newString.toCharArray();
      int oLength = oldString.length();
      StringBuffer buf = new StringBuffer(line2.length);
      buf.append(line2, 0, i).append(newString2);
      i += oLength;
      int j = i;
      while ((i = lcLine.indexOf(lcOldString, i)) > 0) {
        buf.append(line2, j, i - j).append(newString2);
        i += oLength;
        j = i;
      }
      buf.append(line2, j, line2.length - j);
      return buf.toString();
    }
    return line;
  }

  public static final String replace(String line, String oldString, String newString, int[] count)
  {
    if (line == null) {
      return null;
    }
    int i = 0;
    if ((i = line.indexOf(oldString, i)) >= 0) {
      int counter = 0;
      counter++;
      char[] line2 = line.toCharArray();
      char[] newString2 = newString.toCharArray();
      int oLength = oldString.length();
      StringBuffer buf = new StringBuffer(line2.length);
      buf.append(line2, 0, i).append(newString2);
      i += oLength;
      int j = i;
      while ((i = line.indexOf(oldString, i)) > 0) {
        counter++;
        buf.append(line2, j, i - j).append(newString2);
        i += oLength;
        j = i;
      }
      buf.append(line2, j, line2.length - j);
      count[0] = counter;
      return buf.toString();
    }
    return line;
  }

  public static final String replaceIgnoreCase(String line, String oldString, String newString, int[] count)
  {
    if (line == null) {
      return null;
    }
    String lcLine = line.toLowerCase();
    String lcOldString = oldString.toLowerCase();
    int i = 0;
    if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
      int counter = 0;
      char[] line2 = line.toCharArray();
      char[] newString2 = newString.toCharArray();
      int oLength = oldString.length();
      StringBuffer buf = new StringBuffer(line2.length);
      buf.append(line2, 0, i).append(newString2);
      i += oLength;
      int j = i;
      while ((i = lcLine.indexOf(lcOldString, i)) > 0) {
        counter++;
        buf.append(line2, j, i - j).append(newString2);
        i += oLength;
        j = i;
      }
      buf.append(line2, j, line2.length - j);
      count[0] = counter;
      return buf.toString();
    }
    return line;
  }

  public static String[] split(String line, String newString)
  {
    int begin = 0;
    int end = 0;
    ArrayList strList = new ArrayList();
    if (line == null) {
      return null;
    }
    if (newString == "")
    {
      for (int i = 0; i < line.length(); i++) {
        strList.add(line.substring(i, i + 1));
      }
      return (String[])strList.toArray(new String[strList.size()]);
    }

    end = line.indexOf(newString);
    if (end == -1) {
      strList.add(line);
      return (String[])strList.toArray(new String[strList.size()]);
    }
    do
    {
      strList.add(line.substring(begin, end));
      begin = end + newString.length();
      end = line.indexOf(newString, begin);
    }
    while (end >= 0);

    strList.add(line.substring(begin, line.length()));
    return (String[])strList.toArray(new String[strList.size()]);
  }

  public static String joinStr(List list, String delim)
  {
    if ((list == null) || (list.size() < 1))
      return null;
    StringBuffer buf = new StringBuffer();
    Iterator i = list.iterator();
    while (i.hasNext()) {
      buf.append((String)i.next());
      if (i.hasNext())
        buf.append(delim);
    }
    return buf.toString();
  }

  public static List splitStr(String str, String delim)
  {
    List splitList = null;
    StringTokenizer st = null;

    if (str == null) {
      return splitList;
    }
    if (delim != null)
      st = new StringTokenizer(str, delim);
    else {
      st = new StringTokenizer(str);
    }
    if ((st != null) && (st.hasMoreTokens())) {
      splitList = new ArrayList();

      while (st.hasMoreTokens())
        splitList.add(st.nextToken());
    }
    return splitList;
  }

  public static int binsToDecs(String str)
  {
    int ret = 0;
    int v = 1;
    for (int i = 0; i < str.length(); i++) {
      if (i != 0)
        v *= 2;
      if (str.charAt(i) == '0')
        continue;
      if (str.charAt(i) == '1')
        ret += v;
      else
        return -1;
    }
    return ret;
  }

  public static final String escapeHTMLTags(String in)
  {
    if (in == null) {
      return null;
    }

    int i = 0;
    int last = 0;
    char[] input = in.toCharArray();
    int len = input.length;
    StringBuffer out = new StringBuffer((int)(len * 1.3D));
    for (; i < len; i++) {
      char ch = input[i];
      if (ch > '>') {
        continue;
      }
      if (ch == '<') {
        if (i > last) {
          out.append(input, last, i - last);
        }
        last = i + 1;
        out.append(LT_ENCODE);
      }
      else if (ch == '>') {
        if (i > last) {
          out.append(input, last, i - last);
        }
        last = i + 1;
        out.append(GT_ENCODE);
      }
    }
    if (last == 0) {
      return in;
    }
    if (i > last) {
      out.append(input, last, i - last);
    }
    return out.toString();
  }

  public static final String escapeForXML(String string)
  {
    if (string == null) {
      return null;
    }

    int i = 0;
    int last = 0;
    char[] input = string.toCharArray();
    int len = input.length;
    StringBuffer out = new StringBuffer((int)(len * 1.3D));
    for (; i < len; i++) {
      char ch = input[i];
      if (ch > '>') {
        continue;
      }
      if (ch == '<') {
        if (i > last) {
          out.append(input, last, i - last);
        }
        last = i + 1;
        out.append(LT_ENCODE);
      }
      else if (ch == '&') {
        if (i > last) {
          out.append(input, last, i - last);
        }
        last = i + 1;
        out.append(AMP_ENCODE);
      }
      else if (ch == '"') {
        if (i > last) {
          out.append(input, last, i - last);
        }
        last = i + 1;
        out.append(QUOTE_ENCODE);
      }
    }
    if (last == 0) {
      return string;
    }
    if (i > last) {
      out.append(input, last, i - last);
    }
    return out.toString();
  }

  public static final String unescapeFromXML(String string)
  {
    string = replaceString(string, "&lt;", "<");
    string = replaceString(string, "&gt;", ">");
    string = replaceString(string, "&quot;", "\"");
    return replaceString(string, "&amp;", "&");
  }

  public static String encodeCR(String str)
  {
    str = replaceString(str, "\r\n", "\n");
    str = replaceString(str, "\n", "<BR />");
    return str;
  }

  public static String encodeCRWithPram(String str) {
    str = replaceString(str, "　", "");
    str = replaceString(str, "&", "&amp;");
    str = replaceString(str, "<", "&lt;");
    str = replaceString(str, ">", "&gt;");
    str = replaceString(str, "\r\n", "\n");
    str = replaceString(str, "\n", "<BR/>　　");
    str = "　　" + str;
    return str;
  }

  public static String decodeCR(String str)
  {
    str = replaceString(str, "<BR />", "\r\n");
    return str;
  }

  public static String subStr(String StrCmd, int subnum)
  {
    String tempSub = "";
    if (subnum <= 0) {
      subnum = 5;
    }
    for (int i = 0; i < StrCmd.length(); i++) {
      String tmpstr = StrCmd.substring(i, i + 1);
      int codenum = tmpstr.hashCode();
      if (codenum >= 128) {
        subnum -= 2;
      }
      else {
        subnum--;
      }
      tempSub = tempSub + tmpstr;
      if (subnum <= 0) {
        tempSub = tempSub + "...";
        break;
      }
    }
    return tempSub;
  }

  public String getRandom(int iLower, int iUpper)
  {
    int iRandom = 0;
    Random random = new Random();
    float fRandom = random.nextFloat();
    iRandom = iLower + (int)((iUpper - iLower) * fRandom);
    String strRandom = String.valueOf(iRandom);
    return strRandom;
  }

  public static String getCookie(HttpServletRequest req, String key)
  {
    Cookie[] cookies = req.getCookies();
    for (int i = 0; i < cookies.length; i++) {
      if (cookies[i].getName().equals(key)) {
        return cookies[i].getValue();
      }
    }
    return null;
  }

  public static void setCookie(HttpServletResponse res, String key, String value, int age, String domain)
  {
    Cookie newCookie = new Cookie(key, value);
    newCookie.setMaxAge(age);
    newCookie.setDomain(domain);
    newCookie.setPath("/");

    res.addCookie(newCookie);
  }

  public static void setCookie(HttpServletResponse res, String key, String value, int age)
  {
    Cookie newCookie = new Cookie(key, value);
    newCookie.setMaxAge(age);
    newCookie.setPath("/");
    res.addCookie(newCookie);
  }

  public static String getChineseSpace(int num)
  {
    StringBuffer bf = new StringBuffer();
    for (int i = 0; i < num; i++) {
      bf.append("　");
    }
    return bf.toString();
  }

  public static void setCookie(HttpServletResponse res, String key, String value) {
    setCookie(res, key, value, -1);
  }

  public String strDecimalFormat(String strDecimal) {
    String sFormatDecimal = strDecimal;
    if ((strDecimal.length() > 0) && 
      (strDecimal.substring(0, 1).equals("."))) {
      sFormatDecimal = "0" + strDecimal;
    }

    if ((strDecimal.length() > 1) && 
      (strDecimal.substring(0, 2).equals("-."))) {
      sFormatDecimal = "-0" + strDecimal.substring(1);
    }

    return sFormatDecimal;
  }

  public static String getFormatStr(String inStr, int num)
  {
    long temp = 1L;
    if ((inStr == null) || ("".equals(inStr)))
      inStr = "0";
    double inVal = Double.parseDouble(inStr);

    return roundString(inVal, num);
  }

  public static void main(String[] args)
  {
    StringUtils sUtils = new StringUtils();

    String i = getFormatStr(".623641", 0);
    System.out.println("i=" + i.substring(0, i.length() - 2));
  }

  public static String escape(String s)
  {
    try
    {
      int i = 0;

      StringBuffer bf = new StringBuffer("");
      while (i < s.length()) {
        char c = s.charAt(i);
        if (c == '\\')
          bf.append("\\\\");
        else if (c == '\r')
          bf.append("\\r");
        else if (c == '\n')
          bf.append("\\n");
        else if (c == '\t')
          bf.append("\\t");
        else if (c == '"')
          bf.append("\\\"");
        else
          bf.append(c);
        i++;
      }
      return bf.toString();
    } catch (Exception e) {
    }
    return null;
  }

  public static String numFull(String s2, int n)
  {
    String s = s2;
    if (s == null) {
      s = "0";
    }
    int index2 = s.indexOf(".");
    String s1 = "";
    if (index2 > 0) {
      s1 = s.substring(index2 + 1);
    }
    else if (n > 0) {
      s = s + ".";
    }
    for (int i = n - s1.length(); i > 0; i--) {
      s = s + "0";
    }
    return s;
  }

  public static String numFormat(double n)
  {
    String s = "";

    s = DecimalFormat.getInstance().format(n);

    s = replaceString(s, ",", "");

    return s;
  }

  public static String numFormat(long n) {
    String s = "";
    s = DecimalFormat.getInstance().format(n);
    s = replaceString(s, ",", "");
    return s;
  }

  public static String numFormat(int n) {
    String s = "";
    s = DecimalFormat.getInstance().format(n);
    s = replaceString(s, ",", "");
    return s;
  }

  public static String numFormat(float n) {
    String s = "";
    s = DecimalFormat.getInstance().format(n);
    s = replaceString(s, ",", "");
    return s;
  }

  public static String numFormat(String n) {
    String s = "";
    if ((n == null) || (n.length() == 0)) return "";
    try
    {
      s = DecimalFormat.getInstance().format(Double.parseDouble(n));
      s = replaceString(s, ",", "");
    }
    catch (Exception localException)
    {
    }
    return s;
  }

  public static boolean isNumType(String str)
  {
    boolean retValue = true;
    if ((str == null) || (str.length() <= 0))
      return false;
    for (int i = 0; i < str.length(); i++) {
      char t = str.charAt(i);
      if (((t >= '0') && (t <= '9')) || 
        (t == '.') || (t == '-')) continue;
      retValue = false;
      break;
    }

    return retValue;
  }

  public static String nullToStr(String str)
  {
    if (str == null) str = "";
    return str.trim();
  }

  public static String roundString(double f, int n)
  {
    int r = 1;

    boolean fushuFlag = false;

    if (f < 0.0D) {
      fushuFlag = true;
      f *= -1.0D;
    }
    for (int i = 1; i <= n; i++) r *= 10;
    double f2 = Math.round(f * r);
    String s = DecimalFormat.getInstance().format(f2);
    s = replaceString(s, ",", "");

    if (n > 0) {
      if (s.length() <= n) {
        for (int i = s.length(); i <= n; i++) {
          s = "0" + s;
        }
      }
      s = s.substring(0, s.length() - n) + "." + 
        s.substring(s.length() - n);
    }
    if (fushuFlag)
      s = "-" + s;
    return s;
  }

  public static String formulaFormat(String valueStr)
  {
    if (valueStr == null)
      valueStr = "NULL";
    valueStr = valueStr.replaceAll("null", "0");
    valueStr = valueStr.replaceAll("NULL", "0");

    if (valueStr.equals(""))
      valueStr = "0";
    if ((valueStr.startsWith("-")) || (valueStr.startsWith("+"))) {
      valueStr = "(0" + valueStr + ")";
    }
    return valueStr;
  }

  public static String num2Char(int numValue)
  {
    int fatherNum = 0;
    while (numValue > 26) {
      fatherNum++;
      numValue -= 26;
    }
    char numChar = (char)(65 + numValue - 1);
    String retValue = String.valueOf(numChar);
    if (fatherNum > 0) {
      retValue = retValue + String.valueOf((char)(65 + fatherNum - 1));
    }
    return retValue;
  }

  public static String getFmtNum(String inStr, int decDig)
  {
    return getFmtNum(inStr, decDig, 1.0D, true);
  }

  public static String getFmtNum(String inStr, int decDig, double mulNum, boolean groupFlag)
  {
    if (isEmpty(inStr)) {
      return "";
    }
    StringBuffer fmt = new StringBuffer(15);
    long adj = 1L;
    int chgFlag = 1;

    if (groupFlag) {
      fmt.append(",");
    }
    fmt.append("##0");
    for (int i = 0; i < decDig; i++) {
      if (i == 0) {
        fmt.append(".");
      }
      fmt.append('0');
      adj *= 10L;
    }
    DecimalFormat df = new DecimalFormat(fmt.toString());

    double tmpResult = NumberUtils.toDouble(inStr);
    if (tmpResult < 0.0D) {
      tmpResult *= -1.0D;
      chgFlag = -1;
    }
    long tmpLong = (long) (tmpResult * (mulNum * adj) + 0.5D);
    tmpResult = tmpLong / adj * chgFlag;

    return df.format(tmpResult);
  }

  public static final boolean isEmpty(String inStr)
  {
    boolean result = true;
    int len;
    if ((inStr == null) || ((len = inStr.length()) == 0))
      return result;
    for (int i = 0; i < len; i++) {
      if ((inStr.charAt(i) != ' ') && (inStr.charAt(i) != '\t') && (inStr.charAt(i) != '\n')) {
        result = false;
        break;
      }
    }
    return result;
  }

  public static final String MoneyFormat(double dMoney, int n) {
    String fm = "###,##0";
    if (n > 0) {
      fm = fm + ".";
    }
    for (int i = 0; i < n; i++) {
      fm = fm + "0";
    }
    DecimalFormat myformat = new DecimalFormat(fm);
    String sMoney = myformat.format(dMoney);
    return sMoney;
  }

  public static String handleFilePath(String filePath)
  {
    if (filePath == null) return filePath;

    filePath = replaceString(filePath, ":", "");
    filePath = replaceString(filePath, "\\", "");
    filePath = replaceString(filePath, "/", "");
    filePath = replaceString(filePath, ".", "");

    return filePath;
  }

  public static boolean containsIgnoreCase(String str, String searchStr)
  {
    if ((str == null) || (searchStr == null)) {
      return false;
    }
    int len = searchStr.length();
    int max = str.length() - len;
    for (int i = 0; i <= max; i++) {
      if (str.regionMatches(true, i, searchStr, 0, len)) {
        return true;
      }
    }
    return false;
  }

  public static String StringFilter(String str)
  {
    String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\\\]";
    Pattern p = Pattern.compile(regEx);
    Matcher m = p.matcher(str);
    return m.replaceAll("").trim();
  }
}