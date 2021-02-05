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
    private int quantity = MAX_QUANTITY;
    private Integer salesProceeds;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    private void tryBuy(int handedMoney) {
        // チケットが売り切れていたらエラーを投げる
        if (quantity <= 0) { // 10
            throw new TicketSoldOutException("Sold out");
        }
        // お金が不足していたらエラーを投げる
        if (handedMoney < ONE_DAY_PRICE) { // 引数 < 7400
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
    }

    private void setSalesProceeds(int money) {
        // 存在チェック
        if (salesProceeds != null) {
            // 売り上げ加算
            salesProceeds += money;
        } else {
            // 売り上げが存在しないため代入
            salesProceeds = money;
        }
    }

    public void buyOneDayPassport(int handedMoney) {
        // 購入可能か
        tryBuy(handedMoney);

        // チケットの枚数減算
        --quantity;

        // 売り上げ加算
        setSalesProceeds(ONE_DAY_PRICE);
    }

    public Integer buyTwoDayPassport(int handedMoney) {
        // 購入可能か
        tryBuy(handedMoney);

        // チケットの枚数減算
        quantity -= 2;

        // 売り上げ加算
        setSalesProceeds(TWO_DAY_PRICE);

        // おつりを返す
        return handedMoney - getSalesProceeds();
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
