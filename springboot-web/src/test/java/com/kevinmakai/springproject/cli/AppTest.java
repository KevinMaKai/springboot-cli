package com.kevinmakai.springproject.cli;

import static org.junit.Assert.assertTrue;

import com.kevinmakai.springproject.cli.dao.domain.Users;
import com.kevinmakai.springproject.cli.mapper.UsersMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootBaseCliStartApplication.class)
public class AppTest 
{
    @Resource
    private UsersMapper usersMapper;
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    TransactionDefinition transactionDefinition;

    @Autowired
    TransactionTemplate transactionTemplate;

//    @Transactional
    @Test
    public void testDBTranscation1(){
//        Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
//        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
//        Users users = new Users();
//        users.setCid("testcid");
//        users.setFaceImage("faceImage");
//        users.setFaceImageBig("faceBigImage");
//        users.setId("1234444");
//        users.setNickname("test");
//        users.setPassword("password");
//        users.setQrcode("code");
//        users.setUsername("4444");
//        int s = usersMapper.insert(users);
//        int count = usersMapper.selectCountByExample(null);
//        System.out.println(count);
//        if(count>3){
//            System.out.println("rollback");
//            dataSourceTransactionManager.rollback(transactionStatus);//最好是放在catch 里面,防止程序异常而事务一直卡在哪里未提交
//        } else{
//            System.out.println("commit");
//            dataSourceTransactionManager.commit(transactionStatus);//提交
//        }
//        dataSourceTransactionManager.commit(transactionStatus);//提交

//        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

        boolean result = transactionTemplate.execute((TransactionStatus transactionStatus)-> {
                Users users = new Users();
                users.setCid("testcid");
                users.setFaceImage("faceImage");
                users.setFaceImageBig("faceBigImage");
                users.setId("12344441");
                users.setNickname("test");
                users.setPassword("password");
                users.setQrcode("code");
                users.setUsername("44441");
                int s = usersMapper.insert(users);
                int count = usersMapper.selectCountByExample(null);
                System.out.println(count);
                if(count>5){
                    transactionStatus.setRollbackOnly();
                }
                return true;
        });
        System.out.println(result);
    }


    @Test
    public void testDBTranscation(){

//        Users users = new Users();
//        users.setCid("testcid");
//        users.setFaceImage("faceImage");
//        users.setFaceImageBig("faceBigImage");
//        users.setId("123");
//        users.setNikename("test");
//        users.setPassword("password");
//        users.setQrcode("code");
//        usersMapper.insert(users);
        int a=0,b=0,c=0;
        //可以被27整除的三位数
        List<Integer> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        List<Integer> list4 = new ArrayList<>();
        int[] three = new int[1000];
        for(int i=1;i<10;i++){
            for(int j=0;j<10;j++){
                for(int k=0;k<10;k++){
                    int n= Integer.parseInt(i+""+j+""+k);
                    if(getYu(n,27)!=null){
                        list.add(n);

                    }
                    if(getYu(n,11)!=null){
                        list1.add(n);
                    }
                    if(i+j+k==15){
                        list2.add(n);
                    }
                    Double tmp = MathSqure(n,3);
                    BigDecimal bd = new BigDecimal(tmp);
                    BigDecimal  bd2 = bd.setScale(1,BigDecimal  .ROUND_HALF_UP);
                    Double tmp1 = Double.parseDouble(bd2.toString());

                    if(  tmp1.equals(new Double(tmp.intValue()))){
                        list3.add(n);
                    }
                    if(getYu(64800,n)!=null){
                        list4.add(n);
                    }
                }
            }


        }

        int[] array = {list.size(),list1.size(),list2.size(),list3.size(),list4.size()};
        Arrays.sort(array);
        List[] lists = {list,list1,list2,list3,list4};
        List<Integer> maxList = null;
        for (int l=0;l<lists.length;l++){
            if(lists[l].size() == array[4]){
                maxList=lists[l];
                break;
            }
        }
        for(int m=0;m<maxList.size();m++){
            int u =0;
            for (int l=0;l<lists.length;l++){

                if(lists[l].contains(maxList.get(m))){
                     u++;
                 }

            }
            if(u>2){
                System.out.println(maxList.get(m));
            }
        }

    }

    public Integer getYu(int chushu,int beichushu){
        if(Double.valueOf(chushu%beichushu).doubleValue()==0){
            return chushu;
        }
        return null;
    }

    /**
     *
     * @param n  需要开根号的数据
     * @param m  需要保留的精度,即几位小数
     * @return
     */
    public static double MathSqure(int n, int m){
        double[] arr = new double[m];
        if(m >0){
            arr = sc(m);
        }
        int s = sq(n);

        return sb(n, s, arr);
    }

    /**
     * 计算要保留几位小数
     * @param m
     * @return
     */
    public static double[] sc(int m){
        double[] arr = new double[m];
        int num = 0;
        while(num != m){
            double f = 1;
            for(int i=0;i<=num;i++){
                f = f*10;
            }
            arr[num] = 1/f;
            num++;
        }
        return arr;
    }

    /**
     * 计算整数位
     * @param n
     * @return
     */
    public static int sq(int n){
        if( n == 1){
            return 1;
        }
        int tmp = 0;
        for(int i=1;i<=n/2+1;i++){
            if(i*i == n){
                tmp = i;
                break;
            }
            if(i*i > n){
                tmp = i-1;
                break;
            }
        }
        return tmp;
    }

    /**
     * 开根号
     * @param n
     * @param j
     * @param arr
     * @return
     */
    public static double sb(int n, double j, double[] arr){
        double tmp = j;
        for(int p=0;p<arr.length;p++){
            if(p>0){
                j = tmp;//计算过后的值（整数位+小数位的和，赋值给j，下面继续运算）
            }
            for(int i=1;i<=9;i++){//小数位只有九位{0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9}
                tmp = i*arr[p]+j;//i*arr[p],相当于每次加0.1,0.2 ...
                if(tmp*tmp == n){
                    return tmp;
                }
                if(tmp*tmp >n){
                    //避免丢失精度
                    BigDecimal c1 = new BigDecimal(Double.toString(tmp));
                    BigDecimal c2 = new BigDecimal(Double.toString(arr[p]));
                    tmp = c1.subtract(c2).doubleValue();
                    break;
                }
            }
        }
        return tmp;
    }


}
