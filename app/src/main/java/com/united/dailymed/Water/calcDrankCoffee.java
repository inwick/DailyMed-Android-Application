package com.united.dailymed.Water;

public class calcDrankCoffee {

    public Double calcDrank(Double drankAlready,Double amountNow){
        return (drankAlready+(amountNow));
    }
    /* CALCULATING THE REMAINING AMOUNT **/
    public Double calRemaining(Double remain, Double amt) {
        return(remain - (amt));
    }

}
