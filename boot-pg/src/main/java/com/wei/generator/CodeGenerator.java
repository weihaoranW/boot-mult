package com.wei.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CodeGenerator {
static String AUTHOR = "weihaoran";
static String URL = "jdbc:postgresql://127.0.0.1:5432/postgres" +
                     "?connect_timeout=10";
static String USER = "postgres";
static String PASSWORD = "password";
static String PROJECT_PATH = "/home/whr/idea/boot-mult/boot-pg";
static String PARENT_PACKAGE = "com.wei";
static String MAPPER_PATH = "/src/main/resources/mapper/";
static String TABLE_NAME = "msg";

static String[] tableNames = new String[]{"test"};

/**
 * <p>
 * 读取控制台内容
 * </p>
 */
public static String scanner(String tip) {

 Scanner scanner = new Scanner(System.in);
 StringBuilder help = new StringBuilder();
 help.append("请输入" + tip + "：");
 System.out.println(help.toString());
 if (scanner.hasNext()) {
  String ipt = scanner.next();
  if (StringUtils.isNotEmpty(ipt)) {
   return ipt;
  }
 }
 throw new MybatisPlusException("请输入正确的" + tip + "！");
}

public static void main(String[] args) {
//
 for (String tbName : tableNames) {
  exec(tbName);
 }

}

public static void exec(String tbName) {

 if (tbName == null || tbName.length() == 0) {
  return;
 }

 // 代码生成器
 AutoGenerator mpg = new AutoGenerator();

 //-----全局配置------------------------------------------
 GlobalConfig gc = new GlobalConfig();
 //String projectPath = System.getProperty("user.dir");
 String projectPath = PROJECT_PATH;

 System.out.println("---projectPath--" + projectPath);
 gc.setOutputDir(projectPath + "/src/main/java");
 gc.setAuthor(AUTHOR);
 //gc.setControllerName("TestController");
 gc.setOpen(false);
 //XML base resultMap Node
 gc.setBaseResultMap(true);
 // XML columList Node
 gc.setBaseColumnList(true);
 //
 // 自定义文件命名，注意 %s 会自动填充表实体属性！
 // gc.setMapperName("%sDao");
 // gc.setXmlName("%sDao");
 gc.setServiceName("%sService");
 // gc.setServiceImplName("%sServiceDiy");
 gc.setControllerName("%sController");
 mpg.setGlobalConfig(gc);

 //------数据源配置-----------------------------------------
 DataSourceConfig dsc = new DataSourceConfig();
 dsc.setUrl(URL);
 // dsc.setSchemaName("public");
 //dsc.setDriverName("com.mysql.cj.jdbc.Driver");
 dsc.setDriverName("org.postgresql.Driver");
 dsc.setUsername(USER);
 dsc.setPassword(PASSWORD);
 mpg.setDataSource(dsc);

 //----包配置-------------------------------------------
 PackageConfig pc = new PackageConfig();
 //pc.setModuleName(scanner("模块名"));
 pc.setParent(PARENT_PACKAGE);
 //pc.setController("controller");
 //pc.setEntity("model");
 mpg.setPackageInfo(pc);

 // 自定义配置
 InjectionConfig cfg = new InjectionConfig() {
  @Override
  public void initMap() {

  }
 };
 List<FileOutConfig> focList = new ArrayList<>();
 focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
  @Override
  public String outputFile(TableInfo tableInfo) {
   // 自定义输入文件名称
   return projectPath + MAPPER_PATH
           + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
  }
 });
 cfg.setFileOutConfigList(focList);
 mpg.setCfg(cfg);

 //---------模板配置-----------------
 TemplateConfig tc = new TemplateConfig();
 tc.setController("/templates/controller.java.vm");
 // tc.setService("/templatesMybatis/service.java.vm");
 // tc.setServiceImpl("/templatesMybatis/serviceImpl.java.vm");
 // tc.setEntity("/templatesMybatis/entity.java.vm");
 // tc.setMapper("/templatesMybatis/mapper.java.vm");
 // tc.setXml("/templatesMybatis/mapper.xml.vm");
 tc.setXml(null);
 mpg.setTemplate(tc);

 //----------策略配置-----------------------
 StrategyConfig strategy = new StrategyConfig();
 strategy.setNaming(NamingStrategy.underline_to_camel);
 strategy.setColumnNaming(NamingStrategy.underline_to_camel);
 //strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
 strategy.setEntityLombokModel(true);
 strategy.setRestControllerStyle(true);
 //strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
 strategy.setInclude(tbName);
 //strategy.setSuperEntityColumns("id");
 //strategy.setControllerMappingHyphenStyle(true);
 strategy.setTablePrefix(pc.getModuleName() + "_");
 mpg.setStrategy(strategy);
 //
 mpg.setTemplateEngine(new FreemarkerTemplateEngine());
 //mpg.setTemplateEngine(new VelocityTemplateEngine());
 mpg.execute();
}

}
