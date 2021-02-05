/*
 * Copyright 2019-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author jflute
 * @author zak
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    private static final int TWO_DAY_PRICE = 13200;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int quantity = MAX_QUANTITY; // one,twoでチケット共有
    private Integer salesProceeds;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    public Ticket buyOneDayPassport(int handedMoney) {
        tryBuy(handedMoney, ONE_DAY_PRICE); // 購入可能か
        --quantity; // チケットの枚数減算
        calculateSalesProceeds(ONE_DAY_PRICE); // 売り上げ加算

        return new Ticket(ONE_DAY_PRICE);
    }

    public TicketBuyResult buyTwoDayPassport(int handedMoney) {
        tryBuy(handedMoney, TWO_DAY_PRICE); // 購入可能か
        quantity -= 2; // チケットの枚数減算
        calculateSalesProceeds(TWO_DAY_PRICE); // 売り上げ加算

        // return handedMoney - getSalesProceeds(); // おつりを返す
        return new TicketBuyResult(handedMoney, TWO_DAY_PRICE);
    }

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    private void tryBuy(int handedMoney, int price) {
        // チケットが売り切れていたらエラーを投げる
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        // お金が不足していたらエラーを投げる
        if (handedMoney < price) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
    }

    private void calculateSalesProceeds(int money) {
        if (salesProceeds != null) {
            salesProceeds += money; // 売り上げ加算
        } else {
            salesProceeds = money; // 売り上げが存在しないため代入
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getQuantity() {
        return quantity;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
