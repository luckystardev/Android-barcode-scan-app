package com.aperfectpc.nuera_id.barcode.reader;

import java.util.regex.Pattern;

/**
 * 
 * @author Wesley Young <wyoung@aperfectpc.com>
 *
 */
public class UrlUtils
{
  private static final Pattern URL_SCHEME_PATTERN = Pattern
      .compile("^[a-zA-Z0-9]+://");

  private UrlUtils()
  {
  }

  public static String cleanUrl(String url, String defaultScheme)
  {
    return URL_SCHEME_PATTERN.matcher(url).find() ? url : defaultScheme + "://"
        + url;
  }

  public static String cleanUrl(String url)
  {
    return cleanUrl(url, "http");
  }
}
