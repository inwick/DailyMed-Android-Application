package com.united.dailymed;

public class calcDrankCoffee {

    public Double calcDrank(Double drankAlready,Double amountNow){
        return (drankAlready+(amountNow));
    }
    /* CALCULATING THE REMAINING AMOUNT **/
    public Double calRemaining(Double remain, Double amt) {
        return(remain - (amt));
    }

}
