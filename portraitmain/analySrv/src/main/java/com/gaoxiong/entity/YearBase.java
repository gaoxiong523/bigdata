package com.gaoxiong.entity;

/**
 * @author gaoxiong
 * @ClassName YearBase
 * @Description TODO
 * @date 2019/6/3 0003 下午 11:09
 */
public class YearBase {
    /**
     * 年代类型
     */
    private String yearType;
    /**
     * 数量
     */
    private Long count;

    public String getYearType () {
        return yearType;
    }

    public void setYearType ( String yearType ) {
        this.yearType = yearType;
    }

    public Long getCount () {
        return count;
    }

    public void setCount ( Long count ) {
        this.count = count;
    }
}
