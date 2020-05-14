package com.kevinmakai.springproject.cli.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 请填写类的描述
 *
 * @author kevin
 * @date 2020-05-13 01:11
 */
public class DBSequence {

    private static final Logger LOG = LoggerFactory.getLogger(DBSequence.class);

    private final static int MAX_RETRY_TIMES = 10;

    private int blockSize = 5;
    private long startValue = 0;
    private int stepSize = 1;
    private final static String GET_SQL = "select id from sequence_value where name = ?";
    private final static String NEW_SQL = "insert into sequence_value (id,name) values (?,?)";
    private final static String UPDATE_SQL = "update sequence_value set id = ?  where name = ? and id = ?";
    /**
     * 外面是同步的
     */
    private Map<String,Step> stepMap = new HashMap<String, Step>();

    private boolean getNextBlock(String sequenceName, Step step) {
        Long value = getPersistenceValue(sequenceName);
        if (value == null) {
            //如果没有，就初始化
            try {
                //初始化。返回初始化的值
                value = newPersistenceValue(sequenceName);
            } catch (Exception e) {
                LOG.error("newPersistenceValue error!", e);

                //如果初始化失败，说明有程序先初始化了
                value = getPersistenceValue(sequenceName);
            }
        }
        boolean b = saveValue(value,sequenceName) == 1;
        if (b) {
            step.setCurrentValue(value);
            step.setEndValue(value+blockSize*stepSize);
        }
        return b;
    }

    /**
     * 只有一个入口，所以。只需要他同步即可
     *
     * @return
     */
    public synchronized long get(String sequenceName) {
        Step step = stepMap.get(sequenceName);
        if(step ==null) {
            //如果没有，则要从数据库中读入
            step = new Step(startValue,startValue+blockSize*stepSize,stepSize);
            stepMap.put(sequenceName, step);
        } else {
            if (step.currentValue < step.endValue) {
                //默认为0和0，所以没有错

                //返回下一个可用的值
                return step.incrementAndGet();
            }
        }
        for (int i = 0; i < Math.min(blockSize,MAX_RETRY_TIMES); i++) {
            //最多重试次数:    (blockSize,MAX_RETRY_TIMES)的小值,如果有的blockSize=2000,重试2000次不合理

            if (getNextBlock(sequenceName,step)) {
                return step.incrementAndGet();
            }
        }
        throw new RuntimeException("No more value.");
    }

    private int saveValue(long value, String sequenceName) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(UPDATE_SQL);
            statement.setLong(1, value + blockSize*stepSize);
            statement.setString(2, sequenceName);
            statement.setLong(3, value);
            return statement.executeUpdate();
        } catch (Exception e) {
            LOG.error("newPersistenceValue error!", e);
            throw new RuntimeException("newPersistenceValue error!", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOG.error("close statement error!", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error("close connection error!", e);
                }
            }
        }
    }

    private Long getPersistenceValue(String sequenceName) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(GET_SQL);
            statement.setString(1, sequenceName);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
        } catch (Exception e) {
            LOG.error("getPersistenceValue error!", e);
            throw new RuntimeException("getPersistenceValue error!", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    LOG.error("close resultset error!", e);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOG.error("close statement error!", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error("close connection error!", e);
                }
            }
        }
        return null;
    }

    private Long newPersistenceValue(String sequenceName) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(NEW_SQL);
            statement.setLong(1, startValue);
            statement.setString(2, sequenceName);
            statement.executeUpdate();
        } catch (Exception e) {
            LOG.error("newPersistenceValue error!", e);
            throw new RuntimeException("newPersistenceValue error!", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOG.error("close statement error!", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error("close connection error!", e);
                }
            }
        }
        return startValue;
    }



    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    public void setStartValue(long startValue) {
        this.startValue = startValue;
    }

    public void setStepSize(int stepSize) {
        this.stepSize = stepSize;
    }

    static class Step {
        private long currentValue;
        private long endValue;
        private int stepSize;

        Step(long currentValue, long endValue,int stepSize) {
            this.currentValue = currentValue;
            this.endValue = endValue;
            this.stepSize = stepSize;
        }

        public void setCurrentValue(long currentValue) {
            this.currentValue = currentValue;
        }

        public void setEndValue(long endValue) {
            this.endValue = endValue;
        }

        public void setStepSize(int stepSize) {
            this.stepSize = stepSize;
        }

        public  long incrementAndGet() {
            currentValue += stepSize;

            //为了兼容原来的sequence, 返加下一个值
            //return (currentValue-stepSize);
            return currentValue;
        }
    }
}
