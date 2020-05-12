package com.kevinmakai.springproject.cli.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 请填写类的描述
 *
 * @author makai5
 * @date 2020-05-13 00:42
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}
