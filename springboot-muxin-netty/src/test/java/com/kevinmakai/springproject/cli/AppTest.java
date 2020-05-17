package com.kevinmakai.springproject.cli;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
        String n = "";
        int a=0,b=0,c=0;
        //可以被27整除的三位数
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                for(int k=0;k<10;k++){
                    n = i+""+j+""+k;
                    System.out.println(n);
                }
            }

            if(getYu(Integer.parseInt(n),27)!=null){
                System.out.println(n);
            }
        }
    }

    public Integer getYu(int chushu,int beichushu){
        if(Double.valueOf(chushu/beichushu).doubleValue()==0){
            System.out.println("11111111");
            return chushu;
        }
        return null;
    }

    @Test
    public void testSout(){
        System.out.println(123);
    }
}
