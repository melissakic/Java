package com.example.test.loto.entity.dto;

import com.example.test.loto.entity.model.PrizeModel;
import jakarta.ejb.Singleton;

import java.io.Serializable;

@Singleton
public class PreviousPrizeModel implements Serializable {
    private PrizeModel previousPrize;

    public PrizeModel getPreviousPrize() {
        return previousPrize;
    }

    public void setPreviousPrize(PrizeModel previousPrize) {
        this.previousPrize = previousPrize;
    }
}
