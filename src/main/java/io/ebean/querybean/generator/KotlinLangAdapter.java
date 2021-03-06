package io.ebean.querybean.generator;

import java.io.IOException;
import java.io.Writer;

public class KotlinLangAdapter implements LangAdapter {

  @Override
  public void beginClass(Writer writer, String shortName) throws IOException {
    //class QCountry : TQRootBean<Country, QCountry> {
    writer.append("class ").append("Q").append(shortName)
        .append(" : TQRootBean<").append(shortName)
        .append(", Q").append(shortName).append("> {").append(NEWLINE);
  }

  @Override
  public void beginAssocClass(Writer writer, String shortName, String origShortName) throws IOException {
    writer.append("class ").append("Q").append(shortName);
    writer.append("<R>(name: String, root: R) : TQAssocBean<").append(origShortName).append(",R>(name, root) {").append(NEWLINE);

  }

  @Override
  public void alias(Writer writer, String shortName) throws IOException {

    writer.append("  companion object {").append(NEWLINE);
    writer.append("    /**").append(NEWLINE);
    writer.append("     * shared 'Alias' instance used to provide").append(NEWLINE);
    writer.append("     * properties to select and fetch clauses").append(NEWLINE);
    writer.append("     */").append(NEWLINE);
    writer.append("    val _alias = Q").append(shortName).append("(true)").append(NEWLINE);
    writer.append("  }").append(NEWLINE).append(NEWLINE);
  }

  @Override
  public void assocBeanConstructor(Writer writer, String shortName) throws IOException {
    // not required for kotlin as part of type definition
  }

  @Override
  public void fetch(Writer writer, String origShortName) throws IOException {
    // currently a generics issue here ...
    writer.append("  // type safe fetch(properties) using varargs not supported yet ...").append(NEWLINE);
  }

  @Override
  public void rootBeanConstructor(Writer writer, String shortName) throws IOException {

    writer.append(NEWLINE);
    writer.append("  /**").append(NEWLINE);
    writer.append("   * Construct with a given EbeanServer.").append(NEWLINE);
    writer.append("   */").append(NEWLINE);
    writer.append("  constructor(server: EbeanServer) : super(").append(shortName).append("::class.java, server)");
    writer.append(NEWLINE);
    writer.append(NEWLINE);

    writer.append("  /**").append(NEWLINE);
    writer.append("   * Construct using the default EbeanServer.").append(NEWLINE);
    writer.append("   */").append(NEWLINE);
    writer.append("  constructor() : super(").append(shortName).append("::class.java)");
    writer.append(NEWLINE);

    writer.append(NEWLINE);
    writer.append("  /**").append(NEWLINE);
    writer.append("   * Construct for Alias.").append(NEWLINE);
    writer.append("   */").append(NEWLINE);
    writer.append("  private constructor(dummy: Boolean) : super(dummy)").append(NEWLINE);
  }

  @Override
  public void fieldDefn(Writer writer, String propertyName, String typeDefn) throws IOException {

    writer.append("  lateinit var ");
    writer.append(propertyName).append(": ");
    writer.append(typeDefn);
  }

//  @Override
//  public void finderConstructors(Writer writer, String shortName) throws IOException {
//
//
//    writer.append("  val alias = Q").append(shortName).append("._alias")
//        .append(NEWLINE).append(NEWLINE);
//
//    writer.append("  /**").append(NEWLINE);
//    writer.append("   * Construct using the default EbeanServer.").append(NEWLINE);
//    writer.append("   */").append(NEWLINE);
//    writer.append("  constructor() : super(").append(shortName).append("::class.java)").append(NEWLINE);
//    writer.append(NEWLINE);
//    writer.append("  /**").append(NEWLINE);
//    writer.append("   * Construct with a given EbeanServer.").append(NEWLINE);
//    writer.append("   */").append(NEWLINE);
//    writer.append("  constructor(serverName: String) : super(").append(shortName).append("::class.java, serverName)").append(NEWLINE);
//  }
//
//  @Override
//  public void finderWhere(Writer writer, String shortName, String modifier) throws IOException {
//    writer.append(NEWLINE);
//    writer.append("  /**").append(NEWLINE);
//    writer.append("   * Start a new typed query.").append(NEWLINE);
//    writer.append("   */").append(NEWLINE);
//    writer.append("  fun where(): Q").append(shortName).append(" {").append(NEWLINE);
//    writer.append("     return Q").append(shortName).append("(db())").append(NEWLINE);
//    writer.append("  }").append(NEWLINE);
//  }
//
//  @Override
//  public void finderText(Writer writer, String shortName, String modifier) throws IOException {
//    writer.append(NEWLINE);
//    writer.append("  /**").append(NEWLINE);
//    writer.append("   * Start a new document store query.").append(NEWLINE);
//    writer.append("   */").append(NEWLINE);
//    writer.append("  fun text(): Q").append(shortName).append(" {").append(NEWLINE);
//    writer.append("     return Q").append(shortName).append("(db()).text()").append(NEWLINE);
//    writer.append("  }").append(NEWLINE);
//  }
//
//  @Override
//  public void finderClass(Writer writer, String shortName, String idTypeShortName) throws IOException {
//    writer.append("open class ").append("").append(shortName).append("Finder")
//        .append(" : Finder<").append(idTypeShortName).append(",").append(shortName).append("> {").append(NEWLINE);
//  }
//
//  @Override
//  public String finderDefn(String shortName) {
//    return "companion object : " + shortName + "Finder()";
//  }
}
