package com.kevinmakai.springproject.cli.common.utils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 请填写类的描述
 *
 * @author kevin
 * @date 2020-05-13 01:13
 */
public class DBSequenceUtil {
    /**
     * 时间原点  2016-01-01 00:00:00
     */
    public static final long ID_EPOCH = 1451577600000L;

    /**
     * 多个序列生成器集合
     */
    private Map<String, DBSequence> sequenceMap;

    /**
     * 默认序列生成器
     */
    private DBSequence dbDefaultSequence;

    public void setDefaultSequence(DBSequence dbDefaultSequence) {
        this.dbDefaultSequence = dbDefaultSequence;
    }

    public void setSequenceMap(Map<String, DBSequence> sequenceMap) {
        this.sequenceMap = sequenceMap;
    }



    /**
     * 直接使用默认的序列取值。 <br/>
     * @param name sequence名字
     * @return long 序列值
     */
    public long getFromDefaultSequence(String name) {
        if(this.dbDefaultSequence != null) {
            return this.dbDefaultSequence.get(name);
        } else {
            throw new RuntimeException("sequence " + name + " undefined!");
        }
    }

    /**
     * 随机获取序列值
     * <p></>
     * @return long 取到序列值
     */
    public long getRandom(){
        Set<String> keys = getSequenceMapKey();
        if(keys == null || keys.isEmpty()){
            throw new RuntimeException("sequenceMap undefined!");
        }
        int idx = ThreadLocalRandom.current().nextInt(keys.size());
        int i = 0;
        for(String name:keys){
            if(i == idx){
                return get(name);
            }else{
                i++;
            }
        }
        throw new RuntimeException("sequence  undefined!");
    }



    /**
     * 计算当前时间与时间原点相关的天数
     * <p></p>
     * @return long 已过去天数
     */
    public long getPasDays() {
        long diffMills = System.currentTimeMillis() - ID_EPOCH;
        long pasDay = diffMills / (24 * 60 * 60 * 1000);
        return  pasDay;
    }

    //=============================================================================

    /**
     * 返回name集合,自己可以从中随机挑选一个name,再通过get(name)获取序列值
     */
    private Set<String> getSequenceMapKey(){
        if(sequenceMap == null){
            return null;
        }
        return sequenceMap.keySet();
    }

    /**
     * 如果没有在map中指定，则使用默认的。 <br/>
     * 如果没有默认的，则出错。<br/>
     * 没有写入操作，并发无问题。<br/>
     * @param name sequence名字
     * @return long
     */
    private long get(String name) {
        DBSequence sequence = null;
        if (sequenceMap != null) {
            sequence = sequenceMap.get(name);
        }
        if (sequence == null) {
            if (dbDefaultSequence != null) {
                return dbDefaultSequence.get(name);
            } else {
                throw new RuntimeException("sequence " + name + " undefined!");
            }
        }
        return sequence.get(name);
    }
}
