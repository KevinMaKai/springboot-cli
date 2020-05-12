package com.kevinmakai.springproject.cli.configuration.database;

import com.kevinmakai.springproject.cli.utils.DBSequence;
import com.kevinmakai.springproject.cli.utils.DBSequenceUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 自增序列
 *
 * @author kevin
 * @date 2020/2/28 13:39
 */
@Configuration
public class SequencesConfig {

    @Bean
    public DBSequenceUtil dbSequenceUtil(DataSource dataSource) {
        DBSequenceUtil dbSequenceUtil = new DBSequenceUtil();
        dbSequenceUtil.setDefaultSequence(defaultSequence(dataSource));
        Map<String, DBSequence> sequenceMap = new HashMap<>(10);
        dbSequenceUtil.setSequenceMap(sequenceMap);
        sequenceMap.put("DB_TEST_01", sequence1(dataSource));
        sequenceMap.put("DB_TEST_02", sequence2(dataSource));
        sequenceMap.put("DB_TEST_03", sequence3(dataSource));
        sequenceMap.put("DB_TEST_04", sequence4(dataSource));
        sequenceMap.put("DB_TEST_05", sequence5(dataSource));
        sequenceMap.put("DB_TEST_06", sequence6(dataSource));
        return dbSequenceUtil;
    }

    @Bean
    public DBSequence defaultSequence(DataSource dataSource) {
        DBSequence dbSequence = new DBSequence();
        dbSequence.setDataSource(dataSource);
        dbSequence.setStartValue(10000000);
        dbSequence.setBlockSize(100);
        dbSequence.setStepSize(6);
        return dbSequence;
    }

    @Bean
    public DBSequence sequence1(DataSource dataSource) {
        DBSequence dbSequence = new DBSequence();
        dbSequence.setDataSource(dataSource);
        dbSequence.setStartValue(10000001);
        dbSequence.setBlockSize(100);
        dbSequence.setStepSize(6);
        return dbSequence;
    }

    @Bean
    public DBSequence sequence2(DataSource dataSource) {
        DBSequence dbSequence = new DBSequence();
        dbSequence.setDataSource(dataSource);
        dbSequence.setStartValue(10000002);
        dbSequence.setBlockSize(100);
        dbSequence.setStepSize(6);
        return dbSequence;
    }

    @Bean
    public DBSequence sequence3(DataSource dataSource) {
        DBSequence dbSequence = new DBSequence();
        dbSequence.setDataSource(dataSource);
        dbSequence.setStartValue(10000003);
        dbSequence.setBlockSize(100);
        dbSequence.setStepSize(6);
        return dbSequence;
    }

    @Bean
    public DBSequence sequence4(DataSource dataSource) {
        DBSequence dbSequence = new DBSequence();
        dbSequence.setDataSource(dataSource);
        dbSequence.setStartValue(10000004);
        dbSequence.setBlockSize(100);
        dbSequence.setStepSize(6);
        return dbSequence;
    }

    @Bean
    public DBSequence sequence5(DataSource dataSource) {
        DBSequence dbSequence = new DBSequence();
        dbSequence.setDataSource(dataSource);
        dbSequence.setStartValue(10000005);
        dbSequence.setBlockSize(100);
        dbSequence.setStepSize(6);
        return dbSequence;
    }

    @Bean
    public DBSequence sequence6(DataSource dataSource) {
        DBSequence dbSequence = new DBSequence();
        dbSequence.setDataSource(dataSource);
        dbSequence.setStartValue(10000006);
        dbSequence.setBlockSize(100);
        dbSequence.setStepSize(6);
        return dbSequence;
    }



//    @Bean
//    public ConsoleSQL consoleSQL(DataSource dataSource){
//        ConsoleSQL consoleSQL = new ConsoleSQL();
//        consoleSQL.setDataSource(dataSource);
//        return consoleSQL;
//    }

}
