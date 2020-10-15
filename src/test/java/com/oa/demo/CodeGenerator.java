package com.oa.demo;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class CodeGenerator {

	public static final String DB_URL = "jdbc:mysql://127.0.0.1:13306/oa?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&allowMultiQueries=true";
	public static final String USER_NAME = "root";
	public static final String PASSWORD = "123456";
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String AUTHOR = "valonYe";
	//生成的文件输出到哪个目录
	public static final String OUTPUT_FILE = "/Users/kimbovalon/javatest/oaDemo/src/main/java";
	//包名，会按照com/example/demo这种形式生成类
	public static final String PACKAGE = "com.oa.demo";
	//TODO 更多配置请参考http://mp.baomidou.com/#/generate-code

	public void generateByTables(boolean serviceNameStartWithI, String... tableNames) {
		GlobalConfig config = new GlobalConfig();
		DataSourceConfig dataSourceConfig = new DataSourceConfig();
		dataSourceConfig.setDbType(DbType.MYSQL)
			.setUrl(DB_URL)
			.setUsername(USER_NAME)
			.setPassword(PASSWORD)
			.setDriverName(DRIVER);
		StrategyConfig strategyConfig = new StrategyConfig();
		strategyConfig
			.setCapitalMode(true)
			.setEntityLombokModel(false)
//			.setDbColumnUnderline(true)
			.setNaming(NamingStrategy.underline_to_camel)
			.setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
		config.setActiveRecord(false)
			.setAuthor(AUTHOR)
			.setOutputDir(OUTPUT_FILE)
			.setFileOverride(true);
		if (!serviceNameStartWithI) {
			config.setServiceName("%sService");
		}
		new AutoGenerator().setGlobalConfig(config)
			.setDataSource(dataSourceConfig)
			.setStrategy(strategyConfig)
			.setPackageInfo(
				new PackageConfig()
					.setParent(PACKAGE)
					.setController("controller")
					.setEntity("entity")
			).execute();
	}
}
