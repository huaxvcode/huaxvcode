package com.wms.common;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CodeGeneratorInteractive {

  public static void main(String[] args) {
    FastAutoGenerator.create(CodeGeneratorConfig.url, CodeGeneratorConfig.username,
            CodeGeneratorConfig.password)
        // 全局配置
        .globalConfig((scanner, builder) -> {
          builder.author(CodeGeneratorConfig.author) // 设置作者
              .outputDir(CodeGeneratorConfig.userDir + "/src/main/java"); // 输出目录
        })
        // 包配置
        .packageConfig(
            (scanner, builder) -> {
              builder.parent("com") // 设置父包名
                  .moduleName("wms") // 设置父包模块名
                  .entity("entity") // 设置实体类包名
                  .mapper("mapper") // 设置 Mapper 接口包名
                  .service("service") // 设置 Service 接口包名
                  .serviceImpl("service.impl") // 设置 Service 实现类包名
                  .xml("mapper"); // 设置 Mapper XML 文件包名
            })
        // 策略配置
        .strategyConfig((scanner, builder) -> builder.addInclude(
                getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
            .entityBuilder()
            .enableLombok() // 启用 Lombok
            .enableTableFieldAnnotation() // 启用字段注解
            .addTableFills(
                new Column("create_time", FieldFill.INSERT)
            )
            .build())
        // 使用Freemarker引擎模板，默认的是Velocity引擎模板
        .templateEngine(new FreemarkerTemplateEngine())
        .execute();
  }

  // 处理 all 情况
  protected static List<String> getTables(String tables) {
    return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
  }
}
