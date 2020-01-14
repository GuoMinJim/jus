package com.beiming.juc.mybatisplus.config;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: dengchunhai
 * @since 1.0.0
 */
public class CodeGenerator {


  public static void main(String[] args) {
    // 代码生成器
    AutoGenerator mpg = new AutoGenerator();

    // 全局配置
    GlobalConfig gc = new GlobalConfig();
    String projectPath = System.getProperty("user.dir");

    gc.setOutputDir(projectPath + "/src/main/java");
    gc.setAuthor("jinguomin");
    gc.setOpen(false);
    // 实体属性 Swagger2 注解
    gc.setSwagger2(true);
    gc.setBaseColumnList(true);
    gc.setBaseResultMap(true);
    gc.setDateType(DateType.ONLY_DATE);
    mpg.setGlobalConfig(gc);

    // 数据源配置
    DataSourceConfig dsc = new DataSourceConfig();
    dsc.setUrl(
        "jdbc:mysql://47.106.248.229:3306/provider?useUnicode=yes&characterEncoding=UTF-8&allowMultiQueries=true");
    dsc.setDriverName("com.mysql.cj.jdbc.Driver");
    dsc.setUsername("jgm");
    dsc.setPassword("0000");
    mpg.setDataSource(dsc);

    // 包配置
    PackageConfig pc = new PackageConfig();
//    pc.setModuleName("evidence");
    pc.setParent("com.beiming.juc.mybatisPlus");
    pc.setEntity("domain.entity");
    mpg.setPackageInfo(pc);

    // 自定义配置
    InjectionConfig cfg = new InjectionConfig() {
      @Override
      public void initMap() {
        // to do nothing
      }
    };

    // 如果模板引擎是 velocity
//    String templatePath = "/templates/entity.java.vm";

    String templatePath = "/templates/mapper.xml.vm";
    List<FileOutConfig> focList = new ArrayList<>();
    // 自定义配置会被优先输出
    focList.add(new FileOutConfig(templatePath) {
      @Override
      public String outputFile(TableInfo tableInfo) {
        // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
        return projectPath + "/src/main/java/com/beiming/juc/mybatisPlus/domain/entity2/"
            + "/" + tableInfo.getEntityName() + StringPool.DOT_JAVA;
      }
    });
    cfg.setFileOutConfigList(focList);

    mpg.setCfg(cfg);

    // 配置模板
    TemplateConfig templateConfig = new TemplateConfig();

    // 配置自定义输出模板
    //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
//     templateConfig.setEntity("templates/entity.java");
//     templateConfig.setService();
//     templateConfig.setController();

//    templateConfig.setXml(null);
    mpg.setTemplate(templateConfig);

    // 策略配置
    StrategyConfig strategy = new StrategyConfig();
    strategy.setNaming(NamingStrategy.underline_to_camel);
    strategy.setColumnNaming(NamingStrategy.underline_to_camel);
    strategy.setEntityLombokModel(true);
    strategy.setRestControllerStyle(true);
    // 公共父类
//    strategy.setSuperControllerClass("com.bmsoft.evidence.controller.base.BaseController");
    strategy.setSuperEntityClass(BaseEntity.class);
    // 包含的表。 增量更新或修改需重新生成代码，务必配置，以免覆盖
    strategy.setInclude("evidence_review_catalogue");
    strategy.setControllerMappingHyphenStyle(true);
    strategy.setTablePrefix("test");
    mpg.setStrategy(strategy);
    mpg.setTemplateEngine(new FreemarkerTemplateEngine());
    mpg.execute();
  }

}

